package com.techelevator;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import interfaceAndDAO.ParkDAO;
import objects.JDBCParkDAO;
import objects.Park;

public class ParkIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private ParkDAO dao;
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
		dao = new JDBCParkDAO(dataSource);
	}

	@After
	public void tearDown() throws Exception {
		dataSource.getConnection().rollback();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}

	@Test
	public void test_display_info_from_name() {
		
		LocalDate date = LocalDate.of(1919, 02, 26);
		Park testPark = new Park();
		testPark.setName("Acadia");
		testPark.setArea(47389L);
		testPark.setDescription("Covering most of Mount Desert Island and other coastal islands, Acadia features the tallest mountain on the Atlantic coast of the United States, granite peaks, ocean shoreline, woodlands, and lakes. There are freshwater, estuary, forest, and intertidal habitats.");
		testPark.setLocation("Maine");
		testPark.setVisitor(2563129L);
		testPark.setEstablish_date(date);
		testPark.setPark_id(1L);
		
		testPark = dao.displayParkInfoByName("Acadia");
		
		assertEquals("Maine", testPark.getLocation());
		//Not working, always throwing null
		
	}
	
	@Test
	public void test_display_park_names() {
		String[] testParkList;
//		List<Park> parkList = new ArrayList<>();
		Park firstTestPark = new Park();
		firstTestPark.setName("Acadia");
		Park secondTestPark = new Park();
		secondTestPark.setName("Arches");
		Park thirdTestPark = new Park();
		thirdTestPark.setName("Cuyahoga Valley");
//		parkList.add(firstTestPark);
		
		testParkList = dao.returnParkNames();
		
		assertEquals("Acadia", testParkList[0]);
		
		
	
	
	}

}
