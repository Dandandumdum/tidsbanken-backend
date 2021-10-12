package se.experis.tidsbankenbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.experis.tidsbankenbackend.models.Moderator;

@Repository
public interface ModeratorRepository extends JpaRepository <Moderator, Long> {
    Moderator getByUserId (Long id);
}

