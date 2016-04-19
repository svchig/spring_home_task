package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ua.epam.spring.hometask.dao.AuditoriumDAO;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.impl.AuditoriumServiceImplement;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by siarhei_chyhir on 4/18/2016.
 */
public class AuditoriumServiceImplementTest {

    @Mock
    private AuditoriumDAO auditoriumDao;

    private AuditoriumServiceImplement auditoriumService;

    @Before
    public void doSetup() {
        MockitoAnnotations.initMocks(this);
        auditoriumService = new AuditoriumServiceImplement();
        auditoriumService.setAuditoriumDAO(auditoriumDao);
    }

    @Test
    public void testGetAll() {
        when(auditoriumDao.getAll()).thenReturn(new HashSet<Auditorium>());
        Collection<Auditorium> auditoriums = auditoriumService.getAll();
        verify(auditoriumDao).getAll();
        assertEquals(0, auditoriums.size());
    }

    @Test
    public void testGetByName() {
        when(auditoriumDao.getByName(any(String.class))).thenAnswer(new Answer<Auditorium>() {
            public Auditorium answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                String name =  (String) args[0];
                Auditorium auditorium = new Auditorium();
                auditorium.setName(name);
                return auditorium;
            }
        });

        Auditorium auditorium = auditoriumService.getByName("BigHall");
        verify(auditoriumDao).getByName(any(String.class));
        assertEquals("BigHall", auditorium.getName());
    }
}
