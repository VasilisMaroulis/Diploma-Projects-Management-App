package myy803.diplomas_mgt_app.model.strategies;

import java.util.Random;

import myy803.diplomas_mgt_app.model.Application;

public class RandomChoiceStrategy extends TemplateStrategyAlgorithm {

	
	public RandomChoiceStrategy() {
	}
	
	protected int compareApplications(Application application1, Application application2) {
		Random rand = new Random(); 
		return rand.nextInt(2);
	}
}