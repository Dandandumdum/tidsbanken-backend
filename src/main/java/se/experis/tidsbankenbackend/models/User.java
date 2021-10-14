package se.experis.tidsbankenbackend.models;


import org.springframework.lang.Nullable;
import javax.persistence.Table;
import javax.persistence.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nullable
    @Column (name="profilePic")
    private String profilePic;

    @Column (name="isAdmin")
    private boolean isAdmin;

    @Nullable
    @OneToMany (mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @Nullable
    @OneToMany (mappedBy = "user")
    private List<VacationRequest> vacationRequests =new ArrayList<>();

    @Nullable
    @OneToMany (mappedBy = "user")
    private List<IneligiblePeriod> ineligiblePeriods =new ArrayList<>();

//    private String redirectUrl = "/api/user/:" + getId().toString();

    public User(){}

    public User(Long id, String profilePic, boolean isAdmin , List<Comment> comment){
      this.setId(id);
      this.setProfilePic(profilePic);
      this.setAdmin(isAdmin);
      this.setComments(comment);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(@Nullable String profilePic) {
        this.profilePic = profilePic;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Nullable
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(@Nullable List<Comment> comments) {
        this.comments = comments;
    }

    @Nullable
    public List<VacationRequest> getVacationRequests() {
        return vacationRequests;
    }

    public void setVacationRequests(@Nullable List<VacationRequest> vacationRequests) {
        this.vacationRequests = vacationRequests;
    }

    @Nullable
    public List<IneligiblePeriod> getIneligiblePeriods() {
        return ineligiblePeriods;
    }

    public void setIneligiblePeriods(@Nullable List<IneligiblePeriod> ineligiblePeriods) {
        this.ineligiblePeriods = ineligiblePeriods;
    }

 /*   public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }*/
}
