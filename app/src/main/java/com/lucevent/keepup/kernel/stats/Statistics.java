package com.lucevent.keepup.kernel.stats;

public class Statistics {

    public final Long since;

    public final Long lastStart;


    public Statistics(Long since, Long lastStart, Object sportStats)
    {
        this.since = since;
        this.lastStart = lastStart;
        sportStats = sportStats;
    }

}
