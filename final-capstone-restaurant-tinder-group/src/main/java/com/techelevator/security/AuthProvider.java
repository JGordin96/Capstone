package com.techelevator.security;

import com.techelevator.model.User;

public interface AuthProvider {

	boolean isLoggedIn();
	
	User getCurrentUser();
	
	boolean signIn(String username, String password);
	
	void logOff();

    boolean userHasRole(String[] roles);

	void register(String username, String password, String confirmPassword, String address, int milerange);

}
