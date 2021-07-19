package com.bank.exercise;

import com.bank.exercise.model.Account;
import com.bank.exercise.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
public class UserAccountTest {

    private final BigDecimal depositAmount = new BigDecimal(100);
    private final BigDecimal withdrawalAmount = new BigDecimal(50);
    private final BigDecimal negativeAmount = new BigDecimal(-50);

    @Test
    public void should_add_to_account(){
        //given
        Account userAccount = new UserAccount();
        //when
         userAccount.addAmount(depositAmount);
        //then
        assertEquals(depositAmount, userAccount.getAccAmount());
    }

    @Test
    public void should_withdraw_amount_from_account(){
        //given
        Account userAccount = prepareAccountWithBalance();
        BigDecimal balance = userAccount.getAccAmount();
        //when
        userAccount.withdrawAmount(withdrawalAmount);
        //then
        assertEquals(balance.subtract(withdrawalAmount), userAccount.getAccAmount());
    }

    @Test
    public void should_not_withdraw_when_withdrawal_amount_bigger_then_balance(){
        //given
        Account userAccount = prepareAccountWithBalance();
        BigDecimal balanceBeforeTransaction = userAccount.getAccAmount();
        BigDecimal bigWithdrawalAmount = new BigDecimal(200);
        //when
            userAccount.withdrawAmount(bigWithdrawalAmount);
        //then
        assertEquals(balanceBeforeTransaction, userAccount.getAccAmount());
    }

    @Test
    public void should_add_amount_even_when_amount_negative(){
        //given
        Account userAccount = prepareAccountWithBalance();
        BigDecimal balanceBeforeTransaction = userAccount.getAccAmount();
        //when
        userAccount.addAmount(negativeAmount);
        //then
        assertEquals(balanceBeforeTransaction.add(new BigDecimal(50)),userAccount.getAccAmount());
    }

    @Test
    public void should_withdraw_amount_even_when_widrawal_amount_negative(){
        //given
        Account userAccount = prepareAccountWithBalance();
        BigDecimal balanceBeforeTransaction = userAccount.getAccAmount();
        //when
        userAccount.withdrawAmount(negativeAmount);
        //then
        assertEquals(balanceBeforeTransaction.subtract(new BigDecimal(50)),userAccount.getAccAmount());
    }
    @Test
    public void should_add_amount_to_subAccount(){
        //given
        UserAccount userAccount = new UserAccount();
        userAccount.addAmount(depositAmount);
        BigDecimal balanceBeforeTransaction = userAccount.getSubAccountPLN().getAccAmount();
        //when
        userAccount.addToSubAccount(depositAmount);
        //then
        assertEquals(balanceBeforeTransaction.add(depositAmount),userAccount.getSubAccountPLN().getAccAmount());
    }
    @Test
    public void should_not_withdraw_from_subAcc_when_not_enough_balance(){
        //given
        UserAccount userAccount = new UserAccount();
        BigDecimal balanceBeforeTransaction = userAccount.getAccAmount();
        //when
        userAccount.withdrawFromSubAccount(withdrawalAmount);
        //then
        assertEquals(balanceBeforeTransaction, userAccount.getAccAmount());
    }

    private Account prepareAccountWithBalance(){
        Account userAccount = new UserAccount();
        userAccount.addAmount(new BigDecimal(100));
        return userAccount;
    }
}
