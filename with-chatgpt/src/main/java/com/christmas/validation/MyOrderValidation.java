package com.christmas.validation;

import com.christmas.domain.Menu;

import java.util.Map;
import java.util.regex.Pattern;

import static com.christmas.constant.ErrorMessage.ERROR;
import static com.christmas.constant.ErrorMessage.INVALID_INPUT;

public class MyOrderValidation {

    private MyOrderValidation() {}
    public static void validateOrderInfo(String myOrderedFoods) {
        String pattern = "([가-힣]+-\\d+)(,([가-힣]+-\\d+))*";
        boolean isMatch = Pattern.matches(pattern, myOrderedFoods);
        if (isMatch == false)
            throw new IllegalArgumentException(ERROR + INVALID_INPUT);
    }

    public static void validateFood(Menu myOrderedFood, Map<Menu, Integer> myAllOrderedMenus) {
        if (myOrderedFood == null) throw new IllegalArgumentException(ERROR + INVALID_INPUT);
        if (myAllOrderedMenus == null) return;
        if ((myAllOrderedMenus.get(myOrderedFood) != null)) {
            throw new IllegalArgumentException(ERROR + INVALID_INPUT);
        }
    }

    public static void validateOrderNumber(Integer myOrderFoodNumber) {
        if (myOrderFoodNumber < 1)
            throw new IllegalArgumentException(ERROR + INVALID_INPUT);
    }
}
