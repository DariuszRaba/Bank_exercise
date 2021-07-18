package com.bank.exercise.fakeDB;

import com.bank.exercise.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class BankDB {
    private List<User> userList = new ArrayList<>();
    private Map<String,User> userMap = new HashMap<>();




    public void addUser(User user){
        this.userMap.put(user.getPesel(),user);
    }
    public User getUser(String pesel){
        return this.userMap.get(pesel);
    }

//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }
//
//    public Map<String, User> getUserMap() {
//        return userMap;
//    }
//
//    public void setUserMap(Map<String, User> userMap) {
//        this.userMap = userMap;
//    }
}
