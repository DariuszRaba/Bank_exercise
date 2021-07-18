package com.bank.exercise.service;

import com.bank.exercise.dto.UserDto;
import com.bank.exercise.model.Currency;
import com.bank.exercise.model.CurrencyExchanger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class SubAccService {
    private CurrencyExchanger currencyExchanger;
    private UserService userService;


    public BigDecimal convertMoney(String pesel, String amount, String from, String convertTo) {

        UserDto userInfo = userService.getUserInfo(pesel);
        return from.equals(convertTo) ? BigDecimal.ZERO : currencyExchanger.convert(userInfo.getSubAccountUSD(), userInfo.getSubAccountPLN(), BigDecimal.valueOf(Long.parseLong(amount)), Currency.valueOf(convertTo.toUpperCase()));
    }

}
