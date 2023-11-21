package com.christmas.domain;

import java.util.ArrayList;
import java.util.List;

import static com.christmas.domain.DateOfTheWeek.FRIDAY;

public class December {
    private static December instance;
    private static final List<Date> allDates = new ArrayList<>();

    private December() {
        create31Days(FRIDAY);
    }

    public static December getInstance() {
        instance = new December();
        return instance;
    }

    private void create31Days(DateOfTheWeek dateOfTheWeek) {
        DateOfTheWeek currentDateOfTheWeek = dateOfTheWeek;
        for (int i = 1; i <= 31; i++) {
            allDates.add(new Date(currentDateOfTheWeek, i));
            currentDateOfTheWeek = currentDateOfTheWeek.calculateNextDateOfTheWeek();
        }
    }

    public Date findSpecificDate(Integer visitDate) {
        return allDates.get(visitDate - 1);
    }
}
