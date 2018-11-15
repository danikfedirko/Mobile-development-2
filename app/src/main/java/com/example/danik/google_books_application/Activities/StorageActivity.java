package com.example.danik.google_books_application.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.danik.google_books_application.Constants;
import com.example.danik.google_books_application.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

import static com.example.danik.google_books_application.Constants.preferenceRecord;

public class StorageActivity extends AppCompatActivity {

    @BindView(R.id.storageListView)
    ListView storageListView;

    SharedPreferences preferences;
    Set<String> recordsSet = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        preferences = getSharedPreferences(Constants.preference, Context.MODE_PRIVATE);

        recordsSet = preferences.getStringSet(preferenceRecord, null);
        List<String> recordsList = new ArrayList<>(recordsSet);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, recordsList);

        storageListView.setAdapter(arrayAdapter);

    }
}
