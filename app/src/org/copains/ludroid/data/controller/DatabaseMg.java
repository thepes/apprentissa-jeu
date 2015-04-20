package org.copains.ludroid.data.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseMg extends SQLiteOpenHelper {

    private static final int VERSION = 2;
    private static final String NAME = "ApprentissaJeu";

    public DatabaseMg(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (usr_firstname TEXT, usr_birthdate TEXT, usr_sex INTEGER, usr_canread INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if ((oldVersion == 1) && (newVersion == 2)) {
            db.execSQL("DROP TABLE users");
            db.execSQL("CREATE TABLE users (usr_id INTEGER PRIMARY KEY ASC AUTOINCREMENT, usr_firstname TEXT, usr_birthdate TEXT, usr_sex INTEGER, usr_canread INTEGER)");
        }

    }

}
