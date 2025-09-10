import java.util.*;
/**
* This class contains details about a Room in a Hotel
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class Room{
  /**
  * This variable contains a Rooom's name
  */
  private String name;
  /**
  * This variable contains a Room's price
  */
  private double price;

  /**
  * This variable contains an array of Boolean where it holds the
  * availability of the rooms from day 1-31
  */
  private ArrayList<Boolean> availability;
  /**
  * This variable contains an array of Reservations where it contains instances
  * of Reservation objects
  */
  private ArrayList<Reservation> reservations;

  /**
  * This is a constructor for a Room instance 
  * @param name name of the room
  * @param price price of the room
  */
  public Room(String name, double price){
    this.name = name;
    this.price = price;
    this.availability = new ArrayList<Boolean>();
    this.reservations = new ArrayList<Reservation>();

    //sets all availability to true
    for(int i = 0; i < 31; i++){
        this.availability.add(true);
    }
  }
  /**
  * This method gets the name of a Room
  * @return name of a Room
  */
  public String getName(){
    return this.name;
  }

  /**
  * This method gets the price of a Room
  * @return price of a Room
  */
  public double getPrice(){
    return this.price;
  }

  /**
  * This method returns a boolean if a Room is available or not
  * on a day chosen by the usre
  * @param index day of availability
  * @return boolean value
  */
  public boolean isAvailable(int index){
    return this.availability.get(index);
  }

  /**
   * This method gets the availability of the room in all days
   * @return an arrayList of the days the room is available
   */
  public ArrayList<Boolean> getAvailability(){
    return this.availability;
  }

  /**
   * This method sets the availability of the room in a specified day
   * @param index day to set availability
   * @param availability true(set to available) or false(set to not available)
   */
  public void setAvailability(int index, boolean availability){
    this.availability.set(index, availability);
  }

  /**
  * This method gets the array of Reservations of a Room instance
  * @return array of Reservations
  */
  public ArrayList<Reservation> getReservations(){
    return this.reservations;
  }

  /**
  * This method sets a new price for a Room
  * @param price new price of a Room
  */
  public void setPrice(double price){
    this.price = price;
  }
<<<<<<< HEAD
  
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
}
