package se.experis.tidsbankenbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.experis.tidsbankenbackend.enums.RequestState;
import se.experis.tidsbankenbackend.models.VacationRequestStatus;
import se.experis.tidsbankenbackend.repositories.VacationRequestStatusRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VacationRequestStatusService {

    @Autowired
    private VacationRequestStatusRepository vacationRequestStatusRepository;

    public ResponseEntity<List<VacationRequestStatus>> getAllVRS(){
        List<VacationRequestStatus> Statuses= vacationRequestStatusRepository.findAll();
        if (!Statuses.isEmpty()){
            return new ResponseEntity<>(Statuses, HttpStatus.OK);
        }else return new ResponseEntity<>(Statuses, HttpStatus.BAD_REQUEST);
    }
}
