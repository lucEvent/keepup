package com.lucevent.keepup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;

import java.util.Set;

public class AppSettings {

    public static final boolean DEBUG = false;

    /**
     * ************** Default values *********************
     **/
    public static String PREF_PRO_CODE_KEY;

    private static SharedPreferences preferences;

    public static void initialize(Context context)
    {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            PREF_PRO_CODE_KEY = context.getString(R.string.pref_pro_code_key);

        }
        ProSettings.initialize(preferences);
    }

    private static Handler handler;

    public static void initialize(Context context, Handler handler)
    {
        initialize(context);
        AppSettings.handler = handler;
    }

    private static int[] getIntArray(Set<String> set)
    {
        int index = 0;
        int[] res = new int[set.size()];
        for (String s : set)
            res[index++] = Integer.parseInt(s);
        return res;
    }

    public static void printerror(String msg, Exception e)
    {
        if (DEBUG) {
            System.err.println(msg);

            if (e != null)
                e.printStackTrace();
        }
    }

    public static void printlog(String msg)
    {
        if (DEBUG)
            System.out.println(msg);
    }

}
