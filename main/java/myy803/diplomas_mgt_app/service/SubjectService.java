package myy803.diplomas_mgt_app.service;

import java.util.List;

import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Subject;



public interface SubjectService {
	public Subject findById(int theId);
	public void save(Subject theSubject);
	public void deleteById(int theId);
	public List<Subject> findByProfessor(Professor professors);
	public List<Subject> findAllAvailable();
}
