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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DetailsPageActivity extends AppCompatActivity {

    ListView lv_details;
    ArrayAdapter detailsArrayAdapter;
    UserDatabaseHelper helper;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button btn_delete, btn_cancel;
    TextView tv_pAmount, tv_pType, tv_pOccur, tv_pDesc, tv_pAmount2, tv_pType2, tv_pOccur2, tv_pDesc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        lv_details = (ListView) findViewById(R.id.lv_detailsList);

        helper = new UserDatabaseHelper(this);
        ShowDetailsOnListView(helper);

        lv_details.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                DataModel clickedData = (DataModel) adapterView.getItemAtPosition(i);
                createDeleteDialog(clickedData);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.directorymain, menu);
        return true;
    }

    private void ShowDetailsOnListView(UserDatabaseHelper helper2) {
        detailsArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                helper2.getAllInfo());
        lv_details.setAdapter(detailsArrayAdapter);

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
                finish();
                Intent intent2 = new Intent(DetailsPageActivity.this, HomeScreenActivity.class);
                startActivity(intent2);
                return true;
            case R.id.enterData:
                finish();
                Intent intent3 = new Intent(DetailsPageActivity.this, EnterDataActivity.class);
                startActivity(intent3);
                return true;
            case R.id.breakdown:
                finish();
                Intent intent4 = new Intent(DetailsPageActivity.this, BreakdownPageActivity.class);
                startActivity(intent4);
                return true;
            case R.id.settingsPage:
                finish();
                Intent intent5 = new Intent(DetailsPageActivity.this, SettingPageActivity.class);
                startActivity(intent5);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createDeleteDialog(DataModel model) {
        dialogBuilder = new AlertDialog.Builder(this);
        final View dataPopupView = getLayoutInflater().inflate(R.layout.extra_data_popup, null);

        tv_pType = (TextView) dataPopupView.findViewById(R.id.tv_popupType);
        tv_pType2 = (TextView) dataPopupView.findViewById(R.id.tv_popupType2);
        tv_pAmount = (TextView) dataPopupView.findViewById(R.id.tv_popupAmount);
        tv_pAmount2 = (TextView) dataPopupView.findViewById(R.id.tv_popupAmount2);
        tv_pDesc = (TextView) dataPopupView.findViewById(R.id.tv_popupDescription);
        tv_pDesc2 = (TextView) dataPopupView.findViewById(R.id.tv_popupDescription2);
        btn_delete = (Button) dataPopupView.findViewById(R.id.btn_popupDelete);
        btn_cancel = (Button) dataPopupView.findViewById(R.id.btn_popupClose);

        tv_pType2.setText(model.getSpend_type());
        tv_pAmount2.setText(String.valueOf(model.getAmount()));
        tv_pDesc2.setText(model.getDescription());


        dialogBuilder.setView(dataPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.deleteData(model);
                dialog.dismiss();
                ShowDetailsOnListView(helper);
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