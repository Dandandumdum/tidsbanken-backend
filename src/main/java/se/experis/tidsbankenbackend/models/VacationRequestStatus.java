package se.experis.tidsbankenbackend.models;

import se.experis.tidsbankenbackend.enums.RequestState;

import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "VacationRequestStatus")
public class VacationRequestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "status")
    private RequestState status;

    public VacationRequestStatus(){}

    public VacationRequestStatus(Long id, RequestState status){
        this.setId(id);
        this.setStatus(status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestState getStatus() {
        return status;
    }

    public void setStatus(RequestState status) {
        this.status = status;
    }
}
