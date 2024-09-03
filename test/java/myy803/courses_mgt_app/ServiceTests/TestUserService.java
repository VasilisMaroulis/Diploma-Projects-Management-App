package myy803.courses_mgt_app.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import myy803.diplomas_mgt_app.dao.UserDAO;
import myy803.diplomas_mgt_app.model.User;
import myy803.diplomas_mgt_app.service.UserService;
import myy803.diplomas_mgt_app.service.UserServiceImpl;


@ExtendWith(SpringExtension.class)
class TestUserService {

	@TestConfiguration
	static class UserServiceImplTestContextConfiguration{
		
		
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}	
	}
	
	@MockBean
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserService userService;
	
	@MockBean
	UserDAO userRepository;
	

	@Test
	void testUserServiceIsNotNull() {
		Assertions.assertNotNull(userService);
	}

	
	@Test
	void testfindByUsernameReturnsUser() {
		User user = new User();
		user.setUsername("username");
		Mockito.when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
		User storedUser = userService.findByUsername("username").get();
		Assertions.assertNotNull(storedUser);
		Assertions.assertEquals(storedUser.getUsername(), "username");
	}
		
	@Test
	void testSaveUser() {
	
		User theUser = new User();
			
		Mockito.when(userRepository.save(theUser)).thenReturn(theUser);
		Assertions.assertNotNull(theUser);
	}
	
	@Test
	void testIsUserPresentReturnsTrue() {
		User existingUser = new User();
		existingUser.setUsername("testUsername1");
		Mockito.when(userRepository.findByUsername("testUsername1")).thenReturn(Optional.of(existingUser));
		
		boolean existing = userService.isUserPresent(existingUser);
		assertTrue(existing);	
	}
	
	
	@Test
	void testIsUserPresentReturnsFalse() {
		Mockito.when(userRepository.findByUsername("testUsername2")).thenReturn(Optional.empty());
		User nonExistingUser = new User();
		nonExistingUser.setUsername("testUsername2");
		
		boolean nonExisting = userService.isUserPresent(nonExistingUser);
		assertFalse(nonExisting);
						
	}
		
}
