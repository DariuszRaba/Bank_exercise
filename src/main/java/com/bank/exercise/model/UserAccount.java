package com.bank.exercise.model;

import java.math.BigDecimal;

public class UserAccount extends Account {
    private final Account subAccountPLN;
    private final Account subAccountUSD;

    public UserAccount() {
        this.currency = Currency.PLN;
        this.subAccountPLN = new SubAccount(Currency.PLN);
        this.subAccountUSD = new SubAccount(Currency.USD);
    }

    public boolean addToSubAccount(BigDecimal amount) {
        if(this.accAmount.compareTo(amount) < 0) return false;
        this.accAmount = accAmount.subtract(amount);
        this.subAccountPLN.accAmount = subAccountPLN.accAmount.add(amount);
        return true;
    }

    public boolean withdrawFromSubAccount(BigDecimal amount) {
        if (this.subAccountPLN.accAmount.compareTo(amount) < 0) {
            return false;
        }
        this.subAccountPLN.withdrawAmount(amount);
        addAmount(amount);
        return true;
    }



    public Account getSubAccountPLN() {
        return subAccountPLN;
    }

    public Account getSubAccountUSD() {
        return subAccountUSD;
    }
}
