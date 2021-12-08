package com.example.budgetbuddy;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class userdata_needs extends Fragment {
    TextView yearly_display, monthly_display, weekly_display, daily_display;
    public userdata_needs() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_userdata_needs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        yearly_display = (TextView) getView().findViewById(R.id.tv_ryearly_display);
        monthly_display = (TextView) getView().findViewById(R.id.tv_rmonthly_display);
        weekly_display = (TextView) getView().findViewById(R.id.tv_rweekly_display);
        daily_display = (TextView) getView().findViewById(R.id.tv_rdaily_display);
        UserDatabaseHelper helper = new UserDatabaseHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();
        ArrayList<Double> spending = helper.UserSpending();
        Log.i("Spending_Size", String.valueOf(spending.size()));
        double total_spending = 0;

        for(int i=0;i<spending.size();i++){
            total_spending += spending.get(i);
        }
        monthly_display.setText("$" + String.valueOf(total_spending));
        double total_yearly = total_spending * 12;
        yearly_display.setText("$" + String.valueOf(total_yearly));
        double total_weekly = total_yearly / 48;
        weekly_display.setText("$" + String.valueOf(total_weekly));
        double total_daily = total_weekly / 7;
        daily_display.setText("$" + String.valueOf(String.format("%.1f", total_daily)));
    }
}