package com.example.myapplication382;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class pastDetections extends AppCompatActivity {
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_detections);

        listView=findViewById(R.id.history);
        textView=findViewById(R.id.textView7);

        Intent intent=getIntent();

        ArrayList<String> arrayList=intent.getStringArrayListExtra(detectbluetooth.MSG);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        int n=arrayList.size();
        String s= Integer.toString(n);

        textView.setText(s);
    }
}