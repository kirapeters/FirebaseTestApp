package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.DataRemovalRequest;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.firebase.ui.database.SnapshotParser;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public static class EntryHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;

        public EntryHolder(View v) {
            super(v);
            name = (TextView) itemView.findViewById(R.id.viewName);
            description = (TextView) itemView.findViewById(R.id.viewDescription);
        }

    }

    private final String TAG = "ListActivity";

    List<SampleData> sampleDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // create a recycler view and set its layout manager
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // root database reference
        DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference();

        // creating a parser to parse the data as SampleData
        SnapshotParser<SampleData> parser = new SnapshotParser<SampleData>() {
            @Override
            public SampleData parseSnapshot(DataSnapshot dataSnapshot) {
                SampleData sampleData = dataSnapshot.getValue(SampleData.class);
                if (sampleData != null) {
                    sampleData.setId(dataSnapshot.getKey());
                }
                return sampleData;
            }
        };

        // getting a  reference to where the data is saved
        DatabaseReference messagesRef = rootDatabaseReference.child("firebasetest");

        // options for the firebase recycler????
        FirebaseRecyclerOptions<SampleData> options =
                new FirebaseRecyclerOptions.Builder<SampleData>()
                        // Query: reference to where the data is in the database, parser for how to pull in data
                        .setQuery(messagesRef, parser)
                        .build();

        Log.d(TAG, "Creating firebaseRecyclerAdapter");
        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SampleData, EntryHolder>(options) {
            @NonNull
            @Override
            public EntryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                Log.d(TAG, "adapter onCreatViewHolder");
                // need inflator to allow the holder to grow with new entries
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                // inflate with the layout for each entry
                return new EntryHolder(inflater.inflate(R.layout.sample_data_item, viewGroup, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull EntryHolder viewHolder,
                                            int position,
                                            @NonNull SampleData sampleData) {
                Log.d(TAG, "adapter onBindViewHolder");
                viewHolder.name.setText(sampleData.getName());
                viewHolder.description.setText(sampleData.getDescription());

            }

        };

        Log.d(TAG, "setting the adapter");
        recyclerView.setAdapter(firebaseRecyclerAdapter);





        // hard coded temporary list
//        sampleDataList.add(new SampleData("Kira", "Is Awesome"));
//        sampleDataList.add(new SampleData("Simon", "Is Cool"));
//        sampleDataList.add(new SampleData("Dad", "Is a Goober"));
//        sampleDataList.add(new SampleData("Mom", "Is Sweet"));
//        sampleDataList.add(new SampleData("Julia", "Is Amazing"));
//
//        Log.d(TAG, "creating a recyclerViewAdapter");
//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, sampleDataList);
//        Log.d(TAG, "setting the adapter");
//        recyclerView.setAdapter(recyclerViewAdapter);
//        Log.d(TAG, "set the adapter");
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}