package com.bank.exercise.model;


import com.bank.exercise.dto.CurrencyDTO;

import java.math.BigDecimal;

public class CurrencyExchanger {

    private CurrencyDTO currencyDTO;

    public CurrencyExchanger(CurrencyDTO currencyDTO) {
        this.currencyDTO = currencyDTO;
    }

    public BigDecimal convert(UserAccount account, BigDecimal amount, Currency convertTo) {
        BigDecimal convertedAmount = calculateAmount(amount, convertTo);
        switch (convertTo) {
            case PLN:
                if (account.getSubAccountUSD().withdrawAmount(amount)) {
                    account.getSubAccountPLN().addAmount(convertedAmount);
                    return convertedAmount;
                }
                break;
            case USD:
                if (account.getSubAccountPLN().withdrawAmount(amount)) {
                    account.getSubAccountUSD().addAmount(convertedAmount);
                    return convertedAmount;
                }
                break;
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateAmount(BigDecimal amount, Currency convertTo) {
        return convertTo.equals(Currency.PLN) ? amount.multiply(BigDecimal.valueOf(Long.parseLong(this.currencyDTO.getRates().get(0).bid))) :
                amount.divide(BigDecimal.valueOf(Long.parseLong(this.currencyDTO.getRates().get(0).ask)));
    }
}
