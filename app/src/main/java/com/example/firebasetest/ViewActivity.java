package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {
    private final String TAG = "ViewActivity";

    TextView textName;
    TextView textDescription;

    String name = "";
    String description = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Log.d(TAG, "getting the text views");
        textName = findViewById(R.id.viewName);
        textDescription = findViewById(R.id.viewDescription);

        Log.d(TAG, "getting the data from the intent");
        getData();
        Log.d(TAG, "setting the data from the intent");
        setData();
    }

    private void setData() {
        textName.setText(name);
        textDescription.setText(description);
    }

    private void getData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("description")) {
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
        }
        else {
            Toast.makeText(this, "No data.", Toast.LENGTH_LONG).show();
        }
    }
}