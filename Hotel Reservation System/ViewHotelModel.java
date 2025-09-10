import java.util.*;

/**
* This class is the Model for showing the graphical user interface when viewing a hotel
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class ViewHotelModel {
    /**
     * This variable is the chosen hotel to view
     */
    private Hotel hotelViewed;

    /**
     * This is the constructor for a ViewHotelModel instance
     * @param hotel chosen hotel to view
     */
    public ViewHotelModel(Hotel hotel){
        this.hotelViewed = hotel;
    }

    /**
     * This gets the hotel variable of this model
     * @return
     */
    public Hotel getHotel(){
        return hotelViewed;
    }
    
    /**
     * This method gets an arrayList of available rooms for a specific day
     * @param day chosen day to check available rooms from
     * @return arrayList of available rooms
     */
    public ArrayList<Room> getAvailableRooms(int day) {
        ArrayList<Room> availableList = new ArrayList<Room>();
        for (Room room : hotelViewed.getRooms()) {
            if (isAvailable(room, day)) {
                availableList.add(room);
            }
        }
        return availableList;
    }

    /**
     * This method checks if a room is available for a specific day
     * @param room chosen room to check availability of
     * @param day chosen day to check availability
     * @return index of an available day
     * @return false {room is not available}
     */
    private boolean isAvailable(Room room, int day) {
        List<Boolean> availability = room.getAvailability();
        if (day > 0 && day <= availability.size()) {
            return availability.get(day - 1);
        }
        return false;
    }

    /**
     * This method finds the index of a room, given a room list and room name
     * @param roomList array list of rooms to find the room from
     * @param roomName name of the room to find
     * @return a Room instance if the room is found
     * @return null {room not found}
     */
    public Room findRoom(ArrayList<Room> roomList, String roomName){
        Iterator<Room> roomListIterator = roomList.iterator();

        while(roomListIterator.hasNext()){
            Room room = roomListIterator.next();
            if(room.getName().equals(roomName)){
                return room;
            }
        }

        return null;
    }

    /**
     * This method find the reservation of a specified guest
     * @param name name of the guest to find
     * @param roomIndex index of the room to find the reservation
     * @param resIndex index of the reservation to find
     * @return true {reservation with the given guest name is found}
     * @return false {reservation with the given guest name is not found}
     */
    public boolean findGuestRes(String name, Integer roomIndex[], Integer resIndex[]){
        ArrayList<Room> roomList = hotelViewed.getRooms();

        for(int i = 0; i < roomList.size(); i++){
            ArrayList<Reservation> resList = roomList.get(i).getReservations();
            for(int j = 0; j < resList.size(); j++){
                Reservation reservation = resList.get(j);
                if(reservation.getGuestName().equals(name)){
                    roomIndex[0] = i;
                    resIndex[0] = j;
                    return true;
                }
            }
        }

        return false;
    }
}
