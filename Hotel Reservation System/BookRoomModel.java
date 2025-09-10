import java.util.*;

/**
* This class is the Model for handling the operations when booking a room
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class BookRoomModel {
    /**
     * This variable is the hotel chosen to book a room in
     */
    private Hotel chosenHotel;

    /**
     * This variable is the room chosen to book a reservation in
     */
    private Room chosenRoom;

    /**
     * This is the constructor for a BookRoomModel instance
     * @param hotel chosen hotel to book in
     */
    public BookRoomModel(Hotel hotel){
        this.chosenHotel = hotel;
    }

    /**
     * This method gets the chosen hotel in this model
     * @return chosen hotel to book in
     */
    public Hotel getHotel(){
        return chosenHotel;
    }

    /**
     * This method gets the chosen room in this model
     * @return chosen room to book in
     */
    public Room getRoom(){
        return chosenRoom;
    }

    /**
     * This method checks the available rooms in a given hotel
     * @param hotel chosen hotel to check availability of rooms
     * @return true {no rooms are fully available for all days}
     * @return false {some rooms are availably for some days}
     */
    public boolean availableRooms(Hotel hotel){
        Iterator<Room> roomList = hotel.getRooms().iterator();
        int countDay = 0;
        int countRoom = 0;
        while(roomList.hasNext()){
            Room room = roomList.next();
            ArrayList<Boolean> available = room.getAvailability(); 
            Iterator<Boolean> availList = available.iterator();
            while(availList.hasNext()){
                if(availList.next() == false){
                    countDay++;
                }
            }
            if(countDay == available.size()){
                countRoom++;
            } 
        }

        if(countRoom == 0){
            return true;
        }
        else{
            return false;
        }
        
    }

    /**
     * This method checks the availability of the room in all days
     * @param room chosen to check the availability of
     * @return false {room is not available for all days}
     * @return true {room is available for some days}
     */
    public boolean checkAvailability(Room room){
        int count = 0;
        Iterator<Boolean> availableList = room.getAvailability().iterator();

        while(availableList.hasNext()){
            if(availableList.next() == false){
                count++;
            }
        }

        if(count == 31){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * This method find the index of the room, given a hotel and room name
     * @param hotel chosen hotel to find the room from
     * @param roomName name of the room to find
     * @return index of the given room name
     * @return -1 {room not found}
     */
    public int findRoom(Hotel hotel, String roomName){
        Iterator<Room> roomList = hotel.getRooms().iterator();
        int i = 0;

        while(roomList.hasNext()){
            Room roomNext = roomList.next();
            if(roomNext.getName().equals(roomName)){
                this.chosenRoom = roomNext;
                return i;
            }
            i++;
        }

        return -1;
    }

    /**
     * This method add a reservation to a room
     * @param reservation reservation to add to the room
     * @param room room to add the reservation to
     * @return -1 or 0 {invalid checkIn and checkOut days}
     * @return 1 {all reservation details are valid and the reservation is added to the room}
     */
    public int addReservation(Reservation reservation, Room room){
        ArrayList<Boolean> roomAvailable = room.getAvailability();
        ArrayList<Integer> dayList = chosenHotel.getMarkedDayList();
        ArrayList<Double> percenList = chosenHotel.getMarkedPriceList();
        Iterator<Integer> dayIterator = dayList.iterator();

        int in = reservation.getCheckIn() - 1;
        int out = reservation.getCheckOut() - 1;

        int count = 0;
        double totalPrice = 0.0;

        if (in < 0 || out > 30 || in >= out || in == 31 || out == 0){
            return -1;
        }

        for(int i = in; i < out; i++){
            if(roomAvailable.get(i) == false){
                return 0;
            }
        }

        for(int i = in; i < out; i++){
            roomAvailable.set(i, false);
           if(reservation.getDiscountCode().equals("STAY4_GET1") && i == in){
                reservation.addPriceBreakdown("Day " + (i + 1) + ": FREE !");
            }
            else{
                if(room instanceof Deluxe){
                    reservation.addPriceBreakdown("Day " + (i + 1) + ": " + ((Deluxe) room).getPrice());
                }
                else if(room instanceof Executive){
                    reservation.addPriceBreakdown("Day " + (i + 1) + ": " + ((Executive) room).getPrice());
                }
                else{
                    reservation.addPriceBreakdown("Day " + (i + 1) + ": " + room.getPrice());
                }
                
                while(dayIterator.hasNext()){
                    if(dayIterator.next() == i){
                        if(room instanceof Deluxe){
                            totalPrice += ((Deluxe) room).getPrice() * percenList.get(count);
                            reservation.addPriceBreakdown("Day " + (i + 1) + ": " + (percenList.get(count) * ((Deluxe) room).getPrice()));
                        }
                        else if(room instanceof Executive){
                            totalPrice += ((Executive) room).getPrice() * percenList.get(count);
                            reservation.addPriceBreakdown("Day " + (i + 1) + ": " + (percenList.get(count) * ((Executive) room).getPrice()));
                        }
                        else{
                            totalPrice += room.getPrice() * percenList.get(count);
                            reservation.addPriceBreakdown("Day " + (i + 1) + ": " + (percenList.get(count) * room.getPrice()));
                        }
                        count++;
                    }
                }
            }
        }

        if(room instanceof Deluxe){
            totalPrice += ((Deluxe) room).getPrice()*((out-in)-count);
          }
        else if(room instanceof Executive){
            totalPrice += ((Executive) room).getPrice()*((out-in)-count);
        }
        else{
            totalPrice += room.getPrice()*((out-in)-count);
        }

        reservation.setTotalPrice(totalPrice);
        //moved here from controller for better discount application
        checkDiscount(reservation.getDiscountCode(), reservation);

        room.getReservations().add(reservation);

        return 1;
    }

    /**
     * This method checks the discountCode of the reservation if it applies
     * @param discountCode code to check if valid for discount
     * @param reservation reservation to check the discount code from
     */
    public void checkDiscount(String discountCode, Reservation reservation){
        int checkIn = reservation.getCheckIn();
        int checkOut = reservation.getCheckOut();
        double totalPrice = reservation.getTotalPrice();
        ArrayList<Integer> markedDay = chosenHotel.getMarkedDayList();
        Iterator<Integer> mdIterator = markedDay.iterator();
        ArrayList<Double> markedPercent = chosenHotel.getMarkedPriceList();

        if(discountCode.equals("I_WORK_HERE")){
            reservation.setTotalPrice(totalPrice - totalPrice * 0.1);
        }
        else if(discountCode.equals("STAY4_GET1") && (checkOut - checkIn >= 5)){
            int i = 0;
            while(mdIterator.hasNext()){
                //if date modifier exists
                if(checkIn == mdIterator.next()){
                    reservation.setTotalPrice(totalPrice - ((totalPrice/(checkOut-checkIn)) * markedPercent.get(i)));
                }
                i++;
            }
            reservation.setTotalPrice(totalPrice - (totalPrice/(checkOut-checkIn)));
        }
        else if(discountCode.equals("PAYDAY") && (checkInRange(checkIn, checkOut, 1) || checkInRange(checkIn, checkOut, 2))){
            reservation.setTotalPrice(totalPrice - totalPrice * 0.07);
        }
    }
    
    /**
     * This method check if the checkIn and checkOut days are valid for the "PAYDAY" discount code
     * @param checkIn check in day of the reservation
     * @param checkOut check out day of the reservation
     * @param type for day 15 or day 30
     * @return true {valid for day 15 or day 30 discount}
     * @return false {checkIn and checkOut days are invalid for the discount code to work}
     */
    private boolean checkInRange(int checkIn, int checkOut, int type){
        for(int i = checkIn; i < checkOut; i++){
            if(i == 15 && type == 1){
                return true;
            }
            if(i == 30 && type == 2){
                return true;
            }
        }
        
        return false;
    }
}   
