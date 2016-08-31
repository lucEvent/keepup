package com.lucevent.keepup.io;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lucevent.keepup.AppSettings;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "keepup.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        AppSettings.printlog("[DB] En onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        AppSettings.printlog("[DB] Upgrading DB from version " + oldVersion + " to " + newVersion);

        // Delete all needed tables
        // db.execSQL("DROP TABLE "+ "db_xxxxxxxxxxxx");
        // Create all needed tables
        // db.execSQL(CREATE_yyyyyyyyy);
    }

}
