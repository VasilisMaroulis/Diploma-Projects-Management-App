package myy803.courses_mgt_app.ServiceTests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import myy803.diplomas_mgt_app.dao.ProfessorDAO;
import myy803.diplomas_mgt_app.dao.StudentDAO;
import myy803.diplomas_mgt_app.dao.SubjectDAO;
import myy803.diplomas_mgt_app.dao.ThesisDAO;
import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.Subject;
import myy803.diplomas_mgt_app.model.Thesis;
import myy803.diplomas_mgt_app.model.User;
import myy803.diplomas_mgt_app.service.ApplicationService;
import myy803.diplomas_mgt_app.service.ProfessorService;
import myy803.diplomas_mgt_app.service.ProfessorServiceImpl;
import myy803.diplomas_mgt_app.service.ThesisService;



@ExtendWith(SpringExtension.class)
class TestProfessorService {

	@TestConfiguration
	static class ProfessorServiceImplTestContextConfiguration{
		
		
		@Bean
		public ProfessorService professorService() {
			return new ProfessorServiceImpl();
		}	
	}
	
	@Autowired
	ProfessorService professorService;
	
	@MockBean
	ProfessorDAO professorRepository;
	
	@MockBean
	SubjectDAO subjectRepository;
	

	@MockBean
	private ThesisDAO thesisRepository;
	
	@MockBean
	private StudentDAO studentRepository;
		
	@MockBean
	private ApplicationService applicationService;
	
	@MockBean
	private ThesisService thesisService;
	
	
	
	@Test
	void testProfessorServiceIsNotNull() {
		Assertions.assertNotNull(professorService);
	}
	
	User user = new User();
	
	
	@Test
	void testFindByUserIdReturnsProfessor() {
		Mockito.when(professorRepository.findByUserId(1)).thenReturn(new Professor("Pavlos", "Prof", "Mathematician", user));
		
		Professor storedProfessor = professorService.findByUserId(1);
		Assertions.assertNotNull(storedProfessor);
		Assertions.assertEquals("Pavlos", storedProfessor.getFirstName());
		Assertions.assertEquals("Prof", storedProfessor.getLastName());
		Assertions.assertEquals("Mathematician", storedProfessor.getSpecialty());
		Assertions.assertEquals(user, storedProfessor.getUser());
	}
	
	@Test
	void testSave() {
		Professor theProfessor = new Professor();
		//Mockito.when(professorRepository.save(theProfessor)).thenReturn(theProfessor);
		//Assertions.assertNotNull(theProfessor);
		
		professorService.save(theProfessor);
        
        verify(professorRepository, times(1)).save(theProfessor);
		
	}
	
	@Test
	void testAssignSubjectToSpecific() {
		Student theStudent = new Student();
		theStudent.setId(2);
		Subject theSubject = new Subject();
		theSubject.setId(1);
		//Thesis thesis = new Thesis(theStudent, theSubject);
		
		Mockito.when(studentRepository.findById(2)).thenReturn(theStudent);
		Mockito.when(subjectRepository.findById(1)).thenReturn(theSubject);
		
//		professorService.assignSubjectToSpecific(2, 1);
//		verify(thesisRepository, times(1)).save(new Thesis());
		//Mockito.when(thesisRepository.save(the)).thenReturn(new Thesis());
		//Assertions.assertNotNull(theThesis);
		//Assertions.assertEquals(theStudent, theThesis.getStudent());
	}

}

