package interfaceAndDAO;

import java.time.LocalDate;
import java.util.List;

import objects.Reservation;

public interface ReservationDAO {
	
	public Reservation AddNewRseveration (long site_id, String reservationName, LocalDate fromDate, LocalDate toDate, LocalDate createDate );
	
	public static Reservation createReservation(Reservation newReservation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Reservation> getReservationIdByName(String resName);
	
	

}
