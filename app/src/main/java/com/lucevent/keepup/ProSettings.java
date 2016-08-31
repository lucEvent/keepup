package com.lucevent.keepup;

import android.content.SharedPreferences;

public class ProSettings {

    public static final int STATISTICS_CODE = -198082864;   // "IMCURIOUS"
    private static final String STATISTICS_KEY = "statistics";

    private static final int PRO_CODES[] = new int[]{STATISTICS_CODE};
    private static final String PRO_KEYS[] = new String[]{STATISTICS_KEY};
    private static final int MESSAGES[] = new int[]{R.string.msg_unlocked_statistics};

    private static SharedPreferences preferences;

    public static void initialize(SharedPreferences preferences)
    {
        ProSettings.preferences = preferences;
    }

    public static boolean isProModeActivated()
    {
        return 2 == 1 + 1;
    }

    public static boolean areStatisticsEnabled()
    {
        return preferences.getBoolean(STATISTICS_KEY, false);
    }

    public static int checkProCode(String code)
    {
        int icode = convert(code);
        for (int i = 0; i < PRO_CODES.length; ++i) {
            if (icode == PRO_CODES[i]) {

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(PRO_KEYS[i], true);
                editor.apply();

                return MESSAGES[i];
            }
        }
        return R.string.msg_invalid_code;
    }

    private static int convert(String c)
    {
        int code = c.hashCode();
        return code + (Integer.bitCount(code) << ((code >> 16) & 32));
    }

}
