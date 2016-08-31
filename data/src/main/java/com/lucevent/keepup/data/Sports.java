package com.lucevent.keepup.data;

import com.lucevent.keepup.data.util.Sport;

import java.util.ArrayList;

public class Sports extends ArrayList<Sport> {

    public Sports(String[] titles) //new String[]{R.string.football, R.string.basketball, ....};
    {
        super();

        addDefault(titles);
    }

    private void addDefault(String[] titles)
    {
        // Spanish sites
        add(new Sport(100, titles[0], new com.lucevent.keepup.data.section.FootballSections(), new com.lucevent.keepup.data.reader.Football()));

    }

    public Sport getSportByCode(int code)
    {
        for (Sport sport : this)
            if (sport.code == code)
                return sport;

        return null;
    }

}
