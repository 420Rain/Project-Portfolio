import java.util.*;

public class Driver{
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    //Creates a new hotelManager instance
    HotelManager hotelManager = new HotelManager();

    int option;
    
    do{
      //Displays the options that the user can use
      displayMenu();

      //Error handling if user inputs a string
      if(!scan.hasNextInt()) {
        scan.next();
        option = -1;
      }
      else{
        option = scan.nextInt();
        scan.nextLine();
      }

      switch(option){
          //This case leads the user to create a Hotel instance
        case 1:
            createHotel(hotelManager, scan);
            break;
          //This case leads the user to view a Hotel and its details such as it's earnings and Room details
        case 2:
            viewHotel(hotelManager, scan);
            break;
          /*This case leads the user to manage a Hotel with options such as adding a room
          changing hotel name, changing the base price and etc,*/
        case 3:
            manageHotel(hotelManager, scan);
            break;
          //This case leads the user to book a Room in a Hotel instance
        case 4:
            bookRoom(hotelManager, scan);
            break;
          //This case exits the program
        case 5:
          System.out.println("\nThank You for Using the Hotel Reservation System!\n");
          break;
        default:
          System.out.println("\nInvalid Option!\n");
      }
    }while(option != 5);

  }

  //Display menu interface
  private static void displayMenu(){
    System.out.println("===============================");
    System.out.println("   Hotel Reservation System");
    System.out.println("===============================");
    System.out.println("(1) Create a Hotel");
    System.out.println("(2) View a Hotel");
    System.out.println("(3) Manage a Hotel");
    System.out.println("(4) Book a Room");
    System.out.println("(5) Exit the System");
    System.out.println("===============================");
    System.out.print("Enter Option: ");
  }

  //This method asks the user for their input of the name of a Hotel and its number of rooms to give access to the createHotel method
  private static void createHotel(HotelManager hotelManager, Scanner scan){
    String hotelName;
    int numOfRooms;

    do{
        System.out.print("\nEnter a Name For the Hotel: ");
        hotelName = scan.nextLine();

        System.out.print("Enter Number of Rooms (1-50): ");
      //Error handling if user inputs a String instead of an int
        if(!scan.hasNextInt()) {
          scan.next();
          scan.nextLine();
          numOfRooms = -1;
        }
        else{
          numOfRooms = scan.nextInt();
          scan.nextLine();
        }
      //Calls createHotel method to check if valid, if valid method continues running, if not, method loops
    }while(!hotelManager.createHotel(hotelName, numOfRooms));

  }

  //This method gives access to view the user's chosen Hotel
  private static void viewHotel(HotelManager hotelManager, Scanner scan){
    int index;

    do{
        //Checks if there are Hotels to be displayed
        if(hotelManager.displayHotels() == false){
            return;
        }

        System.out.print("Select a Hotel to View: ");
        //Error handling if user inputs String instead of int
        if(!scan.hasNextInt()) {
          scan.next();
          index = -1;
        }
        else{
          index = scan.nextInt();
          scan.nextLine();
        }
      //Calls viewHotel method to check if valid, if valid method continues running, if not, method loops
    }while(!hotelManager.viewHotel(index));

  }

  //This method gives access to Options for the user's chosen Hotel
  private static void manageHotel(HotelManager hotelManager, Scanner scan){
    int index;

    do{
       //Checks if there are Hotels to be displayed
        if(hotelManager.displayHotels() == false){
            return;
        }

        System.out.print("Select a Hotel to Manage: ");
       //Error handling if user inputs String instead of int
        if(!scan.hasNextInt()) {
          scan.next();
          index = -1;
        }
        else{
          index = scan.nextInt();
          scan.nextLine();
        }
        //Calls manageHotel method to check if valid, if valid method continues running, if not, method loops
    }while(!hotelManager.manageHotel(index));

  }

  //This method gives access to Options to book a Room for the user's chosen Hotel and Room
  private static void bookRoom(HotelManager hotelManager, Scanner scan){
    int index;

    do{
       //Checks if there are Hotels to be displayed
        if(hotelManager.displayHotels() == false){
            return;
        }

        System.out.print("Select a Hotel to Book a Room In: ");
      //Error handling if user inputs String instead of int
        if(!scan.hasNextInt()) {
          scan.next();
          index = -1;
        }
        else{
          index = scan.nextInt();
          scan.nextLine();
        }
       //Calls bookRoom method to check if valid, if valid method continues running, if not, method loops
    }while(!hotelManager.bookRoom(index));
  }

}
