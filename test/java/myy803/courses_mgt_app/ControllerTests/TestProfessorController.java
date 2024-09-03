package myy803.courses_mgt_app.ControllerTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import myy803.diplomas_mgt_app.controller.ProfessorController;
import myy803.diplomas_mgt_app.model.User;
import myy803.diplomas_mgt_app.service.ProfessorService;

//@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class TestProfessorController {

	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ProfessorController professorController;
	
	@MockBean
	ProfessorService professorService;
	
	@BeforeEach
    public void setup() {
		mockMvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
    }
	
	@Test
	void testProfessorControllerIsNotNull() {
		Assertions.assertNotNull(professorController);
	}
	
	@Test
	void testMockMvcIsNotNull() {
		Assertions.assertNotNull(mockMvc);
	}
	
	@Test
	void testProfessorServiceIsNotNull() {
		Assertions.assertNotNull(professorService);
	}
	
	User user = new User();
//	
//	@Test
//	void testGetProfessorHomeReturnsPage() throws Exception {
//		
//		mockMvc.perform(get("/professor/dashboard")).
//		andExpect(status().isOk()). 
//		andExpect(view().name("professor/dashboard")); 
//	}
//
//	
//	@Test
//	void testListSubjectsReturnsPage() throws Exception{
//		
//		mockMvc.perform(get("/professor/subjects/list")).
//		andExpect(status().isOk()).
//		andExpect(view().name("professor/subjects-list-prof")); 	
//		
//	}
//	
//	@Test
//	void testShowFormForAddReturnsPage() throws Exception{
//		
//		mockMvc.perform(get("/professor/subjects/showFormForAdd")).
//		andExpect(status().isOk()).
//		andExpect(view().name("professor/subject-form")); 	
//		
//	}
	
}
