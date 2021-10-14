package com.example.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button login, register;
    EditText email, password;
    public String mail, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.et_Email);
        password = (EditText) findViewById(R.id.et_Password);

        login = (Button) findViewById(R.id.btn_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = String.valueOf(email.getText());
                pw = String.valueOf(password.getText());

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