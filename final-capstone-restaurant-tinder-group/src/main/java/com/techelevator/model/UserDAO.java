package com.techelevator.model;

import java.util.List;

public interface UserDAO {

	public void saveUser(String userName, String password, String confirmPassword, String address, int milerange);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

	public User getUserByUserName(String userName);

	void getUserId(User user);

	String updateAddress(String userName, String address);

	int updateMileRange(String userName, int mileRange);

	int getUserIdWithName(String userName);
}
