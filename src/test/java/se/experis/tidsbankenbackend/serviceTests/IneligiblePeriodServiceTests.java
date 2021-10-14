package se.experis.tidsbankenbackend.serviceTests;


import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.experis.tidsbankenbackend.TidsbankenBackendApplication;
import se.experis.tidsbankenbackend.models.IneligiblePeriod;
import se.experis.tidsbankenbackend.models.User;
import se.experis.tidsbankenbackend.models.VacationRequest;
import se.experis.tidsbankenbackend.repositories.IneligiblePeriodRepository;
import se.experis.tidsbankenbackend.services.IneligiblePeriodService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes= {TidsbankenBackendApplication.class})
//@WebMvcTest(IneligiblePeriodService.class)
public class IneligiblePeriodServiceTests {

    IneligiblePeriodRepository mockIneligiblePeriodRepository = mock(IneligiblePeriodRepository.class);
    IneligiblePeriodService mockIneligiblePeriodService = mock(IneligiblePeriodService.class);

    @InjectMocks
    private IneligiblePeriodService ineligiblePeriodService;

    @Mock
    @Autowired
    private IneligiblePeriodRepository ineligiblePeriodRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    User user1 = new User(1L, "CAT PIC", false, null );
    User user2 = new User(2L, "HORSE PIC", false, null );
    User user3 = new User(3L, "WHALE PIC", true, null );
    VacationRequest vacationRequest1 = new VacationRequest(1L, "Mexico", "NOW", "Tomorrow", user1);
    VacationRequest vacationRequest2 = new VacationRequest(2L, "Mexico2", "NOW", "Tomorrow", user2);
    VacationRequest vacationRequest3 = new VacationRequest(3L, "Mexico3", "NOW","Tomorrow", user3);
    IneligiblePeriod testIneligible1 = new IneligiblePeriod(1L,"Now","Later",user1);
    IneligiblePeriod testIneligible2 = new IneligiblePeriod(2L, "22","33",user2);
    IneligiblePeriod testIneligible3 = new IneligiblePeriod(3L, "Well", "Long",user3);

    private ArrayList<IneligiblePeriod> getTestPeriodsList(){
        ArrayList<IneligiblePeriod> periods = new ArrayList<>();
        periods.add(testIneligible1);
        periods.add(testIneligible2);
        periods.add(testIneligible3);
        return periods;
    }
    private List<IneligiblePeriod> getRepoList(){
        List<IneligiblePeriod> repoPeriods = ineligiblePeriodRepository.findAll();
        return repoPeriods;
    }


    @Test
    public void testGetAllIneligiblePeriods(){
        List<IneligiblePeriod> list = getTestPeriodsList();
        List<IneligiblePeriod> repoList = ineligiblePeriodRepository.findAll();

       // doReturn(list).when(mockIneligiblePeriodRepository).findAll();
        doReturn(ResponseEntity.ok(list)).when(mockIneligiblePeriodService).getAllIneligiblePeriods();
        assertEquals(repoList.size(), mockIneligiblePeriodService.getAllIneligiblePeriods().getBody().size());
    }
    @Test
    public void testAddIneligiblePeriod(){
        List<IneligiblePeriod> list = getTestPeriodsList();
        IneligiblePeriod ineligiblePeriod4 = new IneligiblePeriod(4L, "Yesterday", "Today",user3);
        list.add(ineligiblePeriod4);
        //mockIneligiblePeriodRepository.deleteById(4);
        ineligiblePeriodRepository.save(ineligiblePeriod4);
        List<IneligiblePeriod> repoListNew = ineligiblePeriodRepository.findAll();

        doReturn(ResponseEntity.ok(list.get(3).getPeriodStart())).when(mockIneligiblePeriodService).getIneligiblePeriodByID(4L);
        assertEquals(ResponseEntity.ok(repoListNew.get(repoListNew.size()-1).getPeriodStart()), mockIneligiblePeriodService.getIneligiblePeriodByID(4L));

    }
    @Test
    public void testGetIneligiblePeriodById1L(){
        List<IneligiblePeriod> list = getTestPeriodsList();
        List<IneligiblePeriod> repoList = getRepoList();
        doReturn(ResponseEntity.ok(list.get(0).getId())).when(mockIneligiblePeriodService).getIneligiblePeriodByID(1L);
        assertEquals(ResponseEntity.ok(repoList.get(0)).getBody().getId(), mockIneligiblePeriodService.getIneligiblePeriodByID(1L).getBody());
    }

    @Test
    public void testDeleteIneligiblePeriodById1L(){
        List<IneligiblePeriod> list = getTestPeriodsList();
        list.remove(0);
        ineligiblePeriodRepository.deleteById(1L);
        List<IneligiblePeriod> repoListNew = ineligiblePeriodRepository.findAll();

        doReturn(ResponseEntity.ok(list.size())).when(mockIneligiblePeriodService).getAllIneligiblePeriods();
        assertEquals(ResponseEntity.ok(repoListNew.size()), mockIneligiblePeriodService.getAllIneligiblePeriods());
    }
    @Test
    public void testPatchIneligiblePeriod(){
        List<IneligiblePeriod> list = getTestPeriodsList();
        List<IneligiblePeriod> repoList = getRepoList();
        IneligiblePeriod updatePeriod = new IneligiblePeriod(1L,"1066","Later",user1);
        ineligiblePeriodRepository.deleteById(1L);
        ineligiblePeriodRepository.save(updatePeriod);
        List<IneligiblePeriod> repoListNew = ineligiblePeriodRepository.findAll();

        list.remove(0);
        list.add(updatePeriod);

        doReturn(ResponseEntity.ok(list.get(list.size()-1).getPeriodEnd())).when(mockIneligiblePeriodService).updateIneligiblePeriod(1L,updatePeriod);
        assertEquals(ResponseEntity.ok(repoListNew.get(repoListNew.size()-1).getPeriodEnd()), mockIneligiblePeriodService.updateIneligiblePeriod(1L,updatePeriod));

    }
}
