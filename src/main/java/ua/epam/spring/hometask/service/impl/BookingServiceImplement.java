package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.dao.BookingDAO;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class BookingServiceImplement implements BookingService {


    private Map<Long,Ticket> ticketMap;

    public void setTicketMap(Map<Long,Ticket> ticketMap){
        this.ticketMap = ticketMap;
    }

    private BookingDAO bookingDAO;

    private DiscountService discountService;

    public void setBookingDAO(BookingDAO bookingDAO){this.bookingDAO = bookingDAO;}

    public void setDiscountService(DiscountService discountService){this.discountService = discountService;}

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime date, @Nullable User user, @Nonnull Set<Long> seats) {
        double basePrice = event.getBasePrice();
        switch (event.getRating()) {
            case NIGHT:
                basePrice *= EventRating.NIGHT.getValue();
            case LOW:
                basePrice *= EventRating.LOW.getValue();
                break;
            case MID:
                basePrice *= EventRating.MID.getValue();
                break;
            case HIGH:
                basePrice *= EventRating.HIGH.getValue();
                break;
            case VIP:
                basePrice *= EventRating.VIP.getValue();
        }

        Auditorium auditorium = event.getAuditoriums().get(date);
        long vipSeatsCounter = auditorium.countVipSeats(seats);

        double totalPrice = basePrice * vipSeatsCounter;
        totalPrice += (seats.size() - vipSeatsCounter) * basePrice;

        double discount = discountService.getDiscount(user, event, date, seats.size());

        totalPrice = totalPrice*((100 - discount) / 100);

        return totalPrice;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) { bookingDAO.bookTickets(tickets);
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return bookingDAO.getPurchasedTicketsForEvent(event, dateTime);
    }

    @Override
    public Ticket save(@Nonnull Ticket object) {
        return ticketMap.put(object.getId(), object);
    }

    @Override
    public void remove(@Nonnull Ticket object) {
        ticketMap.remove(object.getId());
    }

    @Override
    public Ticket getById(@Nonnull Long id) {
        return ticketMap.get(id);
    }

    @Nonnull
    @Override
    public Collection<Ticket> getAll() {
        return ticketMap.values();
    }
}
