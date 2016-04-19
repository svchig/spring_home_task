package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.BookingDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class BookingDAOImplement implements BookingDAO {
    private Map<Long,Ticket> ticketMap;

    public void setTicketMap(Map<Long,Ticket> ticketMap){
        this.ticketMap = ticketMap;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        return 0;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticketMap.put(ticket.getId(), ticket);
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        Set<Ticket> tickets = new HashSet<>();
        for (Map.Entry<Long, Ticket> entry : ticketMap.entrySet()) {
            Ticket ticket = entry.getValue();
            if (ticket.getEvent().equals(event) && ticket.getDateTime().equals(dateTime)) {
                tickets.add(ticket);
            }
        }
        return tickets;
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
