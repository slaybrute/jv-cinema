package mate.academy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_sesion_id", nullable = false)
    private MovieSession movieSession;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Ticket() {
    }

    public Ticket(MovieSession movieSession, User user) {
        this.movieSession = movieSession;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieSession getMovieSession() {
        return movieSession;
    }

    public void setMovieSession(MovieSession movieSession) {
        this.movieSession = movieSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id)
                && Objects.equals(movieSession, ticket.movieSession)
                && Objects.equals(user, ticket.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieSession, user);
    }

    @Override
    public String toString() {
        return "Ticket{"
                + "id=" + id
                + ", movieSession=" + movieSession
                + ", user=" + user + '}';
    }
}
