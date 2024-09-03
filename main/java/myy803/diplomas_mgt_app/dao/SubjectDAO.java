package myy803.diplomas_mgt_app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Subject;


@Repository
public interface SubjectDAO extends JpaRepository<Subject, Integer> {
	
	public Subject findById(int theId);
	public	List<Subject> findByProfessor(Professor professor); 
}