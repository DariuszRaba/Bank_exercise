package com.bank.exercise.fakeDB;

import com.bank.exercise.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BankDB {
    private Map<String, User> userMap = new HashMap<>();

    public void addUser(User user) {
        this.userMap.put(user.getPesel(), user);
    }

    public User getUser(String pesel) {
        return this.userMap.get(pesel);
    }
}
