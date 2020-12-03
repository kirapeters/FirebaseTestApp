package com.example.firebasetest;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the data of the user of the app
 *
 * Author stores the name or username of the user. ListOfEntries stores the entries that the user
 * has written.
 */
public class UserData {
    private static final String TAG = "UserData";

    private String author;
    private DatabaseFacade database;

    public UserData(String author) {
        this.author = "tempAuthor";
        Log.d(TAG, "UserData Constructor");

        database = new DatabaseFacade(author);
        database.addChildEvent();
    }

    public String getAuthor() {
        return author;
    }

    public void addData(SampleData data) {
        database.addValue(data);
    }

    public List<SampleData> getEntries() {
        List<SampleData> listOfEntries = database.getListOfEntries();
        return listOfEntries;
    }


    // get existing entries in firebase
}
