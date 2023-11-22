package com.christmas.domain;


import java.util.List;

import static com.christmas.constant.NumbersOrSymbols.GIFT_PRESENTED;
import static com.christmas.constant.NumbersOrSymbols.NO_GIFT;

public class Receipt {

    private final MyRequirement requirement;
    private final Price totalAmountBeforeDiscount;
    private final Result eachDiscountAmount;
    private final Price totalDiscountAmount;
    private final Badge eventBadge;

    public Receipt(MyRequirement requirement, Price totalAmountBeforeDiscount,
                   Result eachDiscountAmount, Price totalDiscountAmount, Badge eventBadge) {
        this.requirement = requirement;

        this.totalAmountBeforeDiscount = totalAmountBeforeDiscount;
        this.eachDiscountAmount = eachDiscountAmount;
        this.totalDiscountAmount = totalDiscountAmount;
        this.eventBadge = eventBadge;
    }

    public int visitDate() {
        return requirement.visitDate();
    }

    public MyOrder getMyOrder() {
        return requirement.myOrder();
    }

    public String printingTotalAmountBeforeDiscount() {
        return totalAmountBeforeDiscount.convertToOutputType();
    }

    public String isThereChampagineGift() {
        if (eachDiscountAmount.receiveChampaigneGift()) {
            return GIFT_PRESENTED;
        }
        return NO_GIFT;
    }

    public List<String> bringEachDiscountInfo() {
        return eachDiscountAmount.bringDiscountDetails();
    }

    public String getTotalDiscountAmount() {
        return totalDiscountAmount.convertToOutputType();
    }

    public String getExpectedPayment() {
        Price totalAmountIncludeGift = totalAmountBeforeDiscount.difference(totalDiscountAmount);
        return totalAmountIncludeGift.add(eachDiscountAmount.champaigneAmount());
    }

    public String getEventBadge() {
        return eventBadge.getBadgeName();
    }

    public MyRequirement getRequirement() {
        return requirement;
    }

    public Price getTotalAmountBeforeDiscount() {
        return totalAmountBeforeDiscount;
    }

    public Result getEachDiscountAmount() {
        return eachDiscountAmount;
    }
}
