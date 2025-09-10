import java.util.ArrayList;

<<<<<<< HEAD
/**
* This class contains details about an Executive Room in a Hotel, which is a subclass of Room
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
public class Executive extends Room{

    /** 
     * This variable contains a Executive Room's price
     */
    private double priceExecutive;

    /**
     * This is the constructor for an Executive instance
     * @param name name of the room
     * @param price price of the room
     */
    public Executive(String name, double price) {
        super(name, price);
        this.priceExecutive = price * 1.35;
    }

    /**
     * This method sets a new price for an Executive Room
     * @param price new price of a room
     */
    @Override
    public void setPrice(double price){
        this.priceExecutive = price * 1.35;
    }

    /**
     * This method gets the price of an Executive Room
     * @return price of an Executive Room
     */
    @Override
    public double getPrice(){
        return this.priceExecutive;
    }

<<<<<<< HEAD
    /**
     * This method gets the reservations of an Executive Room
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    @Override
    public ArrayList<Reservation> getReservations(){
        return super.getReservations();
    }
}