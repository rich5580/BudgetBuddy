package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class DetailsPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
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
                Intent intent = new Intent(DetailsPageActivity.this, MainActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(DetailsPageActivity.this, "You have logged out.", Toast.LENGTH_LONG);
                toast.show();
                return true;
            case R.id.home:
//                Intent intent2 = new Intent(DetailsPageActivity.this, HomeScreenActivity.class);
//                startActivity(intent2);
                finish();
                return true;
            case R.id.enterData:
                Intent intent3 = new Intent(DetailsPageActivity.this, EnterDataActivity.class);
                startActivity(intent3);
                return true;
            case R.id.breakdown:
                Intent intent4 = new Intent(DetailsPageActivity.this, BreakdownPageActivity.class);
                startActivity(intent4);
                return true;
            case R.id.settingsPage:
                Intent intent5 = new Intent(DetailsPageActivity.this, SettingPageActivity.class);
                startActivity(intent5);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}