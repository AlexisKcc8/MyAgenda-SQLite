package com.example.agenda.Open_Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.agenda.models.Model_user;

public class OpenHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "myAgenda.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "t_contactos";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "user_name";
    private static final String COLUMN_LAST = "user_last";
    private static final String COLUMN_PHONE = "user_phone";
    private static final String COLUMN_CATEGORY = "user_category";
    private static final String COLUMN_DATE = "user_date";
    private static final String COLUMN_TIME = "user_time";

    public OpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_LAST + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT);";
        db.execSQL(query);;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(Model_user user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, user.getName());
        cv.put(COLUMN_LAST, user.getLast());
        cv.put(COLUMN_PHONE, user.getPhone());
        cv.put(COLUMN_CATEGORY, user.getCategoria());
        cv.put(COLUMN_DATE, user.getDate());
        cv.put(COLUMN_TIME, user.getTime());
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateUser(Model_user user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, user.getName());
        cv.put(COLUMN_LAST, user.getLast());
        cv.put(COLUMN_PHONE, user.getPhone());
        cv.put(COLUMN_CATEGORY, user.getCategoria());
        cv.put(COLUMN_DATE, user.getDate());
        cv.put(COLUMN_TIME, user.getTime());

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{Integer.toString(user.getId())});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
