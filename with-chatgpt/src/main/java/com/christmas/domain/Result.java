package com.christmas.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public record Result(Price christmasEventDiscountAmount, Price weekDayDiscountAmount, Price weekEndDiscountAmount,
                     Price specialDiscountAmount, Price champaigneAmount) {
    public boolean receiveChampaigneGift() {
        return champaigneAmount.getPrice() != 0;
    }

    private List<Price> getAllFields() {
        List<Price> allDiscountAmounts = new LinkedList<>();
        allDiscountAmounts.add(christmasEventDiscountAmount);
        allDiscountAmounts.add(weekDayDiscountAmount);
        allDiscountAmounts.add(weekEndDiscountAmount);
        allDiscountAmounts.add(specialDiscountAmount);
        allDiscountAmounts.add(champaigneAmount);
        return allDiscountAmounts;
    }

    public List<String> bringDiscountDetails() {
        List<Price> priceList = getAllFields();
        return priceList.stream()
                .map(Price::convertToOutputType)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
