/*
 * 
 */
package dw317.hotel.data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;
import group187.hotel.business.DawsonCustomer;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;
import group187.util.ListUtilities;

/**
 * The Class HotelFileLoader
 */
public class HotelFileLoader {
	
	public static int successfullreservationcount = 0;
	
	//The private constructor prevents any form of instantionation
	private HotelFileLoader(){}
	
	//The Room array returned by the above method must be an array whose size is equal to
	//its capacity (i.e. the array must be full to capacity).
	 public static  Room[] getRoomListFromSequentialFile(File file) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(file));
		String str;
	
		List<Room> list = new ArrayList<Room>();
		while ((str = in.readLine()) != null) {
			if (str.isEmpty())
				continue;
			String[] array = str.split("\\*");
			try{
			for (int i = 0; i < array.length-1; i+=2) {
				String roomNumber = array[i];
				RoomType roomType = RoomType.valueOf(array[i+1].toUpperCase());
				DawsonRoom room = new DawsonRoom(Integer.parseInt(roomNumber), roomType);
				list.add(room);
				}
			}catch(IllegalArgumentException iae) {
				System.out.println("cant build room object hotel file illegal argumentt");
				
			}
			
			catch (NullPointerException npe){
				System.out.println("cant build customer object hotel file null pointer");
			}
			
			catch (IndexOutOfBoundsException iob){
				continue;
			}
			
		}

		Room[] rooms = list.toArray(new Room[0]);
		in.close();
		return rooms;

	}
			
	 //The Customer array returned by the above method must be an array whose size is equal
	 //to its capacity (i.e. the array must be full to capacity).
	// Email*First name*Last name*Card type*Card number \\
	public static Customer[] getCustomerListFromSequentialFile(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String str;
		List<Customer> list = new ArrayList<Customer>();
		while ((str = in.readLine()) != null) {
			if (str.isEmpty())
				continue;
			String[] array = str.split("\\*");
			Optional<CreditCard> card;
			int i = 0; 
				try {
					
					Email email = new Email(array[i]);
					Name name = new Name(array[i + 1], array[i + 2]);
					if (array.length != 5)
						card = null;
					else
						card = Optional.of(CreditCard.getInstance(
								CardType.valueOf(array[i+3].toUpperCase()), array[i + 4]));
					DawsonCustomer customer = new DawsonCustomer(name.getFirstName(), name.getLastName(), email, card);
					list.add(customer);
				} catch (IllegalArgumentException iae) {
					System.out.println("cant build customer object hotel file illegal argumentt   ");
					for (String arr: array)
						System.out.print(arr.toString());
					System.out.println(iae.getMessage());
				
				}
				
				catch (NullPointerException npe)
				{
					System.out.println("cant build customer object hotel file null pointer");
				}
				
				catch (IndexOutOfBoundsException iob)
				{
					continue;
				}
		}

		Customer[] customers = list.toArray(new Customer[0]);
		in.close();
		return customers;
	 }

	 
	public static Reservation[] getReservationListFromSequentialFile (File file, Customer[] customerList, Room[] roomList)
    throws IOException, IllegalArgumentException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String str;
		int inYear; int inMonth; int inDay;
		int outYear; int outMonth; int outDay;
		List<Reservation> list = new ArrayList<Reservation>();
		while ((str = in.readLine()) != null) {
			if (str.isEmpty())
				continue;
			String [] array = str.split("\\*");
			int i = 0;
				
				try{
					Email email = new Email(array[i]);
					Customer customer = search(customerList, email);
					inYear = Integer.parseInt(array[i+1]);
					inMonth = Integer.parseInt(array[i+2]);
					inDay = Integer.parseInt(array[i+3]);
					outYear = Integer.parseInt(array[i+4]);
					outMonth = Integer.parseInt(array[i+5]);
					outDay = Integer.parseInt(array[i+6]);
					Room room = search(roomList,Integer.parseInt(array[i+7]));
					DawsonReservation reservation = new DawsonReservation(customer,room,inYear,inMonth,inDay,outYear,outMonth,outDay);
					list.add(reservation);
				}catch (IllegalArgumentException iae){
					System.out.println("cant create customer from search");
				}catch (NullPointerException npe){
					System.out.println("null pointer exception getreservationlistfromfile");
				}
			
			}// end of while
				
				successfullreservationcount++;
		Reservation [] reservation = list.toArray(new Reservation[0]);
		in.close();
		return reservation;
	}
	 //super_woman669@hotmail.com*Andreea*Galchenyuk*mastercard*5388941127716138
	private static Customer search(Customer[] customerList, Email email) {
		// This method will use the email field in order to find the associated customer
		// If the customer can not be found, an Illegal Argument Exception will be thrown
		DawsonCustomer customer = null;
		for(int i =0; i < customerList.length; i++){
			if(customerList[i].getEmail().equals(email)){
				try{
				Name name = new Name(customerList[i].getName());
				customer = new DawsonCustomer(name.getFirstName(),name.getLastName(), email, customerList[i].getCreditCard());
				return customer;
				}catch (IllegalArgumentException iae) {
					System.out.println("name or customer cant be made in search");
				}
				
			}
		}
		return customer;
		
	}

	private static Room search(Room[] roomList, int room) {
		// This method will use the room number in order to find the associated room
		// If the room can not be found, an Illegal Argument Exception will be throwned
		DawsonRoom roomMatch= null;
		for(int i =0; i < roomList.length; i++ ){ 
			if (roomList[i].getRoomNumber() == room){
				try{
				roomMatch = new DawsonRoom(roomList[i].getRoomNumber(),roomList[i].getRoomType());
				return roomMatch;
				}catch (IllegalArgumentException iae) {
					System.out.println("Invalid room type");
				}
			}
		}
		return roomMatch;
		
	}

}

	
	
	

