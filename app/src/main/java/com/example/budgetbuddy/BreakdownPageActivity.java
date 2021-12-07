package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BreakdownPageActivity extends AppCompatActivity {
    ListView lv_breakdown;
    ArrayAdapter breakdownAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakdown_page);
        //Budget breakdown page NEEDS listview displaying monthly spending for each category as an item

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        lv_breakdown = (ListView) findViewById(R.id.lv_breakdown);
        //TextView monthly_total = (TextView) findViewById(R.id.monthly_total);
        showBreakdown(helper);



    }

    private void showBreakdown(UserDatabaseHelper helper){
        breakdownAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, helper.BudgetBreakdown());
        lv_breakdown.setAdapter(breakdownAdapter);
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
                Intent intent = new Intent(BreakdownPageActivity.this, MainActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(BreakdownPageActivity.this, "You have logged out.", Toast.LENGTH_LONG);
                toast.show();
                return true;
            case R.id.details:
                finish();
                Intent intent2 = new Intent(BreakdownPageActivity.this, DetailsPageActivity.class);
                startActivity(intent2);
                return true;
            case R.id.enterData:
                finish();
                Intent intent3 = new Intent(BreakdownPageActivity.this, EnterDataActivity.class);
                startActivity(intent3);
                return true;
            case R.id.home:
                finish();
                Intent intent4 = new Intent(BreakdownPageActivity.this, HomeScreenActivity.class);
                startActivity(intent4);
                return true;
            case R.id.settingsPage:
                finish();
                Intent intent5 = new Intent(BreakdownPageActivity.this, SettingPageActivity.class);
                startActivity(intent5);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}