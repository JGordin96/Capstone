package com.techelevator.model;

public class Preference {
	
	@Override
	public String toString() {
		return "Preference [id=" + id + ", name=" + name + "]";
	}
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
