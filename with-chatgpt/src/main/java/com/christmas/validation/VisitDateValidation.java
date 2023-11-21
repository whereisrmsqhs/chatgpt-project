package com.christmas.validation;

import static com.christmas.constant.ErrorMessage.INVALID_DAY_INPUT;

public class VisitDateValidation {

    private VisitDateValidation() {}

    public static boolean validateVisitDate(String visitDate) {
        int date;
        try {
            date = Integer.parseInt(visitDate);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(INVALID_DAY_INPUT);
        }
        if (date < 1 || date > 32) {
            throw new IllegalArgumentException(INVALID_DAY_INPUT);
        }
        return false;
    }
}
