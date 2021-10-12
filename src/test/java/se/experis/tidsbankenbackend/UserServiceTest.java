package se.experis.tidsbankenbackend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.experis.tidsbankenbackend.models.Comment;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.models.VacationRequest;
import se.experis.tidsbankenbackend.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    UserService mockUserService = Mockito.mock(UserService.class);
    User user1 = new User(1L, "CAT PIC", false, null );
    String redirectUrl = "/api/user/:" + user1.getId().toString() ;
    VacationRequest vacationRequest1 = new VacationRequest(1L, "Mexico", "NOW", "Tomorrow", user1);

    @Test
    public void testGetUser(){
        String redirectUrl = "/api/user/:" + user1.getId().toString() ;
        Mockito.when(mockUserService.getOwnUser()).thenReturn(ResponseEntity.ok(redirectUrl));
        assertEquals(ResponseEntity.ok("/api/user/:1"),mockUserService.getOwnUser());
    }

    @Test
    public void testGetUserByIdNumber1(){
        Mockito.when(mockUserService.getUserById(1L)).thenReturn(ResponseEntity.ok(user1));
        assertEquals(ResponseEntity.ok(user1), mockUserService.getUserById(1L));

    }
    @Test
    public void testUpdateUserById(){
        Mockito.when(mockUserService.getUserById(1L)).thenReturn(ResponseEntity.ok(user1));
        user1.setProfilePic("Dog Pic");
        Mockito.when(mockUserService.updateUser(user1));
    }
    @Test
    public void testAddNewUser(){
        Mockito.when(mockUserService.addUser(user1)).thenReturn(new ResponseEntity<>(user1, HttpStatus.CREATED));
        assertEquals(new ResponseEntity<>(user1, HttpStatus.CREATED), mockUserService.addUser(user1));
    }
    @Test
    public void testDeleteUserById(){
        User user2 = new User(2L , "Whale Pic", false, null);
        Mockito.when(mockUserService.getUserById(2L)).thenReturn(ResponseEntity.ok(user2));
        Mockito.when(mockUserService.deleteUser(2L)).thenReturn(ResponseEntity.ok(2L));
        mockUserService.deleteUser(2L);
        verify(mockUserService).deleteUser(user2.getId());

        assertEquals(ResponseEntity.ok(2L), mockUserService.deleteUser(2L));
    }
    @Test
    public void testGetAllVacationRequestsByUserId(){

        List<VacationRequest> vacationRequests = new ArrayList<>();
        vacationRequests.add(vacationRequest1);
        user1.setVacationRequests(vacationRequests);
        Mockito.when(mockUserService.getAllVacationRequestsByUserId(user1.getId())).thenReturn(ResponseEntity.ok(user1.getVacationRequests()));
        assertEquals(ResponseEntity.ok(user1.getVacationRequests()), mockUserService.getAllVacationRequestsByUserId(user1.getId()));
    }

}
