package com.maxim.web.storages;

import com.maxim.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by Максим on 16.09.2016.
 */
@Repository
public class HibernateUserStorage implements Storage<User> {
    private final HibernateTemplate template;

    @Autowired
    public HibernateUserStorage(HibernateTemplate template) {
        this.template = template;
    }

    public Collection<User> values(){
        return (Collection<User>)template.find("from User");
    }

    @Transactional
    public int add(final User user){
        return (Integer)template.save(user);
    }

    @Transactional
    public void edit(final User user){
        template.update(user);
    }

    @Transactional
    public void delete(final int id){
        User userForDelete=new User();
        userForDelete.setId(id);
        template.delete(userForDelete);
    }

    public User getById(final int id){
        return (User) template.get(User.class, id);
    }

    public User getByLogin(final String login){
        return (User)template.find(
                "select user from User user where user.login like ?",login).get(0);
    }

    public void close(){ throw new UnsupportedOperationException(); }
}
