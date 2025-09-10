import java.util.*;

/**
* This class is the Model for handling the operations to manage a hotel
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class ManageHotelModel{

    /** 
     * This variable contains a list of Hotels
     */
    private ArrayList<Hotel> hotelList;

    /**
     * This is the constructor for a ManageHotelModel instance
     * @param hotelList
     */
    public ManageHotelModel(ArrayList<Hotel> hotelList){
        this.hotelList = hotelList;
    }

    /**
     * This methods gets the ArrayList of hotels of a ManageHotelModel's instance
     * @return ArrayList of Hotel
     */
    public ArrayList<Hotel> getHotelList(){
        return this.hotelList;
    }

    /**
     * This method changes the name of a hotel, given a hotel and a name
     * @param hotel chosen hotel for changing name
     * @param name new name for the hotel
     * @return false the new name inputted is already taken by an exisiting hotel
     * @return true the new name inputted is unique
     */
    public boolean changeName(Hotel hotel, String name){
        for(int i = 0; i < this.hotelList.size(); i++){
            if(this.hotelList.get(i).getName().equals(name)){
                return false;
            }
        }

        hotel.setName(name);
        return true;
    }

    /**
     * This method adds a standard, deluxe, or executive room to a chosen hotel
     * @param hotel chosen hotel for adding a room to
     * @param roomType the type of room to add (standard/deluxe/executive)
     * @return true {there are less than 50 rooms in the hotel and a room was successfully added}
     * @return false {the hotel already has 50 rooms and a room was not added}
     */
    public boolean addRoom(Hotel hotel, int roomType) {
        // Check if a Room instance can still be added
        if (hotel.getNumRooms() < 50) {
            // Generate a unique name for the room
            String roomName = generateUniqueRoomName(hotel);
            // Creates a Room instance
            if(roomType == 2){
                hotel.getRooms().add(new Deluxe(roomName, hotel.getPrice()));
            }
            else if(roomType == 3){
                hotel.getRooms().add(new Executive(roomName, hotel.getPrice()));
            }
            else{
                hotel.getRooms().add(new Room(roomName, hotel.getPrice()));
            }
            hotel.addNumRoom();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method ensures all hotel room names are unique especially when removing a room
     * @param hotel chosen hotel to check the room names
     * @return a unique room name
     */
    private String generateUniqueRoomName(Hotel hotel) {
        String baseName = "Room";
        int count = hotel.getNumRooms();
        String uniqueName = baseName + " " + count;
    
        while (isRoomNameTaken(hotel, uniqueName)) {
            count++;
            uniqueName = baseName + " " + count;
        }
        return uniqueName;
    }
    
    /**
     * This method checks if a room name in the hotel is already existing
     * @param hotel chosen hotel to check room names
     * @param roomName given room name to check if unique
     * @return true {room name is already existing}
     * @return false {room name is unique}
     */
    private boolean isRoomNameTaken(Hotel hotel, String roomName) {
        for (Room room : hotel.getRooms()) {
            if (room.getName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method removes a room in a hotel, given the hotel and room name
     * @param hotel chosen hotel to remove a room from
     * @param roomName existing name of a room in the hotel
     * @return true {chosen room to remove has no reservation and is has been removed from the hotel}
     * @return false {chosen room has a reservation and is not removed from the hotel}
     */
    public boolean removeRoom(Hotel hotel, String roomName){
        //Loops through a Hotel's Rooms
        for(int i = 0; i < hotel.getNumRooms(); i++){
            //Checks if chosen Room exists
            if(roomName.equals(hotel.getRooms().get(i).getName())){
                //Checks if chosen Room still has Reservations
                if(hotel.getRooms().get(i).getReservations().isEmpty()){
                    //Removes Room
                    hotel.getRooms().remove(i);
                    hotel.lessNumRoom();
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
      }

    /**
     * This method updates the base prices of the room in a hotel
     * @param hotel chosen hotel to update the room prices for
     * @param price new price inputted by the user
     * @return false {some rooms have reservations and the prices were not changed}
     * @return true {all rooms have no reservations and the prices were changed}
     */
    public boolean updatePrice(Hotel hotel, double price){
        return hotel.setRoomPrice(price);
    }

    /**
     * This method sets a modified price for a specific day
     * @param day the day of the month to modify the price of
     * @param percent how much the price will be modified
     * @param hotel chosen hotel to modify day price of
     */
    public void setModifier(Integer day, Double percent, Hotel hotel){
        ArrayList<Integer> dayList = hotel.getMarkedDayList();
        ArrayList<Double> doubleList = hotel.getMarkedPriceList();
        Iterator<Integer> dayIterator = dayList.iterator();

        int i = 0;
        while(dayIterator.hasNext()){
            if(dayIterator.next() == day){
                dayList.remove(i);
                doubleList.remove(i);
            }
            i++;
        }

        hotel.setDayPriceModifier(day, percent);
    }

    /**
     * This method removes a reservation in a room
     * @param room chosen room to remove a reservation from
     * @param reservation reservation to be removed from the room
     */
    public void removeReservation(Room room, Reservation reservation){
        for(int i = 0; i < room.getReservations().size(); i++){
          //finds the reservation of the guest name
            if(room.getReservations().get(i).getGuestName().equals(reservation.getGuestName())){
                int checkIn = reservation.getCheckIn() - 1;
                int checkOut = reservation.getCheckOut() - 1;
    
                //Removes reservation by setting the availability of the range of days to true
                for(int j = checkIn; j <= checkOut; j++){
                    room.setAvailability(j, true);
                }
    
                room.getReservations().remove(i);
                return;
            }
        }
    }

    /**
     * This method removes a hotel from the hotel management system
     * @param hotel chosen hotel to remove
     */
    public void removeHotel(Hotel hotel){
        this.hotelList.remove(hotel);
    }

}