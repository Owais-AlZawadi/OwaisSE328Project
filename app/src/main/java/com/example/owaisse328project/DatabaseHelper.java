package com.example.owaisse328project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "students.db";
    public static final String TABLE_NAME = "students_data";
    public static final String COL1 = "studentId";
    public static final String COL2 = "name";
    public static final String COL3 = "lastName";
    public static final String COL4 = "fatherName";
    public static final String COL5 = "nationalId";
    public static final String COL6 = "dateOfBirth";
    public static final String COL7 = "gender";
    public int lastId = 0;


    /* Constructor */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /* Code runs automatically when the dB is created */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (studentId INTEGER PRIMARY KEY, " +
                " name TEXT, lastName TEXT, fatherName TEXT, nationalId TEXT, dateOfBirth TEXT, gender TEXT)";
        db.execSQL(createTable);
    }

    /* Every time the dB is updated (or upgraded) */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /* Basic function to add data. REMEMBER: The fields
       here, must be in accordance with those in
       the onCreate method above.
    */
    public boolean addData(int id, String name, String lName, String fName, int nationalId, String dateOfBirth, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, name);
        contentValues.put(COL3, lName);
        contentValues.put(COL4, fName);
        contentValues.put(COL5, nationalId);
        contentValues.put(COL6, dateOfBirth);
        contentValues.put(COL7, gender);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data are inserted incorrectly, it will return -1
        if(result == -1) {return false;} else {return true;}
    }

    public Boolean checkUser(String ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME+ " WHERE studentId = ?", new String[]{ID});

        if(data.getCount() <= 0){
            return false;
        }
        else{
            return true;
        }
    }

    public Integer Delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "studentId = ?", new String[] {id});
    }

    public Cursor getSpecificResult(String searchBy, String value){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME+ " WHERE " + searchBy + " = ?", new String[]{value});
        if (data != null)
            data.moveToFirst();
        return data;
    }

    // Return everything inside a specific table
    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public void update(String id, String searchBy, String value){
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + TABLE_NAME + " SET " + searchBy + " = '" + value + "' WHERE studentId = " + id;

        db.execSQL(strSQL);
    }
}