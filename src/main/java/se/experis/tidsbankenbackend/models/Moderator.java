package se.experis.tidsbankenbackend.models;

import org.springframework.lang.Nullable;
import javax.persistence.Table;
import javax.persistence.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Moderators")
public class Moderator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany (mappedBy = "moderator")
    private List<VacationRequest> vacationRequests = new ArrayList<>();

    public Moderator(Long id, User userId) {
        this.setId(id);
        this.setUser(userId);

    }
    public Moderator() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }
}
