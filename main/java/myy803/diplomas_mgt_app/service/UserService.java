package myy803.diplomas_mgt_app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import myy803.diplomas_mgt_app.model.User;

@Service
public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
    public Optional<User> findByUsername(String username);
}
