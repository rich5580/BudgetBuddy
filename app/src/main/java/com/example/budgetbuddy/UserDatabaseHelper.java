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

import java.lang.reflect.Array;
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
            "property_ins_monthly DOUBLE," +
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

    public boolean doesExist(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email=?", new String[] {email});

        boolean hasObject = false;
        if (cursor.moveToNext()) {
            String mail = cursor.getString(1);
            if (mail.equals(email)) {
                hasObject = true;
            }

        }


        cursor.close();
        return hasObject;
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

    public ArrayList<Double> UserSpending(){
        //Needs to calculate total user spending for monthly
        //Needs to Sum recurring spending for all categories
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString;
        ArrayList<Double> spending = new ArrayList<Double>();

        if (ACTIVE_TYPE.equals("Student")){
            queryString = "SELECT rent_monthly,transit_monthly FROM user_students WHERE user = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
            cursor.moveToFirst();
            spending.add(cursor.getDouble(0));
            spending.add(cursor.getDouble(1));

        } else if (ACTIVE_TYPE.equals("Adult")){
            queryString = "SELECT car_insurance,gas_monthly,property_ins_monthly,mortgage_monthly,rent_monthly,health_life_monthly,dependency_monthly,debt_monthly FROM user_adults WHERE user = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
            cursor.moveToFirst();
            spending.add(cursor.getDouble(0));
            spending.add(cursor.getDouble(1));
            spending.add(cursor.getDouble(2));
            spending.add(cursor.getDouble(3));
            spending.add(cursor.getDouble(4));
            spending.add(cursor.getDouble(5));
            spending.add(cursor.getDouble(6));
            spending.add(cursor.getDouble(7));
        } else if (ACTIVE_TYPE.equals("Elderly")){
            queryString = "SELECT rentmortgage_monthly,healthlifeinsurance_monthly,transit_monthly,car_insurance,car_gas,groceries_monthly,otherexpense_monthly FROM user_elderly WHERE user = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
            cursor.moveToFirst();
            spending.add(cursor.getDouble(0));
            spending.add(cursor.getDouble(1));
            spending.add(cursor.getDouble(2));
            spending.add(cursor.getDouble(3));
            spending.add(cursor.getDouble(4));
            spending.add(cursor.getDouble(5));
            spending.add(cursor.getDouble(6));
        } else {
            queryString = "SELECT gas_monthly,insurance_monthly,supplies_monthly,wages_monthly,advertising_monthly,utilities_monthly,taxes_monthly FROM user_business WHERE user = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
            cursor.moveToFirst();
            spending.add(cursor.getDouble(0));
            spending.add(cursor.getDouble(1));
            spending.add(cursor.getDouble(2));
            spending.add(cursor.getDouble(3));
            spending.add(cursor.getDouble(4));
            spending.add(cursor.getDouble(5));
            spending.add(cursor.getDouble(6));
        }

        return spending;

    }



    public Double KeyedPurchasesTotal(){
        Double total = 0.0;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT amount FROM user_data WHERE user = ?";
        Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
                total += cursor.getDouble(0);
                cursor.moveToNext();
            }
        return total;
    }

    public double Income(){
        //Needs to send income;
        Double income;
        SQLiteDatabase db = this.getReadableDatabase();
        if (ACTIVE_TYPE.equals("Student")) {
            String queryString = "SELECT weekly_income FROM user_students WHERE user = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
            cursor.moveToFirst();
            income = cursor.getDouble(0);
            cursor.close();
        }
        else if (ACTIVE_TYPE.equals("Elderly")) {
            String queryString = "SELECT pension_monthly, otherincome_monthly FROM user_elderly WHERE user = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
            cursor.moveToFirst();
            income = cursor.getDouble(0) + cursor.getDouble(1);
            cursor.close();
        }
        else if (ACTIVE_TYPE.equals("Adult")) {
            String queryString = "SELECT income_monthly FROM user_adults WHERE user = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
            cursor.moveToFirst();
            income = cursor.getDouble(0);
            cursor.close();
        }
        else  {
            String queryString = "SELECT income_monthly FROM user_business WHERE user = ?";
            Cursor cursor = db.rawQuery(queryString, new String[] {String.valueOf(ACTIVE_USERID)});
            cursor.moveToFirst();
            income = cursor.getDouble(0);
            cursor.close();
        }

        return income;
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

    public void modData(String type, String value) {
        SQLiteDatabase db = this.getReadableDatabase();
        if (ACTIVE_TYPE.equals("Student")) {
            ContentValues cv = new ContentValues();
            cv.put(type,value);
            db.update("user_students", cv, "user=?", new String[] {String.valueOf(ACTIVE_USERID)});
        } else if (ACTIVE_TYPE.equals("Adult")) {
            ContentValues cv = new ContentValues();
            cv.put(type,value);
            db.update("user_adults", cv, "user=?", new String[] {String.valueOf(ACTIVE_USERID)});
        } else if (ACTIVE_TYPE.equals("Elderly")) {
            ContentValues cv = new ContentValues();
            cv.put(type,value);
            db.update("user_elderly", cv, "user=?", new String[] {String.valueOf(ACTIVE_USERID)});
        } else if (ACTIVE_TYPE.equals("Business")) {
            ContentValues cv = new ContentValues();
            cv.put(type,value);
            db.update("user_business", cv, "user=?", new String[] {String.valueOf(ACTIVE_USERID)});
        }
    }

    public List<DataModel> BudgetBreakdown(){
        //Needs to calculate monthly spending for each individual category
        //Needs to display spending for each category as individual listview items
        List<DataModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Double> spending = UserSpending();
        if(ACTIVE_TYPE.equals("Student")){
            Double rentAmount = spending.get(0);
            String rentType = "Monthly Rent";
            DataModel rentData = new DataModel(rentAmount, rentType, "rent_monthly");
            returnList.add(rentData);
            Double transitAmount = spending.get(1);
            String transitType = "Monthly Transit";
            DataModel transitData = new DataModel(transitAmount, transitType, "transit_monthly");
            returnList.add(transitData);

        } else if (ACTIVE_TYPE.equals("Elderly")){
            Double rentAmount = spending.get(0);
            String rentType = "Monthly Rent/Mortgage";
            DataModel rentData = new DataModel(rentAmount, rentType, "rent_mortgage");
            returnList.add(rentData);
            Double healthAmount = spending.get(1);
            String healthType = "Monthly Health/Life insurance";
            DataModel healthData = new DataModel(healthAmount, healthType, "healthlifeinsurance_monthly");
            returnList.add(healthData);
            Double transitAmount = spending.get(2);
            String transitType = "Monthly Transit";
            DataModel transitData = new DataModel(transitAmount, transitType, "transit_monthly");
            returnList.add(transitData);
            Double carAmount = spending.get(3);
            String carType = "Monthly Car Insurance";
            DataModel carData = new DataModel(carAmount, carType, "car_insurance");
            returnList.add(carData);
            Double gasAmount = spending.get(4);
            String gasType = "Monthly Gas";
            DataModel gasData = new DataModel(gasAmount, gasType, "car_gas");
            returnList.add(gasData);
            Double grocAmount = spending.get(5);
            String grocType = "Monthly Grocery";
            DataModel grocData = new DataModel(grocAmount, grocType, "groceries_monthly");
            returnList.add(grocData);
            Double othAmount = spending.get(6);
            String othType = "Monthly Other Expenses";
            DataModel othData = new DataModel(othAmount, othType, "otherexpense_monthly");
            returnList.add(othData);

        } else if (ACTIVE_TYPE.equals("Adult")){
            Double carAmount = spending.get(0);
            String carType = "Monthly Car Insurance";
            DataModel carData = new DataModel(carAmount, carType, "car_insurance");
            returnList.add(carData);
            Double gasAmount = spending.get(1);
            String gasType = "Monthly Gas";
            DataModel gasData = new DataModel(gasAmount, gasType, "gas_monthly");
            returnList.add(gasData);
            Double propAmount = spending.get(2);
            String propType = "Monthly Property Insurance";
            DataModel propData = new DataModel(propAmount, propType, "property_ins_monthly");
            returnList.add(propData);
            Double mortAmount = spending.get(3);
            String mortType = "Monthly Mortgage";
            DataModel mortData = new DataModel(mortAmount, mortType, "mortgage_monthly");
            returnList.add(mortData);
            Double rentAmount = spending.get(4);
            String rentType = "Monthly Rent";
            DataModel rentData = new DataModel(rentAmount, rentType, "rent_monthly");
            returnList.add(rentData);
            Double healthAmount = spending.get(5);
            String healthType = "Monthly Health Insurance";
            DataModel healthData = new DataModel(healthAmount, healthType, "health_life_monthly");
            returnList.add(healthData);
            Double depAmount = spending.get(6);
            String depType = "Monthly Dependencies";
            DataModel depData = new DataModel(depAmount, depType, "dependency_monthly");
            returnList.add(depData);
            Double debtAmount = spending.get(7);
            String debtType = "Monthly Debt";
            DataModel debtData = new DataModel(debtAmount, debtType, "debt_monthly");
            returnList.add(debtData);

        } else if (ACTIVE_TYPE.equals("Business")){
            Double gasAmount = spending.get(0);
            String gasType = "Monthly Gas";
            DataModel gasData = new DataModel(gasAmount, gasType, "gas_monthly");
            returnList.add(gasData);
            Double insAmount = spending.get(1);
            String insType = "Monthly Insurance";
            DataModel insData = new DataModel(insAmount, insType, "insurance_monthly");
            returnList.add(insData);
            Double supAmount = spending.get(2);
            String supType = "Monthly Supplies";
            DataModel supData = new DataModel(supAmount, supType, "supplies_monthly");
            returnList.add(supData);
            Double wageAmount = spending.get(3);
            String wageType = "Monthly Wages";
            DataModel wageData = new DataModel(wageAmount, wageType, "wages_monthly");
            returnList.add(wageData);
            Double adAmount = spending.get(4);
            String adType = "Monthly Advertising";
            DataModel adData = new DataModel(adAmount, adType, "advertising_monthly");
            returnList.add(adData);
            Double utilAmount = spending.get(5);
            String utilType = "Monthly Utilities";
            DataModel utilData = new DataModel(utilAmount, utilType, "utilities_monthly");
            returnList.add(utilData);
            Double taxAmount = spending.get(6);
            String taxType = "Monthly Taxes";
            DataModel taxData = new DataModel(taxAmount, taxType, "taxes_monthly");
            returnList.add(taxData);

        }


        db.close();
        return returnList;
    }
}

