package myy803.diplomas_mgt_app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.Subject;
import myy803.diplomas_mgt_app.model.Thesis;


@Repository
public interface ThesisDAO extends JpaRepository<Thesis, Integer>{
	public Thesis findById(int theId);
	public Optional<Thesis> findByStudent(Student student);
	public List<Thesis> findByProfessor(Professor professor);
	public Optional<Thesis> findBySubject(Subject subject);
}
