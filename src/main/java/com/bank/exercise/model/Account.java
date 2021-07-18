package com.bank.exercise.model;

import java.math.BigDecimal;

public abstract class Account {

    protected final Long accountNumber;
    protected Currency currency;
    protected BigDecimal accAmount;


    protected Account() {
        this.accountNumber = AccountNumber.generateAccountNumber();
        this.accAmount = BigDecimal.ZERO;
    }

     public boolean addAmount(BigDecimal amount){
        this.accAmount = accAmount.add(amount);
        return true;
    }

    protected boolean withdrawAmount(BigDecimal amount){
        if (checkBalance(amount)) {
            this.accAmount = accAmount.subtract(amount);
            return true;
        }
        return false;
    }

    protected boolean checkBalance(BigDecimal amount){
        return this.accAmount.compareTo(amount) >= 0;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAccAmount() {
        return accAmount;
    }
}
