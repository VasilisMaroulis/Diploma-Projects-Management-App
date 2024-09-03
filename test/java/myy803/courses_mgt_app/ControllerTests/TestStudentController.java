package myy803.courses_mgt_app.ControllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import myy803.diplomas_mgt_app.controller.StudentController;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.User;
import myy803.diplomas_mgt_app.service.StudentService;




@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class TestStudentController {

	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
		
	@MockBean
	StudentController studentController;
	
	@MockBean
	StudentService studentService;
	
	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testStudentControllerIsNotNull() {
		Assertions.assertNotNull(studentController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	@Test
	void testStudentServiceIsNotNull() {
		Assertions.assertNotNull(studentService);
	}
		
	@Test
	void testGetStudentHomeReturnsPage() throws Exception {
		
		mockMvc.perform(get("/student/dashboard")).
		andExpect(status().isOk()). 
		andExpect(view().name("student/dashboard")); 
	}
	
	User user = new User();
	
	@Test 
	void testSaveStudentReturnsPage() throws Exception {
		
		Student student = new Student("Makis", "Giwrgos", 7, 7, 7, user);
		
		Mockito.doNothing().when(studentService).save(student);
		
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
	    multiValueMap.add("id", Integer.toString(student.getId()));
	    multiValueMap.add("firstName", student.getFirstName());
	    multiValueMap.add("lastName", student.getLastName());
	    multiValueMap.add("avgGrade", Float.toString(student.getAvgGrade()));
	    multiValueMap.add("numRemainCourses", Float.toString(student.getNumRemainCourses()));
	    multiValueMap.add("year", Float.toString(student.getYear()));
	    multiValueMap.add("user", Integer.toString(student.getUser().getId()));
	   
	    
		mockMvc.perform(
			post("/student/save").
			params(multiValueMap)).
			andExpect(status().isFound()).
			andExpect(view().name("student/information")); 	
	}
	
	@Test 
	void testUpdateStudentReturnsPage() throws Exception {
		
		mockMvc.perform(get("/student/info")).
		andExpect(status().isOk()).
		andExpect(view().name("student/information")); 	
	}
	
	@Test 
	void testShowSubjectsReturnsPage() throws Exception {
		mockMvc.perform(get("/student/showsubjects")).
		andExpect(status().isOk()).
		andExpect(view().name("student/subjects-list-stud")); 	
	}
	
	@Test 
	void testShowDetailsReturnsPage() throws Exception {
		mockMvc.perform(post("/student/details")).
		//params("subId", "100")).
		andExpect(status().isFound()).
		andExpect(view().name("student/subject-details-list-stud")); 	
	}
	
	@Test 
	void testApplyToSubjectReturnsPage() throws Exception {
		mockMvc.perform(post("/student/apply")).
		andExpect(status().isFound()).
		andExpect(view().name("student/subject-details-list-stud")); 	
	}
}
