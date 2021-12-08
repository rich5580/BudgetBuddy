package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HomeScreenActivity extends AppCompatActivity {

    TextView welcomeMessage;
    TextView welcome_date;
    TextView current_spending;
    String retrieved_email;
    int active_userid;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        welcomeMessage = (TextView) findViewById(R.id.tv_hs_welcome);
        welcome_date = (TextView) findViewById(R.id.tv_hs_date);

        welcomeMessage.setText("Welcome " + helper.ACTIVE_FIRST_NAME);

        String date = String.valueOf(android.text.format.DateFormat.format("dd-MM-yyyy", new java.util.Date()));
        welcome_date.setText("Today's Date is: " + date);

        //Including display of current user Income and a field to change it could be cool but not necessary
        //Fragment wants, needs to become spending, displaying users yearly, monthly, weekly, and daily spending (UserSpending Function)
        //Fragment needs, needs to become income minus spending aka budget showing yearly, monthly, weekly, and daily budget (TotalBudget)
        //Add fragments. Make changes to the fragments @ userdata_needs, userdata_wants.
        Fragment userdata_wants = new userdata_wants();
        Fragment userdata_needs = new userdata_needs();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.flFragment, userdata_needs);
        ft.commit();

        Button needs_btn = (Button) findViewById(R.id.btn_needs);
        Button wants_btn = (Button) findViewById(R.id.btn_wants);
        current_spending = (TextView) findViewById(R.id.current_spending);

        Double keyed_purchases = helper.KeyedPurchasesTotal();
        current_spending.setText("$ "+ keyed_purchases.toString());


        needs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flFragment, userdata_needs);
                ft.commit();
            }
        });
        wants_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flFragment, userdata_wants);
                ft.commit();
            }
        });


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
                Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(HomeScreenActivity.this, "You have logged out.", Toast.LENGTH_LONG);
                toast.show();
                return true;
            case R.id.details:
                Intent intent2 = new Intent(HomeScreenActivity.this, DetailsPageActivity.class);
                intent2.putExtra("active_user",active_userid);
                startActivity(intent2);
                return true;
            case R.id.enterData:
                Intent intent3 = new Intent(HomeScreenActivity.this, EnterDataActivity.class);
                intent3.putExtra("active_user",active_userid);
                startActivity(intent3);
                return true;
            case R.id.breakdown:
                Intent intent4 = new Intent(HomeScreenActivity.this, BreakdownPageActivity.class);
                intent4.putExtra("active_user",active_userid);
                startActivity(intent4);
                return true;
            case R.id.settingsPage:
                Intent intent5 = new Intent(HomeScreenActivity.this, SettingPageActivity.class);
                intent5.putExtra("active_user",active_userid);
                startActivity(intent5);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}