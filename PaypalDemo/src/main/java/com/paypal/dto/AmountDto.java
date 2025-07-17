package com.paypal.dto;

import java.math.BigDecimal;

public class AmountDto {

    private BigDecimal amount;
    private String currency;

    public AmountDto() {
        // Default constructor
    }

    public AmountDto(BigDecimal amount, String currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency must not be blank.");
        }
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency must not be blank.");
        }
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "AmountDto{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
