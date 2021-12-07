package com.example.budgetbuddy;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class userdata_needs extends Fragment {

    public userdata_needs() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UserDatabaseHelper helper = new UserDatabaseHelper(this);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        ArrayList<Double> spending = helper.UserSpending();
//        Log.i("Spending_Size", String.valueOf(spending.size()));
//        double total_spending = 0;
//
//        for(int i=0;i<spending.size();i++){
//            total_spending += spending.get(i);
//        }
//        monthly_total.setText("$" + String.valueOf(total_spending));
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
    }
}