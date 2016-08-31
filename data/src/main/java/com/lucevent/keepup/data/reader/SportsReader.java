package com.lucevent.keepup.data.reader;

import com.lucevent.keepup.data.util.LeagueTable;
import com.lucevent.keepup.data.util.Section;

public abstract class SportsReader {

    public abstract LeagueTable getLeagueTable(Section s);

}
