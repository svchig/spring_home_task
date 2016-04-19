package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.dao.AuditoriumDAO;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class AuditoriumServiceImplement implements AuditoriumService {

    private AuditoriumDAO auditoriumDAO;

    public void setAuditoriumDAO(AuditoriumDAO auditoriumDAO) {
        this.auditoriumDAO = auditoriumDAO;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumDAO.getAll();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumDAO.getByName(name);
    }
}
