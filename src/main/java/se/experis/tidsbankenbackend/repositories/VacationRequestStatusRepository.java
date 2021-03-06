package se.experis.tidsbankenbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.experis.tidsbankenbackend.models.VacationRequestStatus;

@Repository
public interface VacationRequestStatusRepository extends JpaRepository <VacationRequestStatus, Long> {
}
