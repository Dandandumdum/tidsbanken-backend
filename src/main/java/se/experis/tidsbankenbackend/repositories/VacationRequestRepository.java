package se.experis.tidsbankenbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.experis.tidsbankenbackend.models.VacationRequest;

import javax.transaction.Status;
import java.util.List;

@Repository
public interface VacationRequestRepository  extends JpaRepository<VacationRequest, Long> {
    List<VacationRequest> findAllByUser (Long id);
    List<VacationRequest> findAllByStatusId_Status (String status);
    List<VacationRequest> findAllByUser_Admin (Boolean isAdmin);
}
