package myy803.diplomas_mgt_app.service;

import java.util.List;

import myy803.diplomas_mgt_app.model.Application;
import myy803.diplomas_mgt_app.model.Subject;

public interface ApplicationService {

	public void save(Application application);
	public boolean isApplicationPresent(Application application);
	public List<Application> findBySubject(Subject subject);
	public List<Application> filterByThresholds(List<Application> applications, float lower, int upper);
	public List<Application> filterByStudentAvailability(List<Application> applications);
}
