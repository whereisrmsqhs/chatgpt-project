package com.christmas.domain;

public class WeekDayDiscountPolicy implements DiscountPolicy {
    @Override
    public Price discount(MyOrder myOrder, Integer visitDate, December december) {
        Date lookedForDate = december.findSpecificDate(visitDate);
        if (lookedForDate.isWeekDay()) {
            return myOrder.calculateDiscountInWeekDay();
        }
        return new Price(0);
    }
}
