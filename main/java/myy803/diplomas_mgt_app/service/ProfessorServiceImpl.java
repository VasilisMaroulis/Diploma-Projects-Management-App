package myy803.diplomas_mgt_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myy803.diplomas_mgt_app.dao.ProfessorDAO;
import myy803.diplomas_mgt_app.dao.StudentDAO;
import myy803.diplomas_mgt_app.dao.SubjectDAO;
import myy803.diplomas_mgt_app.dao.ThesisDAO;
import myy803.diplomas_mgt_app.model.Application;
import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.Subject;
import myy803.diplomas_mgt_app.model.Thesis;
import myy803.diplomas_mgt_app.model.strategies.BestApplicantStrategy;
import myy803.diplomas_mgt_app.model.strategies.BestApplicantStrategyFactory;

@Service
public class ProfessorServiceImpl implements ProfessorService{

	
	@Autowired
	private ProfessorDAO professorRepository;
	
	@Autowired
	private SubjectDAO subjectRepository;
	
	@Autowired
	private ThesisDAO thesisRepository;
	
	@Autowired
	private StudentDAO studentRepository;
		
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ThesisService thesisService;
	
	
	private BestApplicantStrategyFactory strategyfactory = new BestApplicantStrategyFactory();
	

	
	@Autowired
	public  ProfessorServiceImpl(ProfessorDAO theprofessorRepository) {
		professorRepository = theprofessorRepository;
	}
	
	
	
	
	public ProfessorServiceImpl() {
		// TODO Auto-generated constructor stub
	}


	@Override
	@Transactional
	public void save(Professor theProfessor) {
		professorRepository.save(theProfessor);
	}
	
	@Override
	@Transactional
	public Professor findByUserId(int theId) {
		Professor result = professorRepository.findByUserId(theId);
		return result;
	}
	
	@Override
	@Transactional
	public String assignSubject(String strat, int subId) {
		Subject subject = subjectRepository.findById(subId);
		List<Application> applications = subject.getApplications();
		BestApplicantStrategy temp = strategyfactory.createStrategy(strat);
		Student tempStudent = temp.findBestApplicant(applications);
		Thesis tempThesis = new Thesis(tempStudent);
		while(thesisService.isStudentInTheses(tempThesis)) {
			List<Application> filtered = applicationService.filterByStudentAvailability(applications);
			if (filtered.isEmpty()) {
				return "Subject was not assigned";
			}
			else {
			tempStudent = temp.findBestApplicant(filtered);
			tempThesis = new Thesis(tempStudent);
			}
		}
		Thesis thesis = new Thesis(tempStudent, subject);
		thesisRepository.save(thesis);	
		return "Subject was successfully assigned to " + tempStudent.getFirstName()+ " " + tempStudent.getLastName() ;
		
	}
	
	@Override
	@Transactional
	public void assignSubjectToSpecific(int studId, int subId) {			//@RequestParam("studId") 
		Subject subject = subjectRepository.findById(subId);
		Student student = studentRepository.findById(studId);
		Thesis thesis = new Thesis(student, subject);
		thesisRepository.save(thesis);		
		
	}
	
}
