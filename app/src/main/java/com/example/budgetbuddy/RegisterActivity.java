package com.example.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    Button next, cancel;
    EditText fName, lName, email, password;
    public String first, last, mail, pw;
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spin = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lifeStyles));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);

        next = (Button) findViewById(R.id.btn_Reg_Next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                first = String.valueOf(fName.getText());
//                last = String.valueOf(lName.getText());
//                mail = String.valueOf(email.getText());
//                pw = String.valueOf(password.getText());

                if (spin.getSelectedItem().toString().equals("Adult")) {
                    Intent intent = new Intent(RegisterActivity.this, AdultStyleActivity.class);
                    startActivity(intent);
                }
                else if (spin.getSelectedItem().toString().equals("Student")){
                    Intent intent = new Intent(RegisterActivity.this, StudentStyleActivity.class);
                    startActivity(intent);
                }
                else if (spin.getSelectedItem().toString().equals("Elderly")){
                    Intent intent = new Intent(RegisterActivity.this, ElderlyStyleActivity.class);
                    startActivity(intent);
                }
                else if (spin.getSelectedItem().toString().equals("Business")) {
                    Intent intent = new Intent(RegisterActivity.this, BusinessStyleActivity.class);
                    startActivity(intent);
                }



            }
        });

        cancel = (Button) findViewById(R.id.btn_Reg_Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}