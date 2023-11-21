package com.christmas.view;

import com.christmas.domain.Receipt;

public class View {
    private final InputView inputView;
    private final OutputView outputView;

    public View(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void printIntroMessage() {
        outputView.printIntroMessage();
    }

    public String receiveVisitDate() {
        return inputView.receiveInput();
    }

    public void printOrderMenuMessage() {
        outputView.printMenuOrderMessage();
    }

    public String receiveOrder() {
        return inputView.receiveInput();
    }

    public void printPreviewBenefits(Receipt receipt) {
        outputView.printPreviewBenefits(receipt.visitDate());
        outputView.printOrderedMenu(receipt.getMyOrder());
        outputView.printPriceBeforeDiscount(receipt.printingTotalAmountBeforeDiscount());
        outputView.printGiftMenu(receipt.isThereChampagineGift());
        outputView.printTotalBenefitsDetail(receipt.bringEachDiscountInfo());
        outputView.printTotalBenefit(receipt.getTotalDiscountAmount());
        outputView.printExpectedPayment(receipt.getExpectedPayment());
        outputView.printEventBadge(receipt.getEventBadge());
    }
}
