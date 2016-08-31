package com.lucevent.keepup.net;

import android.os.Handler;

public class StatisticsReader {

    public static void fetchStatistics(final Handler handler, final String url)
    {
   /*     new Thread(new Runnable() {
            @Override
            public void run()
            {
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    AppSettings.printerror("[SR] Problem fetching Statistics:" + url, e);
                }
                if (doc == null) return;

                Element stats = doc.select("statistics").get(0);
                long since = Long.parseLong(stats.attr("since"));
                long lastStart = Long.parseLong(stats.attr("laststart"));
                SiteStats siteStats = new SiteStats();

                for (Element siteData : stats.select("site")) {
                    int siteCode = Integer.parseInt(siteData.attr("code"));
                    Site site = AppData.getSiteByCode(siteCode);
                    String siteName = site == null ? "Unknown" : site.name;
                    int nAccesses = Integer.parseInt(siteData.attr("requests"));
                    long lastAccess = Long.parseLong(siteData.attr("last"));
                    String lastIp = siteData.attr("ip");
                    siteStats.add(new SiteStat(siteName, siteCode, nAccesses, lastAccess, lastIp));
                }

                Statistics statistics = new Statistics(since, lastStart, siteStats);
                handler.obtainMessage(AppCode.STATISTICS, statistics).sendToTarget();

            }
        }).start();*/
    }

}
