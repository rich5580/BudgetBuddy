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

    Button btn_back;
    Switch sw_car,sw_insurance,sw_gas, sw_rent, sw_tuition, sw_job;
    EditText edt_monthlyRent, edt_weeklyJob;
    Boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_style);

        sw_insurance = (Switch) findViewById(R.id.sw_Student_Insurance);
        sw_gas = (Switch) findViewById(R.id.sw_Student_Gas);
        edt_monthlyRent = (EditText) findViewById(R.id.edt_rentAmount);
        edt_weeklyJob = (EditText) findViewById(R.id.edt_weeklyJob);

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
                    sw_insurance.setText("Insurance?");
                }
                else if (switchState == Boolean.FALSE) {
                    sw_insurance.setChecked(Boolean.FALSE);
                    sw_gas.setChecked(Boolean.FALSE);
                    sw_insurance.setVisibility(View.INVISIBLE);
                    sw_gas.setVisibility(View.INVISIBLE);
                }
            }
        });

        btn_back = (Button) findViewById(R.id.btn_Student_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentStyleActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


}