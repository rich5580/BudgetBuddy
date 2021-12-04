package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HomeScreenActivity extends AppCompatActivity {

    TextView welcomeMessage;
    TextView welcome_date;
    String retrieved_email;
    int active_userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            retrieved_email = extras.getString("user_email");
        }
        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        welcomeMessage = (TextView) findViewById(R.id.tv_hs_welcome);
        welcome_date = (TextView) findViewById(R.id.tv_hs_date);


        Cursor c = db.rawQuery("Select first_name from users where email= ?", new String[]{retrieved_email});
        c.moveToFirst();
        welcomeMessage.setText("Welcome " + c.getString(0));

        String date = String.valueOf(android.text.format.DateFormat.format("dd-MM-yyyy", new java.util.Date()));
        welcome_date.setText("Today's Date is: " + date);

        c = db.rawQuery("Select id from users where email= ?", new String[]{retrieved_email});
        c.moveToFirst();
        active_userid = c.getInt(0);


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
                startActivity(intent2);
                return true;
            case R.id.enterData:
                Intent intent3 = new Intent(HomeScreenActivity.this, EnterDataActivity.class);
                intent3.putExtra("active_user",active_userid);
                startActivity(intent3);
                return true;
            case R.id.breakdown:
                Intent intent4 = new Intent(HomeScreenActivity.this, BreakdownPageActivity.class);
                startActivity(intent4);
                return true;
            case R.id.settingsPage:
                Intent intent5 = new Intent(HomeScreenActivity.this, SettingPageActivity.class);
                startActivity(intent5);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}