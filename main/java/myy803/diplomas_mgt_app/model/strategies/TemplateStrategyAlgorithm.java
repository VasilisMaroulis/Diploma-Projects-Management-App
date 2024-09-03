package myy803.diplomas_mgt_app.model.strategies;

import java.util.List;

import myy803.diplomas_mgt_app.model.Application;
import myy803.diplomas_mgt_app.model.Student;


public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy{
	
	public TemplateStrategyAlgorithm() {
		}
	
	public Student findBestApplicant(List<Application> applications){
		Application best = applications.get(0);
		
		for (int i=1; i<applications.size(); i++){
			if(compareApplications(best,applications.get(i))==0) {
				best = applications.get(i);
			}
		}
		
		Student student = best.getStudent();
		return student;
	}
	
	protected abstract int compareApplications(Application application1, Application application2);
	
}
