package se.experis.tidsbankenbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.models.VacationRequest;
import se.experis.tidsbankenbackend.services.VacationRequestService;
import java.util.List;

@RestController
public class VacationRequestController {

    @Autowired
    private VacationRequestService vacationRequestService;

    /**
     * Get all VacationRequest
     *
     * @return - all VacationRequest
     */
    @GetMapping
    public ResponseEntity<List<VacationRequest>> getAllVacationRequests(User user) {
        return vacationRequestService.getAllVacationRequests(user);
    }

    /**
     * Get specific VacationRequest
     *
     * @param id -VacationRequest id
     * @return - info about that specific VacationRequest
     */
    @GetMapping("/{id}")
    public ResponseEntity<VacationRequest> getVacationRequestById(@PathVariable Long id, User user) {
        return vacationRequestService.getVacationRequestById(id, user);
    }

    @PostMapping
    public ResponseEntity<VacationRequest> addVacationRequest (@RequestBody VacationRequest vacationRequest) {

        return vacationRequestService.addVacationRequest(vacationRequest);
    }


}
