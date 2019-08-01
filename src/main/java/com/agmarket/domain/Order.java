package com.agmarket.domain;

import org.joda.money.Money;

import java.util.Objects;

public class Order {
    private Long orderId;
    private String userId;
    private Quantity quantity;
    private Money price;
    private OrderType orderType;

    public Order() {
    }

    public Order(Long orderId, String userId, Quantity quantity, Money price, OrderType orderType) {
        this.orderId = orderId;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @Override
    public boolean equals(Object thatObject) {
        if (this == thatObject) return true;
        if (thatObject == null || getClass() != thatObject.getClass()) return false;
        Order thatOrder = (Order) thatObject;
        return orderId.equals(thatOrder.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
