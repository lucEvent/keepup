package com.lucevent.keepup.server.utils;

import java.util.Comparator;
import java.util.TreeSet;

public class StatisticsSet extends TreeSet<SportStat> {

    public StatisticsSet(SportStat[] stats, Comparator<SportStat> comparator)
    {
        super(comparator);

        for (SportStat ss : stats)
            add(ss);

    }

}
