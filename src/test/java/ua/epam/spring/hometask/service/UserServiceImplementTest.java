package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ua.epam.spring.hometask.dao.UserDAO;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.impl.UserServiceImplement;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by siarhei_chyhir on 4/19/2016.
 */
public class UserServiceImplementTest {
    private UserServiceImplement userService;

    @Mock
    private UserDAO userDAO;

    @Before
    public void doSetup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImplement();
        userService.setUserDAO(userDAO);
    }

    @Test
    public void testSave() {
        userService.save(null);
        verify(userDAO, never()).save(any(User.class));

        User user = new User();
        when(userDAO.save(any(User.class))).thenReturn(user);
        userService.save(user);

        verify(userDAO).save(any(User.class));
    }

    @Test
    public void testRemove() {
        userService.remove(null);
        verify(userDAO, never()).remove(any(User.class));

        User user = new User();
        doNothing().when(userDAO).remove(any(User.class));
        userService.remove(user);

        verify(userDAO).remove(any(User.class));
    }

    @Test
    public void testGetById() {
        when(userDAO.getById(any(Long.class))).thenAnswer(new Answer<User>() {
            public User answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Long id =  (Long) args[0];
                User user = new User();
                user.setId(id);
                return user;
            }
        });

        User user = userService.getById(2l);
        verify(userDAO).getById(any(Long.class));
        assertEquals(2l, (long)user.getId());
    }

    @Test
    public void testGetAll() {
        when(userDAO.getAll()).thenReturn(new HashSet<User>());
        Collection<User> users = userService.getAll();
        verify(userDAO).getAll();
        assertEquals(0, users.size());
    }

    @Test
    public void testGetUserByEmail() {
        when(userDAO.getUserByEmail(any(String.class))).thenAnswer(new Answer<User>() {
            public User answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                String name =  (String) args[0];
                User user = new User();
                user.setEmail(name);
                return user;
            }
        });

        User user = userService.getUserByEmail("test@email.ru");
        verify(userDAO).getUserByEmail(any(String.class));
        assertEquals("test@email.ru", user.getEmail());
    }
}
