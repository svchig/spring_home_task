package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.UserDAO;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Map;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class UserDAOImplement implements UserDAO {
    private Map<Long, User> userMap;

    public void setUserMap(Map<Long, User> userMap){this.userMap = userMap;}

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        for (Map.Entry<Long, User> entry: userMap.entrySet()){
            User user = entry.getValue();
            if (email.equals(user.getEmail())) return user;
        }
        return null;
    }

    @Override
    public User save(@Nonnull User object) {
        return userMap.put(object.getId(), object);
    }

    @Override
    public void remove(@Nonnull User object) {
        userMap.remove(object.getId());
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userMap.get(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }
}
