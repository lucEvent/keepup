package com.lucevent.keepup.kernel;

import com.lucevent.keepup.data.Sports;
import com.lucevent.keepup.data.util.Sport;

public class AppData {

    public static Sports sports;

    public static Sport getSportByCode(int site_code)
    {
        return sports.getSportByCode(site_code);
    }

}
