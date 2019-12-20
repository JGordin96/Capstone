package com.techelevator.model;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	@Pattern(regexp="^[_A-z0-9]*((-|\\s)*[_A-z0-9])*$", message="Usernames may not contain non alphanumeric characters")
	private String userName;
	
	@Size(min=10, message="Password too short, must be at least 10")
	@Pattern.List({
		@Pattern(regexp=".*[a-z].*", message="Must have a lower case"),
		@Pattern(regexp=".*[A-Z].*", message="Must have a capital")
	})
	
	private String password;
	private String role;
	private String confirmPassword;
	private String address;
	private String[] preferences;
	private int id;
	private int milerange;
	private String addressForAPI;
	private Double milerangeForAPI;
	private String prefsForAPI = "";
	
	public String getPrefsForAPI(List<Preference> preferences) {
		for (Preference prefs : preferences) {
			prefsForAPI += prefs.getName()+"%20";
		}
		return prefsForAPI;
	}
	public void setPrefsForAPI(String prefsForAPI) {
		this.prefsForAPI = prefsForAPI;
	}
	public Double getMilerangeForAPI(int milerange) {
		milerangeForAPI = milerange * 1609.344;
		return milerangeForAPI;
	}
	public void setMilerangeForAPI(Double milerangeForAPI) {
		this.milerangeForAPI = milerangeForAPI;
	}
	public String getAddressForAPI(String address) {
		//this.address = address.trim();
		addressForAPI = address.trim().replaceAll("\\s", "%20");
		return addressForAPI;
	}
	public void setAddressForAPI(String addressForAPI) {
		this.addressForAPI = addressForAPI;
	}
	public int getMilerange() {
		return milerange;
	}
	public void setMilerange(int milerange) {
		this.milerange = milerange;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getUserName() {
		return userName;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String[] getPreferences() {
		return preferences;
	}
	public void setPreferences(String[] preferences) {
		this.preferences = preferences;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", role=" + role + ", confirmPassword="
				+ confirmPassword + ", address=" + address + ", preferences=" + preferences + ", id=" + id
				+ ", milerange=" + milerange + "]";
	}
}
