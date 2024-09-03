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

import myy803.diplomas_mgt_app.dao.ApplicationDAO;
import myy803.diplomas_mgt_app.model.Application;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.Subject;
import myy803.diplomas_mgt_app.model.Thesis;
import myy803.diplomas_mgt_app.service.ApplicationService;
import myy803.diplomas_mgt_app.service.ApplicationServiceImpl;
import myy803.diplomas_mgt_app.service.ThesisService;




@ExtendWith(SpringExtension.class)
class TestApplicationService {

	@TestConfiguration
	static class ApplicationServiceImplTestContextConfiguration{
		
		
		@Bean
		public ApplicationService applicationService() {
			return new ApplicationServiceImpl();
		}	
	}
	
	
	
	@Autowired
	ApplicationService applicationService;
	
	
	@MockBean
	ThesisService thesisService;
	
	@MockBean
	ApplicationDAO applicationRepository;
	

	@Test
	void testApplicationServiceIsNotNull() {
		Assertions.assertNotNull(applicationService);
	}

	Subject subject = new Subject();
	Student student = new Student();
	
	@Test
	void testfindBySubjectReturnsApplication() {
		Application application1 = new Application();
		Application application2 = new Application();
		application1.setSubject(subject);
		application2.setSubject(subject);
		List<Application> applications = new ArrayList <Application>();
		applications.add(application1);
		applications.add(application2);
		
		Mockito.when(applicationRepository.findBySubject(subject)).thenReturn(applications);
		List<Application> foundApplications = applicationService.findBySubject(subject);
		Assertions.assertNotNull(foundApplications);
		Assertions.assertEquals(subject, foundApplications.get(0).getSubject());
		Assertions.assertEquals(subject, foundApplications.get(1).getSubject());
	}
		
	@Test
	void testSaveApplication() {
	
		Application theApplication = new Application();
			
		Mockito.when(applicationRepository.save(theApplication)).thenReturn(theApplication);
		Assertions.assertNotNull(theApplication);
	}
	
	@Test
	void testIsApplicationPresentReturnsTrue() {
		Application existingApplication =new Application(subject, student);

		Mockito.when(applicationRepository.findByStudentAndSubject(student, subject)).thenReturn(Optional.of(existingApplication));
			
		boolean existing = applicationService.isApplicationPresent(existingApplication);
		assertTrue(existing);	
	}
	
	
	@Test
	void testIsApplicationPresentReturnsFalse() {
		Application nonExistingApplication = new Application();
		Mockito.when(applicationRepository.findByStudentAndSubject(student, subject)).thenReturn(Optional.of(nonExistingApplication));
						
		boolean nonExisting = applicationService.isApplicationPresent(nonExistingApplication);
		assertFalse(nonExisting);
	}
		
	@Test
	void testFilterByThresholdsReturnsApplications(){	
		Application app1 = new Application(null, new Student("first", null, 0, 5.5f, 0, null));
		Application app2 = new Application(null, new Student("second", null, 0, 6.5f, 5, null));
		Application app3 = new Application(null, new Student("third", null, 0, 7.0f, 0, null));
		
		List<Application> applications = new ArrayList<Application>();
		applications.add(app1);
		applications.add(app2);
		applications.add(app3);
		
		List<Application> filtered = applicationService.filterByThresholds(applications, 6.0f, 2);
		
		Assertions.assertNotNull(filtered);
		Assertions.assertEquals(1, filtered.size());
		Assertions.assertEquals("third", filtered.get(0).getStudent().getFirstName());
		
	}
	
	@Test
	void testFilterByStudentAvailabilityReturnsApplications() {
		Thesis theThesis = new Thesis();
		theThesis.setStudent(student);
		Application app1 = new Application(null, student);
		Application app2 = new Application();
	
		List<Application> applications = new ArrayList<Application>();
		applications.add(app1);
		applications.add(app2);
		
		Mockito.when(thesisService.isStudentInTheses(theThesis)).thenReturn(true);
		
		//List<Application> filtered = applicationService.filterByStudentAvailability(applications);
		//Assertions.assertNotNull(filtered);
		//Assertions.assertEquals(1, filtered.size());
				
	}
}
