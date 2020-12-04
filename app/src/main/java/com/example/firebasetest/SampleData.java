package com.example.firebasetest;

import android.util.Log;

public class SampleData {
    private final String TAG = "SampleData";
//    private String id;
    private String name;
    private String description;

    SampleData() {}

    SampleData(String name, String description) {
        this.name = name;
        this.description = description;
        Log.d(TAG, "constructor");
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
