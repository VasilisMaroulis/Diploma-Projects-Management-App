package myy803.diplomas_mgt_app.model.strategies;

public class BestApplicantStrategyFactory {
	
	
	public BestApplicantStrategyFactory(){
	}
	
	public BestApplicantStrategy createStrategy(String strat) {
		switch(strat) {
		case "bestAvgGrade"		 :	BestAvgGradeStrategy bestAvg = new BestAvgGradeStrategy();
									return bestAvg;
		case "fewestRemCourses"	 : 	FewestCoursesStrategy fewestRem = new FewestCoursesStrategy();					
									return fewestRem;
		case "randomChoice"		 :	RandomChoiceStrategy randomChoice = new RandomChoiceStrategy();
									return randomChoice;
		default					 :	return null;
				
		}
	}
}