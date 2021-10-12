package se.experis.tidsbankenbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.experis.tidsbankenbackend.enums.RequestState;
import se.experis.tidsbankenbackend.models.Moderator;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.models.VacationRequest;
import se.experis.tidsbankenbackend.repositories.ModeratorRepository;
import se.experis.tidsbankenbackend.repositories.UserRepository;
import se.experis.tidsbankenbackend.repositories.VacationRequestRepository;

import java.lang.module.ResolutionException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VacationRequestService {

    @Autowired
    private VacationRequestRepository vacationRequestRepository;
    private UserRepository userRepository;
    private ModeratorRepository moderatorRepository;

    public ResponseEntity<List<VacationRequest>> getAllVacationRequests(User user){
        List<VacationRequest> nonAdminRequests = new ArrayList<>();
        List<VacationRequest> allRequests = vacationRequestRepository.findAll();
        if(!allRequests.isEmpty()){
            if(user.isAdmin()){
                return new ResponseEntity<>(allRequests, HttpStatus.OK);
            }else{
                nonAdminRequests.addAll(vacationRequestRepository.findAllByStatusId_Status("APPROVED"));
                nonAdminRequests.addAll(vacationRequestRepository.findAllByUser(user.getId()));
                return new ResponseEntity<>(nonAdminRequests, HttpStatus.OK);
            }
        } else return new ResponseEntity<>(allRequests, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<VacationRequest> addVacationRequest(VacationRequest vacationRequest){
        var returnRequest = new VacationRequest();
        if(vacationRequest != null){
            returnRequest = vacationRequestRepository.save(vacationRequest);
            return new ResponseEntity<>(returnRequest, HttpStatus.CREATED);
        }else return new ResponseEntity<>(returnRequest, HttpStatus.NO_CONTENT);
    }
    public ResponseEntity<VacationRequest> getVacationRequestById(Long id, User user){
        var returnRequest = new VacationRequest();
        if(vacationRequestRepository.existsById(id)){
            if (user.isAdmin()){
                returnRequest = vacationRequestRepository.getById(id);
                return new ResponseEntity<>(returnRequest, HttpStatus.OK);
            }else if (user.getId() != vacationRequestRepository.getById(id).getUser().getId() || vacationRequestRepository.getById(id).getStatusId().getStatus() != RequestState.APPROVED){
                return new ResponseEntity<>(returnRequest, HttpStatus.FORBIDDEN);
            }else{
                returnRequest = vacationRequestRepository.getById(id);
                return new ResponseEntity<>(returnRequest, HttpStatus.OK);
            }
        }else return new ResponseEntity<>(returnRequest, HttpStatus.NO_CONTENT);
    }
    public ResponseEntity<VacationRequest> updateVacationRequestById(Long id, User user, VacationRequest vacationRequest){
        var returnRequest = new VacationRequest();
        VacationRequest requestToUpdate = vacationRequestRepository.findById(id).get();
        if(!vacationRequestRepository.existsById(id)){
            if(user.isAdmin()){
                vacationRequest.setUpdated(true);
                vacationRequest.setUpdatedTimestamp(new Timestamp(new Date().getTime()));
                vacationRequest.setModerator(moderatorRepository.getByUserId(user.getId()));
                returnRequest = vacationRequestRepository.save(vacationRequest);
                return new ResponseEntity<>(returnRequest, HttpStatus.OK);

            }else {
                if( !getVacationRequestById(id, user).getBody().isUpdated()){
                    requestToUpdate = vacationRequestRepository.save(vacationRequest);
                    returnRequest = requestToUpdate;
                    return new ResponseEntity<>(returnRequest, HttpStatus.OK);
                }else if(vacationRequest.getStatusId().getStatus() != getVacationRequestById(id, user).getBody().getStatusId().getStatus()){
                    return new ResponseEntity<>(returnRequest, HttpStatus.FORBIDDEN);
                }else{
                    returnRequest = vacationRequestRepository.save(vacationRequest);
                    requestToUpdate = vacationRequestRepository.save(vacationRequest);
                    returnRequest = requestToUpdate;
                    return new ResponseEntity<>(returnRequest, HttpStatus.OK);
                }
            }
        }else return new ResponseEntity<>(returnRequest,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Long> deleteVacationRequestById (Long id){
        if(vacationRequestRepository.existsById(id)){
            vacationRequestRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }else return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
    }
}
