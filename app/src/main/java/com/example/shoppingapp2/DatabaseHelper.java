package com.example.shoppingapp2;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String SHOPPING_ITEMS = "shopping_items";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ENGLISH = "english";
    public static final String COLUMN_KOREAN = "korean";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "shopping.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableStatement = "CREATE TABLE " + SHOPPING_ITEMS + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ENGLISH + " TEXT, " + COLUMN_KOREAN + " TEXT)";
        db.execSQL(CreateTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Items item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ENGLISH, item.getEnglish());
        cv.put(COLUMN_KOREAN, item.getKorean());
        long insert = db.insert(SHOPPING_ITEMS, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean delOne(Items item){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + SHOPPING_ITEMS + " WHERE " + COLUMN_ID + " = " + item.getId();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }
    public List<Items> getAllData(){
        List<Items> returnList = new ArrayList<>();
        String query = "SELECT * FROM " + SHOPPING_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                int itemID = cursor.getInt(0);
                String itemEnglish = cursor.getString(1);
                String itemKorean = cursor.getString(2);

                Items newItem = new Items(itemID, itemEnglish, itemKorean);
                returnList.add(newItem);

            } while (cursor.moveToNext());

        }
        else {

        }
        cursor.close();
        db.close();
        return returnList;
    }
}




















