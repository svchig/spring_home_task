package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.epam.spring.hometask.dao.BookingDAO;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.impl.BookingServiceImplement;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by siarhei_chyhir on 4/18/2016.
 */
public class BookingServiceImplementTest {
    @Mock
    private BookingDAO bookingDao;

    @Mock
    private DiscountService discountService;

    private BookingServiceImplement bookingService;

    @Before
    public void doSetup() {
        MockitoAnnotations.initMocks(this);
        bookingService = new BookingServiceImplement();
        bookingService.setBookingDAO(bookingDao);
        bookingService.setDiscountService(discountService);
    }

    @SuppressWarnings("serial")
    @Test
    public void testGetTicketsPrice() {
//        int iYear = 2016;
//        int iMonth = 4;
//        int iDay = 12;
        LocalDateTime date = LocalDateTime.now();
        NavigableSet<LocalDateTime> dates = new TreeSet<LocalDateTime>(){{
            add(date);
        }};

        Set<Long> vipSets = new HashSet<Long>(){{
            add(1l);
            add(2l);
        }};

        Auditorium auditorium = new Auditorium("vipHall", 30, vipSets);
        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<LocalDateTime, Auditorium>(){{
            put(date, auditorium);
        }};


        Event event = new Event("stop-cadr", dates, 100, EventRating.HIGH, auditoriums);

        User user = new User();

        when(discountService.getDiscount(any(User.class), any(Event.class), any(LocalDateTime.class),
                any(Long.class))).thenReturn(Long.valueOf((byte) 10l));


        Set<Long> sets = new HashSet<Long>(){{
            add(1l);
            add(3l);
        }};


        double price = bookingService.getTicketsPrice(event, date, user, sets);

        assertEquals(234, price, 0);
    }
}
