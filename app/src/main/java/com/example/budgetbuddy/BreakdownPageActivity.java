package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BreakdownPageActivity extends AppCompatActivity {
    ListView lv_breakdown;
    ArrayAdapter breakdownAdapter;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    TextView tv_iType;
    EditText edt_iAmount;
    Button btn_mod, btn_close;
    UserDatabaseHelper helper = new UserDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakdown_page);
        //Budget breakdown page NEEDS listview displaying monthly spending for each category as an item

        SQLiteDatabase db = helper.getWritableDatabase();

        lv_breakdown = (ListView) findViewById(R.id.lv_breakdown);
        lv_breakdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DataModel clickedInfo = (DataModel) adapterView.getItemAtPosition(i);
                createInfoDialog(clickedInfo);
            }
        });
        showBreakdown(helper);



    }

    public void createInfoDialog(DataModel model) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View infoPopupView = getLayoutInflater().inflate(R.layout.info_popup, null);

        tv_iType = (TextView) infoPopupView.findViewById(R.id.tv_dataPopupType2);
        edt_iAmount = (EditText) infoPopupView.findViewById(R.id.edt_dataAmount);
        btn_close = (Button) infoPopupView.findViewById(R.id.btn_popupClose);
        btn_mod = (Button) infoPopupView.findViewById(R.id.btn_popupMod);

        tv_iType.setText(model.getSpend_type());
        edt_iAmount.setHint(String.valueOf(model.getAmount()));

        dialogBuilder.setView(infoPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btn_mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_iAmount.getText().toString().equals("")) {
                    Toast.makeText(BreakdownPageActivity.this, "Add amount", Toast.LENGTH_LONG).show();
                } else {
                    helper.modData(model.getFieldName(),edt_iAmount.getText().toString());
                    dialog.dismiss();
                    showBreakdown(helper);
                }

            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
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