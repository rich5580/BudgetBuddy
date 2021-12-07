package com.example.budgetbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

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

    public static String ACTIVE_USER = "ACTIVE_USER";
    public static int ACTIVE_USERID;
    public static String ACTIVE_FIRST_NAME = "";
    public static String ACTIVE_LAST_NAME="";
    public static String ACTIVE_TYPE = "";
    public static String ACTIVE_PASSWORD="";


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

    private static final String DATA_CREATE="CREATE TABLE user_data (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "amount DOUBLE," +
            "spend_type String," +
            "description String," +
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
        db.execSQL(DATA_CREATE);
    }
    public void addUser(SQLiteDatabase db, ContentValues values){
        db.insert(UserDatabaseHelper.DATABASE_NAME, "NullPlaceholder", values);
        ACTIVE_USER = (String) values.get(UserDatabaseHelper.email);
    }

    public void getUser(SQLiteDatabase db, String email){
        Cursor data = db.rawQuery("SELECT id FROM users WHERE email=?", new String[]{email});
        ACTIVE_USER = String.valueOf(data.getInt(0));
    }

    public void DeleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + email + "= ?";
        db.rawQuery(queryString, new String[] {ACTIVE_USER});

    }

    public boolean isValidEmailAndPassword(String email, String password) {
        // get data from the database
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email=?", new String[] {email});

        boolean hasObject = false;
        if (cursor.moveToNext()) {
            String pw = cursor.getString(4);
            if (pw.equals(password)) {
                hasObject = true;
                //special important line
                ACTIVE_USER = email;
                ACTIVE_USERID = cursor.getInt(0);
                ACTIVE_FIRST_NAME = cursor.getString(2);
                ACTIVE_LAST_NAME = cursor.getString(3);
                ACTIVE_TYPE = cursor.getString(5);
            }

        }

        // close both the cursor and the db when done
        cursor.close();
        db.close();
        return hasObject;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(ACTIVITY_NAME,
                "Calling onUpgrade, old version= " + i + " new version= "
                        + i1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS user_students");
        db.execSQL("DROP TABLE IF EXISTS user_adults");
        db.execSQL("DROP TABLE IF EXISTS user_elderly");
        db.execSQL("DROP TABLE IF EXISTS user_business");
        db.execSQL("DROP TABLE IF EXISTS user_data");
        onCreate(db);
    }

    // Updates name phone and password in the database user table where user email equals active user
    public boolean updateSettings(String fName, String lName, String pw) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(f_name, fName);
        contentValues.put(l_name, lName);
        contentValues.put(password, pw);

        db.update(DATABASE_NAME, contentValues, "email = ?", new String[] {ACTIVE_USER});
        ACTIVE_FIRST_NAME = fName;
        ACTIVE_LAST_NAME = lName;
        ACTIVE_PASSWORD = pw;

        return true;

    }

    public boolean deleteOne(){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + DATABASE_NAME + " WHERE " + email + "= ?";
        Cursor cursor = db.rawQuery(queryString, new String[] {ACTIVE_USER});

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean deleteData(DataModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM user_data WHERE id= ?";
        Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(model.getId())});

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public void UserSpending(){
        //Needs to calculate total user spending for yearly, monthly, weekly, and daily
        //Needs to Sum recurring spending for all categories
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM user_data WHERE user = ?";

        Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});


    }

    public List<DataModel> BudgetBreakdown(){
        //Needs to calculate monthly spending for each individual category
        //Needs to display spending for each category as individual listview items
        List<DataModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM user_data WHERE user = ?";

        Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
        return returnList;
    }

    public void TotalBudget(){
        //Needs to compate UserSpending to income at yearly, monthly, weekly, and daily. Income - total spending
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM user_data WHERE user = ?";

        Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
    }


    // Gets all contacts with the ID matching the active user, searching the database customer table which contains all contacts
    public List<DataModel> getAllInfo() {
        List<DataModel> returnList = new ArrayList<>();

        // get data from the database
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + "user_data" + " WHERE " + "user" + " = ?";



        Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
        if (cursor.moveToFirst()) {
            // loop through the cursor (result set) and create new customer objects. Put them into the return list.
            do {
                int id = cursor.getInt(0);
                Double amount = cursor.getDouble(1);
                String spend_type = cursor.getString(2);
                String description = cursor.getString(3);
                DataModel newData = new DataModel(id, amount, spend_type, description);
                returnList.add(newData);

            } while (cursor.moveToNext());
        }
        else {
            // failure. do not add anything to the list
        }

        // close both the cursor and the db when done
        cursor.close();
        db.close();
        return returnList;
    }
}

