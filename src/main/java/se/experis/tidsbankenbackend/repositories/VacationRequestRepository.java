package se.experis.tidsbankenbackend.repositories;

import org.springframework.stereotype.Repository;
import se.experis.tidsbankenbackend.models.VacationRequest;

import java.util.List;

@Repository
public interface VacationRequestRepository {
    List<VacationRequest> findAllByUser (Long id);
}
