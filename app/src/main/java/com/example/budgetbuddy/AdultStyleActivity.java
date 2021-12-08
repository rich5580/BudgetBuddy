package com.example.budgetbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class AdultStyleActivity extends AppCompatActivity {

    Button btn_cancel, btn_adult_reg;
    Switch sw_car, sw_prop, sw_mort, sw_rent, sw_life, sw_income, sw_dep, sw_child, sw_fam, sw_pet, sw_other, sw_debt;
    TextView lbl_car, lbl_gas, lbl_prop, lbl_income, lbl_dep;
    EditText edt_car, edt_gas, edt_prop, edt_mort, edt_rent, edt_life, edt_income, edt_dep, edt_debt;
    Boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_style);

        UserDatabaseHelper helper = new UserDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Bundle extras = getIntent().getExtras();

        // Buttons
        btn_adult_reg = (Button) findViewById(R.id.btn_Adult_Registration);
        btn_cancel = (Button) findViewById(R.id.btn_Adult_cancel);
        // Switches
        sw_car = (Switch) findViewById(R.id.sw_Adult_Car);
        sw_prop = (Switch) findViewById(R.id.sw_Adult_property);
        sw_mort = (Switch) findViewById(R.id.sw_Adult_mortgage);
        sw_rent = (Switch) findViewById(R.id.sw_Adult_rent);
        sw_life = (Switch) findViewById(R.id.sw_Adult_health);
        sw_income = (Switch) findViewById(R.id.sw_Adult_income);
        sw_dep = (Switch) findViewById(R.id.sw_Adult_dep);
        sw_child = (Switch) findViewById(R.id.sw_Adult_Children);
        sw_fam = (Switch) findViewById(R.id.sw_Adult_Family);
        sw_pet = (Switch) findViewById(R.id.sw_Adult_Pets);
        sw_other = (Switch) findViewById(R.id.sw_Adult_Other);
        sw_debt = (Switch) findViewById(R.id.sw_Adult_debt);
        // Edit Texts
        edt_car = findViewById(R.id.edt_ACarAmount);
        edt_gas = findViewById(R.id.edt_AgasAmount);
        edt_prop = findViewById(R.id.edt_APropInAmount);
        edt_mort = findViewById(R.id.edt_AMortgageAmount);
        edt_rent = findViewById(R.id.edt_ARentAmount);
        edt_life = findViewById(R.id.edt_AHealthAmount);
        edt_income = findViewById(R.id.edt_AIncomeAmount);
        edt_dep = findViewById(R.id.edt_ADepAmount);
        edt_debt = findViewById(R.id.edt_ADebtAmount);
        // Labels
        lbl_car = findViewById(R.id.CarLbl);
        lbl_gas = findViewById(R.id.GasLbl);
        lbl_prop = findViewById(R.id.PropInLbl);
        lbl_income = findViewById(R.id.IncomeLbl);
        lbl_dep = findViewById(R.id.DepLbl);

        btn_adult_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdultStyleActivity.this, MainActivity.class);
                //Insert code here to add to database.
                ContentValues values = new ContentValues();
                values.put("car_owned", sw_car.isChecked());
                values.put("car_insurance", edt_car.getText().toString());
                values.put("gas_monthly",edt_gas.getText().toString());
                values.put("own_property", sw_prop.isChecked());
                values.put("property_ins_monthly",edt_prop.getText().toString());
                values.put("mortgage",sw_mort.isChecked());
                values.put("mortgage_monthly", edt_mort.getText().toString());
                values.put("renting",sw_rent.isChecked());
                values.put("rent_monthly",edt_rent.getText().toString());
                values.put("health_life_insurance",sw_life.isChecked());
                values.put("health_life_monthly", edt_life.getText().toString());
                values.put("income", sw_income.isChecked());
                values.put("income_monthly", edt_income.getText().toString());
                values.put("dependencies", sw_dep.isChecked());
                values.put("pet_dependency", sw_pet.isChecked());
                values.put("children_dependency", sw_child.isChecked());
                values.put("family_dependency", sw_fam.isChecked());
                values.put("other_dependency", sw_other.isChecked());
                values.put("dependency_monthly", edt_dep.getText().toString());
                values.put("debt", sw_debt.isChecked());
                values.put("debt_monthly", edt_debt.getText().toString());

                if (extras != null) {
                    Integer value = extras.getInt("user_id");
                    values.put("user", value);
                }
                db.insert("user_adults", "NullPlaceholder", values);

                startActivity(intent);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.DeleteUser();
                Intent intent = new Intent(AdultStyleActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        sw_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_car.isChecked();

                if (switchState == Boolean.TRUE) {
                    lbl_car.setVisibility(View.VISIBLE);
                    edt_car.setVisibility(View.VISIBLE);
                    lbl_gas.setVisibility(View.VISIBLE);
                    edt_gas.setVisibility(View.VISIBLE);

                } else {
                    lbl_car.setVisibility(View.INVISIBLE);
                    edt_car.setVisibility(View.INVISIBLE);
                    edt_car.setText("");
                    lbl_gas.setVisibility(View.INVISIBLE);
                    edt_gas.setVisibility(View.INVISIBLE);
                    edt_gas.setText("");
                }
            }
        });

        sw_prop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_prop.isChecked();

                if (switchState == Boolean.TRUE) {
                    lbl_prop.setVisibility(View.VISIBLE);
                    edt_prop.setVisibility(View.VISIBLE);

                } else {
                    lbl_prop.setVisibility(View.INVISIBLE);
                    edt_prop.setVisibility(View.INVISIBLE);
                    edt_prop.setText("");
                }
            }
        });

        sw_mort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_mort.isChecked();

                if (switchState == Boolean.TRUE) {
                    edt_mort.setVisibility(View.VISIBLE);

                } else {
                    edt_mort.setVisibility(View.INVISIBLE);
                    edt_mort.setText("");
                }
            }
        });

        sw_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_rent.isChecked();

                if (switchState == Boolean.TRUE) {
                    edt_rent.setVisibility(View.VISIBLE);

                } else {
                    edt_rent.setVisibility(View.INVISIBLE);
                    edt_rent.setText("");
                }
            }
        });

        sw_life.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_life.isChecked();

                if (switchState == Boolean.TRUE) {
                    edt_life.setVisibility(View.VISIBLE);

                } else {
                    edt_life.setVisibility(View.INVISIBLE);
                    edt_life.setText("");
                }
            }
        });

        sw_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_income.isChecked();

                if (switchState == Boolean.TRUE) {
                    lbl_income.setVisibility(View.VISIBLE);
                    edt_income.setVisibility(View.VISIBLE);

                } else {
                    lbl_income.setVisibility(View.INVISIBLE);
                    edt_income.setVisibility(View.INVISIBLE);
                    edt_income.setText("");
                }
            }
        });

        sw_dep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_dep.isChecked();

                if (switchState == Boolean.TRUE) {
                    sw_child.setVisibility(View.VISIBLE);
                    sw_fam.setVisibility(View.VISIBLE);
                    sw_other.setVisibility(View.VISIBLE);
                    sw_pet.setVisibility(View.VISIBLE);


                } else {
                    sw_child.setVisibility(View.INVISIBLE);
                    sw_fam.setVisibility(View.INVISIBLE);
                    sw_other.setVisibility(View.INVISIBLE);
                    sw_pet.setVisibility(View.INVISIBLE);

                }
            }
        });

        sw_debt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_debt.isChecked();

                if (switchState == Boolean.TRUE) {
                    edt_debt.setVisibility(View.VISIBLE);

                } else {
                    edt_debt.setVisibility(View.INVISIBLE);
                    edt_debt.setText("");
                }
            }
        });
    }
}