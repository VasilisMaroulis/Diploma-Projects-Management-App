package myy803.diplomas_mgt_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myy803.diplomas_mgt_app.model.Application;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.Subject;

@Repository
public interface ApplicationDAO extends JpaRepository<Application, Integer> {
	
	public List<Application> findBySubject(Subject subject);
	public Optional <Application> findByStudentAndSubject(Student student, Subject subject);
}