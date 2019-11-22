package com.techelevator;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import interfaceAndDAO.ParkDAO;
import interfaceAndDAO.ReservationDAO;
import objects.JDBCParkDAO;
import objects.JDBCReservationDAO;
import objects.Reservation;

public class ReservationIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private ReservationDAO dao;
//	Park testPark = new Park();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/*
		 * The following line disables autocommit for connections returned by this
		 * DataSource. This allows us to rollback any changes after each test
		 */
		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dataSource.destroy();
	}

	@Before
	public void setUp() throws Exception {
	//	dataSource.getConnection().rollback();
		dao = new JDBCReservationDAO(dataSource);
	}

	@After
	public void tearDown() throws Exception {
		dataSource.getConnection().rollback();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}
	@Test
	public void create_reservation_test(long site_id, String reservationName, LocalDate fromDate, LocalDate toDate,
			LocalDate createDate) {
		Reservation testRes = new Reservation ();
		Long testID = 50L;
		String resName = "Test Family";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String StartDate = "01/08/2021";
		LocalDate startDateLocal = LocalDate.parse(StartDate, formatter);
		String EndDate = "07/08/2021";
		LocalDate endDateLocal = LocalDate.parse(StartDate, formatter);
		LocalDate resDate = LocalDate.now();
		dao.AddNewRseveration(testID, resName, startDateLocal, endDateLocal, resDate);
		
		ReservationDAO.createReservation(testRes);
		
		assertEquals(Double.valueOf(testRes.getReservation_id()), Double.valueOf(50L));
		//Test was passing- now getting "intialization error?"
		
		
	}

}
