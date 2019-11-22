package objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import interfaceAndDAO.CampsiteDAO;

public class JDBCCampSiteDAO implements CampsiteDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCCampSiteDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Campsite> topFiveSitesFromInput(Long campID, LocalDate arrivalDate, LocalDate departDate) {
		List<Campsite> availableSites = new ArrayList<Campsite>();
		String sqlFindTopFiveAvailableSites = "SELECT distinct * FROM site " + 
				"join campground on site.campground_id = campground.campground_id " + 
				"where site.campground_id = ? " + 
				"and site_id not in " + 
				"(select site.site_id from site " + 
				"JOIN reservation ON reservation.site_id = site.site_id " + 
				"WHERE ? > reservation.from_date and ? < reservation.to_date) " + 
				"order by daily_fee " + 
				"LIMIT 5";
		Campsite theSite;
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindTopFiveAvailableSites, campID, arrivalDate, departDate);
		while(results.next()) {
			theSite = mapRowToSite(results);
			availableSites.add(theSite);
		}
		return  availableSites;
	}
	
	private Campsite mapRowToSite(SqlRowSet results) {
		Campsite theSite;
		theSite = new Campsite();
		theSite.setSite_id(results.getLong("site_id"));
		theSite.setCampGround_id(results.getLong("campground_id"));
		theSite.setSite_Number(results.getLong("site_number"));
		theSite.setMax_Occupancy(results.getLong("max_occupancy"));
		theSite.setHandicapAccessible(results.getBoolean("accessible"));
		theSite.setMax_RV_Length(results.getInt("max_rv_length"));
		theSite.setUtilityHookup(results.getBoolean("utilities"));
		theSite.setCampGround_id(results.getLong("campground_id"));

		return theSite;
	}
	

	
	public static void printTopSites(List<Campsite> topSiteList) {
		System.out.println("Top 5 Campsites: ");
		for(Campsite tempSite : topSiteList) {
		
			System.out.print("\nSite Number: " + tempSite.getSite_Number());
			System.out.println("Max Occupancy: " +tempSite.getMax_Occupancy());
			System.out.println("Handicap Accessible: "+tempSite.isHandicapAccessible());
			System.out.println("Max RV Length: " +tempSite.getMax_RV_Length());
			System.out.println("Utility Hookup: " +tempSite.isUtilityHookup());
		}
		
		
	}
	
	
	
	

}
