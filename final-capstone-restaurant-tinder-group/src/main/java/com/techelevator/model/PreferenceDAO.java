package com.techelevator.model;

import java.util.List;

public interface PreferenceDAO {
	
	public List<Preference> getUserPreferences(int id);

	public Preference addUserPreference(int userId, int prefId);

	public List<Preference> getUserPrefs(User user);

	void removeUserPreference(int userId);

	void insertNewUserPref(User user, int i);

}
