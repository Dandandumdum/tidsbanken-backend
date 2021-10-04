package se.experis.tidsbankenbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;
import javax.persistence.Table;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp timestamp;
    @Nullable
    @Column (name="message")
    private String message;


    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @JsonIgnore
    @Nullable
    @JsonGetter("user")
    public String user(){
        return "/api/users"+ user.getId();
    }

    @ManyToOne
    @JoinColumn(name = "request_id")
    private VacationRequest vacationRequest;
    @JsonIgnore
    @Nullable
    @JsonGetter("vacationRequests")
    public String vacationRequest(){
        return "/api/vacationrequest"+ vacationRequest.getId();
    }

    public Comment(){}

    public Comment(Long id , Timestamp timestamp,String message, VacationRequest requestId, User userId ){
        this.setId(id);
        this.setTimestamp(timestamp);
        this.setMessage(message);
        this.setVacationRequest(requestId);
        this.setUser(userId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @Nullable
    public VacationRequest getVacationRequest() {
        return vacationRequest;
    }

    public void setVacationRequest(@Nullable VacationRequest vacationRequest) {
        this.vacationRequest = vacationRequest;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
