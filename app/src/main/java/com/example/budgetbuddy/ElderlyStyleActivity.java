package com.example.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class ElderlyStyleActivity extends AppCompatActivity {

    Button btn_back, btn_finish;
    Switch sw_Pension, sw_Income,sw_RentMortgage,sw_HealthLifeInsurance,sw_Transit,
    sw_Car,sw_Insurance, sw_Gas, sw_Groceries, sw_Leisure;
    EditText edt_Pension, edt_Income, edt_RentMortgage, edt_HealthLifeInsurance, edt_Transit,
    edt_Groceries,edt_Leisure;
    Boolean switchState;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elderly_style);

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Bundle extras = getIntent().getExtras();

        //Switches that do not do anything at the moment
        sw_Insurance = findViewById(R.id.sw_Elderly_Insurance);
        sw_Gas = findViewById(R.id.sw_Elderly_Gas);

        //Edit Texts
        edt_Pension = findViewById(R.id.edt_ElderlyPension);
        edt_Income = findViewById(R.id.edt_ElderlyIncome);
        edt_RentMortgage = findViewById(R.id.edt_rentMortgageAmount);
        edt_HealthLifeInsurance = findViewById(R.id.edt_HealthLifeInsurance);
        edt_Transit = findViewById(R.id.edt_monthlyTransit2);
        edt_Groceries = findViewById(R.id.edt_monthlyGroceries);
        edt_Leisure = findViewById(R.id.edt_LeisureMisc);

        sw_Pension = findViewById(R.id.sw_Elderly_Pension);
        sw_Pension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_Pension.isChecked();

                if (switchState == Boolean.TRUE){
                    edt_Pension.setVisibility(View.VISIBLE);
                }else{
                    edt_Pension.setVisibility(View.INVISIBLE);
                    edt_Pension.setText("");
                }
            }
        });

        sw_Income = findViewById(R.id.sw_Elderly_Income);
        sw_Income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_Income.isChecked();

                if (switchState == Boolean.TRUE){
                    edt_Income.setVisibility(View.VISIBLE);

                }else{
                    edt_Income.setVisibility(View.INVISIBLE);
                    edt_Income.setText("");

                }
            }
        });

        sw_RentMortgage = findViewById(R.id.sw_Rent_Mortgage);
        sw_RentMortgage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_RentMortgage.isChecked();

                if (switchState == Boolean.TRUE){
                    edt_RentMortgage.setVisibility(View.VISIBLE);

                }else{
                    edt_RentMortgage.setVisibility(View.INVISIBLE);
                    edt_RentMortgage.setText("");
                }
            }
        });

        sw_HealthLifeInsurance = findViewById(R.id.sw_Health_Life_Insurance);
        sw_HealthLifeInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_HealthLifeInsurance.isChecked();

                if (switchState == Boolean.TRUE){
                    edt_HealthLifeInsurance.setVisibility(View.VISIBLE);

                }else{
                    edt_HealthLifeInsurance.setVisibility(View.INVISIBLE);
                    edt_HealthLifeInsurance.setText("");
                }
            }
        });

        sw_Transit = findViewById(R.id.sw_transit2);
        sw_Transit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_Transit.isChecked();

                if (switchState == Boolean.TRUE){
                    edt_Transit.setVisibility(View.VISIBLE);

                }else{
                    edt_Transit.setVisibility(View.INVISIBLE);
                    edt_Transit.setText("");
                }
            }
        });

        sw_Car = (Switch) findViewById(R.id.sw_Elderly_Car);
        sw_Car.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                switchState = sw_Car.isChecked();

                if (switchState == Boolean.TRUE) {
                    sw_Insurance.setVisibility(View.VISIBLE);
                    sw_Gas.setVisibility(View.VISIBLE);
                }
                else {
                    sw_Insurance.setChecked(Boolean.FALSE);
                    sw_Gas.setChecked(Boolean.FALSE);
                    sw_Insurance.setVisibility(View.INVISIBLE);
                    sw_Gas.setVisibility(View.INVISIBLE);
                }
            }
        });

        sw_Groceries = findViewById(R.id.sw_groceries);
        sw_Groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_Groceries.isChecked();

                if (switchState == Boolean.TRUE){
                    edt_Groceries.setVisibility(View.VISIBLE);

                }else{
                    edt_Groceries.setVisibility(View.INVISIBLE);
                    edt_Groceries.setText("");
                }
            }
        });

        sw_Leisure = findViewById(R.id.sw_Leisure_Misc);
        sw_Leisure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_Leisure.isChecked();

                if (switchState == Boolean.TRUE){
                    edt_Leisure.setVisibility(View.VISIBLE);

                }else{
                    edt_Leisure.setVisibility(View.INVISIBLE);
                    edt_Leisure.setText("");
                }
            }
        });

        btn_back = (Button) findViewById(R.id.btn_Elderly_Back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElderlyStyleActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_finish = (Button) findViewById(R.id.btn_Elderly_Finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElderlyStyleActivity.this, MainActivity.class);
                //Insert code here to add to database.
                ContentValues values = new ContentValues();
                values.put("pension", sw_Pension.isChecked());
                values.put("pension_monthly", edt_Pension.getText().toString());
                values.put("other_income", sw_Income.isChecked());
                values.put("otherincome_monthly", edt_Income.getText().toString());
                values.put("rent_mortgage", sw_RentMortgage.isChecked());
                values.put("rentmortgage_monthly", edt_RentMortgage.getText().toString());
                values.put("health_life_insurance", sw_HealthLifeInsurance.isChecked());
                values.put("healthlifeinsurance_monthly", edt_HealthLifeInsurance.getText().toString());
                values.put("transit", sw_Transit.isChecked());
                values.put("transit_monthly", edt_Transit.getText().toString());
                values.put("own_car", sw_Car.isChecked());
                values.put("car_insurance", sw_Insurance.isChecked());
                values.put("car_gas", sw_Gas.isChecked());
                values.put("groceries", sw_Groceries.isChecked());
                values.put("groceries_monthly", edt_Groceries.getText().toString());
                values.put("otherexpense_monthly", edt_Leisure.getText().toString());

                if (extras != null) {
                    Integer value = extras.getInt("user_id");
                    values.put("user", value);
                }
                db.insert("user_elderly", "NullPlaceholder", values);
                startActivity(intent);
            }
        });

    }
}