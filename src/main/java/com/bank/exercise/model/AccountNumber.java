package com.bank.exercise.model;


public class AccountNumber {
    private static Long number = 0L;


    public static Long generateAccountNumber(){
        number++;
        return number;
    }


}
