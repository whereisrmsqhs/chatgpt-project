package com.christmas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.christmas.constant.NumbersOrSymbols.PRESERNTATION_THRESHOLD;

public class Price {
    private final Integer price;

    public Price(Integer price) {
        this.price = price;
    }

    public String convertToOutputType() {
        String toConvertedPrice = String.valueOf(price);
        StringBuilder toParsedPrice = new StringBuilder();

        for (int i = toConvertedPrice.length() - 1; i >= 0; i--) {
            toParsedPrice.append(toConvertedPrice.charAt(i));
            if (i >= 1 && (toConvertedPrice.length() - i) % 3 == 0) {
                toParsedPrice.append(",");
            }
        }
        return toParsedPrice.reverse().toString();
    }

    public Integer multiply(Integer multiplicand) {
        return price * multiplicand;
    }

    @JsonIgnore
    public boolean isHigherThanPresentationThreshold() {
        return (price >= PRESERNTATION_THRESHOLD);
    }

    public Integer getPrice() {
        return price;
    }

    public boolean checkDiscountExist() {
        return price != 0;
    }

    public Price difference(Price totalDiscountAmount) {
        return new Price(price - totalDiscountAmount.getPrice());
    }

    public String add(Price toBeAdd) {
        Price totalPayment = new Price(price + toBeAdd.getPrice());
        return totalPayment.convertToOutputType();
    }

    public boolean lessThanMinimumPrice() {
        return price < 10000;
    }
}
