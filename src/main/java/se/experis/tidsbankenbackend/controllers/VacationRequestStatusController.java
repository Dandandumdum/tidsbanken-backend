package se.experis.tidsbankenbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.experis.tidsbankenbackend.models.VacationRequestStatus;
import se.experis.tidsbankenbackend.services.VacationRequestStatusService;

import java.util.List;

@RestController
public class VacationRequestStatusController {


    @Autowired
    private VacationRequestStatusService vacationRequestStatusService;

    /**
     * Get all VacationRequestStatus
     *
     * @return - all VacationRequestStatus
     */
    @GetMapping
    public ResponseEntity<List<VacationRequestStatus>> getAllVRS() {
        return vacationRequestStatusService.getAllVRS();
    }
}
