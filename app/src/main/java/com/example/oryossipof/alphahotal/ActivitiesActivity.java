package com.example.oryossipof.alphahotal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class  ActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        ArrayList<MyActivity> arrayOfUsers = new ArrayList<MyActivity>();
        // Create the adapter to convert the array to views
        ActivityAdapter adapter = new ActivityAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        final ListView listView = (ListView) findViewById(R.id.activityListView);

        // Add item to adapter
        listView.setAdapter(adapter);
    }
}
