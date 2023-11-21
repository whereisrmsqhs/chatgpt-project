package com.christmas.view;

import com.christmas.domain.MyOrder;

import java.util.*;

import static com.christmas.constant.OutputviewMessages.*;

public class OutputView {
    public void printIntroMessage() {
        System.out.println(FIRST_GREETING);
        askReservationDay();
    }

    private void askReservationDay() {
        System.out.println(ASK_VISITING_DAY);
    }

    public void printMenuOrderMessage() {
        System.out.println(ORDER_MENU_GUIDE);
    }

    public void printPreviewBenefits(int day) {
        System.out.println(MONTH + day + DAY_UNIT + PREVIEW_EVENT);
    }

    public void printOrderedMenu(MyOrder myOrder) {
        System.out.println(ORDERED_MENU);
        System.out.println(myOrder);
    }

    public void printPriceBeforeDiscount(String totalAmountBeforeDiscount) {
        System.out.println(TOTAL_ORDER_AMOUNT_BEFORE_DISCOUNT);
        System.out.println(totalAmountBeforeDiscount + WON_UNIT + ENTER);
    }

    public void printGiftMenu(String gift) {
        System.out.println(GIFT_MENU);
        System.out.println(gift + ENTER);
    }

    public void printTotalBenefitsDetail(List<String> eachDiscountInfo) {
        System.out.println(BENEFITS_DETAIL);
        Map<String, String> eachBenefitMap = organizeEachDiscountInfo(eachDiscountInfo);
        if (eachBenefitMap.size() == 0) {
            System.out.println(NONE + ENTER);
            return;
        }
        for (String key : eachBenefitMap.keySet()) {
            System.out.println(key + MINUS + eachBenefitMap.get(key) + WON_UNIT);
        }
        System.out.print(ENTER);
    }

    private Map<String, String> organizeEachDiscountInfo(List<String> eachDiscountInfo) {
        Map<String, String> eachBenefitPrintMap = new LinkedHashMap<>();
        List<String> keyMessages = new LinkedList<>(Arrays.asList(CHRISTMAS_DDAY_DISCOUNT, WEEKDAY_DISCOUNT
                , WEEKEND_DISCOUNT, SPECIAL_DISCOUNT, GIFT_EVENT_DISCOUNT));
        for (int i = 0; i < 5; i++) {
            if (!eachDiscountInfo.get(i).equals("0")) {
                eachBenefitPrintMap.put(keyMessages.get(i), eachDiscountInfo.get(i));
            }
        }
        return eachBenefitPrintMap;
    }

    public void printTotalBenefit(String totalDiscountAmount) {
        System.out.println(TOTAL_BENEFIT_DETAIL);
        if (totalDiscountAmount.equals("0"))
            System.out.print(MINUS);
        System.out.println(totalDiscountAmount + WON_UNIT + ENTER);
    }

    public void printExpectedPayment(String expectedPayment) {
        System.out.println(EXPECTED_AMOUNT_OF_PAYMENT_AFTER_DISCOUNT);
        System.out.println(expectedPayment + WON_UNIT + ENTER);
    }

    public void printEventBadge(String badge) {
        System.out.println(EVENT_BADGE);
        System.out.println(badge);
    }
}
