package com.christmas.domain;

public interface DiscountPolicy {
    Price discount(MyOrder myOrder, Integer visitDate, December december);
}
