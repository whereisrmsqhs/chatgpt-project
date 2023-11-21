package com.christmas.domain;

public enum Badge {
    NONE("없음", new Price(0)),
    STAR("별", new Price(5000)),
    TREE("트리", new Price(10000)),
    SANTA("산타", new Price(20000));

    private String badgeName;
    private Price acquiredCondition;

    Badge(String badgeName, Price acquiredCondition) {
        this.badgeName = badgeName;
        this.acquiredCondition = acquiredCondition;
    }

    public static Badge calculateObatainableBadge(Price totalDiscountAmount) {
        Badge tobeReturned = NONE;
        for (Badge badge : values()) {
            if (totalDiscountAmount.getPrice() > badge.getAcquiredPrice()) {
                tobeReturned = badge;
            }
        }
        return tobeReturned;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public Integer getAcquiredPrice() {
        return acquiredCondition.getPrice();
    }
}
