package com.bank.exercise.controller;

import com.bank.exercise.dto.UserDto;
import com.bank.exercise.exceptions.UserCreationException;
import com.bank.exercise.service.SubAccService;
import com.bank.exercise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private SubAccService subAccService;

    @PostMapping("/create/{name}/{sureName}/{pesel}")
    @ResponseStatus(CREATED)
    public void createUser(@PathVariable("name") String name,
                           @PathVariable("sureName") String sureName,
                           @PathVariable("pesel") String pesel) throws UserCreationException {
        userService.createUser(name, sureName, pesel);
    }

    @GetMapping(value = "/info/{pesel}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserInfo(@PathVariable("pesel") String pesel){
        UserDto userInfo = userService.getUserInfo(pesel);
        return userInfo;
    }

    @PutMapping(value = "/convert/{pesel}/{amount}/{from}/{convertTo}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal convertTo(@PathVariable("pesel") String pesel,
                                @PathVariable("amount") String amount,
                                @PathVariable("from") String from,
                                @PathVariable("convertTo") String convertTo){
        return subAccService.convertMoney(pesel, amount, from, convertTo);
    }
}
