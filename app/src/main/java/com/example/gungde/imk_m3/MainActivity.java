package com.example.gungde.imk_m3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> mlistArray = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.list);

        mlistArray.add("Anoa");
        mlistArray.add("Babi");
        mlistArray.add("Cacing");
        mlistArray.add("Domba");
        mlistArray.add("Elang");

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mlistArray);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Anda memilih "+ parent.getItemAtPosition(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
