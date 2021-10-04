package se.experis.tidsbankenbackend.models;

import javax.persistence.*;

@Entity
@Table(name = "VactionRequestStatus")
public class VacationRequestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "status")
    private String status;

    public VacationRequestStatus(){}

    public VacationRequestStatus(Long id, String status){
        this.setId(id);
        this.setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
