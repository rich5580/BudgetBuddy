package com.example.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button next, cancel;
    EditText fName, lName, email, password, repassword;
    public String first, last, mail, pw, repw;
    Spinner spin;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spin = (Spinner) findViewById(R.id.spinner);
        fName = (EditText) findViewById(R.id.et_Reg_FName);
        lName = (EditText) findViewById(R.id.et_Reg_LName);
        email = (EditText) findViewById(R.id.et_Reg_Email);
        password = (EditText) findViewById(R.id.et_Reg_Password);
        repassword = (EditText) findViewById(R.id.et_Reg_PWConfirm);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lifeStyles));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        next = (Button) findViewById(R.id.btn_Reg_Next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    first = String.valueOf(fName.getText());
                    last = String.valueOf(lName.getText());
                    mail = String.valueOf(email.getText());
                    pw = String.valueOf(password.getText());
                    repw = String.valueOf(repassword.getText());

                    if (first.equals("") || last.equals("") || mail.equals("") || pw.equals("") || repw.equals("")) {
                        Toast toast = Toast.makeText(RegisterActivity.this, "Please fill out register form before continuing.", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        ContentValues values = new ContentValues();
                        values.put(UserDatabaseHelper.email, mail);
                        values.put(UserDatabaseHelper.f_name, first);
                        values.put(UserDatabaseHelper.l_name, last);
                        values.put(UserDatabaseHelper.password, pw);
                        db.insert(UserDatabaseHelper.DATABASE_NAME, "NullPlaceholder", values);


                        if (pw.equals(repw)) {
                            if (spin.getSelectedItem().toString().equals("Adult")) {
                                Intent intent = new Intent(RegisterActivity.this, AdultStyleActivity.class);
                                startActivity(intent);
                            }
                            else if (spin.getSelectedItem().toString().equals("Student")) {
                                Intent intent = new Intent(RegisterActivity.this, StudentStyleActivity.class);
                                startActivity(intent);
                            }
                            else if (spin.getSelectedItem().toString().equals("Elderly")) {
                                Intent intent = new Intent(RegisterActivity.this, ElderlyStyleActivity.class);
                                startActivity(intent);
                            }
                            else if (spin.getSelectedItem().toString().equals("Business")) {
                                Intent intent = new Intent(RegisterActivity.this, BusinessStyleActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast toast = Toast.makeText(RegisterActivity.this, "Your passwords do not match.", Toast.LENGTH_LONG);
                            toast.show();
                        }

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