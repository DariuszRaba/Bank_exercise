package com.bank.exercise.service;

import com.bank.exercise.dto.UserDto;
import com.bank.exercise.exceptions.BadRequestException;
import com.bank.exercise.exceptions.UserCreationException;
import com.bank.exercise.fakeDB.BankDB;
import com.bank.exercise.model.User;
import com.bank.exercise.util.UserToUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {

    private BankDB bankDB;
    private UserToUserDTO userToUserDTO;


    public boolean createUser(String name, String sureName, String pesel) throws UserCreationException {
        if (checkIfUserOldEnough(pesel)) {
          return bankDB.addUser(new User(name, sureName, pesel));
        } else {
            throw new UserCreationException();
        }
    }

    public UserDto getUserInfo(String pesel) {
        User user = bankDB.getUser(pesel);
        return userToUserDTO.convert(user);
    }

    public boolean checkIfUserOldEnough(String pesel) {
        int OVERAGE = 18;
        try {
            LocalDate birthDate = prepareDateFromPesel(pesel);
            return Period.between(birthDate, LocalDate.now()).getYears() >= OVERAGE;
        } catch (ParseException | NumberFormatException e) {
            throw new BadRequestException();
        }
    }

    private LocalDate prepareDateFromPesel(String pesel) throws ParseException, NumberFormatException {
        String substring = pesel.substring(0, 6);
        String[] split = substring.split("(?<=\\G.{2})");
        int month = Integer.parseInt(split[1]);
        if (month <= 12) {
            Date birthDate = new SimpleDateFormat("yyMMdd").parse(substring);
            return LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault());
        }
        String birthMonth = "";
        if (month < 30) {
            birthMonth = "0" + (month - 20);
        }
        String birthDate = "20" + pesel.substring(0, 2) + (birthMonth) + pesel.substring(4, 6);
        Date birthDateAfter2000 = new SimpleDateFormat("yyyyMMdd").parse(birthDate);
        return LocalDate.ofInstant(birthDateAfter2000.toInstant(), ZoneId.systemDefault());
    }
}
