package myy803.diplomas_mgt_app.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myy803.diplomas_mgt_app.dao.ThesisDAO;
import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Thesis;

@Service
public class ThesisServiceImpl implements ThesisService {
	
	@Autowired
	private ThesisDAO thesisRepository;
		
	
	@Autowired
	public ThesisServiceImpl(ThesisDAO theThesisRepository) {
		thesisRepository = theThesisRepository;
	}
	

	@Override
	@Transactional
	public Thesis findById(int theId) {
		Thesis result = thesisRepository.findById(theId);
		return result;
	}
	
	@Override
	@Transactional
	public void save(Thesis theThesis) {
		thesisRepository.save(theThesis);
	}

	
	@Override
	@Transactional
	public List<Thesis> findByProfessor(Professor professor){
		List<Thesis> thesis = thesisRepository.findByProfessor(professor);
		return thesis;
	}
	
	
	@Override
	@Transactional
	public boolean isThesisPresent(Thesis thesis) {
		Optional<Thesis> found = thesisRepository.findBySubject(thesis.getSubject());
		return found.isPresent();
	}
	
	@Override
	@Transactional
	public boolean isStudentInTheses(Thesis thesis) {
		Optional<Thesis> found = thesisRepository.findByStudent(thesis.getStudent());
		return found.isPresent();
	}
}
