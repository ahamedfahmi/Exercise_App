package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    DBHelper dbHelper;
    ArrayList<String> id,title , color;

    CustomAdapter customAdapter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front);

        recyclerView = findViewById(R.id.recycleView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Create_Plan.class);
                startActivity(intent);
            }
        });

        textView = findViewById(R.id.textView);

        dbHelper = new DBHelper(MainActivity.this);
        id = new ArrayList<>();
        title = new ArrayList<>();
        color = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(MainActivity.this , id , title ,color);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    void storeDataInArray(){
        Cursor cursor = dbHelper.readData();
        if(cursor.getCount() == 0){
            textView.setText("No Plans Were Found");
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                color.add(cursor.getString(2));
            }
        }
    }
}