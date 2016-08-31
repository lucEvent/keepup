package com.lucevent.keepup.server.utils;

import com.googlecode.objectify.annotation.Id;

public class SportStat {

    @Id
    public long code;

    public String name;

    public int nAccesses;

    public long lastAccess;

    public String lastIp;

    public SportStat()
    {
    }

}
