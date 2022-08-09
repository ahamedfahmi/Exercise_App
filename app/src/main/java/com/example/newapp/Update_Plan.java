package com.example.newapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Plan extends AppCompatActivity {

    EditText title , color;
    Button updateBtn , deleteBtn;

    String id, Title ,Color;
    String title2 , color2;

    String[] items =  {"red" , "blue" , "green", "white"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_plan);

        AutoCompleteTextView act1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        EditText editText = (EditText)findViewById(R.id.select_item);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Update_Plan.this, android.R.layout.simple_spinner_dropdown_item,items);

        act1.setAdapter(adapter);

        act1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(adapterView.getItemAtPosition(i).toString());
                Toast.makeText(getApplicationContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });

        title = findViewById(R.id.title);
        color = findViewById(R.id.select_item);
        updateBtn = findViewById(R.id.button2);
        deleteBtn = findViewById(R.id.deleteBtn);

        getIntentData();
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(Update_Plan.this);
                dbHelper.updateData(id , title.getText().toString().trim() , color.getText().toString().trim());
                OpenActivity();
            }
        });


        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle(Title);
        }

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmationDialog();
            }
        });


    }

    void getIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("color")){
            id = getIntent().getStringExtra("id");
            Title = getIntent().getStringExtra("title");
            Color = getIntent().getStringExtra("color");

            title.setText(Title);
            color.setText(Color);

        }else{
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }

    public void OpenActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    void confirmationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + Title + " ?");
        builder.setMessage("Are you sure , You want to delete " + Title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper = new DBHelper(Update_Plan.this);
                dbHelper.deleteData(id);
                OpenActivity();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }




}