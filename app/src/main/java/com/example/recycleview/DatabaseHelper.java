package com.example.recycleview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import javax.xml.transform.sax.SAXResult;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "RestaurantApp";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "food";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FOOD_TITLE = "food_title";

    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FOOD_TITLE + " TEXT);";
        String queryUser = "CREATE TABLE " + TABLE_USER + " (" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD + " TEXT);";
        db.execSQL(query);
        db.execSQL(queryUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void insertFood(String foodTitle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FOOD_TITLE, foodTitle);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            Toast.makeText(context, "Added failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME, username);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_USER, null, contentValues);
        if (result == -1){
            Toast.makeText(context, "Registered failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Registered successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllFoods(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    boolean checkEmailAndPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[] {email, password});
        if(cursor.getCount() > 0){
            Toast.makeText(context, "The user is in the database", Toast.LENGTH_SHORT).show();
            return  true;
        } else {
            return false;
        }
    }

    void updateData(String rowId, String foodTitle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_FOOD_TITLE, foodTitle);

        long result = db.update(TABLE_NAME, contentValues, "id=?", new String[]{rowId});
        if (result == -1){
            Toast.makeText(context, "Failed to update note", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Note update successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String rowId){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, "id=?", new String[]{rowId});
        if (result == -1){
            Toast.makeText(context, "Failed to delete note", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Note deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
