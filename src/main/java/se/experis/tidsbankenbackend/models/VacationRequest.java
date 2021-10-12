package se.experis.tidsbankenbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;
import se.experis.tidsbankenbackend.services.VacationRequestService;

import javax.persistence.Table;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VacationRequests")
public class VacationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Nullable
    @Column (name ="title")
    private String title;
    @Column (name="period_start")
    private String periodStart;
    @Column (name="period_end")
    private String periodEnd;

    @ManyToOne
    @JoinColumn(name ="owner_id")
    private User user;

    @JsonIgnore
    @Nullable
    @JsonGetter("user")
    public String user(){
        return "/api/users"+ getUser().getId();
    }

    @Nullable
    @ManyToOne
    @JoinColumn(name ="moderator_id")
    private Moderator moderator;
    @JsonIgnore
    @Nullable
    @JsonGetter("moderator")
    public String moderator(){
        return "/api/moderators"+ getModerator().getId();
    }

    @OneToOne
    @JoinColumn(name = "status_id")
    private VacationRequestStatus statusId;

    @JsonIgnore
    @JsonGetter("status")
    public String status (){return "/api/statuses/" + getStatusId().getId();}

    @Nullable
    @OneToMany (mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    private boolean updated;
    private Timestamp updatedTimestamp;


    public VacationRequest(){}

    public VacationRequest(Long id, String title, String periodStart, String periodEnd, User user){
        this.setId(id);
        this.setTitle(title);
        this.setPeriodStart(periodStart);
        this.setPeriodEnd(periodEnd);
        this.setUser(user);
        this.setUpdated(false);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Nullable
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(@Nullable List<Comment> comments) {
        this.comments = comments;
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    public VacationRequestStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(VacationRequestStatus statusId) {
        this.statusId = statusId;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public Timestamp getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp;
    }
}
