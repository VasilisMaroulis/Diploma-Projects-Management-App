package myy803.diplomas_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myy803.diplomas_mgt_app.dao.SubjectDAO;
import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Subject;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired	
	private SubjectDAO subjectRepository;
		
	
	@Autowired
	public SubjectServiceImpl(SubjectDAO theSubjectRepository) {
		subjectRepository= theSubjectRepository;	
	}
	
	
	public SubjectServiceImpl() {}

	
	@Override
	@Transactional
	public Subject findById(int theId) {
		Subject result = subjectRepository.findById(theId);
	
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the subject
			throw new RuntimeException("Did not find subject id - " + theId);
		}		
	}
	
	@Override
	@Transactional
	public void save(Subject theSubject) {
		subjectRepository.save(theSubject);		
	}
	
	
	@Override
	@Transactional
	public void deleteById(int theId) {
		subjectRepository.deleteById(theId);		
	}
	
	
	@Override
	@Transactional
	public List<Subject> findByProfessor(Professor professor){
		List<Subject> subjects = subjectRepository.findByProfessor(professor);
		return subjects;
	}	
	
	
	@Override
	@Transactional
	public List<Subject> findAllAvailable(){
		List<Subject> subjects = subjectRepository.findAll();
		
		List<Subject> available = new ArrayList<>();
		for (Subject sub:subjects) {
			if(sub.isTaken()==false) {
				available.add(sub);
			}	
		}
		return available;
	}
	}