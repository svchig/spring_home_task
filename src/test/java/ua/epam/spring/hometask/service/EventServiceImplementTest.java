package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.epam.spring.hometask.dao.EventDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.impl.EventServiceImplement;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
/**
 * Created by siarhei_chyhir on 4/19/2016.
 */
public class EventServiceImplementTest {
    @Mock
    private EventDAO eventDAO;

    private EventServiceImplement eventService;

    @Before
    public void doSetup() {
        MockitoAnnotations.initMocks(this);
        eventService = new EventServiceImplement();
        eventService.setEventDAO(eventDAO);
    }

    @Test
    public void testGetForDateRange() {

        LocalDateTime date = LocalDateTime.now();
        LocalDateTime date1 = LocalDateTime.now().plusDays(1);


        eventService.getForDateRange(null, null);
        verify(eventDAO, never()).getForDateRange(any(LocalDateTime.class), any(LocalDateTime.class));

        when(eventDAO.getForDateRange(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(new HashSet<Event>());

        eventService.getForDateRange(date, date1);
        verify(eventDAO).getForDateRange(any(LocalDateTime.class), any(LocalDateTime.class));
    }

}
