package se.experis.tidsbankenbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.experis.tidsbankenbackend.models.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser (Long id);
    List<Comment> findAllByVacationRequest (Long id);

}
