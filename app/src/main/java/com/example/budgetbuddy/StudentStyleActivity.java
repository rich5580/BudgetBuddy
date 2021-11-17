package com.example.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
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

public class StudentStyleActivity extends AppCompatActivity {

    Button btn_cancel, btn_finish;
    Switch sw_car,sw_insurance,sw_gas, sw_rent, sw_tuition, sw_job,
            sw_dependencies, sw_dependKid, sw_dependPet,
            sw_loan, sw_transit;
    EditText edt_monthlyRent, edt_weeklyJob, edt_monthlyTransit;
    Boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_style);

        // Switches
        sw_insurance = (Switch) findViewById(R.id.sw_Student_Insurance);
        sw_gas = (Switch) findViewById(R.id.sw_Student_Gas);
        sw_dependKid = (Switch) findViewById(R.id.sw_dependKid);
        sw_dependPet = (Switch) findViewById(R.id.sw_dependPet);

        // Edit Texts
        edt_monthlyRent = (EditText) findViewById(R.id.edt_rentAmount);
        edt_weeklyJob = (EditText) findViewById(R.id.edt_weeklyJob);
        edt_monthlyTransit = (EditText) findViewById(R.id.edt_monthlyTransit);

        sw_rent = (Switch) findViewById(R.id.sw_Rent);
        sw_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_rent.isChecked();

                if (switchState == Boolean.TRUE) {
                    edt_monthlyRent.setVisibility(View.VISIBLE);

                } else {
                    edt_monthlyRent.setVisibility(View.INVISIBLE);
                    edt_monthlyRent.setText("");
                }
            }
        });

        sw_tuition = (Switch) findViewById(R.id.sw_Tuition);
        sw_tuition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sw_job = (Switch) findViewById(R.id.sw_Job);
        sw_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_job.isChecked();

                if (switchState == Boolean.TRUE) {
                    edt_weeklyJob.setVisibility(View.VISIBLE);
                } else {
                    edt_weeklyJob.setVisibility(View.INVISIBLE);
                    edt_weeklyJob.setText("");
                }
            }
        });

        sw_car = (Switch) findViewById(R.id.sw_Student_Car);
        sw_car.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                switchState = sw_car.isChecked();

                if (switchState == Boolean.TRUE) {
                    sw_insurance.setVisibility(View.VISIBLE);
                    sw_gas.setVisibility(View.VISIBLE);
                }
                else {
                    sw_insurance.setChecked(Boolean.FALSE);
                    sw_gas.setChecked(Boolean.FALSE);
                    sw_insurance.setVisibility(View.INVISIBLE);
                    sw_gas.setVisibility(View.INVISIBLE);
                }
            }
        });

        sw_dependencies = (Switch) findViewById(R.id.sw_dependencies);
        sw_dependencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_dependencies.isChecked();

                if (switchState == Boolean.TRUE) {
                    sw_dependKid.setVisibility(View.VISIBLE);
                    sw_dependPet.setVisibility(View.VISIBLE);
                }
                else {
                    sw_dependKid.setChecked(Boolean.FALSE);
                    sw_dependPet.setChecked(Boolean.FALSE);
                    sw_dependKid.setVisibility(View.INVISIBLE);
                    sw_dependPet.setVisibility(View.INVISIBLE);
                }
            }
        });

        sw_loan = (Switch) findViewById(R.id.sw_loan);
        sw_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_loan.isChecked();

                if (switchState == Boolean.TRUE) {

                }
                else {

                }
            }
        });

        sw_transit = (Switch) findViewById(R.id.sw_transit);
        sw_transit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchState = sw_transit.isChecked();

                if (switchState == Boolean.TRUE) {
                    edt_monthlyTransit.setVisibility(View.VISIBLE);
                }
                else {
                    edt_monthlyTransit.setVisibility(View.INVISIBLE);
                    edt_monthlyTransit.setText("");
                }
            }
        });

        btn_cancel = (Button) findViewById(R.id.btn_Student_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentStyleActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_finish = (Button) findViewById(R.id.btn_finishRegistration);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentStyleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


}