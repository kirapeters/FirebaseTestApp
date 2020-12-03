package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private final String TAG = "ListActivity";
    DatabaseFacade database;

    List<SampleData> sampleDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getIntent();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        database = new DatabaseFacade("firebasetest");
//        database.addChildEventWithTextView(recyclerView);

        sampleDataList.add(new SampleData("Kira", "Is Awesome"));
        sampleDataList.add(new SampleData("Simon", "Is Cool"));
        sampleDataList.add(new SampleData("Dad", "Is a Goober"));
        sampleDataList.add(new SampleData("Mom", "Is Sweet"));
        sampleDataList.add(new SampleData("Julia", "Is Amazing"));

        Log.d(TAG, "creating a recyclerViewAdapter");
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, sampleDataList);
        Log.d(TAG, "setting the adapter");
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.d(TAG, "set the adapter");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}