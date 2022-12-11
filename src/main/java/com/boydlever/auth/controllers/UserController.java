package com.boydlever.auth.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.boydlever.auth.models.LoginUser;
import com.boydlever.auth.models.User;

@Controller
public class UserController {
// import service
	@GetMapping("/")
	public String logregform(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "logreg.jsp";
	}
	
}
