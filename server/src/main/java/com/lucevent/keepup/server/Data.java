package com.lucevent.keepup.server;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import com.lucevent.keepup.data.Sports;
import com.lucevent.keepup.data.util.Date;
import com.lucevent.keepup.server.utils.Statistics;

public class Data {

    public static Sports sports;

    public static Statistics stats;

    public Data()
    {
        if (sports == null) {
            Date.setTitles(new String[]{"%d seconds ago", "%d minutes ago", "%d hours ago", "%d days ago", "%d months ago", "%d years ago",});

            sports = new Sports(new String[]{"", "", "", "", ""});

            ObjectifyService.run(new VoidWork() {
                public void vrun()
                {
                    stats = Statistics.initialize(sports);
                }
            });

        }
    }

}
