package com.boydlever.auth.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.boydlever.auth.models.LoginUser;
import com.boydlever.auth.models.User;
import com.boydlever.auth.services.UserService;

@Controller
public class UserController {
// import service
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String logregform(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "logreg.jsp";
	}
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
		//TO-DO later -- call a register method in the service
		User registeredUser = userService.register(newUser, result);
		//1. get the registeredUser by calling register in service and make use of the binding result
        //2. check for result errors
		//2.1 If there are no errors, put the missing model in and return jsp
        
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "logreg.jsp";
        }
      //2.2 If no errors, set userId in session and redirect
        session.setAttribute("userId", registeredUser.getId());
        return "redirect:/dashboard";
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
        // User user = userServ.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "logreg.jsp";
        }
    
       // 2.2 If no errors, get the user info from user and set userId in the session
    
        return "redirect:/dashboard";
    }
    
}
