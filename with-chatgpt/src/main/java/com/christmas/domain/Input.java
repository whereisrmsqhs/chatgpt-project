package com.christmas.domain;

public class Input {
    private String order;
    private String date;

    public Input() {
    }

    public Input(String order, String date) {
        this.order = order;
        this.date = date;
    }

    public String getOrder() {
        return order;
    }

    public String getDate() {
        return date;
    }
}
