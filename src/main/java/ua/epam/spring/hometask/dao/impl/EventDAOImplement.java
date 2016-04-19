package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.EventDAO;
import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class EventDAOImplement implements EventDAO {
    private Map<Long, Event> eventMap;

    public void setEventMap(Map<Long, Event> eventMap){
        this.eventMap = eventMap;
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        for (Map.Entry<Long, Event> entry: eventMap.entrySet()){
            Event event = entry.getValue();
            if (name.equals(event.getName())) return event;
        }
        return null;
    }

    @Nonnull
    @Override
    public Collection<Event> getForDateRange(@Nonnull LocalDateTime from, @Nonnull LocalDateTime to) {
        Collection<Event> events = new HashSet<Event>();
        for (Map.Entry<Long, Event> entry : eventMap.entrySet()) {
            Event event = entry.getValue();
            if (event.airsOnDates(from, to)) {
                events.add(event);
            }
        }
        return events;
    }

    @Override
    public Event save(@Nonnull Event object) {
        return eventMap.put(object.getId(),object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventMap.remove(object.getId());
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventMap.get(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventMap.values();
    }
}
