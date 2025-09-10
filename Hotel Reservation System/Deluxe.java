import java.util.ArrayList;
<<<<<<< HEAD
/**
* This class contains details about a Deluxe Room in a Hotel, which is a subclass of Room
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
=======

>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
public class Deluxe extends Room{

    /** 
     * This variable contains a Deluxe Room's price
     */
    private double priceDeluxe;

    /**
     * This is the constructor for a Deluxe instance
     * @param name name of the room
     * @param price price of the room
     */
    public Deluxe(String name, double price) {
        super(name, price);
        this.priceDeluxe = price * 1.2;
    }

    /**
     * This method sets a new price for a Deluxe Room
     * @param price new price of a room
     */
    @Override
    public void setPrice(double price){
        this.priceDeluxe = price * 1.2;
    }

    /**
     * This method gets the price of a Deluxe Room
     * @return price of an Executive Room
     */
    @Override
    public double getPrice(){
        return this.priceDeluxe;
    }

<<<<<<< HEAD
    /**
     * This method gets the reservations of a Deluxe Room
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    @Override
    public ArrayList<Reservation> getReservations(){
        return super.getReservations();
    }
}