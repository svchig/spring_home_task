package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.dao.DiscountDAO;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by siarhei_chyhir on 4/12/2016.
 */
public class DiscountServiceImplement implements DiscountService {

    private List<DiscountDAO> discounts;

    public void setDiscountStrategies(List<DiscountDAO> discounts) {
        this.discounts = discounts;
    }
    @Override
    public long getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDate, long numberOfTickets) {
        byte discount = 0;
        for (DiscountDAO strategy : discounts) {
            discount = (byte) Math.max(discount, strategy.getDiscount(user, event, LocalDateTime.from(airDate), numberOfTickets));
        }
        return discount;
    }
}
