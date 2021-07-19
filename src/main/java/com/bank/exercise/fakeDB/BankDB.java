package com.bank.exercise.fakeDB;

import com.bank.exercise.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class BankDB {
    private Map<String, User> userMap;

    public BankDB() {
        this.userMap = prepareFakeData();
    }

    private Map<String, User> prepareFakeData() {
        HashMap<String, User> fakeUsers = new HashMap<>();
        fakeUsers.put("90060804123", new User("Marian","Pazdzioch","90060804123"));
        fakeUsers.put("90060804124", new User("Marian","Pazdzioch","90060804124"));
        fakeUsers.put("90060804125", new User("Marian","Pazdzioch","90060804125"));
        fakeUsers.put("90060804126", new User("Marian","Pazdzioch","90060804126"));
        fakeUsers.put("90060804127", new User("Marian","Pazdzioch","90060804127"));
        fakeUsers.put("90060804128", new User("Marian","Pazdzioch","90060804128"));
        fakeUsers.put("90060804129", new User("Marian","Pazdzioch","90060804129"));
        fakeUsers.put("90060804110", new User("Marian","Pazdzioch","90060804110"));
        fakeUsers.forEach((k,v)-> v.getUserAccount().addAmount(new BigDecimal(100000L)));
        fakeUsers.forEach((k,v)-> v.getUserAccount().addToSubAccount(new BigDecimal(10000L)));
        return fakeUsers;
    }

    public long getUsersAmount(){
        return this.userMap.values().size();
    }

    public void addUser(User user) {
        this.userMap.put(user.getPesel(), user);
    }

    public User getUser(String pesel) {
        return this.userMap.get(pesel);
    }
}
