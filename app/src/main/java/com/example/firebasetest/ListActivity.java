package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {
    DatabaseFacade database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        TextView textView = findViewById(R.id.tvData);
        database = new DatabaseFacade("firebasetest");
        database.addChildEventWithTextView(textView);
    }
}