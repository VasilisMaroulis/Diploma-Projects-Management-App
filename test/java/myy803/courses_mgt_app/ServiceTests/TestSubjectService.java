package myy803.courses_mgt_app.ServiceTests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import myy803.diplomas_mgt_app.dao.SubjectDAO;
import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Subject;
import myy803.diplomas_mgt_app.service.SubjectService;
import myy803.diplomas_mgt_app.service.SubjectServiceImpl;


@ExtendWith(SpringExtension.class)
public class TestSubjectService {

	@TestConfiguration
	static class SubjectServiceImplTestContextConfiguration{
		
		
		@Bean
		public SubjectService subjectService() {
			return new SubjectServiceImpl(null);
		}	
	}
	
	@Autowired
	SubjectService subjectService;
	
	@MockBean
	SubjectDAO subjectRepository;
	
	Professor professor = new Professor();
	
	@Test
	void testSubjectServiceIsNotNull() {
		Assertions.assertNotNull(subjectService);
	}
	
	
	@Test
	void testFindByIdReturnsSubject() {
		Subject theSubject = new Subject();
		theSubject.setId(7);
		
		Mockito.when(subjectRepository.findById(7)).thenReturn(theSubject);
		
		Subject storedSubject = subjectService.findById(7);
		Assertions.assertNotNull(storedSubject);
		Assertions.assertEquals(7, storedSubject.getId());
		
	}
	
	@Test
	void testSaveSubject() {
	
		Subject theSubject = new Subject();
			
		Mockito.when(subjectRepository.save(theSubject)).thenReturn(theSubject);
		Assertions.assertNotNull(theSubject);
	}
	
	@Test
	void testDeleteSubject() {
		//TO DO
		
	}
	
	@Test
	void testFindByProfessorReturnsSubjects() {
		Subject theSubject = new Subject();
		theSubject.setProfessor(professor);
		List<Subject> subjects = new ArrayList<Subject>();
		subjects.add(theSubject);
		
		Mockito.when(subjectRepository.findByProfessor(professor)).thenReturn(subjects);
		
		List<Subject> storedSubjects = subjectService.findByProfessor(professor);
		Assertions.assertNotNull(storedSubjects);
		Assertions.assertEquals(professor, storedSubjects.get(0).getProfessor());
		
	}	
	
	@Test
	void testFindAllAvailableReturnsSubjects() {
		Subject subject1 = new Subject();
		Subject subject2 = new Subject();
		List<Subject> subjects = new ArrayList<Subject>();
		subject1.setTaken(true);
		subject2.setTaken(false);
		subjects.add(subject1);
		subjects.add(subject2);
		
		Mockito.when(subjectRepository.findAll()).thenReturn(subjects);
		
		List<Subject> availableSubjects = subjectService.findAllAvailable();
		Assertions.assertNotNull(availableSubjects);
		Assertions.assertEquals(1, availableSubjects.size());
		Assertions.assertFalse(availableSubjects.get(0).isTaken());
		
		
		
	}	
	
	
	
}
