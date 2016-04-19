package ua.epam.spring.hometask.domain;

/**
 * @author Yuriy_Tkach
 */
public enum EventRating {

    NIGHT(0.5),
    LOW(0.3),
    MID(1),
    HIGH(1.3),
    VIP(3);
    private double value;
    private EventRating(double value){this.value = value;}

    public double getValue(){return value;}
}
