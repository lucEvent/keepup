package com.lucevent.keepup.io;

import android.content.Context;

public class DBManager {

    private Database db;

    public DBManager(Context context)
    {
        db = new Database(context);
    }

}
