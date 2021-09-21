package com.bank.exercise.controller;

import com.bank.exercise.dto.UserDto;
import com.bank.exercise.exceptions.UserCreationException;
import com.bank.exercise.model.UserCreationForm;
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

    @PostMapping("/create/")
    @ResponseStatus(CREATED)
    public void createUser(@RequestBody UserCreationForm userData) throws UserCreationException {
        userService.createUser(userData);
    }

    @GetMapping(value = "/info/{pesel}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserInfo(@PathVariable("pesel") String pesel) {
        UserDto userInfo = userService.getUserInfo(pesel);
        return userInfo;
    }

    @PutMapping(value = "/convert/{pesel}/{amount}/{from}/{convertTo}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal convertTo(@PathVariable("pesel") String pesel,
                                @PathVariable("amount") String amount,
                                @PathVariable("from") String from,
                                @PathVariable("convertTo") String convertTo) {
        return subAccService.convertMoney(pesel, amount, from, convertTo);
    }
}
