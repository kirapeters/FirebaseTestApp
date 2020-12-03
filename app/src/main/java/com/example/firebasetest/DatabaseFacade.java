package com.example.firebasetest;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a simpler interface to interact with the firebase database for the
 * purposes of this app. With this class, you can save new objects to the database and
 * get the list of objects that you saved to the database.
 */
public class DatabaseFacade {
    private final String TAG = "DatabaseFacade";
    private String referenceName;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;

    // for entry data
    private List<SampleData> sampleDataList;

    DatabaseFacade(String name) {
        this.referenceName = name;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child(referenceName).child("entries");
        sampleDataList = new ArrayList<>();

        Log.d(TAG, "created a database");
    }

    /**
     * Add a value to the database
     * @param data the object to be saved
     */
    public void addValue(Object data) {
        databaseReference.push().setValue(data);
    }

    public void addChildEvent() {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                SampleData data = dataSnapshot.getValue(SampleData.class);
                sampleDataList.add(data);
                Log.d(TAG, "Added " + data + " to the list");
                Log.d(TAG, "name is " + data.getName());
                Log.d(TAG, "description is " + data.getDescription());
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

        databaseReference.addChildEventListener(childEventListener);
    }

    public List<SampleData> getListOfEntries() {
//        addChildEvent();
        Log.d(TAG, "returning a list of entries: " + sampleDataList);
        return sampleDataList;
    }
}
