package com.christmas.domain;

public record MyRequirement(MyOrder myOrder, Integer visitDate) {

    public Price calculateTotalAmountBeforeDiscount() {
        return myOrder.calculateTotalAmountBeforeDiscount();
    }

    public Price calculateDiscountByPolicy(DiscountPolicy discountPolicy, December december) {
        return discountPolicy.discount(myOrder, visitDate, december);
    }
}
