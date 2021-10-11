package se.experis.tidsbankenbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.experis.tidsbankenbackend.models.VacationRequest;

import java.util.List;

@Repository
public interface VacationRequestRepository  extends JpaRepository<VacationRequest, Long> {
    List<VacationRequest> findAllByUser (Long id);
}
