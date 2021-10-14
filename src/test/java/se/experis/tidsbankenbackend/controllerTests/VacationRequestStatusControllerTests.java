package se.experis.tidsbankenbackend.controllerTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import se.experis.tidsbankenbackend.enums.RequestState;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.models.VacationRequest;
import se.experis.tidsbankenbackend.models.VacationRequestStatus;
import se.experis.tidsbankenbackend.repositories.VacationRequestRepository;
import se.experis.tidsbankenbackend.repositories.VacationRequestStatusRepository;
import se.experis.tidsbankenbackend.services.VacationRequestService;
import se.experis.tidsbankenbackend.services.VacationRequestStatusService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VacationRequestStatusControllerTests {

    VacationRequestStatusService mockService = mock(VacationRequestStatusService.class);
   // VacationRequestStatusService mockService = new VacationRequestStatusService(mockRepository);
@BeforeEach
           void setUp() throws Exception{
    User user1 = new User(1L, "CAT PIC", false, null );
    User user2 = new User(2L, "HORSE PIC", false, null );
    User user3 = new User(3L, "WHALE PIC", true, null );
    VacationRequest vacationRequest1 = new VacationRequest(1L, "Mexico", "NOW", "Tomorrow", user1);
    VacationRequest vacationRequest2 = new VacationRequest(2L, "Mexico2", "NOW", "Tomorrow", user2);
    VacationRequest vacationRequest3 = new VacationRequest(3L, "Mexico3", "NOW", "Tomorrow", user3);

}
    VacationRequestStatus vacationRequestStatusAPPROVED = new VacationRequestStatus(1L, RequestState.APPROVED);
    VacationRequestStatus vacationRequestStatusDENIED = new VacationRequestStatus(2L, RequestState.DENIED);
    VacationRequestStatus vacationRequestStatusPENDING = new VacationRequestStatus(3L, RequestState.PENDING);
    @Test
    public void testGetAll(){
        List<VacationRequestStatus> VCS = new ArrayList<>();
        VCS.add(vacationRequestStatusAPPROVED);
        VCS.add(vacationRequestStatusDENIED);
        VCS.add(vacationRequestStatusPENDING);

        when(mockService.getAllVRS()).thenReturn(ResponseEntity.ok(VCS));
        assertEquals(ResponseEntity.ok(VCS), mockService.getAllVRS());


    }

}
