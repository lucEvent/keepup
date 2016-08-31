package com.lucevent.keepup.server.utils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Unindex;
import com.googlecode.objectify.cmd.LoadType;
import com.lucevent.keepup.data.Sports;
import com.lucevent.keepup.data.util.Sport;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Entity
public class Statistics {

    @Id
    private Long id;

    @Unindex
    public Long since;

    @Ignore
    public Long lastStart;

    @Unindex
    private SportStat[] sportStats;


    public static Statistics initialize(Sports sports)
    {
        LoadType<Statistics> db = ofy().load().type(Statistics.class);

        Statistics statistics;
        if (db.count() > 0) {

            statistics = db.first().now();
            statistics.lastStart = System.currentTimeMillis();

        } else {

            statistics = new Statistics();
            initializeStats(statistics, sports);

            ofy().save().entity(statistics).now();

        }
        return statistics;
    }

    public void count(int position, String ip)
    {
        synchronized (this) {
            sportStats[position].nAccesses++;
            sportStats[position].lastAccess = System.currentTimeMillis();
            sportStats[position].lastIp = ip;
            ofy().save().entity(this).now();//save()    //possible improvement? save only when the server instance is destroyed
        }
    }

    public SportStat[] getSportsStats()
    {
        return sportStats;
    }

    public void save()
    {
        ofy().save().entity(this).now();
    }

    public void reset(Sports sports)
    {
        initializeStats(this, sports);
        ofy().save().entity(this).now();
    }

    private static void initializeStats(Statistics stats, Sports sports)
    {
        stats.since = System.currentTimeMillis();
        stats.lastStart = System.currentTimeMillis();
        stats.sportStats = new SportStat[sports.size()];
        for (int i = 0; i < sports.size(); i++) {
            Sport s = sports.get(i);
            stats.sportStats[i] = new SportStat();
            stats.sportStats[i].name = s.name;
            stats.sportStats[i].code = s.code;
            stats.sportStats[i].nAccesses = 0;
            stats.sportStats[i].lastAccess = 0;
            stats.sportStats[i].lastIp = "";
        }
    }

}
