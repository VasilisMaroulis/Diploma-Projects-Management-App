package myy803.diplomas_mgt_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import myy803.diplomas_mgt_app.model.Professor;
import myy803.diplomas_mgt_app.model.Role;
import myy803.diplomas_mgt_app.model.Student;
import myy803.diplomas_mgt_app.model.User;
import myy803.diplomas_mgt_app.service.ProfessorService;
import myy803.diplomas_mgt_app.service.StudentService;
import myy803.diplomas_mgt_app.service.SubjectService;
import myy803.diplomas_mgt_app.service.UserService;

@Controller
public class AuthController {
    @Autowired
    UserService userService;
    
    @Autowired
    StudentService studentService;
    
    @Autowired
    ProfessorService professorService;
    
    @Autowired
    SubjectService subjectService;
    

    @RequestMapping("/login")
    public String login(){
        return "auth/signin";
    }
    

    @RequestMapping("/register")
    public String register( Model model){				
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User user,  Model model){
       
        if(userService.isUserPresent(user)){
            model.addAttribute("successMessage", "User already registered!");
            return "auth/signin";
        }
        
        if(user.getRole().equals(Role.STUDENT )) {
        	Student init =new  Student(user);
        	studentService.save(init);
        }else {
        	Professor init = new Professor(user);
        	professorService.save(init);
        }
        
        
        userService.saveUser(user);
        model.addAttribute("successMessage", "User registered successfully!");

        return "auth/signin";
    }
}
