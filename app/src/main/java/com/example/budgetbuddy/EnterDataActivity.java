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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EnterDataActivity extends AppCompatActivity {
    int active_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data);

        EditText amount = (EditText) findViewById(R.id.spending_data_amount);
        EditText desc = (EditText) findViewById(R.id.edt_spending_info);

        Button add = (Button) findViewById(R.id.add_button);

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if (helper.ACTIVE_TYPE.equals("Student")) {
            Spinner spn_student = (Spinner) findViewById(R.id.spn_spendingData);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spendingTypeStudent,
                    android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_student.setAdapter(adapter);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (amount.getText().toString().equals("")) {
                        Toast.makeText(EnterDataActivity.this, "Please enter a value.", Toast.LENGTH_LONG).show();
                    } else {
                        ContentValues values = new ContentValues();
                        values.put("amount", Double.parseDouble(amount.getText().toString()));
                        values.put("spend_type", spn_student.getSelectedItem().toString());
                        values.put("description",desc.getText().toString());
                        values.put("user", active_user);
                        db.insert("user_data","NullPlaceholder", values);
                        Toast.makeText(EnterDataActivity.this,"Data added", Toast.LENGTH_LONG).show();
                        amount.setText("");
                        desc.setText("");
                    }


                } });
        } else if (helper.ACTIVE_TYPE.equals("Adult")) {
            Spinner spn_adult = (Spinner) findViewById(R.id.spn_spendingData);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spendingTypeAdult,
                    android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_adult.setAdapter(adapter);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (amount.getText().toString().equals("")) {
                        Toast.makeText(EnterDataActivity.this, "Please enter a value.", Toast.LENGTH_LONG).show();
                    } else {
                        ContentValues values = new ContentValues();
                        values.put("amount", Double.parseDouble(amount.getText().toString()));
                        values.put("spend_type", spn_adult.getSelectedItem().toString());
                        values.put("description",desc.getText().toString());
                        values.put("user", active_user);
                        db.insert("user_data","NullPlaceholder", values);
                        Toast.makeText(EnterDataActivity.this,"Data added", Toast.LENGTH_LONG).show();
                        amount.setText("");
                        desc.setText("");
                    }

                } });
        } else if (helper.ACTIVE_TYPE.equals("Elderly")) {
            Spinner spn_elderly = (Spinner) findViewById(R.id.spn_spendingData);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spendingTypeElderly,
                    android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_elderly.setAdapter(adapter);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (amount.getText().toString().equals("")) {
                        Toast.makeText(EnterDataActivity.this, "Please enter a value.", Toast.LENGTH_LONG).show();
                    } else {
                        ContentValues values = new ContentValues();
                        values.put("amount", Double.parseDouble(amount.getText().toString()));
                        values.put("spend_type", spn_elderly.getSelectedItem().toString());
                        values.put("description",desc.getText().toString());
                        values.put("user", active_user);
                        db.insert("user_data","NullPlaceholder", values);
                        Toast.makeText(EnterDataActivity.this,"Data added", Toast.LENGTH_LONG).show();
                        amount.setText("");
                        desc.setText("");
                    }

                } });
        } else if (helper.ACTIVE_TYPE.equals("Business")) {
            Spinner spn_business = (Spinner) findViewById(R.id.spn_spendingData);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spendingTypeBusiness,
                    android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn_business.setAdapter(adapter);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (amount.getText().toString().equals("")) {
                        Toast.makeText(EnterDataActivity.this, "Please enter a value.", Toast.LENGTH_LONG).show();
                    } else {
                        ContentValues values = new ContentValues();
                        values.put("amount", Double.parseDouble(amount.getText().toString()));
                        values.put("spend_type", spn_business.getSelectedItem().toString());
                        values.put("description",desc.getText().toString());
                        values.put("user", active_user);
                        db.insert("user_data","NullPlaceholder", values);
                        Toast.makeText(EnterDataActivity.this,"Data added", Toast.LENGTH_LONG).show();
                        amount.setText("");
                        desc.setText("");
                    }

                } });
        }

        active_user = helper.ACTIVE_USERID;

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
                finish();
                Intent intent2 = new Intent(EnterDataActivity.this, HomeScreenActivity.class);
                startActivity(intent2);
                return true;
            case R.id.details:
                finish();
                Intent intent3 = new Intent(EnterDataActivity.this, DetailsPageActivity.class);
                startActivity(intent3);
                return true;
            case R.id.breakdown:
                finish();
                Intent intent4 = new Intent(EnterDataActivity.this, BreakdownPageActivity.class);
                startActivity(intent4);
                return true;
            case R.id.settingsPage:
                finish();
                Intent intent5 = new Intent(EnterDataActivity.this, SettingPageActivity.class);
                startActivity(intent5);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}