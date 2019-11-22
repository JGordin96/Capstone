package interfaceAndDAO;

import java.time.LocalDate;
import java.util.List;

import objects.Campsite;

public interface CampsiteDAO {
	
	public List <Campsite> topFiveSitesFromInput(Long campID, LocalDate arrivalDate, LocalDate departDate);
	

}
