package com.maxim.model;

import java.util.Date;

/**
 * Created by Максим on 16.09.2016.
 */
public class User {
    private int id;
    private String name;
    private String surname;
    private Date birthday;
    private String login;
    private String password;
    private String about;
    private String address;

    public User(){}

    public void setId(int id) {this.id=id;}
    public void setName(String name){this.name=name;}
    public void setSurname(String surname){this.surname=surname;}
    public void setBirthday(Date birthday){this.birthday=birthday;}
    public void setLogin(String login){this.login=login;}
    public void setPassword(String password){this.password=password;}
    public void setAbout(String about){this.about=about;}
    public void setAddress(String address){this.address=address;}

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public String getSurname(){return this.surname;}
    public Date getBirthday(){return this.birthday;}
    public String getLogin(){return this.login;}
    public String getPassword(){return this.password;}
    public String getAbout(){return this.about;}
    public String getAddress(){return this.address;}
}
