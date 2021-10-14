package se.experis.tidsbankenbackend.controllerTests;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.experis.tidsbankenbackend.models.Comment;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.models.VacationRequest;
import se.experis.tidsbankenbackend.services.CommentService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CommentControllerTest {

    CommentService mockCommentService = Mockito.mock(CommentService.class);
    Timestamp timestampTest = new Timestamp(new Date().getTime());

    @Test
    public void testGetAllComments() {
        VacationRequest vacationRequest1 = new VacationRequest();
        vacationRequest1.setId(1L);
        User user1 = new User();
        user1.setId(1L);
        Comment comment1 = new Comment(1L,timestampTest,"Comment1", vacationRequest1,user1);
        Comment comment2 = new Comment(2L,timestampTest,"Comment2", vacationRequest1,user1);

        List<Comment> allComments = new ArrayList<>();
        allComments.add(comment2);
        allComments.add(comment1);

        Mockito.when(mockCommentService.getAllComments()).thenReturn(ResponseEntity.ok(allComments));

        assertEquals(ResponseEntity.ok(allComments),mockCommentService.getAllComments());
        for (Comment d:allComments) {
            System.out.println(d.getTimestamp());

        }
    }

    @Test
    public void testGetCommentByIdNumber2(){
        VacationRequest vacationRequest1 = new VacationRequest();
        vacationRequest1.setId(1L);
        User user1 = new User();
        user1.setId(1L);
        Comment comment2 = new Comment(2L,timestampTest,"Comment2", vacationRequest1,user1);

        Mockito.when(mockCommentService.getCommentById(2L)).thenReturn(ResponseEntity.ok(comment2));
        assertEquals(ResponseEntity.ok(comment2),mockCommentService.getCommentById(2L));

    }

    @Test
    public void testGetCommentsByUserIdNumber1(){
        VacationRequest vacationRequest1 = new VacationRequest();
        vacationRequest1.setId(1L);
        User user1 = new User();
        user1.setId(1L);
        Comment comment1 = new Comment(1L,timestampTest,"Comment1", vacationRequest1,user1);
        Comment comment2 = new Comment(2L,timestampTest,"Comment2", vacationRequest1,user1);
        List<Comment> allComments = new ArrayList<>();
        allComments.add(comment1);
        allComments.add(comment2);
        user1.setComments(allComments);

        Mockito.when(mockCommentService.getCommentsByUserId(1L)).thenReturn(ResponseEntity.ok(user1.getComments()));
        assertEquals(ResponseEntity.ok(user1.getComments()),mockCommentService.getCommentsByUserId(1L));
    }
    @Test
    public void testGetCommentsByVacationRequestIdNumber1(){
        VacationRequest vacationRequest1 = new VacationRequest();
        vacationRequest1.setId(1L);
        User user1 = new User();
        user1.setId(1L);
        Comment comment1 = new Comment(1L,timestampTest,"Comment1", vacationRequest1,user1);
        Comment comment2 = new Comment(2L,timestampTest,"Comment2", vacationRequest1,user1);
        List<Comment> allComments = new ArrayList<>();
        allComments.add(comment1);
        allComments.add(comment2);
        vacationRequest1.setComments(allComments);

        Mockito.when(mockCommentService.getCommentsByVacationRequestId(1L)).thenReturn(ResponseEntity.ok(vacationRequest1.getComments()));
        assertEquals(ResponseEntity.ok(vacationRequest1.getComments()),mockCommentService.getCommentsByVacationRequestId(1L));
    }
    @Test
    public void testAddNewComment(){
        VacationRequest vacationRequest1 = new VacationRequest();
        vacationRequest1.setId(1L);
        User user1 = new User();
        user1.setId(1L);
        Comment commentToAdd = new Comment(1L,timestampTest,"New Comment", vacationRequest1,user1);

        Mockito.when(mockCommentService.addComment(commentToAdd)).thenReturn(new ResponseEntity<>(commentToAdd, HttpStatus.CREATED));
        assertEquals(new ResponseEntity<>(commentToAdd, HttpStatus.CREATED), mockCommentService.addComment(commentToAdd));
        System.out.println(commentToAdd.getTimestamp());
    }
    @Test
    public void testUpdateCommentById(){
        VacationRequest vacationRequest1 = new VacationRequest();
        vacationRequest1.setId(1L);
        User user1 = new User();
        user1.setId(1L);
        Comment commentOld = new Comment(1L,timestampTest,"Old Comment", vacationRequest1,user1);

        Mockito.when(mockCommentService.getCommentById(1L)).thenReturn(ResponseEntity.ok(commentOld));
        commentOld.setMessage("Updated Comment");
        Mockito.when(mockCommentService.updateComment(commentOld)).thenReturn(ResponseEntity.ok(commentOld));

        assertEquals(ResponseEntity.ok(commentOld), mockCommentService.updateComment(commentOld));
    }
    @Test
    public void testDeleteComment(){
        VacationRequest vacationRequest1 = new VacationRequest();
        vacationRequest1.setId(1L);
        User user1 = new User();
        user1.setId(1L);
        Comment commentOld = new Comment(1L,timestampTest,"Old Comment", vacationRequest1,user1);

        Mockito.when(mockCommentService.getCommentById(1L)).thenReturn(ResponseEntity.ok(commentOld));
        Mockito.when(mockCommentService.deleteCommentById(1L)).thenReturn(ResponseEntity.ok(1L));

        assertEquals(ResponseEntity.ok(1L), mockCommentService.deleteCommentById(1L));

    }
}
