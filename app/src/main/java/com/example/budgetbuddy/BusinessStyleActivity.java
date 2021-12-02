package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class BusinessStyleActivity extends AppCompatActivity {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch sw_Business_Vehicle;
    EditText edt_Business_Vehicle_Gas, edt_Business_Vehicle_Insurance, edt_Business_Supplies,
            edt_Business_Wages, edt_Business_Advertising, edt_Business_Utilities,
            edt_Business_Taxes, edt_Business_Income;
    TextView tv_Business_Vehicle_Gas_Label, tv_Business_Vehicle_Insurance_Label;
    Button btn_Business_Cancel, btn_Business_Finish_Registration;
    Boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_style);

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Bundle extras = getIntent().getExtras();

        // TextViews
        tv_Business_Vehicle_Gas_Label = (TextView) findViewById(R.id.tv_Business_Vehicle_Gas_Label);
        tv_Business_Vehicle_Insurance_Label = (TextView) findViewById(R.id.tv_Business_Vehicle_Insurance_Label);

        // Switches
        sw_Business_Vehicle = (Switch) findViewById(R.id.sw_Business_Vehicle);
        sw_Business_Vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_Business_Vehicle.isChecked();
                if (switchState == Boolean.TRUE) {
                    tv_Business_Vehicle_Gas_Label.setVisibility(View.VISIBLE);
                    edt_Business_Vehicle_Gas.setVisibility(View.VISIBLE);
                    tv_Business_Vehicle_Insurance_Label.setVisibility(View.VISIBLE);
                    edt_Business_Vehicle_Insurance.setVisibility(View.VISIBLE);
                } else {
                    tv_Business_Vehicle_Gas_Label.setVisibility(View.INVISIBLE);
                    edt_Business_Vehicle_Gas.setVisibility(View.INVISIBLE);
                    edt_Business_Vehicle_Gas.setText("");
                    tv_Business_Vehicle_Insurance_Label.setVisibility(View.INVISIBLE);
                    edt_Business_Vehicle_Insurance.setVisibility(View.INVISIBLE);
                    edt_Business_Vehicle_Insurance.setText("");
                }
            }
        });

        // EditTexts
        edt_Business_Vehicle_Gas = (EditText) findViewById(R.id.edt_Business_Vehicle_Gas);
        edt_Business_Vehicle_Insurance = (EditText) findViewById(R.id.edt_Business_Vehicle_Insurance);
        edt_Business_Supplies = (EditText) findViewById(R.id.edt_Business_Supplies);
        edt_Business_Wages = (EditText) findViewById(R.id.edt_Business_Wages);
        edt_Business_Advertising = (EditText) findViewById(R.id.edt_Business_Advertising);
        edt_Business_Utilities = (EditText) findViewById(R.id.edt_Business_Utilities);
        edt_Business_Taxes = (EditText) findViewById(R.id.edt_Business_Taxes);
        edt_Business_Income = (EditText) findViewById(R.id.edt_Business_Income);

        // Buttons
        btn_Business_Cancel = (Button) findViewById(R.id.btn_Business_Cancel);
        btn_Business_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.DeleteUser();
                Intent intent = new Intent(BusinessStyleActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_Business_Finish_Registration = (Button) findViewById(R.id.btn_Business_Finish_Registration);
        btn_Business_Finish_Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BusinessStyleActivity.this, MainActivity.class);

                ContentValues values = new ContentValues();
                values.put("company_vehicles", sw_Business_Vehicle.isChecked());
                values.put("gas_monthly", edt_Business_Vehicle_Gas.getText().toString());
                values.put("insurance_monthly", edt_Business_Vehicle_Insurance.getText().toString());
                values.put("supplies_monthly", edt_Business_Supplies.getText().toString());
                values.put("wages_monthly", edt_Business_Wages.getText().toString());
                values.put("advertising_monthly", edt_Business_Advertising.getText().toString());
                values.put("utilities_monthly", edt_Business_Utilities.getText().toString());
                values.put("taxes_monthly", edt_Business_Taxes.getText().toString());
                values.put("income_monthly", edt_Business_Income.getText().toString());

                if (extras != null) {
                    Integer value = extras.getInt("user_id");
                    values.put("user", value);
                }
                db.insert("user_business", "NullPlaceholder", values);
                startActivity(intent);
            }
        });
    }
}