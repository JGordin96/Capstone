package objects;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import interfaceAndDAO.ParkDAO;

public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override 
	public Park displayParkInfoByName(String choice) {
		Park tempPark = new Park();
		String sqlGetAllParks = "SELECT park_id, name, location, establish_date, area, visitors, description FROM park WHERE name =?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks, choice);
		while (results.next()) {
			tempPark.setName(results.getString("name"));
			tempPark.setPark_id(results.getLong("park_id"));
			tempPark.setLocation(results.getString("location"));
			tempPark.setVisitor(results.getLong("visitors"));
			tempPark.setEstablish_date(results.getDate("establish_date").toLocalDate());
			tempPark.setDescription(results.getString("description"));
			tempPark.setArea(results.getLong("area"));
		}
		return tempPark;
	}

	@Override
	public String[] returnParkNames() {
	    List<Park> parks = new ArrayList<Park>();

	    String sqlString = "SELECT * FROM park";
	    SqlRowSet results = jdbcTemplate.queryForRowSet(sqlString);
	    while (results.next()) {
	        Park park = new Park();
	        String name = results.getString("name");
	        park.setName(name);
	        parks.add(park);
	    }
	    int arraySize = parks.size()+1;
	    String[] parkArray = new String[arraySize]; 
	    for(int i = 0; i < parks.size(); i ++)  {
	        parkArray[i] = parks.get(i).getName();
	    }
	    parkArray[arraySize-1] = "Quit";
	    return parkArray;
	}

}
   