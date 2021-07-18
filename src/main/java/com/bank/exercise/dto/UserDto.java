package com.bank.exercise.dto;

import com.bank.exercise.model.Account;
import com.bank.exercise.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class UserDto {

    private String name;
    private String sureName;
    private Currency currencyMainAccount;
    private BigDecimal userMainAccountAmount;
    private Account subAccountPLN;
    private Account subAccountUSD;

}
