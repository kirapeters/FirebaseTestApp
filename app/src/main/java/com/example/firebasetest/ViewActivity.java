package com.example.firebasetest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * View Activity: Where the user will see a entry in full view
 */
public class ViewActivity extends AppCompatActivity {

    private String TAG = "ViewActivity";

    //  ImageView imageView;
    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Log.d(TAG, "ViewActivity onCreate");

        title = findViewById(R.id.name);
        description = findViewById(R.id.description);

        Bundle bundle = this.getIntent().getExtras();
        String titleStr = bundle.getString("title");
        String descriptionStr = bundle.getString("description");

        title.setText(titleStr);
        description.setText(descriptionStr);
    }
}



    /**
     * Loads in data from the BrowseActivity and gets the screen ready for display.
     * @param savedInstanceState stuff
     */





