package myy803.diplomas_mgt_app.service;

import myy803.diplomas_mgt_app.model.Student;


public interface StudentService {
	
	public void save(Student theStudent);
	public Student findById(int theId);
	public Student findByUserId(int userId);
}
