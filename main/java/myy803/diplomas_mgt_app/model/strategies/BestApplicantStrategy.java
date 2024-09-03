package myy803.diplomas_mgt_app.model.strategies;

import java.util.List;

import myy803.diplomas_mgt_app.model.Application;
import myy803.diplomas_mgt_app.model.Student;


public interface BestApplicantStrategy {
	public Student findBestApplicant(List <Application> applications);
}
