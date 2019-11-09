package com.example.securevotes;

import java.util.Date;

public class Election {
    String name;
    int year;
    Date startDate;
    Date endDate;

    public Election( String name, int year, Date startDate, Date endDate) {
        this.name = name;
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
