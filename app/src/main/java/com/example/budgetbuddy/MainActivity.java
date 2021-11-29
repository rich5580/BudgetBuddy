package com.example.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login, register;
    EditText email, password;
    public String mail, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        email = (EditText) findViewById(R.id.et_Email);
        password = (EditText) findViewById(R.id.et_Password);

        login = (Button) findViewById(R.id.btn_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String retrieve_email = "";
                String retrieve_pw = "";
                mail = String.valueOf(email.getText());
                pw = String.valueOf(password.getText());
                Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);

                Cursor c = db.rawQuery("Select email,pswd from users where email= ?", new String[]{mail});
                c.moveToFirst();
                if (c.getCount() == 0) {
                    Toast toast = Toast.makeText(MainActivity.this, "Email and/or password does not exist.", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    retrieve_email = c.getString(0);
                    retrieve_pw = c.getString(1);
                }

                if (mail.equals("") || pw.equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this, "Please enter email and/or password before you can login.", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if (retrieve_email.equals(mail) && retrieve_pw.equals(pw)) {
                    startActivity(intent);
                }
                else {
                    Toast toast = Toast.makeText(MainActivity.this, "Email and/or password does not exist.", Toast.LENGTH_LONG);
                    toast.show();
                }
                c.close();

            }
        });

        register = (Button) findViewById(R.id.btn_Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}