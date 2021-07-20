package com.bank.exercise.controller;

import com.bank.exercise.fakeDB.BankDB;
import com.bank.exercise.model.CurrencyExchanger;
import com.bank.exercise.model.NbpHttpClient;
import com.bank.exercise.service.SubAccService;
import com.bank.exercise.service.UserService;
import com.bank.exercise.util.UserToUserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@Configuration
public class ApplicationContextConfig {

    @Bean
    public BankDB bankDB(){
        return new BankDB();
    }

    @Bean
    public UserToUserDTO userToUserDTO(){
        return new UserToUserDTO();
    }
    @Bean
    public UserService userService(){
        return new UserService(bankDB(),userToUserDTO());
    }
    @Bean
    public NbpHttpClient nbpHttpClient(){
        return new NbpHttpClient();
    }

    @Bean
    public CurrencyExchanger currencyExchanger(){
        return new CurrencyExchanger(nbpHttpClient());
    }

    @Bean
    public SubAccService subAccService(){
        return new SubAccService(currencyExchanger(),userService());
    }

    @Bean
    public UserController userController(){
        return new UserController(userService(),subAccService());
    }

}
