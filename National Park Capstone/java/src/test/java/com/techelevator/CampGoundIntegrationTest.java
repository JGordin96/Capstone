package com.techelevator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import static org.junit.Assert.assertArrayEquals;

import interfaceAndDAO.CampgroundDAO;
import interfaceAndDAO.ParkDAO;
import objects.Campground;
import objects.JDBCCampGroundDAO;
import objects.JDBCParkDAO;
import objects.Park;

public class CampGoundIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private CampgroundDAO dao;
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
		dao = new JDBCCampGroundDAO(dataSource);
	}

	@After
	public void tearDown() throws Exception {
		dataSource.getConnection().rollback();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void get_all_campground_info_with_park_id() {
		//List<Campground> allCampInfo = new ArrayList();
	
		Park testPark = new Park();
		Campground testCamp = new Campground();
		testPark.setPark_id(1L);
		
		testCamp.setName("Blackwoods");
		testCamp.setPark_id(1L);
		testCamp.setOpeningMonth(01);
		testCamp.setClosingMonth(12);
		testCamp.setDailyFee(35.0);
		testCamp.setCampGround_id(1L);
		
		List<Campground> allCampInfo = dao.returnAllCampGroundInfo(1L);
		
		assertEquals(Double.valueOf(allCampInfo.get(0).getCampGround_id()), Double.valueOf(1L));
		assertEquals(allCampInfo.get(0).getName(), "Blackwoods");
		assertEquals(allCampInfo.get(0).getClosingMonth(), 12);
		assertEquals(allCampInfo.get(0).getOpeningMonth(), 01);
		
	}

}
