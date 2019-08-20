package io.artcreativity.firstappdev.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseAccess extends SQLiteOpenHelper {

    private static DatabaseAccess databaseAccess;
    public static String DATABASE_NAME = "appdev.db";
    public static String PERSON_TABLE = "persons";
    public static int VERSION = 2;

    public static DatabaseAccess getInstance(Context context){
        if(databaseAccess==null){
            databaseAccess = new DatabaseAccess(context);
        }
        return databaseAccess;
    }

    private DatabaseAccess(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + PERSON_TABLE +"(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "first_name String," +
                "last_name String," +
                "avatar String," +
                "email String," +
                "phone String," +
                "facebook String," +
                "whatsapp String) ");
        Log.d("TAG", "onCreate: Table persons");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion==1)
            db.execSQL("ALTER TABLE " + PERSON_TABLE + " ADD first_name String");
    }
}
