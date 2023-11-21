package com.christmas.service;


import com.christmas.domain.*;

public class PlannerService {
    public Price calculateDDayDiscount(MyRequirement requirement, December december) {
        ChristmasDiscountPolicy policy = new ChristmasDiscountPolicy();
        return requirement.calculateDiscountByPolicy(policy, december);
    }

    public Price calculateWeekDayDiscount(MyRequirement requirement, December december) {
        WeekDayDiscountPolicy policy = new WeekDayDiscountPolicy();
        return requirement.calculateDiscountByPolicy(policy, december);
    }

    public Price calculateWeekendDiscount(MyRequirement requirement, December december) {
        WeekEndDiscountPolicy policy = new WeekEndDiscountPolicy();
        return requirement.calculateDiscountByPolicy(policy, december);
    }

    public Price calculateSpecialDiscount(MyRequirement requirement, December december) {
        SpecialDiscountPolicy policy = new SpecialDiscountPolicy();
        return requirement.calculateDiscountByPolicy(policy, december);
    }

    public Price includeChampaigneGift(Price totalAmountBeforeDiscount) {
        if (totalAmountBeforeDiscount.isHigherThanPresentationThreshold()) {
            return Menu.getChamPaginePrice();
        }
        return new Price(0);
    }

    public Price calculateTotalDiscount(Price christmasEventDiscountAmount, Price weekDayDiscountAmount,
                                        Price weekEndDiscountAmount, Price specialDiscountAmount,
                                        Price champaigneAmount) {
        return new Price(christmasEventDiscountAmount.getPrice() + weekDayDiscountAmount.getPrice() +
                weekEndDiscountAmount.getPrice() + specialDiscountAmount.getPrice() + champaigneAmount.getPrice());
    }

    public Badge calculateBadge(Price totalDiscountAmount) {
        return Badge.calculateObatainableBadge(totalDiscountAmount);
    }
}
