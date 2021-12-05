package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingPageActivity extends AppCompatActivity {

    UserDatabaseHelper db;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView tv_youSure;
    private EditText edt_fName, edt_lName, edt_pw;
    private Button btn_cancel, btn_save, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        db = new UserDatabaseHelper(this);
        String fName = db.ACTIVE_FIRST_NAME;
        String lName = db.ACTIVE_LAST_NAME;
        String pw = db.ACTIVE_PASSWORD;

        edt_fName = (EditText) findViewById(R.id.edt_settingsFName);
        edt_lName = (EditText) findViewById(R.id.edt_settingsLName);
        edt_pw = (EditText) findViewById(R.id.edt_settingsPassword);

        edt_fName.setHint("" + fName);
        edt_lName.setHint("" + lName);
        edt_pw.setHint("" + pw);

        btn_save = (Button) findViewById(R.id.btn_settingsSaveInfo);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if the user decides to only edit one text edit then the system will know to keep the other information the same instead of changing it to a blank space
                if (edt_fName.getText().toString().isEmpty()) {
                    edt_fName.setText(edt_fName.getHint());
                }
                if (edt_lName.getText().toString().isEmpty()) {
                    edt_lName.setText(edt_lName.getHint());
                }
                if (edt_pw.getText().toString().isEmpty()) {
                    edt_pw.setText(edt_pw.getHint());
                }
                // If the user decides to change any of their information, then this calls the database and updates the database to the new information that the user entered
                db.updateSettings(edt_fName.getText().toString(), edt_lName.getText().toString(), edt_pw.getText().toString());
                finish();
                startActivity(getIntent());
            }
        });

        btn_delete = (Button) findViewById(R.id.btn_settingsDeleteAcc);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDeleteDialog();
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
                Intent intent = new Intent(SettingPageActivity.this, MainActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(SettingPageActivity.this, "You have logged out.", Toast.LENGTH_LONG);
                toast.show();
                return true;
            case R.id.details:
                finish();
                Intent intent2 = new Intent(SettingPageActivity.this, DetailsPageActivity.class);
                startActivity(intent2);
                return true;
            case R.id.enterData:
                finish();
                Intent intent3 = new Intent(SettingPageActivity.this, EnterDataActivity.class);
                startActivity(intent3);
                return true;
            case R.id.breakdown:
                finish();
                Intent intent4 = new Intent(SettingPageActivity.this, BreakdownPageActivity.class);
                startActivity(intent4);
                return true;
            case R.id.home:
                finish();
                Intent intent5 = new Intent(SettingPageActivity.this, HomeScreenActivity.class);
                startActivity(intent5);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Button btn_yes;
    public void createDeleteDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View deletePopupView = getLayoutInflater().inflate(R.layout.delete_acc_popup, null);


        tv_youSure = (TextView) deletePopupView.findViewById(R.id.tv_deleteText);
        btn_yes = (Button) deletePopupView.findViewById(R.id.btn_deleteYes);
        btn_cancel = (Button) deletePopupView.findViewById(R.id.btn_deleteCancel);

        dialogBuilder.setView(deletePopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteOne();
                Intent intent = new Intent(SettingPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}