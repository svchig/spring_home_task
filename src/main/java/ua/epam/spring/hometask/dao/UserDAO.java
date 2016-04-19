package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by siarhei_chyhir on 4/11/2016.
 */
public interface UserDAO extends AbstractDomainObjectDAO<User> {

    /**
     * Finding user by email
     *
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable
    User getUserByEmail(@Nonnull String email);
}
