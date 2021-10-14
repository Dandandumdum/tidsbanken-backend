package se.experis.tidsbankenbackend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "ineligible_period")
public class IneligiblePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="period_start")
    private String periodStart;
    @Column(name = "period_end")
    private String periodEnd;

    @ManyToOne
    @JoinColumn (name = "created_by")
    private User user;
    @JsonIgnore
    @Nullable
    @JsonGetter("user")
    public String user(){
        return "/api/users"+ getUser().getId();
    }

    public IneligiblePeriod(){}

    public IneligiblePeriod(Long id, String periodStart, String periodEnd, User createdByUserId){
        this.setId(id);
        this.setPeriodStart(periodStart);
        this.setPeriodEnd(periodEnd);
        this.setUser(createdByUserId);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
