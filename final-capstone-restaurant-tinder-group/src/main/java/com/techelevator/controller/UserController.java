package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Preference;
import com.techelevator.model.PreferenceDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class UserController {

	private PreferenceDAO preferenceDAO;
	private UserDAO userDAO;

	@Autowired
	public UserController(UserDAO userDAO, PreferenceDAO preferenceDAO) {
		this.userDAO = userDAO;
		this.preferenceDAO = preferenceDAO;
	}
	
	@RequestMapping("/")
	public String home(ModelMap map) {
		return "home";
	}

	//create account for new user
	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("user")) {
			modelHolder.addAttribute("user", new User());
		}
		return "newUser";
	}
	
	// what is this doing??
	@RequestMapping(path="/users/new", method=RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute User user, BindingResult result, RedirectAttributes flash) {
		List<FieldError> errors = result.getFieldErrors();
	    for (FieldError error : errors ) {
	        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
	    }
		if(result.hasErrors()) {
			flash.addFlashAttribute("user", user);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
			return "redirect:/users/new";
		}
		
		userDAO.saveUser(user.getUserName(), user.getPassword(), user.getConfirmPassword(), user.getAddress(), 
				user.getMilerange());
		
		userDAO.getUserId(user);
		preferenceDAO.insertNewUserPref(user, user.getId());

		return "redirect:/login";
	}
}
