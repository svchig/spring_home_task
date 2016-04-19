package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.DiscountDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class DiscountDAOWholesaleImplement implements DiscountDAO {
    @Override
    public long getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        long usersTickets = user.getTickets().size();
        long sumTickets = usersTickets + numberOfTickets;
        long usersTenth = usersTickets / 10;
        long sumTenth = sumTickets / 10;

        long discountCount = sumTenth - usersTenth;
        long discount = 50 * discountCount / numberOfTickets;
        return discount;
    }
}
