package com.boydlever.auth.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.boydlever.auth.models.LoginUser;
import com.boydlever.auth.models.User;
import com.boydlever.auth.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public User register(User newUser, BindingResult result) {
		//1. Find user in the DB by email
		Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
		//2. If the email is present, reject:
		if(optionalUser.isPresent()) {
			result.rejectValue("email","unique","This email is already registered.");
		}
		//Reject if password doesn't match confirm
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("password","matches","The confirm password does not match. ");
		}
		//if result has errors, return
		if(result.hasErrors()) {
			return null;
		}
		//Hash and set password, save user to database
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		userRepo.save(newUser);
		
		return newUser;
	}
	public User login(LoginUser newLogin, BindingResult result) {
		return null;
	}
}
