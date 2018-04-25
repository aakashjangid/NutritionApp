package com.wastedpotential.nutritionapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wastedpotential.nutritionapp.model.User;
import com.wastedpotential.nutritionapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/all")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public int registerUser(@RequestBody User user) {
		return userService.register(user);
	}
	
	@RequestMapping(value="/register/{encrypt}")
	public String getEmail(@PathVariable String encrypt) {
		String email = userService.getEmail(encrypt);
		userService.activateUser(email);
		return "<h1>Nutrition App</h1>"
				+ "<h4>Your email is activated. Kindly Proceed with the Login.</h4>"
				+ "<br/> <a href=\"http://yi1007106dt:8080/nutritionapp/users/test\">Click Here for Login Page</a>";
	}
	
	@RequestMapping(value="/test")
	public String test() {
		return "<h1>Hello World</h1>";
	}
}