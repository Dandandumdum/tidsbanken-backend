package se.experis.tidsbankenbackend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import se.experis.tidsbankenbackend.models.Comment;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    UserService mockUserService = Mockito.mock(UserService.class);
    User user1 = new User(1L, "CAT PIC", false, null );

    @Test
    public void testGetUser(){
        //User user1 = new User(1L, "CAT PIC", false);
        var redirectUrl = "/api/user/:" + user1.getId().toString() ;

        Mockito.when(mockUserService.getOwnUser(user1.getId())).thenReturn(ResponseEntity.ok(redirectUrl));
        assertEquals(ResponseEntity.ok("/api/user/:1"),mockUserService.getOwnUser(user1.getId()));
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

}
