import java.util.*;

public class HotelReservationSystemModel {
    private ArrayList<Hotel> Hotels;

    public HotelReservationSystemModel(){
        this.Hotels = new ArrayList<Hotel>();
    }

    public int createHotel(String name, int numOfRooms){
        //Checks if the new Hotel's name exists within the array of Hotels
        for(int i = 0; i < this.Hotels.size(); i++){
            if(name.equals(this.Hotels.get(i).getName())){
                return -1;
            } 
        }
        //Checks if number of Rooms inputted by the User is within the Range
        if(numOfRooms < 1 || numOfRooms > 50){
            return -2;
        }
        //Creates Hotel instance
        this.Hotels.add(new Hotel(name, numOfRooms, 1299.0));

        return 1;
    }

    public ArrayList<Hotel> getHotelList() {
		return this.Hotels;
	}
}
