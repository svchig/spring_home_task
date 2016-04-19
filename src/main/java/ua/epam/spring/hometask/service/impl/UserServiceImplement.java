package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.dao.UserDAO;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class UserServiceImplement implements UserService {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        if (email != null) {
            return userDAO.getUserByEmail(email);
        }else return null;
    }

    @Override
    public User save(@Nonnull User object) {
        if (object != null) {
            return userDAO.save(object);
        }else return null;
    }

    @Override
    public void remove(@Nonnull User object) {
        if (object != null) {
            userDAO.remove(object);
        }
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userDAO.getById(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDAO.getAll();
    }
}
