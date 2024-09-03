package myy803.diplomas_mgt_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myy803.diplomas_mgt_app.dao.ApplicationDAO;
import myy803.diplomas_mgt_app.model.Application;
import myy803.diplomas_mgt_app.model.Subject;
import myy803.diplomas_mgt_app.model.Thesis;

@Service
public class ApplicationServiceImpl implements ApplicationService{

	
	@Autowired
	private ApplicationDAO applicationRepository;
	
	@Autowired 
	private ThesisService thesisService;
	
	@Autowired
	public ApplicationServiceImpl(ApplicationDAO theApplicationRepository) {
		applicationRepository = theApplicationRepository;
	}
	
	
	public ApplicationServiceImpl() {
		}
	
	
	@Override
	@Transactional
	public boolean isApplicationPresent(Application application) {
		Optional<Application> found = applicationRepository.findByStudentAndSubject(application.getStudent(), application.getSubject());
		return found.isPresent();
	}
	
	@Override
	@Transactional
	public void save(Application theApplication) {
		applicationRepository.save(theApplication);	
	}
	
	
	@Override
	@Transactional	
	public	List<Application> findBySubject(Subject subject){
		List <Application> result = applicationRepository.findBySubject(subject);
		return result;		
	}
	
	@Override
	@Transactional
	public List<Application> filterByThresholds(List<Application> applications, float lower, int upper){
		List<Application> filtered = new ArrayList<Application>();
		for (Application app : applications) {
			if (app.getStudent().getAvgGrade() >= lower && app.getStudent().getNumRemainCourses() < upper) {
				filtered.add(app);
			}
		}
		
		return filtered;
	}
	
	@Override
	@Transactional
	public List<Application> filterByStudentAvailability(List<Application> applications){
		List<Application> filtered = new ArrayList<Application>();
		for (Application app : applications) {
			Thesis theThesis = new Thesis(app.getStudent(),app.getSubject());
			if (!thesisService.isStudentInTheses(theThesis)) {
				filtered.add(app);				
			}		
		}
		return filtered;
	}
		
}