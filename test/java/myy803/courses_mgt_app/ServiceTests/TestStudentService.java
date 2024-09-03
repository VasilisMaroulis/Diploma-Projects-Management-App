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

import myy803.diplomas_mgt_app.dao.StudentDAO;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.User;
import myy803.diplomas_mgt_app.service.StudentService;
import myy803.diplomas_mgt_app.service.StudentServiceImpl;


@ExtendWith(SpringExtension.class)
class TestStudentService {

	@TestConfiguration
	static class StudentServiceImplTestContextConfiguration{
		
		
		@Bean
		public StudentService studentService() {
			return new StudentServiceImpl();
		}	
	}
	
	@Autowired
	StudentService studentService;
	
	@MockBean
	StudentDAO studentRepository;
	
	@Test
	void testStudentServiceIsNotNull() {
		Assertions.assertNotNull(studentService);
	}
	
	User user = new User();
	
	@Test
	void testFindByIdReturnsStudent() {
		Mockito.when(studentRepository.findById(2)).thenReturn(new Student("Spyros", "StudentOne", 5, 7.5f , 3, user));
		
		Student storedStudent = studentService.findById(2);
		Assertions.assertNotNull(storedStudent);
		Assertions.assertEquals("Spyros", storedStudent.getFirstName());
		Assertions.assertEquals("StudentOne", storedStudent.getLastName());
		Assertions.assertEquals(5, storedStudent.getYear());
		Assertions.assertEquals(7.5, storedStudent.getAvgGrade());
		Assertions.assertEquals(3, storedStudent.getNumRemainCourses());
		Assertions.assertEquals(user, storedStudent.getUser());
	}
	
	@Test
	void testFindByUserIdReturnsStudent() {
		Mockito.when(studentRepository.findByUserId(3)).thenReturn(new Student("Fwths", "StudentTwo", 6, 6.5f , 7, user));
		
		Student storedStudent = studentService.findByUserId(3);
		Assertions.assertNotNull(storedStudent);
		Assertions.assertEquals("Fwths", storedStudent.getFirstName());
		Assertions.assertEquals("StudentTwo", storedStudent.getLastName());
		Assertions.assertEquals(6, storedStudent.getYear());
		Assertions.assertEquals(6.5, storedStudent.getAvgGrade());
		Assertions.assertEquals(7, storedStudent.getNumRemainCourses());
		Assertions.assertEquals(user, storedStudent.getUser());
	}
	
	@Test
	void testSave() {
		Student theStudent = new Student();
		
		//Mockito.when(studentRepository.save(theStudent)).thenReturn(theStudent);
		//Assertions.assertNotNull(theStudent);
		studentService.save(theStudent);
		verify(studentRepository, times(1)).save(theStudent);
	}
}
