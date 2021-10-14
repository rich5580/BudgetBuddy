package com.example.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterContActivity extends AppCompatActivity {

    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cont);

        spin = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterContActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lifeStyles));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);
    }
}