package com.oxy.prioritylinearlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {
    ArrayAdapter adapter;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        list.add("item 0");
        list.add("item 1");
        list.add("item 2");
        list.add("item 3");
        list.add("item 4");
        listView.setAdapter(adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                list));
    }

    public void onAddChild(View v){
        list.add("add child "+list.size());
        adapter.notifyDataSetChanged();
    }

    public void onRemoveChild(View v){
        if(list.size() == 0){
            return;
        }
        list.remove(list.size() - 1);
        adapter.notifyDataSetChanged();
    }
}
