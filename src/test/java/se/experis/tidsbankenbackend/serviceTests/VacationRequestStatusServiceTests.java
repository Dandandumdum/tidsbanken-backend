package se.experis.tidsbankenbackend.serviceTests;


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

public class VacationRequestStatusServiceTests {

    VacationRequestStatus vacationRequestStatusAPPROVED = new VacationRequestStatus(1L, RequestState.APPROVED);
    VacationRequestStatus vacationRequestStatusDENIED = new VacationRequestStatus(2L, RequestState.DENIED);
    VacationRequestStatus vacationRequestStatusPENDING = new VacationRequestStatus(3L, RequestState.PENDING);
    @Test
    public void testGetAll(){
        var statusTester = new VacationRequestStatusService();
        List<VacationRequestStatus> VCS = new ArrayList<>();
        VCS.add(vacationRequestStatusAPPROVED);
        VCS.add(vacationRequestStatusDENIED);
        VCS.add(vacationRequestStatusPENDING);

        assertEquals(statusTester.getAllVRS().getBody(), VCS);

    }

}
