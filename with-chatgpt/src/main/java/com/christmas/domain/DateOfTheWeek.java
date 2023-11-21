package com.christmas.domain;

import com.christmas.constant.WeekDaysAndWeekEnds;

import java.util.Arrays;

import static com.christmas.constant.WeekDaysAndWeekEnds.*;

public enum DateOfTheWeek {
    MONDAY(1, "월료일", WEEK_DAYS),
    TUESDAY(2, "화요일", WEEK_DAYS),
    WEDNESDAY(3, "수요일", WEEK_DAYS),
    THURSDAY(4, "목요일", WEEK_DAYS),
    FRIDAY(5, "금요일", WEEK_ENDS),
    SATURDAY(6, "토요일", WEEK_ENDS),
    SUNDAY(7, "일요일", WEEK_DAYS);

    private final Integer index;
    private final String dayOfTheWeek;
    private final WeekDaysAndWeekEnds weekdayType;

    DateOfTheWeek(Integer index, String dayOfTheWeek, WeekDaysAndWeekEnds weekdayType) {
        this.index = index;
        this.dayOfTheWeek = dayOfTheWeek;
        this.weekdayType = weekdayType;
    }

    public Integer getIndex() {
        return index;
    }

    public boolean isSpecialDay(Integer day) {
        return (this.dayOfTheWeek.equals("일요일") || day == 25);
    }

    public DateOfTheWeek calculateNextDateOfTheWeek() {
        int tempNextIndex = index + 1;
        if (index + 1 == 8) tempNextIndex = 1;
        Integer nextIndex = tempNextIndex;

        return Arrays.stream(DateOfTheWeek.values())
                .filter(date -> date.getIndex().equals(nextIndex))
                .findFirst()
                .orElse(null);
    }

    public boolean isWeekDay() {
        return weekdayType.equals(WEEK_DAYS);
    }

    public boolean isWeekEnd() {
        return weekdayType.equals(WEEK_ENDS);
    }
}
