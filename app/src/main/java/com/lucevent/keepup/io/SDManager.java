package com.lucevent.keepup.io;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class SDManager {

    private Context context;

    public SDManager(Context context)
    {
        this.context = context;
    }

    public static File getDirectory()
    {
        File directory;
        if (Environment.getExternalStorageState() == null)
            directory = new File(Environment.getDataDirectory() + "/NewsUp/");
        else
            directory = new File(Environment.getExternalStorageDirectory() + "/NewsUp/");

        if (!directory.exists())
            directory.mkdir();

        return directory;
    }

    public long getCacheSize()
    {
        long dbsize = context.getDatabasePath(Database.DATABASE_NAME).length();
        long size = 0;
        File[] files = context.getFilesDir().listFiles();
        for (File f : files) size += f.length();
        return size + dbsize;
    }

    public void wipeData()
    {
        File[] files = context.getFilesDir().listFiles();
        for (File f : files) f.delete();
    }

}
