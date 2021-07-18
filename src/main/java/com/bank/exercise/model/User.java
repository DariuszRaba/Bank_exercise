package com.bank.exercise.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor


public class User {
    private String name;
    private String sureName;
    private String pesel;
    private final UserAccount userAccount = new UserAccount();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }
}
