package group187.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.lib.Email;
import group187.hotel.business.DawsonRoom;

public class ReservationListDB implements ReservationDAO {
	
	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;
	public ReservationListDB (ListPersistenceObject listPersistenceObject)
	{
		this.listPersistenceObject = listPersistenceObject;
		database = listPersistenceObject.getReservationDatabase();
		allRooms = listPersistenceObject.getRoomDatabase();
		factory = null;
	}
	public ReservationListDB (ListPersistenceObject listPersistenceObject,
	HotelFactory factory)
	{
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		database = listPersistenceObject.getReservationDatabase();
		allRooms = listPersistenceObject.getRoomDatabase();
	}


	@Override
	public void add(Reservation reserv) throws DuplicateReservationException {
		boolean found = false;
		// Check if reservation object is already in the list
		for (int i = 0; i < database.size(); i++)
			if (reserv.equals(database.get(i))){
				throw new DuplicateReservationException("The reservation: " + reserv.toString() + " is already in the list");

			}

			found = true;
		if (found == false){
			Reservation reservationObj = reserv;
		}

		Reservation reservationObj = reserv; // Create a copy of the reserv object to add to the list
		int index = binarySearch(reservationObj); // Get the index to add the reservation object in proper order
		database.add(index, reservationObj); // Add the object at the proper index
		

	}
	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Reservation> getReservations(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void clearAllPast() {
		// TODO Auto-generated method stub
		
	}
	
	private <T> int binarySearch(T o){
		Reservation reservObj = null;
		Room roomObj = null;
		if (o instanceof Reservation)
			reservObj = (Reservation)o;
		else if (o instanceof Room)
			roomObj = (DawsonRoom)o.;
		else
			throw new IllegalArgumentException("CustomerListDB - binarySearch(T o) - The object in the parameter must be either an Email or a Customer");
		
		int startIndex = 0, // Start index where to start searching
		endIndex = database.size(); // End index where to stop searching
		while (endIndex >= startIndex){
			int  midIndex = (endIndex+startIndex) / 2;
			Email temp = database.get(midIndex).getEmail();
			if (temp.compareTo(emailObj) < 0)			
				startIndex = midIndex+1;
				
		else if (temp.compareTo(emailObj) > 0)
				endIndex = midIndex -1;

			else if (temp.equals(emailObj))
				return midIndex;
		}		
		return startIndex;
	}			
	
}
