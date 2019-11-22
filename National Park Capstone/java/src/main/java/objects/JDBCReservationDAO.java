package objects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import interfaceAndDAO.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {

	private static JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public Reservation AddNewRseveration(long site_id, String reservationName, LocalDate fromDate, LocalDate toDate,
			LocalDate createDate) {
		Reservation newReservation = new Reservation();
		newReservation.setSite_id(site_id);
		newReservation.setReservationName(reservationName);
		newReservation.setFrom_date(fromDate);
		newReservation.setTo_date(toDate);
		newReservation.setCreate_date(createDate);

		return newReservation;

	}

	public static void createReservation(Reservation newReservation) {
		String sqlInsertDepartment = "insert into reservation(site_id, name, to_date, from_date, create_date)"
				+ "values (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertDepartment, newReservation.getSite_id(), newReservation.getReservationName(),
				newReservation.getTo_date(), newReservation.getFrom_date(), newReservation.getCreate_date());
	}

	@Override
	public List<Reservation> getReservationIdByName(String resName) {
		List<Reservation> someResNames = new ArrayList();
		Reservation someRes = new Reservation();
		String sqlSelectIDWithName = "SELECT reservation_id FROM reservation WHERE name =?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectIDWithName, resName);
		while (results.next()) {
			someRes.setReservation_id(results.getLong("reservation_id"));
			someResNames.add(someRes);

		}
		return someResNames;
	}
	
	public static void printReservationId (List<Reservation> someList) {
		for (Reservation aRes : someList) {
			System.out.print(aRes.getReservation_id() + "Enjoy your stay!");

		}
	}
}
