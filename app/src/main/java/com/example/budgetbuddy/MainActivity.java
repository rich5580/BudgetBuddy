package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login, register, btn_close;
    EditText email, password;
    public String mail, pw;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    ImageView image;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.icons8_budget_100__1_);
        pb = (ProgressBar) findViewById(R.id.pb_mainScreen);

        email = (EditText) findViewById(R.id.et_Email);
        password = (EditText) findViewById(R.id.et_Password);

        login = (Button) findViewById(R.id.btn_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String retrieved_email = "";
                String retrieved_pw = "";
                mail = String.valueOf(email.getText());
                pw = String.valueOf(password.getText());
                if (mail.equals("") || pw.equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this, "Enter an email/password to continue.", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    Boolean isValid = helper.isValidEmailAndPassword(mail,pw);
                    if (isValid){
                        Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast toast = Toast.makeText(MainActivity.this, "Email and/or password does not exist.", Toast.LENGTH_LONG);
                        toast.show();
                    }

                }
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
                pb.setVisibility(View.INVISIBLE);

            }
        }, 1000);   //5 seconds

        register = (Button) findViewById(R.id.btn_Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                createInfoDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createInfoDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View infoPopupView = getLayoutInflater().inflate(R.layout.about_popup, null);

        btn_close = (Button) infoPopupView.findViewById(R.id.btn_infoClose);

        dialogBuilder.setView(infoPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}