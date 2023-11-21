package com.christmas.controller;

import com.christmas.domain.*;
import com.christmas.service.PlannerService;
import com.christmas.validation.MyOrderValidation;
import com.christmas.validation.VisitDateValidation;
import com.christmas.view.View;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.christmas.constant.ErrorMessage.ERROR;

public class PlannerController {

    private final View view;
    private final PlannerService service;

    public PlannerController(View view, PlannerService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        Integer visitDate = receiveVisitingDate();
        MyOrder myOrder = receiveMenuOrders();
        MyRequirement myRequirement = new MyRequirement(myOrder, visitDate);
        Receipt myReceipt = calculateAllBenefits(myRequirement);
        previewAllBenefits(myReceipt);
    }

    private void previewAllBenefits(Receipt myReceipt) {
        view.printPreviewBenefits(myReceipt);
    }

    private Receipt calculateAllBenefits(MyRequirement requirement) {
        Price totalAmountBeforeDiscount = requirement.calculateTotalAmountBeforeDiscount();
        if (totalAmountBeforeDiscount.lessThanMinimumPrice()) {
            return noEventAppliedReceipt(requirement, totalAmountBeforeDiscount);
        }
        Result eachDiscountAmount = getEachDiscount(requirement, totalAmountBeforeDiscount);
        Price totalDiscountAmount = service.calculateTotalDiscount(eachDiscountAmount.christmasEventDiscountAmount(),
                eachDiscountAmount.weekDayDiscountAmount(),
                eachDiscountAmount.weekEndDiscountAmount(), eachDiscountAmount.specialDiscountAmount(),
                eachDiscountAmount.champaigneAmount());
        Badge eventBadge = service.calculateBadge(totalDiscountAmount);
        return new Receipt(requirement, totalAmountBeforeDiscount, eachDiscountAmount, totalDiscountAmount, eventBadge);
    }

    private Receipt noEventAppliedReceipt(MyRequirement requirement, Price totalAmountBeforeDiscount) {
        Price noEventDiscount = new Price(0);
        Result noDiscountResult = new Result(noEventDiscount, noEventDiscount, noEventDiscount
                , noEventDiscount, noEventDiscount);
        return new Receipt(requirement, totalAmountBeforeDiscount, noDiscountResult
                , noEventDiscount, Badge.NONE);
    }

    private Result getEachDiscount(MyRequirement requirement, Price totalAmountBeforeDiscount) {
        Price christmasEventDiscountAmount = service.calculateDDayDiscount(requirement, December.getInstance());
        Price weekDayDiscountAmount = service.calculateWeekDayDiscount(requirement, December.getInstance());
        Price weekEndDiscountAmount = service.calculateWeekendDiscount(requirement, December.getInstance());
        Price specialDiscountAmount = service.calculateSpecialDiscount(requirement, December.getInstance());
        Price champaigneAmount = service.includeChampaigneGift(totalAmountBeforeDiscount);
        return new Result(christmasEventDiscountAmount, weekDayDiscountAmount,
                weekEndDiscountAmount, specialDiscountAmount, champaigneAmount);
    }

    private MyOrder receiveMenuOrders() {
        boolean isError = true;
        String myOrderedFoods = null;
        MyOrder myOrder = null;
        view.printOrderMenuMessage();
        while (isError) {
            try {
                myOrderedFoods = view.receiveOrder();
                MyOrderValidation.validateOrderInfo(myOrderedFoods);
                myOrder = new MyOrder(myOrderedFoods);
                isError = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return myOrder;
    }

    private Integer receiveVisitingDate() {
        boolean isError = true;
        String visitDate = null;
        view.printIntroMessage();
        while (isError) {
            try {
                visitDate = view.receiveVisitDate();
                isError = VisitDateValidation.validateVisitDate(visitDate);
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR + e.getMessage());
            }
        }
        return Integer.parseInt(visitDate);
    }
}
