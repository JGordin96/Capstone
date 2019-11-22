package interfaceAndDAO;

import java.util.List;

import objects.Campground;

public interface CampgroundDAO {
	
	public List <Campground> returnAllCampGroundInfo(Long parkID);

	

}
