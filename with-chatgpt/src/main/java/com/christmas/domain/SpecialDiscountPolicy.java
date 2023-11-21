package com.christmas.domain;

import static com.christmas.constant.NumbersOrSymbols.SPECIAL_DISCOUNT_AMOUNT;

public class SpecialDiscountPolicy implements DiscountPolicy {

    @Override
    public Price discount(MyOrder myOrder, Integer visitDate, December december) {
        Date lookedForDate = december.findSpecificDate(visitDate);
        if (lookedForDate.isSpecial()) {
            return new Price(SPECIAL_DISCOUNT_AMOUNT);
        }
        return new Price(0);
    }
}
