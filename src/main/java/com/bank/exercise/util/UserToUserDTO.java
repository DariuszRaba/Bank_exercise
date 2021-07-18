package com.bank.exercise.util;

import com.bank.exercise.dto.UserDto;
import com.bank.exercise.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTO {


    public UserDto convert(User user) {
        return new UserDto(user.getName(),
                user.getSureName(),
                user.getUserAccount().getCurrency(),
                user.getUserAccount().getAccAmount(),
                user.getUserAccount().getSubAccountPLN(),
                user.getUserAccount().getSubAccountUSD());
    }
}
