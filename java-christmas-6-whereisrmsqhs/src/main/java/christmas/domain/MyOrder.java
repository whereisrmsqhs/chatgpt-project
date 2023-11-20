package christmas.domain;

import christmas.validation.MyOrderValidation;

import java.util.EnumMap;
import java.util.Map;

import static christmas.constant.ErrorMessage.*;
import static christmas.constant.NumbersOrSymbols.*;
import static christmas.domain.Menu.findMyMenu;

public class MyOrder {

    private static Map<Menu, Integer> myAllOrderedMenus = null;

    public MyOrder(String myOrderedMenus) {
        myAllOrderedMenus = new EnumMap<>(Menu.class);
        try {
            saveMyOrders(myOrderedMenus);
            validateIsAllDrinks();
            validateTotalNumberOfFood();
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private void validateTotalNumberOfFood() {
        Integer numberOfMenus = 0;
        for (Integer value : myAllOrderedMenus.values()) {
            numberOfMenus += value;
        }
        if (numberOfMenus >= 21)
            throw new IllegalArgumentException(ERROR + INVALID_INPUT);
    }

    private void validateIsAllDrinks() {
        boolean isNonDrinkMenu = false;
        for (Menu menu : myAllOrderedMenus.keySet()) {
            if (menu.isNotDrink()) {
                isNonDrinkMenu = true;
                return;
            }
        }
        throw new IllegalArgumentException(ERROR + ONLY_DRINKS);
    }

    private void saveMyOrders(String myOrderedMenus) {
        String[] eachFoodOrder = myOrderedMenus.split(DIVIDE_EACH_ORDERED_MENU);
        for (String eachOrder : eachFoodOrder) {
            int dashIndex = eachOrder.indexOf(DIVIDE_FOOD_NUMBER);
            try {
                Menu myOrderedFood = findMyMenu(eachOrder.substring(0, dashIndex));
                MyOrderValidation.validateFood(myOrderedFood, myAllOrderedMenus);
                Integer myOrderFoodNumber = Integer.valueOf(eachOrder.substring(dashIndex + 1));
                MyOrderValidation.validateOrderNumber(myOrderFoodNumber);
                myAllOrderedMenus.put(myOrderedFood, myOrderFoodNumber);
            } catch (IllegalArgumentException e) {
                throw e;
            }
        }
    }

    public Price calculateTotalAmountBeforeDiscount() {
        Integer totalAmount = 0;
        for (Menu menu : myAllOrderedMenus.keySet()) {
            totalAmount += menu.calculateEachMenuAmount(myAllOrderedMenus.get(menu));
        }
        return new Price(totalAmount);
    }

    public Price calculateDiscountInWeekDay() {
        Integer totalDiscount = 0;
        for (Menu menu : myAllOrderedMenus.keySet()) {
            if (menu.isDessert()) {
                totalDiscount += WEEKDAY_WEEKEND_EVENT_PER_DISCOUNT * myAllOrderedMenus.get(menu);
            }
        }
        return new Price(totalDiscount);
    }

    public Price calculateDiscountInWeekEnd() {
        Integer totalDiscount = 0;
        for (Menu menu : myAllOrderedMenus.keySet()) {
            if (menu.isMain()) {
                totalDiscount += WEEKDAY_WEEKEND_EVENT_PER_DISCOUNT * myAllOrderedMenus.get(menu);
            }
        }
        return new Price(totalDiscount);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Menu menu : myAllOrderedMenus.keySet()) {
            stringBuilder.append(menu.getName())
                    .append(" ")
                    .append(myAllOrderedMenus.get(menu) + UNIT)
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
