package com.techelevator.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.PreferenceDAO;
import com.techelevator.model.RestaurantDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;
import com.techelevator.security.AuthProvider;

@Controller
public class AuthenticationController {

	@Autowired
	private AuthProvider auth;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PreferenceDAO prefDAO;
	
	@Autowired
	private RestaurantDAO restaurantDAO;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String displayLoginForm() {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String userName, @RequestParam String password,
			@RequestParam(required = false) String destination, HttpSession session) {
		if (userDAO.searchForUsernameAndPassword(userName, password)) {
			session.setAttribute("currentUser", userDAO.getUserByUserName(userName));
			if (destination != null && !destination.isEmpty()) {
				return "redirect:" + destination;
			} else {
				return "redirect:/users/" + userName;
			}
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		model.remove("currentUser");
		session.invalidate();
		restaurantDAO.removeAllMatchesFromDatabase();
		return "redirect:/";
	}

	@RequestMapping(path = "/users/{userName}", method = RequestMethod.GET)
	public String usernameProfile(@PathVariable String userName, ModelMap map) {
		map.put("user", userDAO.getUserByUserName(userName));
		map.put("preferences", prefDAO.getUserPreferences(userDAO.getUserByUserName(userName).getId()));
		return "userProfile";
	}

	@RequestMapping(path = "/users/{userName}", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute User user, @PathVariable String userName, ModelMap map) {
		int userId = userDAO.getUserByUserName(userName).getId() ;
		userDAO.updateAddress(user.getUserName(), user.getAddress());
		userDAO.updateMileRange(user.getUserName(), user.getMilerange());
		prefDAO.removeUserPreference(userId);
		prefDAO.insertNewUserPref(user, userId);
		map.put("user", userDAO.getUserByUserName(userName));
		return "redirect:/users/" + userName;
	}

}
