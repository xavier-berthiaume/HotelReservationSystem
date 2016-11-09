package group187.hotel.data;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.DawsonCustomer;
import group187.util.ListUtilities;

public class CustomerListDBTest {
	public static List<Customer> database;
	public static ListPersistenceObject listPersistenceObject;
	
	public static void main(String[] args) {
		System.out.println("test");
			Email email1 = new Email("zhu@abc.com");
			Name name = new Name("eric", "hughes");
			Optional<CreditCard> cardTest1 = Optional.of(new Amex("374616906032009"));
			DawsonCustomer customer1 = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1, cardTest1);

			customer1.setCreditCard(cardTest1);

			Email email2 = new Email("fhse@apl.com");
			Name name2 = new Name("something", "something");
			Optional<CreditCard> cardTest2 = Optional.of(new Amex("374616906032009"));
			DawsonCustomer customer2 = new DawsonCustomer(name2.getFirstName(), name2.getLastName(), email2, cardTest2);
			customer2.setCreditCard(cardTest2);

			System.out.println("Running binary search");
			binarySearch(customer2);
	}
	
	@SuppressWarnings("rawtypes")
	public static void binarySearch(Customer customer){
		listPersistenceObject = new SequentialTextFileList("datafiles\\database\\rooms.txt" , "datafiles\\database\\customers.txt", "datafiles\\database\\reservations.txt");
		database = listPersistenceObject.getCustomerDatabase();
		database.toArray(new Comparable[database.size()]);
		ListUtilities.sort(database.toArray(new Comparable[database.size()]));
		System.out.println("Item 1: " + database.get(0).toString());
		System.out.println("Item 2: " + database.get(1).toString());
		System.out.println("Item 3: " + database.get(2).toString());
		System.out.println("Item 4: " + database.get(3).toString());
		System.out.println("Item 5: " + database.get(4).toString());
		System.out.println("Item 6: " + database.get(5).toString());
		Customer custObj; // Customer var to hold objects from database
		Email email = customer.getEmail(), // Email from customer passed through parameter
			  emailObj; // Email var to hold objects from database
		int index = -1, // Index where to add new customer, -1 if duplicate
			startIndex = 0, // Start index where to start searching
		    endIndex = database.size(); // End index where to stop searching
			while (endIndex >= startIndex){
				int  midIndex = (endIndex+startIndex) / 2;
				
				if (database.get(midIndex).getEmail().compareTo(email) < 0){			
					endIndex = midIndex;
					startIndex++;
				}
	
				else if (database.get(midIndex).getEmail().compareTo(email) > 0){
					startIndex = midIndex;
					endIndex--;
				}
			
				if ((database.get(midIndex - 1).getEmail().compareTo(email) < 0) && (database.get(midIndex + 1).getEmail().compareTo(email) > 0))
					System.out.println("The index where the customer should go is: " + midIndex);
				
			}
			
 	}

}
