package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class EnterDataActivity extends AppCompatActivity {
    int active_user;
    Boolean data_added = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data);

        EditText amount = (EditText) findViewById(R.id.spending_data_amount);
        EditText type = (EditText) findViewById(R.id.spending_data_type);
        Switch override = (Switch) findViewById(R.id.override_button);
        Button add = (Button) findViewById(R.id.add_button);

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        active_user = helper.ACTIVE_USERID;

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            active_user = extras.getInt("active_user");
//        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("amount", Integer.parseInt(amount.getText().toString()));
                values.put("spend_type", type.getText().toString());
                values.put("affects_category",!override.isChecked());
                values.put("user", active_user);

                db.insert("user_data","NullPlaceholder", values);
                data_added = true;

                finish();

            } });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.directorymain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(EnterDataActivity.this, MainActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(EnterDataActivity.this, "You have logged out.", Toast.LENGTH_LONG);
                toast.show();
                return true;
            case R.id.home:
//                Intent intent2 = new Intent(EnterDataActivity.this, HomeScreenActivity.class);
//                startActivity(intent2);
                finish();
                return true;
            case R.id.enterData:
                Intent intent3 = new Intent(EnterDataActivity.this, EnterDataActivity.class);
                startActivity(intent3);
                return true;
            case R.id.breakdown:
                Intent intent4 = new Intent(EnterDataActivity.this, BreakdownPageActivity.class);
                startActivity(intent4);
                return true;
            case R.id.settingsPage:
                Intent intent5 = new Intent(EnterDataActivity.this, SettingPageActivity.class);
                startActivity(intent5);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onDestroy() {
        super.onDestroy();

        if (isFinishing() && data_added){
            Toast.makeText(this,"Data added", Toast.LENGTH_LONG).show();
        }
    }
}