package com.christmas.domain;

public class WeekEndDiscountPolicy implements DiscountPolicy {
    @Override
    public Price discount(MyOrder myOrder, Integer visitDate, December december) {
        Date lookedForDate = december.findSpecificDate(visitDate);
        if (lookedForDate.isWeekEnd()) {
            return myOrder.calculateDiscountInWeekEnd();
        }
        return new Price(0);
    }
}
