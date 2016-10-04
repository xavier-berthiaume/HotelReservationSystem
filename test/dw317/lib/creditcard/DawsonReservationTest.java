package dw317.lib.creditcard;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.*;
import dw317.lib.Email;
import group187.hotel.business.DawsonCustomer;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;
public class DawsonReservationTest {

	public static void main(String[] args) {
		// Create all necessary variables
		DawsonCustomer customer;
		Email emailObj;
		DawsonRoom room;
		int inYear, inMonth, inDate, outYear, outMonth, outDate, roomNumber;
		String email, firstName, lastName;
		String[] customerInfo = new String[8];
		
		// Set all variables
		customerInfo = getCustomerReservationInfo(1, "Reservation");
		email = customerInfo[0];
		inYear = Integer.parseInt(customerInfo[1]);
		inMonth = Integer.parseInt(customerInfo[2]);
		inDate = Integer.parseInt(customerInfo[3]);
		outYear = Integer.parseInt(customerInfo[4]);
		outMonth = Integer.parseInt(customerInfo[5]);
		outDate = Integer.parseInt(customerInfo[6]);
		roomNumber = Integer.parseInt(customerInfo[7]);
		customerInfo = getCustomerReservationInfo(1, "Customer");
		firstName = customerInfo[1];
		lastName = customerInfo[2];
		emailObj = new Email(email);
		room = new DawsonRoom(roomNumber, RoomType.NORMAL);
		customer = new DawsonCustomer(firstName, lastName, emailObj);
		
		// Create object with pertinent info
		DawsonReservation reservationTest = new DawsonReservation(customer, room, inYear, inMonth,
				inDate, outYear, outMonth, outDate);		
	}
	
	public static String[] getCustomerReservationInfo(int lineNumber, String type) {
		// Create necessary variables
		String reservationFile = "C:\\Users\\Z\\git\\HotelReservationSystem\\"
						+ "datafiles\\reservation187.txt";
		String customerFile = "C:\\Users\\Z\\git\\HotelReservationSystem\\"
				+ "datafiles\\customer187.txt";
		String[] ReservationInfo = new String[8];
		String[] customerInfo = new String[5];
		Boolean reservation, customer;
		
		// Set arrays with customer/reservation info
		try {
				// Set reservation array
				if (type.equals("Reservation"){
					reservation = true;
					BufferedReader br = new BufferedReader(new FileReader(reservationFile));
					String line = Files.readAllLines(Paths.get(reservationFile)).get(lineNumber)
					customerInfo = line.split("*");
				}
				
				// Set customer array
				else if (type.equals("Customer")){
					customer = true;
					BufferedReader br = new BufferedReader(new FileReader(customerFile));
					String line = Files.readAllLines(Paths.get(customerFile)).get(lineNumber)
					customerInfo = line.split("*");
				}
		}
		catch (FileNotFoundException f){
			if (reservation)
				System.out.println("The specified file: " + reservationFile + " has not been found");
			else
				System.out.println("The specified file: " + customerFile + " has not been found");
		}
		
		return customerInfo;
		
	}

}
