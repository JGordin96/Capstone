package objects;

public class Campsite {

	private Long site_id;
	private Long campGround_id;
	private Long site_Number;
	private Long max_Occupancy;
	private boolean handicapAccessible;
	private int max_RV_Length;
	private boolean utilityHookup;

	public Long getSite_id() {
		return site_id;
	}
 
	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}

	public Long getCampGround_id() {
		return campGround_id;
	}

	public void setCampGround_id(Long campGround_id) {
		this.campGround_id = campGround_id;
	}

	public Long getSite_Number() {
		return site_Number;
	}

	public void setSite_Number(Long site_Number) {
		this.site_Number = site_Number;
	} 

	public Long getMax_Occupancy() {
		return max_Occupancy;
	}

	public void setMax_Occupancy(Long max_Occupancy) {
		this.max_Occupancy = max_Occupancy;
	}

	public boolean isHandicapAccessible() {
		return handicapAccessible;
	}

	public void setHandicapAccessible(boolean handicapAccessible) {
		this.handicapAccessible = handicapAccessible;
	}

	public int getMax_RV_Length() {
		return max_RV_Length;
	}

	public void setMax_RV_Length(int max_RV_Length) {
		this.max_RV_Length = max_RV_Length;
	}

	public boolean isUtilityHookup() {
		return utilityHookup;
	}

	public void setUtilityHookup(boolean utilityHookup) {
		this.utilityHookup = utilityHookup;
	}
}
