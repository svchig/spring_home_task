package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.epam.spring.hometask.dao.DiscountDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.impl.DiscountServiceImplement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by siarhei_chyhir on 4/19/2016.
 */
public class DiscountServiceImplementTest {
    private DiscountServiceImplement discountService;

    @Mock
    private DiscountDAO discount1;

    @Mock
    private DiscountDAO discount2;

    @SuppressWarnings("serial")
    @Before
    public void doSetup() {
        MockitoAnnotations.initMocks(this);
        discountService = new DiscountServiceImplement();
        List<DiscountDAO> strategies = new ArrayList<DiscountDAO>(){{
            add(discount1);
            add(discount2);
        }};
        discountService.setDiscountStrategies(strategies);
    }

    @Test
    public void testGetDiscount() {
        LocalDateTime date = LocalDateTime.now();

        when(discount1.getDiscount(any(User.class), any(Event.class), any(LocalDateTime.class),
                any(Long.class))).thenReturn(30l);

        when(discount2.getDiscount(any(User.class), any(Event.class), any(LocalDateTime.class),
                any(Long.class))).thenReturn(20l);

        long discount = discountService.getDiscount(new User(), new Event(), date, 10l);

        assertEquals(30l, discount);
    }
}
