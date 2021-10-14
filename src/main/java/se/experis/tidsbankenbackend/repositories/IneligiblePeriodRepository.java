package se.experis.tidsbankenbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.experis.tidsbankenbackend.models.IneligiblePeriod;

@Repository
public interface IneligiblePeriodRepository extends JpaRepository<IneligiblePeriod, Long> {
    IneligiblePeriod findByUserId (Long id);
}
