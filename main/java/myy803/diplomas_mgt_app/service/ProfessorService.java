package myy803.diplomas_mgt_app.service;

import myy803.diplomas_mgt_app.model.Professor;


public interface ProfessorService {

			
		public void save(Professor theProfessor);
		public Professor findByUserId(int userId);
		public String assignSubject(String strat, int subId);
		public void assignSubjectToSpecific(int studId, int subId);
}
