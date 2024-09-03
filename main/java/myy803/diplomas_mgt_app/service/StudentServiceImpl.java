package myy803.diplomas_mgt_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myy803.diplomas_mgt_app.dao.StudentDAO;
import myy803.diplomas_mgt_app.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	
	@Autowired
	private StudentDAO studentRepository;
	
	
	@Autowired
	public  StudentServiceImpl(StudentDAO thestudentRepository) {
		studentRepository = thestudentRepository;
	}
	
	public StudentServiceImpl() {}
	
	@Override
	@Transactional
	public Student  findById(int theId) {
		Student result = studentRepository.findById(theId);
				
		if (result != null ) {
			return result;
		}
		else {
			// we didn't find the student
			throw new RuntimeException("Did not find student id - " + theId);
		}
	}
	@Override
	@Transactional
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
	}
	
	
	@Override
	@Transactional
	public Student findByUserId(int theId) {
		Student result = studentRepository.findByUserId(theId);
		return result;
	}
		
}
