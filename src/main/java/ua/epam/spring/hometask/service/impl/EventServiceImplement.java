package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.dao.EventDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class EventServiceImplement implements EventService {

    private EventDAO eventDAO;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        if (name != null) {
            return eventDAO.getByName(name);
        }else return null;
    }


    @Nonnull
    @Override
    public Collection<Event> getForDateRange(@Nonnull LocalDateTime from, @Nonnull LocalDateTime to) {
        if ((from != null)&&(to !=null)) {
            return eventDAO.getForDateRange(from, to);
        } else return new ArrayList<Event>();
    }

    @Override
    public Event save(@Nonnull Event object) {
        if (object != null) {
            return eventDAO.save(object);
        }else return null;
    }

    @Override
    public void remove(@Nonnull Event object) {
        if (object != null) {
            eventDAO.remove(object);
        }
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDAO.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDAO.getAll();
    }
}
