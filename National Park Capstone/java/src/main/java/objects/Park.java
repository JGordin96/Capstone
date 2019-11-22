package objects;

import java.time.LocalDate;

public class Park {

	private Long park_id;
	private String name;
	private String location;
	private LocalDate establish_date; 
	private Long area;
	private Long visitor;
	private String description;

	public Long getPark_id() {
		return park_id;
	}

	public void setPark_id(Long park_id) {
		this.park_id = park_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getEstablish_date() {
		return establish_date;
	}

	public void setEstablish_date(LocalDate establish_date) {
		this.establish_date = establish_date;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public Long getVisitor() {
		return visitor;
	}

	public void setVisitor(Long visitor) {
		this.visitor = visitor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
