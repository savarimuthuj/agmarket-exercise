package com.agmarket.domain;

public enum Unit {
    KILO_GRAM("kg");
    private String code;

    Unit(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.getCode();
    }
}
