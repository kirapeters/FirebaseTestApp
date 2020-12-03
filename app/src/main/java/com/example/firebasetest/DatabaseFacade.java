package com.example.firebasetest;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DatabaseFacade {
    private String referenceName;
    private DatabaseReference rootDatabaseReference;
    private DatabaseReference dataReference;
    private ChildEventListener childEventListener;

    DatabaseFacade(String name) {
        this.referenceName = name;
        rootDatabaseReference = FirebaseDatabase.getInstance().getReference();
        dataReference = FirebaseDatabase.getInstance().getReference().child(referenceName);
    }

    public void addValue(Object data) {
        dataReference.push().setValue(data);
    }

    public void addChildEventWithTextView(TextView textView) {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                SampleData data = dataSnapshot.getValue(SampleData.class);
                String txt = textView.getText() + "\n" + data.getName() + ": " + data.getDescription();
                textView.setText(txt);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        dataReference.addChildEventListener(childEventListener);
    }
}
