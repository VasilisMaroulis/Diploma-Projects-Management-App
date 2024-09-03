package myy803.diplomas_mgt_app.model.strategies;

import myy803.diplomas_mgt_app.model.Application;

public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm{
	
	
	public BestAvgGradeStrategy() {
	}
	
	protected int compareApplications(Application application1, Application application2) {
		
		if (application1.getStudent().getAvgGrade() >= application2.getStudent().getAvgGrade()) {
			return 1;
		}
		
		return 0;	
	}
}