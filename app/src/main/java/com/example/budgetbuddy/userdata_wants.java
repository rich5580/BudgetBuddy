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
public class userdata_wants extends Fragment {
    TextView iyearly_display, imonthly_display, iweekly_display, idaily_display;
    public userdata_wants() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_userdata_wants, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iyearly_display = (TextView) getView().findViewById(R.id.tv_iyearly_display);
        imonthly_display = (TextView) getView().findViewById(R.id.tv_imonthly_display);
        iweekly_display = (TextView) getView().findViewById(R.id.tv_iweekly_display);
        idaily_display = (TextView) getView().findViewById(R.id.tv_idaily_display);
        UserDatabaseHelper helper = new UserDatabaseHelper(getActivity());
        SQLiteDatabase db = helper.getWritableDatabase();
        ArrayList<Double> spending = helper.UserSpending();
        Log.i("Spending_Size", String.valueOf(spending.size()));
        double total_spending = 0;

        for(int i=0;i<spending.size();i++){
            total_spending += spending.get(i);
        }
        double income = helper.Income();
        double monthly_net = income - total_spending;
        imonthly_display.setText("$" + String.valueOf(monthly_net));
        double net_yearly = monthly_net * 12;
        iyearly_display.setText("$" + String.valueOf(net_yearly));
        double net_weekly = net_yearly / 48;
        iweekly_display.setText("$" + String.valueOf(net_weekly));
        double net_daily = net_weekly / 7;
        idaily_display.setText("$" + String.valueOf(String.format("%.1f", net_daily)));

    }
}