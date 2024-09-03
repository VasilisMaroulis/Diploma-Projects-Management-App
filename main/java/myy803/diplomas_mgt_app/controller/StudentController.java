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
import myy803.diplomas_mgt_app.service.StudentService;
import myy803.diplomas_mgt_app.service.SubjectService;
import myy803.diplomas_mgt_app.service.ThesisService;
import myy803.diplomas_mgt_app.service.UserService;

@Controller
@RequestMapping("/student")
public class StudentController {

	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ThesisService thesisService;
	
	@Autowired
	public StudentController(StudentService theStudentService) {
		
		 studentService = theStudentService;
	}
	
	
	private User findUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Optional<User> user = userService.findByUsername(currentPrincipalName);
		return user.get();
	}
	
		
	
    @RequestMapping("/dashboard")
    public String getStudentHome(Model theModel){			
    	User theUser =  findUser();
		theModel.addAttribute("user", theUser);
		return "student/dashboard";
     }
    
	@RequestMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student theStudent){
		studentService.save(theStudent);
		return "student/information";
	}
	
	
	@RequestMapping("/info")
	public String updateStudent(Model theModel) {	
			int theId = findUser().getId();
		   	Student theStudent = studentService.findByUserId(theId);
			theModel.addAttribute("student", theStudent);
			return "student/information";
	}
	
	
	@RequestMapping("/showsubjects")
	public String showSubjects(Model theModel) {		
		List<Subject> subjects = subjectService.findAllAvailable();
		theModel.addAttribute("subjects", subjects);
		return "student/subjects-list-stud";
		
	}
	
	
	@RequestMapping("/details")
	public String showDetails(@RequestParam int subId,Model theModel) {
		Subject theSubject = subjectService.findById(subId);
		Student theStudent = studentService.findByUserId(findUser().getId());
		Professor theProfessor = theSubject.getProfessor();
		theModel.addAttribute(theSubject);
		theModel.addAttribute(theProfessor);
		theModel.addAttribute(theStudent);
		return "student/subject-details-stud";
	}
	
	@RequestMapping("/apply")
	public String applyToSubject( @RequestParam int subId,Model theModel) {	
		Subject theSubject = subjectService.findById(subId);
		Student theStudent = studentService.findByUserId(findUser().getId());
		Professor theProfessor = theSubject.getProfessor();
		Application theApplication = new Application(theSubject,theStudent);
		Thesis theThesis = new Thesis(theStudent);
		
		theModel.addAttribute(theSubject);
		theModel.addAttribute(theProfessor);
		theModel.addAttribute(theStudent);
		if (thesisService.isStudentInTheses(theThesis)) {
			theModel.addAttribute("successMessage", "You already have a running diploma thesis!");
			 return "student/subject-details-stud";
		}
		else if (applicationService.isApplicationPresent(theApplication)) {
			 theModel.addAttribute("successMessage", "Already applied to this subject!");
			 return "student/subject-details-stud";
		}else
		
		applicationService.save(theApplication);
		theModel.addAttribute("successMessage", "Succesfully applied");
		return "student/subject-details-stud";
		}
	
}
