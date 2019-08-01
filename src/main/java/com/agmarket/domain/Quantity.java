package com.agmarket.domain;

public class Quantity {
    private double amount;
    private Unit unit;

    public Quantity() {
    }

    public Quantity(double amount, Unit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
