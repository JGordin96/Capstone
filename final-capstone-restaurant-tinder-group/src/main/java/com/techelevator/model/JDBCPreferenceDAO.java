package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCPreferenceDAO implements PreferenceDAO {

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCPreferenceDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void insertNewUserPref (User user, int userId) {
		String sql = "INSERT INTO users_preferences (user_id, preference_id) VALUES (?,?)";
		
		List<Preference> prefsList = getUserPrefs(user);
		for (Preference p : prefsList) {
			int pId = p.getId();
			jdbcTemplate.update(sql, userId, pId);
		}
	}
	
	@Override
	public List<Preference> getUserPrefs (User user){
		List<Preference> preferences = new ArrayList<>();
		for (String pref : user.getPreferences()) {
			Preference somePref = new Preference();
			somePref.setName(pref);
			if (somePref.getName().equals("Burger")) {
				somePref.setId(0);
			}
			if (somePref.getName().equals("Sandwich")) {
				somePref.setId(1);
			}
			if (somePref.getName().equals("Mexican")) {
				somePref.setId(2);
			}
			if (somePref.getName().equals("Pizza")) {
				somePref.setId(3);
			}
			if (somePref.getName().equals("Greek")) {
				somePref.setId(4);
			}
			if (somePref.getName().equals("BBQ")) {
				somePref.setId(5);
			}
			if (somePref.getName().equals("Sushi")) {
				somePref.setId(6);
			}
			if (somePref.getName().equals("Chinese")) {
				somePref.setId(7);
			}
			if (somePref.getName().equals("Italian")) {
				somePref.setId(8);
			}
			if (somePref.getName().equals("Vegetarian")) {
				somePref.setId(9);
			}
			preferences.add(somePref);
		} return preferences;
	}
	
	@Override
	public List<Preference> getUserPreferences(int id) {
		List<Preference> preferences = new ArrayList<>();
		String sql = "select * from users_preferences inner join preferences on preferences.preference_id = users_preferences.preference_id where user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while (results.next()) {
			Preference preference = new Preference();
			preference.setId(results.getInt("preference_id"));
			preference.setName(results.getString("preference"));
			preferences.add(preference);
		}
		return preferences;
	}

	@Override
	public Preference addUserPreference(int userId, int prefId) {
		Preference preference = new Preference();
		String sql = "INSERT INTO users_preferences (user_id, preference_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, userId, prefId);
		return preference;
	}
	
	@Override
	public void removeUserPreference(int userId) {
		String sql = "DELETE FROM users_preferences WHERE user_id = ?";
		jdbcTemplate.update(sql, userId);
	}

}
