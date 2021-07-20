package com.bank.exercise.service;

import com.bank.exercise.dto.UserDto;
import com.bank.exercise.fakeDB.BankDB;
import com.bank.exercise.model.User;
import com.bank.exercise.util.UserToUserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    BankDB bankDB;
    @Mock
    UserToUserDTO userToUserDTO;

    @InjectMocks
    UserService userService;

    @Test
    public void Should_return_user_info(){
        //given
        User user = new User("Ferdynand", "Kiepski", "90060856123");
        UserDto userDto = new UserDto(user.getName(),
                user.getSureName(),
                user.getUserAccount().getCurrency(),
                user.getUserAccount().getAccAmount(),
                user.getUserAccount().getSubAccountPLN(),
                user.getUserAccount().getSubAccountUSD());
        when(bankDB.getUser("90060856123")).thenReturn(user);
        when(userToUserDTO.convert(user)).thenReturn(userDto);
        //when
        UserDto userInfo = userService.getUserInfo("90060856123");
        //then
        assertEquals(userInfo,userDto);
    }

    @Test
    public void should_check_if_user_under_age(){
        //given
        String underAgePesel = getUnderAgePesel();
        String overAgePesel = "93120178965";
        System.out.println(underAgePesel);
        //when
        boolean checkWrongPesel = userService.checkIfUserOldEnough(underAgePesel);
        boolean checkCorrectPesel = userService.checkIfUserOldEnough(overAgePesel);
        //then
        assertFalse(checkWrongPesel);
        assertTrue(checkCorrectPesel);
    }

    public static String getUnderAgePesel(){
        String pattern = "yyyy/MM/dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String currentDate = dateFormat.format(today);
        return currentDate.substring(2,4) + "210165785";
    }

}
