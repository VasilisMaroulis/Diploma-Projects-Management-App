package myy803.diplomas_mgt_app.model.strategies;

import myy803.diplomas_mgt_app.model.Application;
public class FewestCoursesStrategy extends TemplateStrategyAlgorithm{
	
	
	public FewestCoursesStrategy() {
	}
	
	protected int compareApplications(Application application1, Application application2) {
		if (application1.getStudent().getNumRemainCourses() <= application2.getStudent().getNumRemainCourses()) {
			return 1;
		}
		
		return 0;
	}	
}






