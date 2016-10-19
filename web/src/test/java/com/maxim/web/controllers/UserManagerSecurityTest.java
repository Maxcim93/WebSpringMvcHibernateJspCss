package com.maxim.web.controllers;

import com.maxim.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by Максим on 19.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:spring-context.xml",
                      "classpath:spring-security.xml"})
public class UserManagerSecurityTest
{
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockSecurity;

    @Before
    public void setup() {
        mockSecurity = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    //тестирование аутентификации через login page конкретных пользователей
    @Test
    public void testUserRoleAccessFromLogout() throws Exception{
        mockSecurity.perform(formLogin("/login")
                .user("login").password("login"))
                .andExpect(authenticated()
                        .withUsername("login")
                        .withRoles("USER"));
    }

    /**
     * Для rest сервисов, тестирование доступа к конкртеным страницам
     * конкртеных пользователей
     * @throws Exception
     */
    @Test
    public void testUserRoleAccessFromBasicAuth() throws Exception{
        mockSecurity.perform(get("/manager").with(httpBasic("login","login")))
                .andExpect(authenticated()
                .withUsername("login")
                .withRoles("USER"));
    }

    /**
     * Тестирование механизма аутентификации (создание контекста) без привязки к
     * заданным настройкам безопасности
     * @throws Exception
     */
    @Test
    public void testUserRoleAccessFromGetWithUser() throws Exception{
        mockSecurity.perform(get("/manager")
                .with(user("user").roles("ADMIN")))
                .andExpect(authenticated()
                        .withRoles("ADMIN")
                        .withUsername("user"));
    }
}
