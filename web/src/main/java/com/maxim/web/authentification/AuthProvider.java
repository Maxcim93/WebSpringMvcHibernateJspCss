package com.maxim.web.authentification;

import com.maxim.model.User;
import com.maxim.web.storages.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Максим on 17.10.2016.
 */
@Service("provider")
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    Storage<User> userStorage;

    @Transactional
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String login=auth.getName();
        String password=auth.getCredentials().toString();

        User user=userStorage.getByLogin(login);

        List<GrantedAuthority> lsGranted=new LinkedList<GrantedAuthority>();
        if (user!=null && user.getPassword().equals(password)){
            lsGranted.add(new SimpleGrantedAuthority(user.getRole()));
            return new UsernamePasswordAuthenticationToken(login,password,lsGranted);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
