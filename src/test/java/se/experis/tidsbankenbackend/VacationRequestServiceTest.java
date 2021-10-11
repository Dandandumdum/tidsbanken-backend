package se.experis.tidsbankenbackend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.models.VacationRequest;
import se.experis.tidsbankenbackend.services.VacationRequestService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationRequestServiceTest {

    VacationRequestService mockVacationRequestService = Mockito.mock(VacationRequestService.class);
    User user1 = new User(1L, "CAT PIC", false, null );
    VacationRequest vacationRequest1 = new VacationRequest(1L, "Mexico", "NOW", "Tomorrow", user1);
    VacationRequest vacationRequest2 = new VacationRequest(2L, "Mexico2", "NOW", "Tomorrow", user1);

    @Test
    public void testGetAllVacationRequests(){

        List<VacationRequest> allVacationRequests = new ArrayList<>();
        allVacationRequests.add(vacationRequest1);
        allVacationRequests.add(vacationRequest2);

        Mockito.when(mockVacationRequestService.getAllVacationRequests()).thenReturn(ResponseEntity.ok(allVacationRequests));
        assertEquals(ResponseEntity.ok(allVacationRequests), mockVacationRequestService.getAllVacationRequests());
    }

}
