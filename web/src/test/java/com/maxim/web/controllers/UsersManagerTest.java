package com.maxim.web.controllers;

import com.maxim.model.User;
import com.maxim.web.storages.Storage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Максим on 20.09.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:spring-context.xml")
public class UsersManagerTest {

    @Autowired
    Storage<User> usersStorage;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testMethod() throws Exception {
        //создание новго пользователя
        User newUser=new User();
        newUser.setName("NewUserName");
        newUser.setSurname("SernameNewUser");
        newUser.setBirthday(new Date(87678));
        int idNewUser=usersStorage.add(newUser);

        //получение пользователя от сервелета и проверка с
        //ранее созданным
        this.mockMvc.perform(get("/manager/users/user/id="+idNewUser).accept(MediaType.parseMediaType("application/xml;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(view().name("user-view"))
                .andExpect(model().attribute("user",newUser));

        //удаление созданного пользователя из бд
        usersStorage.delete(idNewUser);
        //usersStorage.close();
    }
}