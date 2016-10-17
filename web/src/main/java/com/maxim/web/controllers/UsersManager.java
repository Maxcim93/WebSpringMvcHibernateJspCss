package com.maxim.web.controllers;

import com.maxim.model.User;
import com.maxim.web.storages.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Максим on 16.09.2016.
 */
@Controller
@RequestMapping(value = "/manager")
public class UsersManager {

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }

    @Autowired
    private Storage<User> usersStorage;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap){
        modelMap.addAttribute("users", usersStorage.values());
        return "allusers-view";
    }

    @RequestMapping(value = "/users/user", method = RequestMethod.GET)
    public String getUser(ModelMap modelMap){
        /*int idUser=Integer.parseInt(userId.split("=")[1]);
        User userFromStorage=usersStorage.getById(idUser);*/

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userFromStorage=
                usersStorage.getByLogin(auth.getName());
        modelMap.addAttribute("user", userFromStorage);
        return "user-view";
    }

    @RequestMapping(value = "/users/create", method = RequestMethod.GET)
    public String getCreateUserPage(ModelMap modelMap){
        return "create-user";
    }

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") User user,
                             ModelMap modelMap){
        usersStorage.add(user);
        return "redirect:/manager/users";
    }

    @RequestMapping(value = "/users/edit/{userId}", method = RequestMethod.GET)
    public String getEditUserPage(@PathVariable String userId,ModelMap modelMap){
        int idUser=Integer.parseInt(userId.split("=")[1]);
        User userFromStorage=usersStorage.getById(idUser);
        modelMap.addAttribute("user", userFromStorage);
        return "edit-user";
    }

    @RequestMapping(value = "/users/edit/{userId}", method = RequestMethod.POST)
    public String editUser(@PathVariable String userId,
                           @ModelAttribute("user") User user,
                           ModelMap modelMap){
        int idUser=Integer.parseInt(userId.split("=")[1]);
        user.setId(idUser);
        usersStorage.edit(user);
        return "redirect:/manager/users";
    }

    @RequestMapping(value = "/users/delete/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String userId,ModelMap modelMap){
        int idUser=Integer.parseInt(userId.split("=")[1]);
        usersStorage.delete(idUser);
        return "redirect:/manager/users";
    }
}
