package com.christmas.domain;

import com.christmas.constant.MenuCategory;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

import static com.christmas.constant.MenuCategory.*;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", new Price(6000), APPETIZER),
    TAPAS("타파스", new Price(5500), APPETIZER),
    CAESAR_SALAD("시저샐러드", new Price(8000), APPETIZER),
    T_BONE_STEAK("티본스테이크", new Price(55000), MAIN),
    BBQ_RIBS("바비큐립", new Price(54000), MAIN),
    SEAFOOD_PASTA("해산물파스타", new Price(35000), MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", new Price(25000), MAIN),
    CHOCOLATE_CAKE("초코케이크", new Price(15000), DESSERT),
    ICE_CREAM("아이스크림", new Price(5000), DESSERT),
    ZERO_COKE("제로콜라", new Price(3000), DRINK),
    RED_WINE("레드와인", new Price(60000), DRINK),
    CHAMPAGNE("샴페인", new Price(25000), DRINK);

    private final String name;
    private final Price price;
    private final MenuCategory type;

    Menu(String name, Price price, MenuCategory type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public static Menu findMyMenu(String myOrderedFood) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(myOrderedFood))
                .findFirst()
                .orElse(null);
    }

    public Integer calculateEachMenuAmount(Integer orderNum) {
        return price.multiply(orderNum);
    }

    public boolean isDessert() {
        return (type.equals(DESSERT));
    }

    public boolean isMain() {
        return (type.equals(MAIN));
    }

    public static Price getChamPaginePrice() {
        return CHAMPAGNE.price;
    }

    public boolean isNotDrink() {
        return !type.equals(DRINK);
    }
}
