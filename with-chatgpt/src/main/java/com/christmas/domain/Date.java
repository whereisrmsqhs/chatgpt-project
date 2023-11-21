package com.christmas.domain;

public class Date {
    private final DateOfTheWeek dateOfTheWeek;
    private final Integer day;
    private final boolean specialDiscount;

    public Date(DateOfTheWeek dateOfTheWeek, Integer day) {
        this.dateOfTheWeek = dateOfTheWeek;
        this.day = day;
        specialDiscount = setSpecialDiscount();
    }

    private boolean setSpecialDiscount() {
        return (dateOfTheWeek.isSpecialDay(day));
    }

    public boolean isApplicableChristmasDiscount() {
        return (day >= 1 && day <= 25);
    }

    public Integer dateFarFromChristmas() {
        return 25 - day;
    }

    public boolean isWeekDay() {
        return dateOfTheWeek.isWeekDay();
    }

    public boolean isWeekEnd() {
        return dateOfTheWeek.isWeekEnd();
    }

    public boolean isSpecial() {
        return specialDiscount;
    }
}
