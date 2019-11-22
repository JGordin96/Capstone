package objects;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import interfaceAndDAO.CampgroundDAO;

public class JDBCCampGroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCCampGroundDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Campground> returnAllCampGroundInfo(Long parkID) {
		List<Campground> allCampGrounds = new ArrayList<>();
		String sqlReturnCampGoundInfo = "SELECT campground_id, name, open_from_mm, open_to_mm, daily_fee FROM campground WHERE park_id =?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlReturnCampGoundInfo, parkID);
		while (results.next()) {
			Campground tempGround = new Campground();
			tempGround.setCampGround_id(results.getLong("campground_id"));
			tempGround.setName(results.getString("name"));
			tempGround.setOpeningMonth(results.getInt("open_from_mm"));
			tempGround.setClosingMonth(results.getInt("open_to_mm"));
			tempGround.setDailyFee(results.getDouble("daily_fee"));
			allCampGrounds.add(tempGround);
		}
		return allCampGrounds;
	}
	
	public static void printCampGroundInfo(List<Campground> campList) {
		for(Campground tempGround: campList) {
			System.out.println("\nCampground ID: " +tempGround.getCampGround_id());
			System.out.println("Campground Name: " +tempGround.getName());
			System.out.println("Opening month: " + tempGround.getOpeningMonth());
			System.out.println("Closing month: " +tempGround.getClosingMonth());
			System.out.println("Cost Per Day: " +tempGround.getDailyFee());
			
		}
	
	}


}
 