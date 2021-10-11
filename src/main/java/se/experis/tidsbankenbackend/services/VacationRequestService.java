package se.experis.tidsbankenbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.experis.tidsbankenbackend.models.VacationRequest;
import se.experis.tidsbankenbackend.repositories.VacationRequestRepository;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class VacationRequestService {

    @Autowired
    private VacationRequestRepository vacationRequestRepository;

    public ResponseEntity<List<VacationRequest>> getAllVacationRequests(){
        List<VacationRequest> allRequests = vacationRequestRepository.findAll();
        if(!allRequests.isEmpty()){
            return new ResponseEntity<>(allRequests, HttpStatus.OK);
        } else return new ResponseEntity<>(allRequests, HttpStatus.NOT_FOUND);
    }
}
