package com.techelevator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import interfaceAndDAO.CampgroundDAO;
import interfaceAndDAO.CampsiteDAO;
import interfaceAndDAO.ParkDAO;
import interfaceAndDAO.ReservationDAO;
import objects.Campground;
import objects.Campsite;
import objects.JDBCCampGroundDAO;
import objects.JDBCCampSiteDAO;
import objects.JDBCParkDAO;
import objects.JDBCReservationDAO;
import objects.Park;
import objects.Reservation;

public class CampgroundCLI {
	
	public static final String VIEW_CAMPGROUNDS = "View Campgrounds";
	public static final String SEARCH_FOR_RESERVATION = "Search For Reservation";
	public static final String RETURN_TO_PREV = "Return to Previous Screen";
	public static final String[] COMMAND_MENU = new String[] {VIEW_CAMPGROUNDS,SEARCH_FOR_RESERVATION, RETURN_TO_PREV};
	
	public static final String SEARCH_FOR_RESERVATION_2 = "Search for Available Reservation";
	public static final String RETURN_TO_PREV_ = "Return to Previous Screen";
	public static final String[] TO_RESERVATION_MENU = new String[] {SEARCH_FOR_RESERVATION_2,RETURN_TO_PREV_};
	

	Park park = new Park();
	private Menu menu;
	private CampgroundDAO campgroundDAO;
	private CampsiteDAO campsiteDAO;
	private ParkDAO parkDAO;
	private ReservationDAO reservationDAO;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		this.menu = new Menu(System.in, System.out);

		campgroundDAO = new JDBCCampGroundDAO(datasource);
		campsiteDAO = new JDBCCampSiteDAO(datasource);
		parkDAO = new JDBCParkDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
	}
	public void displayParkInfo(Park somePark) {
		//format and add banner
		System.out.println(somePark.getName());
		System.out.println("Location:" + somePark.getLocation());
		System.out.println("Date Established: "+ somePark.getEstablish_date());
		System.out.println("Area: "+ somePark.getArea());
		System.out.println("Annual Vistors: " + somePark.getVisitor());
		System.out.println(somePark.getDescription());
		
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(COMMAND_MENU);
			if(choice.equals(VIEW_CAMPGROUNDS)) {
				Long parkId = somePark.getPark_id();
				List<Campground> campGrounds = campgroundDAO.returnAllCampGroundInfo(parkId);
				JDBCCampGroundDAO.printCampGroundInfo(campGrounds);
				//add formatting
				
			}
			else if(choice.equals(SEARCH_FOR_RESERVATION)) {
				String resChoice = (String)menu.getChoiceFromOptions(TO_RESERVATION_MENU);
				Long parkId = somePark.getPark_id();
				List<Campground> campGrounds = campgroundDAO.returnAllCampGroundInfo(parkId);
				
				JDBCCampGroundDAO.printCampGroundInfo(campGrounds);
				Scanner userInput = new Scanner (System.in);
				System.out.println("Please enter the campground ID:  ");
				String camp = userInput.nextLine();
				Long campId = Long.parseLong(camp);
				System.out.println("Please enter the arrival date (day/month/year): ");
				String arrivalDate = userInput.nextLine().trim();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate arrivalLocalDate = LocalDate.parse(arrivalDate, formatter);
				System.out.println("Please enter the departure date (day/month/year): ");
				String departDate = userInput.nextLine();
				LocalDate departLocalDate = LocalDate.parse(departDate, formatter);
				
				
				List<Campsite> topSites = campsiteDAO.topFiveSitesFromInput(campId, arrivalLocalDate, departLocalDate);
				if (topSites.size() == 0) {
					System.out.print("No sites availble. Please try again");
					
				}
				else {
					JDBCCampSiteDAO.printTopSites(topSites);
					
					System.out.print("\n Please select the site ID to reserve: ");
					String site = userInput.nextLine();
					Long siteID = Long.parseLong(site);
					System.out.println("\n Please enter the name for the reservation: ");
					String reservationName = userInput.nextLine();
					LocalDate createDate = LocalDate.now();
					//Reservation newReservation = new Reservation();
					Reservation newReservation = reservationDAO.AddNewRseveration(siteID, reservationName,arrivalLocalDate, departLocalDate, createDate);
					JDBCReservationDAO.createReservation(newReservation);
				
					
					System.out.println("\nThank you, your reservation ID is: ");
					List<Reservation> name = reservationDAO.getReservationIdByName(reservationName);
					JDBCReservationDAO.printReservationId(name);
					System.exit(0);
				
				}
				
				
			}
			else if(choice.equals(RETURN_TO_PREV)) {
				break;
				
			}
		}
		
		
	}
	public void run() {
		while (true) {
			//add banner
			String choice = (String) menu.getChoiceFromOptions(parkDAO.returnParkNames());
			Park tempPark = parkDAO.displayParkInfoByName(choice);
			displayParkInfo(tempPark);
			
			System.out.println(tempPark.getName());
			
			if(choice.equals("Quit")) {
				System.exit(0);
			}
			
		

		}
	}
}
