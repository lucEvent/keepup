package com.lucevent.keepup.server.utils;


import com.lucevent.keepup.data.util.Date;
import com.lucevent.keepup.data.util.LeagueTable;
import com.lucevent.keepup.data.util.LeagueTableRow;

import java.util.Comparator;

public class BackendParser {


    public static StringBuilder toHtml(Statistics stats, String options)
    {
        StringBuilder sb = new StringBuilder();

        StatisticsSet statisticsSet;

        if (options.contains("s")) {
            sb.append("\n # Statistics ordered by site name #\n\n");
            statisticsSet = new StatisticsSet(stats.getSportsStats(), new Comparator<SportStat>() {
                @Override
                public int compare(SportStat o1, SportStat o2)
                {
                    return o1.name.compareTo(o2.name);
                }
            });
            appendHtml(sb, statisticsSet);
        }

        if (options.contains("n")) {
            sb.append("\n # Statistics ordered by number of requests #\n\n");
            statisticsSet = new StatisticsSet(stats.getSportsStats(), new Comparator<SportStat>() {
                @Override
                public int compare(SportStat o1, SportStat o2)
                {
                    return o1.nAccesses < o2.nAccesses ? 1 : -1;
                }
            });
            appendHtml(sb, statisticsSet);
        }

        if (options.contains("t")) {
            sb.append("\n # Statistics ordered by last access time #\n\n");
            statisticsSet = new StatisticsSet(stats.getSportsStats(), new Comparator<SportStat>() {
                @Override
                public int compare(SportStat o1, SportStat o2)
                {
                    return o1.lastAccess < o2.lastAccess ? 1 : -1;
                }
            });
            appendHtml(sb, statisticsSet);
        }

        if (options.isEmpty()) {
            SportStat[] siteStats = stats.getSportsStats();
            for (SportStat ss : siteStats)
                if (ss.nAccesses != 0)
                    sb.append("\t").append(ss.name).append(": ").append(ss.nAccesses).append(" requests\n");
        }
        return sb;
    }

    private static String[] paddings = {"\t\t\t\t", "\t\t\t", "\t\t", "\t", ""};

    private static void appendHtml(StringBuilder sb, StatisticsSet statisticsSet)
    {
        for (SportStat ss : statisticsSet)
            if (ss.nAccesses > 0)
                sb.append("\t").append(ss.name).append(":").append(paddings[(ss.name.length() + 1) >> 3])
                        .append(ss.nAccesses).append(" requests\t[last ")
                        .append(Date.getAge(ss.lastAccess))
                        .append("]\t[from ").append(ss.lastIp).append("]\n");
    }

    private static void appendEntries(StringBuilder sb, StatisticsSet statisticsSet)
    {
        for (SportStat ss : statisticsSet)
            if (ss.nAccesses > 0)
                sb.append("<site code='").append(ss.code)
                        .append("' requests='").append(ss.nAccesses)
                        .append("' last='").append(ss.lastAccess)
                        .append("' ip='").append(ss.lastIp)
                        .append("'/>");
    }

    public static StringBuilder toEntry(Statistics stats, String options)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<statistics since='").append(stats.since)
                .append("' laststart='").append(stats.lastStart).append("'>");

        StatisticsSet statisticsSet = null;

        if (options.contains("s")) {
            statisticsSet = new StatisticsSet(stats.getSportsStats(), new Comparator<SportStat>() {
                @Override
                public int compare(SportStat o1, SportStat o2)
                {
                    return o1.name.compareTo(o2.name);
                }
            });
        } else if (options.contains("n")) {
            statisticsSet = new StatisticsSet(stats.getSportsStats(), new Comparator<SportStat>() {
                @Override
                public int compare(SportStat o1, SportStat o2)
                {
                    return o1.nAccesses < o2.nAccesses ? 1 : -1;
                }
            });
        } else if (options.contains("t")) {
            statisticsSet = new StatisticsSet(stats.getSportsStats(), new Comparator<SportStat>() {
                @Override
                public int compare(SportStat o1, SportStat o2)
                {
                    return o1.lastAccess < o2.lastAccess ? 1 : -1;
                }
            });
        }

        if (statisticsSet != null)
            appendEntries(sb, statisticsSet);

        sb.append("</statistics>");
        return sb;
    }


    public static StringBuilder toEntry(LeagueTable leagueTable)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<leaguetable n='").append(leagueTable.size()).append("'>");
        for (LeagueTableRow row : leagueTable)
            sb.append("<row>").append(row.wrap()).append("</row>");
        sb.append("</leaguetable>");
        return sb;
    }

}
