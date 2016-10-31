package dw317.lib.ListUtilitiesTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import group187.util.ListUtilities;

public class MergeTest {

	@SuppressWarnings("unchecked")
	public static Comparable[] main(String[] args) throws FileNotFoundException {
		 @SuppressWarnings("rawtypes")
		Comparable[] list1 = {5, 7, 8, 77, 88, 111},
			      list2 = {2, 3, 5, 7, 10, 55},
			      list3 =  (Comparable[]) Array.newInstance(
			    		  list1.getClass().getComponentType(), list1.length + list2.length);
			int l1counter = 0;
			int l2counter = 0;
			boolean isReservation = false;
			boolean isCustomer = false;
			File duplicates = new File("datafiles/duplicates/mergeduplicates.txt");
			try {
				duplicates.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (list1[0] != null){
				if (list1[0] instanceof Reservation)
					isReservation = true;
				else if (list1[0] instanceof Customer)
					isCustomer = true;
			}
			else
				return list3;
			
			for (int i =0; i < list1.length + list2.length; i++)
			{
				if (l1counter != list1.length && l2counter != list2.length)
				{
					if (isReservation){
						if (((Reservation)list1[l1counter]).compareTo((Reservation) list1[l1counter]) == 0)
							ListUtilities.saveListToTextFile(((Reservation)list1[l1counter]).toString().split("\\*"), duplicates);
						if (((Reservation)list1[l1counter]).compareTo((Reservation) list1[l1counter]) < 0){
							list3[i] = list1[l1counter];
							l1counter++;
						}
						else{
							list3[i] = list2[l2counter];
							l2counter++;
						}
					
					}
					
					else if (isCustomer){
						if (((Customer)list1[l1counter]).compareTo((Customer) list1[l1counter]) == 0)
							try {
								ListUtilities.saveListToTextFile(list1[1].toString().split("\\*"), duplicates);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						if (((Customer)list1[l1counter]).compareTo((Customer) list1[l1counter]) < 0){
							list3[i] = list1[l1counter];
							l1counter++;
						}
						else{
							list3[i] = list2[l2counter];
							l2counter++;
						}
					
					}
				}
				else
				{
					if (l1counter == list1.length)
					{
						list3[i] = list2[l2counter];
						l2counter++;
					}
					else
					{
						list3[i] = list1[l1counter];
						l1counter++;
					}
				}
			}
				
	 			return list3;
	}

	}


