package se.experis.tidsbankenbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.experis.tidsbankenbackend.models.IneligiblePeriod;
import se.experis.tidsbankenbackend.repositories.IneligiblePeriodRepository;

import java.util.ArrayList;
import java.util.List;
@Component
@Service
public class IneligiblePeriodService {

    @Autowired
    private IneligiblePeriodRepository ineligiblePeriodRepository;

    public ResponseEntity<List<IneligiblePeriod>> getAllIneligiblePeriods(){
        List<IneligiblePeriod> ineligiblePeriods = ineligiblePeriodRepository.findAll();
        if(!ineligiblePeriods.isEmpty()){
            return new ResponseEntity<>(ineligiblePeriods, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(ineligiblePeriods, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<IneligiblePeriod> addIneligiblePeriod(IneligiblePeriod ineligiblePeriod){
        var returnIneligiblePeriod = new IneligiblePeriod();
        if(ineligiblePeriod != null ){
            returnIneligiblePeriod = ineligiblePeriodRepository.save(ineligiblePeriod);
            return new ResponseEntity<>(returnIneligiblePeriod, HttpStatus.OK);
        }else return new ResponseEntity<>(returnIneligiblePeriod, HttpStatus.NO_CONTENT);

    }

    public ResponseEntity<Long> getIneligiblePeriodByID(Long id){
        if (ineligiblePeriodRepository.existsById(id)){
            return new ResponseEntity<>(id, HttpStatus.OK);
        }else return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Long> deleteIneligiblePeriodById(Long id){
        if(ineligiblePeriodRepository.existsById(id)){
            ineligiblePeriodRepository.deleteById(id);
            return  new ResponseEntity<>(id, HttpStatus.OK);
        }else return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<IneligiblePeriod> updateIneligiblePeriod(Long id,IneligiblePeriod ineligiblePeriod){
        var returnPeriod = new IneligiblePeriod();
        var periodToUpdate = ineligiblePeriodRepository.getById(id);
        if(ineligiblePeriodRepository.existsById(ineligiblePeriod.getId())){
            periodToUpdate = ineligiblePeriodRepository.save(ineligiblePeriod);
            returnPeriod = periodToUpdate;
           return  new ResponseEntity<>(returnPeriod,HttpStatus.OK);
        }else return  new ResponseEntity<>(returnPeriod,HttpStatus.NOT_FOUND);
    }
}
