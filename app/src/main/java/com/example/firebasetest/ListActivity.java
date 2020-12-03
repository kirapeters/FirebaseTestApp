package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

/**
 * BrowseActivity is the screen on the app where the user will be able to view a list of previous
 * entries that they have created. Will be displayed with a ListView
 */
public class ListActivity extends AppCompatActivity {

    private String TAG = "ListActivity";

    private UserData userData;
    private List<SampleData> listOfEntries;

    ListView listView;
//    String mTitle[] = {"Blessing of the Day 1", "Blessing of the Day 2", "Blessing of the Day 3", "Blessing of the Day 4"};
//    String mDescription[] = {"Blessing Entry 1", "Blessing Entry 2", "Blessing Entry 3", "Blessing Entry 4",};

    /**
     * Where the magic of getting all the data from the list of entries and displaying them
     * back to the user will happen.
     * @param savedInstanceState stuff
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Log.d(TAG, "Inside ListActivity");

        listView = findViewById(R.id.list_of_data);
//        userData = new UserData("tempAuthor"); // todo: implement someway to get and save author/username or something
//        listOfEntries = userData.getEntries();
//
//        if (listOfEntries != null) {
            Log.d(TAG, "Going to display a list of entries");
            Query query = FirebaseDatabase.getInstance().getReference().child("tempAuthor").child("entries");
            Log.d(TAG, query.toString());
            query.keepSynced(true);
            FirebaseListOptions<SampleData> options = new FirebaseListOptions.Builder<SampleData>()
                    .setQuery(query, SampleData.class)
                    .setLayout(R.layout.activity_list_item)
                    .build();
            ListAdapter adapter = new FirebaseListAdapter<SampleData>(options) {
                @Override
                protected void populateView(View view, SampleData sampleData, int position)
                {
                    Log.d(TAG, "populating view");
                    ((TextView)view.findViewById(R.id.textview1)).setText(sampleData.getName());
                    ((TextView)view.findViewById(R.id.textview2)).setText(sampleData.getDescription());
                }
            };
            listView.setAdapter(adapter);

//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString("title", listOfEntries.get(position).getName());
//                    bundle.putString("description", listOfEntries.get(position).getDescription());
//                    intent.putExtras(bundle);
//
//                    startActivity(intent);
//                }
//            });
//        }
    }
//    class MyAdapter extends ArrayAdapter<SampleData> {
//
//        Context context;
//        List<SampleData> listOfEntries;
//
//        MyAdapter(Context c, List<SampleData> listOfEntries) {
//            super(c, R.layout.activity_list_item, R.id.textview1, listOfEntries);
//            this.context = c;
//            this.listOfEntries = listOfEntries;
//
//            Log.d(TAG, "listOfEntries: " + listOfEntries);
//            Log.d(TAG, "created an adapter");
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row = layoutInflater.inflate(R.layout.activity_list_item, parent, false);
//
//            TextView myTitle = row.findViewById(R.id.textview1);
//            TextView myDescription = row.findViewById(R.id.textview2);
//
//            // set resources on views
//            Log.d(TAG, "setting resources on views");
//            myTitle.setText(listOfEntries.get(position).getName());
//            myDescription.setText(listOfEntries.get(position).getDescription());
//
//            return row;
//        }
//    }
}