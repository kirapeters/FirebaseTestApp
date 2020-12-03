package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    DatabaseFacade database;
    EditText nameText;
    EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        database = new DatabaseFacade("tempAuthor");

        nameText = findViewById(R.id.text_name);
        descriptionText = findViewById(R.id.text_description);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save_item:
                saveData();
                Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    private void saveData() {
        String name = nameText.getText().toString();
        String description = descriptionText.getText().toString();

        SampleData data = new SampleData(name, description);

        database.addValue(data);
    }

    private void clean() {
        nameText.setText("");
        descriptionText.setText("");

        nameText.requestFocus();
    }

    public void goToListActivity(View view) {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}