package group187.hotel.business;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;

public class DawsonRoom implements Room {
	private static final long serialVersionUID = 42031768871L;
	private final int roomNumber;
	private final RoomType roomType; 
	
	public DawsonRoom(int roomNumber,RoomType roomType){
		
		if (!isValid(roomNumber))
			throw new IllegalArgumentException();
		this.roomNumber = roomNumber; 
		this.roomType = roomType; 
	}
	
	public boolean isValid(int roomNumber) throws IllegalArgumentException{
		try{
		
			int[] roomArr = new int[3];
			// Converts the int to an array for easier digit extraction
			for (int i = 2; i >= 0; i--){
				roomArr[i] = roomNumber % 10;
				roomNumber = roomNumber / 10;
			}
			int floor = roomArr[0];
			if (floor < 1 || floor > 8)
				throw new IllegalArgumentException("The floor can only be between 1 and 8");
			if (roomArr[1] != 0)
				throw new IllegalArgumentException("The room number can only start with 0");
			if (roomArr[2] < 1 || roomArr[2] > 8)
				throw new IllegalArgumentException("The room number can only end with 1 - 8");
		}
		
		catch(IllegalArgumentException e){
			System.out.print(e.getMessage());
		}
		
		return true;
		
	}
	@Override
	public int getFloor(){
		return ((this.roomNumber / 100) );
	}
	@Override
	public int getNumber(){
		return (roomNumber % 10);
	}
	
	@Override
	public String toString() {
		return (this.roomNumber + "*" + this.roomType);
	}

	@Override
	public int compareTo(Room o) {
		
		if (o == null)
			throw new NullPointerException();
		if (this.getFloor() < o.getFloor())
			return 1;
		if (this.getFloor() == o.getFloor()){
			if (this.getNumber() < o.getNumber())
				return 1;
			else if (this.getNumber() > o.getNumber())
				return -1;
			else
				return 0;
		}
		if (this.getFloor() > o.getFloor())
			return -1;
		return 0;
	}

	@Override
	public RoomType getRoomType() {
		return this.roomType;
	}

	@Override
	public int getRoomNumber() {
		return this.roomNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomNumber;
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DawsonRoom))
			return false;
		DawsonRoom other = (DawsonRoom) obj;
		if (roomNumber != other.roomNumber)
			return false;
		if (roomType != other.roomType)
			return false;
		return true;
	}

}
