package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.DiscountDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class DiscountDAOBirthdayBonusImplement implements DiscountDAO {
    @Override
    public long getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        LocalDate birthday = user.getBirthday();
        int yearDifference = airDateTime.getYear() - birthday.getYear();
        LocalDateTime changedAirDate = airDateTime.minusYears(yearDifference);
        Period betweenDates = Period.between(LocalDate.from(changedAirDate), birthday);
        int daysDifference = betweenDates.getDays();

        if (Math.abs(daysDifference) < 5) {
            return 5;
        } else {
            return 0;
        }
    }
}
