package myy803.courses_mgt_app.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import myy803.diplomas_mgt_app.dao.ThesisDAO;
import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.Subject;
import myy803.diplomas_mgt_app.model.Thesis;
import myy803.diplomas_mgt_app.service.ThesisService;
import myy803.diplomas_mgt_app.service.ThesisServiceImpl;

@ExtendWith(SpringExtension.class)
public class TestThesisService {

	@TestConfiguration
	static class ThesisServiceImplTestContextConfiguration{
		
		
		@Bean
		public ThesisService thesisService() {
			return new ThesisServiceImpl(null);
		}	
	}
	
	@Autowired
	ThesisService thesisService;
		
	
	@MockBean
	ThesisDAO thesisRepository;
	
	Professor professor = new Professor();
	Student student = new Student();
	Subject subject = new Subject();
	
	@Test
	void testThesisServiceIsNotNull() {
		Assertions.assertNotNull(thesisService);
	}	

	@Test
	void testFindByIdReturnsThesis() {
		Thesis theThesis = new Thesis();
		theThesis.setId(4);
		
		Mockito.when(thesisRepository.findById(4)).thenReturn(theThesis);
		
		Thesis storedThesis = thesisService.findById(4);
		Assertions.assertNotNull(storedThesis);
		Assertions.assertEquals(4, storedThesis.getId());
		
	}
	
	@Test
	void testFindByProfessorReturnsThesis() {
		Thesis theThesis = new Thesis();
		theThesis.setProfessor(professor);
		List<Thesis> theses = new ArrayList<Thesis>();
		theses.add(theThesis);
		
		Mockito.when(thesisRepository.findByProfessor(professor)).thenReturn(theses);
		
		List<Thesis> storedThesis = thesisService.findByProfessor(professor);
		Assertions.assertNotNull(storedThesis);
		Assertions.assertEquals(professor, storedThesis.get(0).getProfessor());
		
	}
	
	@Test
	void testSaveThesis() {
	
		Thesis theThesis = new Thesis();
			
		Mockito.when(thesisRepository.save(theThesis)).thenReturn(theThesis);
		Assertions.assertNotNull(theThesis);
	}
	
	@Test
	void testIsThesisPresentReturnsTrue() {
		Thesis theThesis = new Thesis();
		theThesis.setSubject(subject);
		
		Mockito.when(thesisRepository.findBySubject(subject)).thenReturn(Optional.of(theThesis));
		
		boolean exists = thesisService.isThesisPresent(theThesis);
		assertTrue(exists);
		
	}
	
	@Test
	void testIsThesisPresentReturnsFalse() {
		Thesis theThesis = new Thesis();
		theThesis.setSubject(subject);
		
		Mockito.when(thesisRepository.findBySubject(subject)).thenReturn(Optional.empty());
		
		boolean exists = thesisService.isThesisPresent(theThesis);
		assertFalse(exists);
		
	}
	
	@Test
	void testIsStudentinThesesReturnsTrue() {
		Thesis theThesis = new Thesis();
		theThesis.setStudent(student);
		
		Mockito.when(thesisRepository.findByStudent(student)).thenReturn(Optional.of(theThesis));
		
		boolean exists = thesisService.isStudentInTheses(theThesis);
		assertTrue(exists);
		
	}
	
	@Test
	void testIsStudentinThesesReturnsFalse() {
		Thesis theThesis = new Thesis();
		theThesis.setStudent(student);
		
		Mockito.when(thesisRepository.findByStudent(student)).thenReturn(Optional.empty());
		
		boolean exists = thesisService.isStudentInTheses(theThesis);
		assertFalse(exists);
		
	}
	
	
}
