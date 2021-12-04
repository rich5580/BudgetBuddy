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

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView tv_youSure;
    private Button btn_cancel, btn_save, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

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
                Intent intent2 = new Intent(SettingPageActivity.this, DetailsPageActivity.class);
                startActivity(intent2);
                return true;
            case R.id.enterData:
                Intent intent3 = new Intent(SettingPageActivity.this, EnterDataActivity.class);
                startActivity(intent3);
                return true;
            case R.id.breakdown:
                Intent intent4 = new Intent(SettingPageActivity.this, BreakdownPageActivity.class);
                startActivity(intent4);
                return true;
            case R.id.home:
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