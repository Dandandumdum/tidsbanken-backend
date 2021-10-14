package se.experis.tidsbankenbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.experis.tidsbankenbackend.controllers.CommentController;
import se.experis.tidsbankenbackend.models.Comment;
import se.experis.tidsbankenbackend.repositories.CommentRepository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comparator<Comment> sortByTimestamp = (o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp());

    public ResponseEntity<List<Comment>> getAllComments(){
        List<Comment> comments = commentRepository.findAll();
        Collections.sort(comments, sortByTimestamp);
        if(!comments.isEmpty()){
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }else return new ResponseEntity<>(comments, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Comment> getCommentById(Long id){
        var returnComment = new Comment();
        if(commentRepository.existsById(id)){
            returnComment = commentRepository.findById(id).get();
            return new ResponseEntity<>(returnComment, HttpStatus.OK);
        }else return new ResponseEntity<>(returnComment, HttpStatus.NOT_FOUND);
    }

    public  ResponseEntity<List<Comment>> getCommentsByUserId(Long id){
        List<Comment> comments = commentRepository.findAllByUser(id);
        if(!comments.isEmpty()){
            return new ResponseEntity<>(comments,HttpStatus.OK);
        }else return new ResponseEntity<>(comments, HttpStatus.NO_CONTENT);
    }

    public  ResponseEntity<List<Comment>> getCommentsByVacationRequestId(Long id){
        List<Comment> comments = commentRepository.findAllByVacationRequest(id);
        if(!comments.isEmpty()){
            return new ResponseEntity<>(comments,HttpStatus.OK);
        }else return new ResponseEntity<>(comments, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Comment> addComment(Comment comment){
        var returnComment = new Comment();
        if(comment !=null){
            comment.setTimestamp(new Timestamp(new Date().getTime()));
            returnComment = commentRepository.save(comment);
            return new ResponseEntity<>(returnComment, HttpStatus.CREATED);
        } else return new ResponseEntity<>(returnComment, HttpStatus.NO_CONTENT);
    }
    public ResponseEntity<Comment> updateComment(Comment comment){
        var returnComment = new Comment();
        if(!commentRepository.existsById(comment.getId())){
            return new ResponseEntity<>(returnComment,HttpStatus.BAD_REQUEST);
        }
        returnComment = commentRepository.save(comment);
        return new ResponseEntity<>(returnComment, HttpStatus.OK);
    }

    public ResponseEntity<Long> deleteCommentById(Long id){
        if(commentRepository.existsById(id)){
            commentRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }else return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }

}
