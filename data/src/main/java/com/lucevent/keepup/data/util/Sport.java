package com.lucevent.keepup.data.util;

import com.lucevent.keepup.data.reader.SportsReader;

public class Sport {

    public final int code;

    public final String name;

    public Sections sections;

    private SportsReader reader;

    public Sport(int code, String name, Sections sections, SportsReader reader)
    {
        this.code = code;
        this.name = name;
        this.sections = sections;
        this.reader = reader;
    }

    public LeagueTable getLeagueTable(Section section)
    {
        return reader.getLeagueTable(section);
    }

}
