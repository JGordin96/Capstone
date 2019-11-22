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

import interfaceAndDAO.CampgroundDAO;
import interfaceAndDAO.CampsiteDAO;
import objects.Campground;
import objects.Campsite;
import objects.JDBCCampGroundDAO;
import objects.JDBCCampSiteDAO;

public class CampsiteIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private CampsiteDAO dao;
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
		dao = new JDBCCampSiteDAO(dataSource);
	}

	@After
	public void tearDown() throws Exception {
		dataSource.getConnection().rollback();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}

	@Test
	public void top_five_campsites_by_price() {
		List<Campsite> testList = new ArrayList();
		
		Campsite testSiteFirst = new Campsite();
		Campsite testSiteSecond = new Campsite();
		Campsite testSiteThird = new Campsite();
		Campsite testSiteFour = new Campsite();
		Campsite testSiteFive = new Campsite();
		
		Campground testGround = new Campground();
		
		testGround.setDailyFee(35.0);
		testGround.setCampGround_id(1L);
		
		testSiteFirst.setCampGround_id(1L);
		testSiteFirst.setSite_id(1L);
		testSiteFirst.setSite_Number(1L);
		
		testSiteSecond.setCampGround_id(1L);
		testSiteSecond.setSite_id(2L);
		testSiteSecond.setSite_Number(2L);
		
		testSiteThird.setCampGround_id(1L);
		testSiteThird.setSite_id(3L);
		testSiteThird.setSite_id(3L);
		
		testSiteFour.setCampGround_id(1L);
		testSiteFour.setSite_id(4L);
		testSiteFour.setSite_id(4L);
		
		testSiteFive.setCampGround_id(1L);
		testSiteFive.setSite_id(5L);
		testSiteFive.setSite_id(5L);
		
		testList.add(testSiteFirst);
		testList.add(testSiteSecond);
		testList.add(testSiteThird);
		testList.add(testSiteFour);
		testList.add(testSiteFive);
		
		assertEquals(Double.valueOf(testList.get(0).getSite_id()), Double.valueOf(1L));
		assertEquals(Double.valueOf(testList.get(1).getSite_id()), Double.valueOf(2L));
		assertEquals(Double.valueOf(testList.get(2).getSite_id()), Double.valueOf(3L));
		assertEquals(Double.valueOf(testList.get(3).getSite_id()), Double.valueOf(4L));
		assertEquals(Double.valueOf(testList.get(4).getSite_id()), Double.valueOf(5L));
		assertEquals(Double.valueOf(testGround.getDailyFee()), Double.valueOf(35.0));
	
	}

}
