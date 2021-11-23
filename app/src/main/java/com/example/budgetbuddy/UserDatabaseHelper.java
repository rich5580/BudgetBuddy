package com.example.budgetbuddy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    protected static final String ACTIVITY_NAME = "UserDatabaseHelper";

    static String DATABASE_NAME = "users";
    static int VERSION_NUM = 1;
    static final String ID = "id";
    static final String email = "email";
    static final String f_name = "first_name";
    static final String l_name = "last_name";
    static final String password = "pswd";
    static final String TABLE_NAME = "users";

    // Database creation statement saved as a string
    private static final String DATABASE_CREATE= "create table "
            + TABLE_NAME + "(" + ID
            + " integer primary key autoincrement, " + email
            + " string not null, " + f_name + " string not null," + l_name + " string not null," + password + " string not null," + "type string" + ");";

    private static final String STUDENT_CREATE= "CREATE TABLE user_students (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "own_car BOOLEAN," +
            "car_insurance BOOLEAN," +
            "car_gas BOOLEAN," +
            "rent_monthly DOUBLE," +
            "tuition BOOLEAN," +
            "weekly_income DOUBLE," +
            "dependencies BOOLEAN," +
            "children_toggle BOOLEAN," +
            "pets_toggle BOOLEAN," +
            "loans BOOLEAN," +
            "transit_monthly DOUBLE," +
            "user INT REFERENCES users (id)" + ");";

    private static final String ADULT_CREATE= "CREATE TABLE user_adults (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "car_owned BOOLEAN," +
            "car_insurance DOUBLE," +
            "gas_monthly DOUBLE," +
            "own_property BOOLEAN," +
            "property_ins_yearly DOUBLE," +
            "mortgage BOOLEAN," +
            "mortgage_monthly DOUBLE," +
            "renting BOOLEAN," +
            "rent_monthly DOUBLE," +
            "health_life_insurance BOOLEAN," +
            "health_life_monthly DOUBLE," +
            "income BOOLEAN," +
            "income_monthly DOUBLE," +
            "dependencies BOOLEAN," +
            "pet_dependency BOOLEAN," +
            "children_dependency BOOLEAN," +
            "family_dependency BOOLEAN," +
            "other_dependency BOOLEAN," +
            "dependency_monthly DOUBLE," +
            "debt BOOLEAN," +
            "debt_monthly DOUBLE," +
            "user INT REFERENCES users (id)" + ");";

    private static final String ELDERLY_CREATE="CREATE TABLE user_elderly (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "pension BOOLEAN," +
            "pension_monthly DOUBLE," +
            "other_income BOOLEAN," +
            "otherincome_monthly DOUBLE," +
            "rent_mortgage BOOLEAN," +
            "rentmortgage_monthly DOUBLE," +
            "health_life_insurance BOOLEAN," +
            "healthlifeinsurance_monthly DOUBLE," +
            "transit BOOLEAN," +
            "transit_monthly DOUBLE," +
            "own_car BOOLEAN," +
            "car_insurance BOOLEAN," +
            "car_gas BOOLEAN," +
            "groceries BOOLEAN," +
            "groceries_monthly DOUBLE," +
            "otherexpense_monthly DOUBLE," +
            "user INT REFERENCES users (id)" + ");";

    private static final String BUSINESS_CREATE="CREATE TABLE user_business (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "company_vehicles BOOLEAN," +
            "gas_monthly DOUBLE," +
            "insurance_monthly DOUBLE," +
            "supplies_monthly DOUBLE," +
            "wages_monthly DOUBLE," +
            "advertising_monthly DOUBLE," +
            "utilities_monthly DOUBLE," +
            "taxes_monthly DOUBLE," +
            "income_monthly DOUBLE," +
            "user INT REFERENCES users (id)" + ");";

    public UserDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(ACTIVITY_NAME, "Calling onCreate.");
        db.execSQL(DATABASE_CREATE);
        db.execSQL(STUDENT_CREATE);
        db.execSQL(ADULT_CREATE);
        db.execSQL(ELDERLY_CREATE);
        db.execSQL(BUSINESS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(ACTIVITY_NAME,
                "Calling onUpgrade, old version= " + i + " new version= "
                        + i1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

