package myy803.diplomas_mgt_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.diplomas_mgt_app.model.Application;
import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.Subject;
import myy803.diplomas_mgt_app.model.Thesis;
import myy803.diplomas_mgt_app.model.User;
import myy803.diplomas_mgt_app.service.ApplicationService;
import myy803.diplomas_mgt_app.service.ProfessorService;
import myy803.diplomas_mgt_app.service.StudentService;
import myy803.diplomas_mgt_app.service.SubjectService;
import myy803.diplomas_mgt_app.service.ThesisService;
import myy803.diplomas_mgt_app.service.UserService;


@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ThesisService thesisService;

	@Autowired
	public ProfessorController(ProfessorService theProfessorService) {
		
		professorService = theProfessorService;
		
	}
	
	
	private User findUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Optional<User> takis = userService.findByUsername(currentPrincipalName);
		return takis.get();
	}
	

    @RequestMapping("/dashboard")
    public String getProfessorHome(@ModelAttribute User user, Model theModel){
    	User theUser = findUser();
		theModel.addAttribute("user", theUser);
        return "professor/dashboard";
    }
    
	@RequestMapping("/save")
	public String saveProfessor(@ModelAttribute("professor") Professor theProfessor){
		professorService.save(theProfessor);
		return "redirect:/professor/dashboard";
	}
	
    
	@RequestMapping("/info")
	public String updateProfessor(Model theModel) {
		int theId=findUser().getId();
		Professor theProfessor = professorService.findByUserId(theId);
		theModel.addAttribute("professor", theProfessor);
		return "professor/information";
	}
	
	
	@RequestMapping("/subjects/list")
	public String listSubjects(Model theModel) {   																				
		Professor theProfessor = professorService.findByUserId(findUser().getId());
		List<Subject> theSubjects = subjectService.findByProfessor(theProfessor);
		theModel.addAttribute("subjects", theSubjects);
	   	theModel.addAttribute("professor", theProfessor);
	   	return "professor/subjects-list-prof";	
	 }
	
	
	@RequestMapping("/subjects/showFormForAdd")						
	public String showFormForAdd(Model theModel) {
		Professor prof = professorService.findByUserId(findUser().getId());
	    Subject theSubject = new Subject(prof);
		theModel.addAttribute("subject", theSubject);
		return "professor/subject-form";
	}
	    
	    
	@RequestMapping("/subjects/save")
	public String saveSubject(@ModelAttribute("subject") Subject theSubject){
		subjectService.save(theSubject);
		return "redirect:/professor/subjects/list";
	}
	    
    @RequestMapping("/subjects/delete")
    public String delete(@RequestParam("subjectId") int theId, Model theModel) {
    	Subject subject = subjectService.findById(theId);
    	List<Application> found = applicationService.findBySubject(subject);
    	if (found.isEmpty() == false) {
       		return "redirect:/professor/subjects/list";
       	}
    	
    	subjectService.deleteById(theId);
    	return "redirect:/professor/subjects/list";		   	
    }
	
	
    @RequestMapping("/subjects/edit")
    public String edit(@RequestParam("subjectId") int theId, Model themodel) {
    	Subject theSubject = subjectService.findById(theId);
    	themodel.addAttribute(theSubject);
    	
    	return "professor/subject-form";
    }
	
    @RequestMapping("/applications")
  	public String showApplicants(@RequestParam int subjectId, Model theModel) {
  		Subject subject = subjectService.findById(subjectId);
  		List<Application> applications = subject.getApplications();
  		theModel.addAttribute("applications", applications);
  		theModel.addAttribute("subject", subject);
  		
  		return "professor/applications-list";
  	}
      
      
    @RequestMapping("/filter")
  	public String filterTheses(@RequestParam int id, @RequestParam int lower, @RequestParam int upper, Model theModel) {
  		Subject subject = subjectService.findById(id);
  		List<Application> applications = subject.getApplications();
  		List<Application> filtered = applicationService.filterByThresholds(applications, (float)lower, upper);
  		theModel.addAttribute("applications", filtered);
  		theModel.addAttribute("subject", subject);
  		
  		return "professor/applications-list";
  	}
	
	
    @RequestMapping("/subjects/assignByStrategy")
    public String assignByStrategy(@RequestParam("subId") int subId,@RequestParam("strat") String strat, Model theModel) {
    	Subject theSubject = subjectService.findById(subId);
  		List<Application> applications = theSubject.getApplications();
  		Thesis theThesis = new Thesis(theSubject);
  		theModel.addAttribute("student", applications);
  		theModel.addAttribute("subject", theSubject);
  		if (thesisService.isThesisPresent(theThesis)) {
      		theModel.addAttribute("successMessage", "A student has already been assigned to this subject!");
      		return "/professor/applications-list";
  		}
  		String text = professorService.assignSubject(strat, subId);
  		if (!text.endsWith("assigned")) {
  			theSubject.setTaken(true);
  			subjectService.save(theSubject);
  		}
  		theModel.addAttribute("successMessage", text);
  		
      	return "professor/applications-list"; 	
      }
  	
    @RequestMapping("/subjects/assignToSpecific")
    public String assignToSpecific(@RequestParam("studId") int studId,@RequestParam("subId") int subId, Model theModel) {
    	Student theStudent = studentService.findById(studId);
    	Subject theSubject = subjectService.findById(subId);
      	Thesis theThesis = new Thesis(theStudent, theSubject);
  		List<Application> applications = theSubject.getApplications();
  		theModel.addAttribute("student", applications);
  		theModel.addAttribute("subject", theSubject);
      	if (thesisService.isThesisPresent(theThesis)) {
      		theModel.addAttribute("successMessage", "A student has already been assigned to this subject!");
      		return "/professor/applications-list";
      	}
      	else if (thesisService.isStudentInTheses(theThesis)) {
      		theModel.addAttribute("successMessage", "This student has already been assigned to another subject!");
      		return "/professor/applications-list";
      	}
      	professorService.assignSubjectToSpecific(studId, subId);
      	theSubject.setTaken(true);
      	subjectService.save(theSubject);
      	theModel.addAttribute("successMessage", "Subject successfully assigned to student: " + theStudent.getFirstName() + " " + theStudent.getLastName());
      	return "/professor/applications-list";	
    }		
	
	   
	@RequestMapping("/theses")
	public String showTheses(Model theModel) {
		Professor theProfessor = professorService.findByUserId(findUser().getId());
		List<Thesis> theses = thesisService.findByProfessor(theProfessor);
		theModel.addAttribute("theses", theses);
		return "professor/theses-list";
	}
	
	@RequestMapping("/theses/grades")
	public String gradeThesis(@RequestParam int thesisId, Model theModel) {
		Thesis thesis = thesisService.findById(thesisId);
		theModel.addAttribute("thesis",thesis);
		return "professor/grades-form";
	}
	
	@RequestMapping("/grades/save")
	public String saveGrades(@ModelAttribute("thesis") Thesis theThesis) {
		theThesis.calculateTotalGrade();
		thesisService.save(theThesis);
		return"redirect:/professor/theses";
	}
	
}
