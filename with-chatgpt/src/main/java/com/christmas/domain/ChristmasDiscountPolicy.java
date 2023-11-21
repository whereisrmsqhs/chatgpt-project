package com.christmas.domain;

import static com.christmas.constant.NumbersOrSymbols.EACH_DAY_DISCOUNT_UNIT;
import static com.christmas.constant.NumbersOrSymbols.MAX_CHRISTMAS_D_DAY_DISCOUNT;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    @Override
    public Price discount(MyOrder myOrder, Integer visitDate, December december) {
        Date lookedForDate = december.findSpecificDate(visitDate);
        if (lookedForDate.isApplicableChristmasDiscount()) {
            return new Price(MAX_CHRISTMAS_D_DAY_DISCOUNT - lookedForDate.dateFarFromChristmas()
                    * EACH_DAY_DISCOUNT_UNIT);
        }
        return new Price(0);
    }
}
