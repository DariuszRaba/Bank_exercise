package com.bank.exercise.controller;

import com.bank.exercise.model.UserCreationForm;
import com.bank.exercise.service.UserServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationContextConfig.class})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @BeforeEach
    private void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_returned_201_status_code_from_user_creation() throws Exception {
        final UserCreationForm userCreationForm = getsUserForm();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/user/create/").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userCreationForm))).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(201, status);
    }

    @Test
    public void should_returned_200_status_code_from_user_info() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/user/info/90060804123")).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(200, status);
    }

    @Test
    public void should_returned_200_status_code_from_money_conversion() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/user/convert/90060804123/100/pln/usd")).andReturn();
        int status = mvcResult.getResponse().getStatus();

        MvcResult mvcResult2 = mvc.perform(MockMvcRequestBuilders.put("/user/convert/90060804123/10/usd/pln")).andReturn();
        final int status2 = mvcResult2.getResponse().getStatus();

        assertAll(() -> assertEquals(200, status),
                () -> assertEquals(200, status2));
    }

    @Test
    public void should_returned_FORBIDDEN_status_code_from_under_age_user() throws Exception {
        final UserCreationForm userCreationForm = getsUserForm();
        userCreationForm.setPesel(UserServiceTest.getUnderAgePesel());
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/user/create/").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userCreationForm))).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(403, status);
    }

    @Test
    public void should_returned_BAD_REQUEST_statdus_code_from_invalid_pesel() throws Exception {
        final UserCreationForm userCreationForm = getsUserForm();
        userCreationForm.setPesel("900j0804123");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/user/create/")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userCreationForm))).andReturn();
        int status = mvcResult.getResponse().getStatus();

        assertEquals(400, status);
    }

    private UserCreationForm getsUserForm() {
        return new UserCreationForm("Marian", "Pazdzioch", "90060812345");
    }

}
