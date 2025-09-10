import java.util.*;
/**
* This class represents a Hotel where the hotel contains a name, 
* base price, earnings, and rooms
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class Hotel{
    /**
    * This variable contains the name of the Hotel
    */
    private String name;

    /**
    * This variable contains the base price for all the rooms in the Hotel
    */
    private double price;

    /**
    * This variable contains the total earnings of the Hotel
    */
    private double earnings;

    /**
     * This variable contains the number of Rooms in a Hotel
     */
    private int numRooms;

    /**
    * This variable is an array of Room objects which represents the rooms of the hotel
    */
    private ArrayList<Room> hotelRooms;
    private ArrayList<Integer> markedDayList;
    private ArrayList<Double> markedPriceList;

<<<<<<< HEAD
    /**
    * This method creates a new Hotel Instance
    * @param name the name of the hotel
    * @param numOfRooms the number of rooms of the hotel
    * @param roomPrice the base price of the rooms
    */
    public Hotel(String name, int numOfRooms, double roomPrice){
      this.name = name;
      this.price = roomPrice;
      this.numRooms = numOfRooms;
      this.hotelRooms = new ArrayList<Room>();
      this.markedDayList = new ArrayList<Integer>();
      this.markedPriceList = new ArrayList<Double>();
=======
  /**
  * This method creates a new Hotel Instance
  * @param name the name of the hotel
  * @param numOfRooms the number of rooms of the hotel
  * @param roomPrice the base price of the rooms
  */
  public Hotel(String name, int numOfRooms, double roomPrice){
    this.name = name;
    this.price = roomPrice;
    this.numRooms = numOfRooms;
    this.hotelRooms = new ArrayList<Room>();
    this.markedDayList = new ArrayList<Integer>();
    this.markedPriceList = new ArrayList<Double>();
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324


      int numStdRooms = (numOfRooms + 2) / 3;
      int numDlxRooms = (numOfRooms + 1) / 3;
      int numExcRooms = numOfRooms / 3;

<<<<<<< HEAD
      for (int i = 0; i < numStdRooms; i++) {
        hotelRooms.add(new Room("Room " + (i + 1), roomPrice));
      } 
      for (int i = 0; i < numDlxRooms; i++) {
        hotelRooms.add(new Deluxe("Room " + (numStdRooms + i + 1), roomPrice)); // Adjust price if needed
      }
      for (int i = 0; i < numExcRooms; i++) {
        hotelRooms.add(new Executive("Room " + (numStdRooms + numDlxRooms + i + 1), roomPrice)); // Adjust price if needed
      }
    }
=======
    for (int i = 0; i < numStdRooms; i++) {
      hotelRooms.add(new Room("Room " + (i + 1), roomPrice));
    } 
    for (int i = 0; i < numDlxRooms; i++) {
      hotelRooms.add(new Deluxe("Room " + (numStdRooms + i + 1), roomPrice)); // Adjust price if needed
    }
    for (int i = 0; i < numExcRooms; i++) {
      hotelRooms.add(new Executive("Room " + (numStdRooms + numDlxRooms + i + 1), roomPrice)); // Adjust price if needed
    }
  }
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324

  /**
  * This method gets the name of the Hotel
  * @return name of the Hotel
  */
  public String getName(){
    return this.name;
  }

  /**
  * This method gets the base price of the Rooms of the Hotel
  * @return base price of the Rooms
  */
  public double getPrice(){
    return this.price;
  }
  
  /**
  * This method gets the earnings of the Hotel
  * @return earnings of the Hotel
  */
  public double getEarnings(){
    double temp = 0;
    //adds all the totalPrice of the Reservations in a Hotel
    for(int i = 0; i < numRooms; i++){
      for(int j = 0; j < hotelRooms.get(i).getReservations().size(); j++){
        temp += hotelRooms.get(i).getReservations().get(j).getTotalPrice();
      }
    }
    this.earnings = temp;
    return this.earnings;
  }

  /**
  * This method gets the number of Rooms of the Hotel
  * @return number of rooms of the Hotel
  */
  public int getNumRooms(){
    return this.numRooms;
  }

  /**
   * This method gets a room in the hotel given an index
   * @param index room to return based on the room list
   * @return a room in the hotel
   */
  public Room getRoom(int index){
    return hotelRooms.get(index);
  }

  /**
  * This method gets the array of Rooms of the Hotel
  * @return array of Rooms
  */
  public ArrayList<Room> getRooms(){
     return this.hotelRooms;   
  }

  /**
  * This method sets a new name of the Hotel as it replaces the old one
  * @param name name of the hotel
  */
  public void setName(String name){ //error handling in hotelManager to check if list of hotels contain inputted name
    this.name = name;
  }

  /**
  * This method changes the new base price of the Rooms in the Hotel
  * @param price base price of a Room
  */
  public boolean setRoomPrice(double price){
    for(int i = 0; i < numRooms; i++){ //cause you can only change price if theres no reservations in all rooms
      if(!hotelRooms.get(i).getReservations().isEmpty()){
        return false;
      }
    }

    this.price = price;

    for(int i = 0; i < numRooms; i++){
      hotelRooms.get(i).setPrice(price);
    }
    
    return true;
  }

  /**
   * This method add a day and price when the price of a day has been modified
   * @param day day to modify the price of
   * @param price new modified price
   */
  public void setDayPriceModifier(Integer day, double price){
    this.markedDayList.add(day);
    this.markedPriceList.add(price);
  }

  /**
   * This method gets the days that have been price modified
   * @return arrayList of price modified days
   */
  public ArrayList<Integer> getMarkedDayList(){
    return this.markedDayList;
  }

  /**
   * This method gets the list of modified prices
   * @return arrayList of modified prices
   */
  public ArrayList<Double> getMarkedPriceList(){
    return this.markedPriceList;
  }

  /**
   * This methods add a room to the hotel
   */
  public void addNumRoom(){
    this.numRooms++;
  }

  /**
   * This methods removes a room from the hotel
   */
  public void lessNumRoom(){
    this.numRooms--;
  }

}
