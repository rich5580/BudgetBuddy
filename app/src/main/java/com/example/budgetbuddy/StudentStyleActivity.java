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
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class StudentStyleActivity extends AppCompatActivity {

    Button btn_back;
    Switch sw_car;
    Boolean switchState;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_style);

        listView = (ListView) findViewById(R.id.yesCar);
        CarAdapter adapter = new CarAdapter(StudentStyleActivity.this);
        listView.setAdapter(adapter);

        sw_car = (Switch) findViewById(R.id.sw_Student_Car);
        sw_car.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                switchState = sw_car.isChecked();

                if (switchState == Boolean.TRUE) {
                    Toast toast = Toast.makeText(StudentStyleActivity.this, "Switched on", Toast.LENGTH_LONG);
                    toast.show();

                    LayoutInflater inflater = StudentStyleActivity.this.getLayoutInflater();
                    inflater.inflate(R.layout.car_insurance_activity, null);
//                    adapter.notifyDataSetChanged();
//                    adapter.getView().findViewById(R.layout.car_insurance_activity);



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

    private class CarAdapter extends ArrayAdapter<String> {

        public CarAdapter(@NonNull Context context) {
            super(context, 0);
        }

        public View getView(View convertView, ViewGroup parent) {
            LayoutInflater inflater = StudentStyleActivity.this.getLayoutInflater();
            View result = null;
            result = inflater.inflate(R.layout.car_insurance_activity, null);
            return result;
        }
    }

}