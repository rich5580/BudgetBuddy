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
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class StudentStyleActivity extends AppCompatActivity {

    Button btn_back;
    Switch sw_car,sw_insurance,sw_gas;
    Boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_style);

        sw_insurance = (Switch) findViewById(R.id.sw_Student_Insurance);
        sw_gas = (Switch) findViewById(R.id.sw_Student_Gas);

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