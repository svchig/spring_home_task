package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.AuditoriumDAO;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class AuditoriumDAOImplement implements AuditoriumDAO {
    private Map<String,Auditorium> auditoriumMap;

    public void setAuditoriumMap(Map<String, Auditorium> auditoriumMap){
        this.auditoriumMap = auditoriumMap;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return new HashSet<Auditorium>(auditoriumMap.values());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumMap.get(name);
    }
}
