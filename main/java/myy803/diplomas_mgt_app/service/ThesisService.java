package myy803.diplomas_mgt_app.service;

import java.util.List;

import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Thesis;

public interface ThesisService {
	public Thesis findById(int theId);
	public List<Thesis> findByProfessor(Professor professor);
	public void save(Thesis thesis);
	public boolean isThesisPresent(Thesis thesis);
	public boolean isStudentInTheses(Thesis thesis);
}
