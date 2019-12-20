package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.security.PasswordHasher;

@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher hashMaster;
	private PreferenceDAO preferenceDAO;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher hashMaster) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hashMaster = hashMaster;
	}
	
	@Override
	public void saveUser(String userName, String password, String confirmPassword, String address, int milerange) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		
		jdbcTemplate.update("INSERT INTO app_user(username, password, salt, address, mile_range) VALUES (?, ?, ?, ?, ?)", 
				userName, hashedPassword, saltString, address, milerange);
	}
	
	@Override
	public void getUserId(User user) {
		String sql = "select user_id from app_user where username = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user.getUserName());
		while (result.next()) {
			user.setId(result.getInt("user_id"));
		}
	}
	
	@Override
	public int getUserIdWithName(String userName) {
		int id=0;
		String sql = "select user_id from app_user where username = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userName);
		while (result.next()) {
			id = result.getInt("user_id");
		} return id;
	}
	

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM app_user "+
							      "WHERE UPPER(username) = ? ";
		
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(user.next()) {
			String dbSalt = user.getString("salt");
			String dbHashedPassword = user.getString("password");
			String givenPassword = hashMaster.computeHash(password, Base64.decode(dbSalt));
			return dbHashedPassword.equals(givenPassword);
		} else {
			return false;
		}
	}

	@Override
	public void updatePassword(String userName, String password) {
		jdbcTemplate.update("UPDATE app_user SET password = ? WHERE username = ?", password, userName);
	}
	
	@Override
	public String updateAddress(String userName, String address) {
		jdbcTemplate.update("UPDATE app_user SET address = ? WHERE username = ?", address, userName);
		return address;
	}
	
	@Override
	public int updateMileRange(String userName, int mileRange) {
		jdbcTemplate.update("UPDATE app_user SET mile_range = ? WHERE username = ?", mileRange, userName);
		return mileRange;
	}

	@Override
	public User getUserByUserName(String userName) {
		String sql ="SELECT * "+
		"FROM app_user "+
		"WHERE UPPER(username) = ? ";

		SqlRowSet user = jdbcTemplate.queryForRowSet(sql, userName.toUpperCase()); 
		User thisUser = null;
		if(user.next()) {
			thisUser = new User();
			thisUser.setUserName(user.getString("username"));
			thisUser.setId(user.getInt("user_id"));
			thisUser.setPassword(user.getString("password"));
			thisUser.setAddress(user.getString("address"));
			thisUser.setMilerange(user.getInt("mile_range"));
		}
		return thisUser;
	}
	

}
