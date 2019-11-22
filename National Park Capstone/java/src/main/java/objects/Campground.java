package objects;

public class Campground {

	private Long campGround_id;
	private Long park_id;
	private String name;
	private int openingMonth;
	private int closingMonth;
	private double dailyFee;

	public Long getCampGround_id() {
		return campGround_id;
	}

	public void setCampGround_id(Long campGround_id) {
		this.campGround_id = campGround_id;
	}

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

	public int getOpeningMonth() { 
		return openingMonth;
	}

	public void setOpeningMonth(int openingMonth) {
		this.openingMonth = openingMonth;
	}

	public int getClosingMonth() {
		return closingMonth;
	}

	public void setClosingMonth(int closingMonth) {
		this.closingMonth = closingMonth;
	}

	public double getDailyFee() {
		return dailyFee;
	}

	public void setDailyFee(double dailyFee) {
		this.dailyFee = dailyFee;
	}
 
}
