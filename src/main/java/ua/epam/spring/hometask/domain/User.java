package ua.epam.spring.hometask.domain;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

/**
 * @author Yuriy_Tkach
 */
public class User extends DomainObject {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private NavigableSet<Ticket> tickets = new TreeSet<>();

    public User() {
        super();
    }

    public User(long id, String firstName, String lastName, LocalDate birthday, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }

    public User(String firstName, String lastName, LocalDate birthday, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
    }

    public User(String firstName, String lastName, LocalDate birthday, String email, NavigableSet<Ticket> tickets) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.tickets = tickets;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NavigableSet<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(NavigableSet<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
//    public int hashCode() {
//        return Objects.hash(firstName, lastName, email);
//    }

    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        User other = (User) obj;

        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;

        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }

        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }

        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }

        if (tickets == null) {
            if (other.tickets != null)
                return false;
        } else if (!tickets.equals(other.tickets))
            return false;

        return true;
    }

}
