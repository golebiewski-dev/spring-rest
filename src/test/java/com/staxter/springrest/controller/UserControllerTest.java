package com.staxter.springrest.controller;

import com.staxter.springrest.SpringRestApplication;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringRestApplication.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

    String registerUser = "{\"firstName\": \"John\", \"lastName\": \"Smith\",\"userName\": \"JohnSmith\",\"password\": \"kZ6u]r=P%4wYK.H7\"}";
    String existingUser = "{\"userName\": \"JohnSmith\",\"password\": \"kZ6u]r=P%4wYK.H7\"}";
    String existingUserWithWrongPassword = "{\"userName\": \"JohnSmith\",\"password\": \"wrong-pass\"}";
    String notExistingUser = "{\"userName\": \"admin\",\"password\": \"admin\"}";

    @Autowired
    private MockMvc mvc;

    @Test
    public void test1_givenUser_whenRegister_thenStatus200() throws Exception {
        mvc.perform(post("/userservice/register").contentType(MediaType.APPLICATION_JSON).content(registerUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.userName").value("JohnSmith"));
    }

    @Test
    public void test2_givenExistingUser_whenRegister_thenStatus409() throws Exception {
        mvc.perform(post("/userservice/register").contentType(MediaType.APPLICATION_JSON).content(registerUser))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.code").value("USER_ALREADY_EXISTS"))
                .andExpect(jsonPath("$.description").value("A user with the given username already exists"));
    }

    @Test
    public void test3_givenExistingUser_whenLogin_thenStatus200() throws Exception {
        mvc.perform(post("/userservice/login").contentType(MediaType.APPLICATION_JSON).content(existingUser))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Smith"))
                .andExpect(jsonPath("$.userName").value("JohnSmith"));
    }

    @Test
    public void test4_givenExistingWithWrongPasswordUser_whenLogin_thenStatus200() throws Exception {
        mvc.perform(post("/userservice/login").contentType(MediaType.APPLICATION_JSON).content(existingUserWithWrongPassword))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.code").value("USER_NOT_FOUND"))
                .andExpect(jsonPath("$.description").value("User not found or incorrect password"));
    }

    @Test
    public void test5_givenExistingWithWrongPasswordUser_whenLogin_thenStatus200() throws Exception {
        mvc.perform(post("/userservice/login").contentType(MediaType.APPLICATION_JSON).content(notExistingUser))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.code").value("USER_NOT_FOUND"))
                .andExpect(jsonPath("$.description").value("User not found or incorrect password"));
    }
}
