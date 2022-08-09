package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Create_Plan extends AppCompatActivity {

    String[] items =  {"red" , "blue" , "green", "white"};

    EditText title , color;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);

        AutoCompleteTextView act1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        EditText editText = (EditText)findViewById(R.id.editTextTextPersonName2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Create_Plan.this, android.R.layout.simple_spinner_dropdown_item,items);

        act1.setAdapter(adapter);

        act1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(adapterView.getItemAtPosition(i).toString());
                Toast.makeText(getApplicationContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
            }
        });

        title = findViewById(R.id.editTextTextPersonName);
        color = findViewById(R.id.editTextTextPersonName2);
        addButton = findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(Create_Plan.this);
                dbHelper.addPlan(title.getText().toString().trim(),color.getText().toString().trim());
                OpenActivity();
            }
        });

    }

    public void OpenActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}