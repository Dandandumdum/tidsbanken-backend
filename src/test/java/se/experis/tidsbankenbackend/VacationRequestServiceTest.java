package se.experis.tidsbankenbackend;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.experis.tidsbankenbackend.enums.RequestState;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.models.VacationRequest;
import se.experis.tidsbankenbackend.models.VacationRequestStatus;
import se.experis.tidsbankenbackend.repositories.VacationRequestRepository;
import se.experis.tidsbankenbackend.services.VacationRequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class VacationRequestServiceTest {

    VacationRequestService mockVacationRequestService = Mockito.mock(VacationRequestService.class);

    User user1 = new User(1L, "CAT PIC", false, null );
    User user2 = new User(2L, "HORSE PIC", false, null );
    User user3 = new User(3L, "WHALE PIC", true, null );
    VacationRequest vacationRequest1 = new VacationRequest(1L, "Mexico", "NOW", "Tomorrow", user1);
    VacationRequest vacationRequest2 = new VacationRequest(2L, "Mexico2", "NOW", "Tomorrow", user2);
    VacationRequest vacationRequest3 = new VacationRequest(3L, "Mexico3", "NOW", "Tomorrow", user3);
    VacationRequestStatus vacationRequestStatusAPPROVED = new VacationRequestStatus(1L, RequestState.APPROVED);
    VacationRequestStatus vacationRequestStatusDENIED = new VacationRequestStatus(2L, RequestState.DENIED);
    VacationRequestStatus vacationRequestStatusPENDING = new VacationRequestStatus(3L, RequestState.PENDING);


    @Test
    public void testGetAllVacationRequestsAdmin(){

        vacationRequest1.setStatusId(vacationRequestStatusAPPROVED);
        vacationRequest2.setStatusId(vacationRequestStatusDENIED);
        vacationRequest3.setStatusId(vacationRequestStatusPENDING);

        List<VacationRequest> allVacationRequests = new ArrayList<>();
        allVacationRequests.add(vacationRequest1);
        allVacationRequests.add(vacationRequest2);
        allVacationRequests.add(vacationRequest3);

        Mockito.when(mockVacationRequestService.getAllVacationRequests(user3)).thenReturn(ResponseEntity.ok(allVacationRequests));
        assertEquals(ResponseEntity.ok(allVacationRequests), mockVacationRequestService.getAllVacationRequests(user3));
        assertEquals(allVacationRequests, ResponseEntity.ok(allVacationRequests).getBody());
        Mockito.verify(mockVacationRequestService).getAllVacationRequests(user3);
    }

    @Test
    public void testGetAllVacationRequestsNonAdmin(){
        vacationRequest1.setStatusId(vacationRequestStatusAPPROVED);
        vacationRequest2.setStatusId(vacationRequestStatusDENIED);
        vacationRequest3.setStatusId(vacationRequestStatusPENDING);
        List<VacationRequest> user1Requests = new ArrayList<>();
        user1Requests.add(vacationRequest1);
        user1.setVacationRequests(user1Requests);
        List<VacationRequest> allVacationRequests = new ArrayList<>();

        if (!user1.isAdmin()){
            if(user1.getVacationRequests().size() < 1 )
                for (VacationRequest v: user1.getVacationRequests()) {
                    allVacationRequests.add(v);
                }else{
                    allVacationRequests.add(user1.getVacationRequests().get(0));
            }
        }else{
            allVacationRequests.add(vacationRequest1);
            allVacationRequests.add(vacationRequest2);
            allVacationRequests.add(vacationRequest3);
        }

        Mockito.when(mockVacationRequestService.getAllVacationRequests(user1)).thenReturn(ResponseEntity.ok(allVacationRequests));
        assertEquals(user1Requests, mockVacationRequestService.getAllVacationRequests(user1).getBody());
        Mockito.verify(mockVacationRequestService).getAllVacationRequests(user1);
    }

    @Test
    public void testPostNewVacationRequest(){
        VacationRequest vacationRequest4 = new VacationRequest();
        Mockito.when(mockVacationRequestService.addVacationRequest(vacationRequest4)).thenReturn(new ResponseEntity<>(vacationRequest4, HttpStatus.CREATED));
        assertEquals(new ResponseEntity<>(vacationRequest4, HttpStatus.CREATED), mockVacationRequestService.addVacationRequest(vacationRequest4));
        Mockito.verify(mockVacationRequestService).addVacationRequest(vacationRequest4);

    }

    @Test
    public void testGetVacationRequestById(){
        Mockito.when(mockVacationRequestService.getVacationRequestById(vacationRequest1.getId(),user2)).thenReturn(ResponseEntity.ok(vacationRequest1));
        assertEquals(ResponseEntity.ok(vacationRequest1), mockVacationRequestService.getVacationRequestById(vacationRequest1.getId(),user2));
        Mockito.verify(mockVacationRequestService).getVacationRequestById(vacationRequest1.getId(),user2);

    }
    @Test
    public void testGetVacationRequestByIdForbidden(){
        Mockito.when(mockVacationRequestService.getVacationRequestById(1L,user2)).thenReturn(new ResponseEntity<>(vacationRequest1,HttpStatus.FORBIDDEN));
        assertEquals(new ResponseEntity<>(vacationRequest1,HttpStatus.FORBIDDEN), mockVacationRequestService.getVacationRequestById(1L,user2));
        Mockito.verify(mockVacationRequestService).getVacationRequestById(vacationRequest1.getId(),user2);
    }
    @Test
    public void testGetVacationRequestByIdLogic(){
        ResponseEntity<VacationRequest> returnValue;
        Mockito.when(mockVacationRequestService.getVacationRequestById(1L,user2)).thenReturn(new ResponseEntity<>(vacationRequest1,HttpStatus.FORBIDDEN));
            if (user2.isAdmin()){
            returnValue  = mockVacationRequestService.getVacationRequestById(1L,user2);

            }else if (user2.getId() != mockVacationRequestService.getVacationRequestById(1L, user2).getBody().getUser().getId() ||
                        mockVacationRequestService.getVacationRequestById(1L, user2).getBody().getStatusId().getStatus() != RequestState.APPROVED){
               returnValue = null;
            }else{
                returnValue  = mockVacationRequestService.getVacationRequestById(1L,user2);

            }

        assertNotEquals(ResponseEntity.ok(vacationRequest1),returnValue);
    }
    @Test
    public void testGetVacationRequestByIdLogicAdmin(){
        ResponseEntity<VacationRequest> returnValue;
        Mockito.when(mockVacationRequestService.getVacationRequestById(1L,user3)).thenReturn(new ResponseEntity<>(vacationRequest1,HttpStatus.OK));
        if (user3.isAdmin()){
            returnValue  = mockVacationRequestService.getVacationRequestById(1L,user3);

        }else if (user3.getId() != mockVacationRequestService.getVacationRequestById(1L, user3).getBody().getUser().getId() ||
                mockVacationRequestService.getVacationRequestById(1L, user2).getBody().getStatusId().getStatus() != RequestState.APPROVED){
            returnValue = null;
        }else{
            returnValue  = mockVacationRequestService.getVacationRequestById(1L,user3);
        }
        assertEquals(ResponseEntity.ok(vacationRequest1),returnValue);
    }
    @Test
    public void testPatchVacationRequestById(){
        vacationRequest1.setUpdated(false);
        Mockito.when(mockVacationRequestService.updateVacationRequestById(1L,user1, vacationRequest2)).thenReturn(ResponseEntity.ok(vacationRequest1));
        assertEquals(ResponseEntity.ok(vacationRequest1), mockVacationRequestService.updateVacationRequestById(1L,user1, vacationRequest2));
        Mockito.verify(mockVacationRequestService).updateVacationRequestById(1L,user1,vacationRequest2);
    }
    @Test
    public void testPatchVacationRequestNonAdminUpdatedFalse(){
        ResponseEntity<VacationRequest> returnValue;
        var user = user1;
        var id = 1L;
        var vacationRequest = vacationRequest1;
        vacationRequest.setUpdated(false);
        VacationRequest vacationRequestUpdated = new VacationRequest(1L, "HONDURAS", "In a week", "3 weeks later", user1 );
        vacationRequestUpdated.setStatusId(vacationRequestStatusPENDING);
        Mockito.when(mockVacationRequestService.updateVacationRequestById(id,user,vacationRequestUpdated)).thenReturn(ResponseEntity.ok(vacationRequestUpdated));
        if(user.isAdmin()){
            returnValue = mockVacationRequestService.updateVacationRequestById(id,user,vacationRequestUpdated);
        }else{
            if(!vacationRequest.isUpdated()){
                returnValue = mockVacationRequestService.updateVacationRequestById(id,user,vacationRequestUpdated);
                System.out.println(returnValue.getBody().getTitle());
            }else if(vacationRequest.getStatusId() != vacationRequestUpdated.getStatusId()){
                returnValue = null;
            }else{
                returnValue = mockVacationRequestService.updateVacationRequestById(id,user,vacationRequestUpdated);
            }
        }
        assertEquals(ResponseEntity.ok(vacationRequestUpdated),returnValue);
    }
    @Test
    public void testPatchVacationRequestAdmin(){
        ResponseEntity<VacationRequest> returnValue;
        var user = user3;
        var id = 1L;
        var vacationRequest = vacationRequest1;
        vacationRequest.setUpdated(false);
        VacationRequest vacationRequestUpdated = new VacationRequest(1L, "HONDURAS", "In a week", "3 weeks later", user1 );
        vacationRequestUpdated.setStatusId(vacationRequestStatusPENDING);
        Mockito.when(mockVacationRequestService.updateVacationRequestById(id,user,vacationRequestUpdated)).thenReturn(ResponseEntity.ok(vacationRequestUpdated));
        if(user.isAdmin()){
            returnValue = mockVacationRequestService.updateVacationRequestById(id,user,vacationRequestUpdated);
        }else{
            if(!vacationRequest.isUpdated()){
                returnValue = mockVacationRequestService.updateVacationRequestById(id,user,vacationRequestUpdated);
            }else if(vacationRequest.getStatusId() != vacationRequestUpdated.getStatusId()){
                returnValue = null;
            }else{
                returnValue = mockVacationRequestService.updateVacationRequestById(id,user,vacationRequestUpdated);
            }
        }
        assertEquals(ResponseEntity.ok(vacationRequestUpdated),returnValue);
    }
    @Test
    public void testDeleteVacationRequest(){
        Mockito.when(mockVacationRequestService.deleteVacationRequestById(vacationRequest1.getId())).thenReturn(ResponseEntity.ok(vacationRequest1.getId()));
        assertEquals(ResponseEntity.ok(1L), mockVacationRequestService.deleteVacationRequestById(vacationRequest1.getId()));
        Mockito.verify(mockVacationRequestService).deleteVacationRequestById(vacationRequest1.getId());
    }
}
