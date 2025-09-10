import java.util.*;

/**
 * This class represents the Hotel management system and its functionalities
 * @author Rainier A. Dulatre
 * @author Patrick Hans A. Perez
 * @version 1.0
 */
public class HotelManager{
    /**
     * This variable contains an array of Hotel objects
     * representing the Hotels available in the Hotel Manager
     */
    private ArrayList<Hotel> Hotels;

    /**
     * This method creates a HotelManager instance
     */
    public HotelManager(){
        this.Hotels = new ArrayList<Hotel>();
    }

    /**
     * This method displays the Hotel instances available in the array
     * @return true {if hotels are found and can be displayed}
     * false {if there are no instances present in the array}
     */
    public boolean displayHotels(){
        if(this.Hotels.size() == 0){
            System.out.println("\nCannot Proceed. No Hotels Found");
            return false;
        }
        
        System.out.println("\nHotel List:");
        for(int i = 0; i < this.Hotels.size(); i++){
            System.out.println("(" + (i+1) + ") " + this.Hotels.get(i).getName());
        }
        System.out.println();

        return true;
    }

    /**
     * This method creates a Hotel instance and adds it to the array of Hotels
     * @param name name of the Hotel
     * @param numOfRooms desired number of rooms in the Hotel
     * @return true {if name and number of rooms are valid}
     * false {if name and number of rooms are invalid}
     */
    public boolean createHotel(String name, int numOfRooms){
        //Checks if the new Hotel's name exists within the array of Hotels
        for(int i = 0; i < this.Hotels.size(); i++){
            if(name.equals(this.Hotels.get(i).getName())){
                System.out.println("\nCannot Create Hotel. That Hotel Name Already Exists");
                return false;
            } 
        }
        //Checks if number of Rooms inputted by the User is within the Range
        if(numOfRooms < 1 || numOfRooms > 50){
            System.out.println("\nCannot Create Hotel. Number of Rooms Must be Between 1 and 50");
            return false;
        }
        //Creates Hotel instance
        this.Hotels.add(new Hotel(name, numOfRooms, 1299.0));

        System.out.println("\nHotel Successfully Added\n");
        return true;
    }

    /**
     * This method gives the user a functionality of viewing a Hotel's information
     * such as its name, number of rooms, its earnings and Reservation/Room details
     * @param index the index of the chosen Hotel object in the array
     * @return true {Hotel is found and functionality works}
     * false {Hotel is not found and some of the functionality does not work}
     */
    public boolean viewHotel(int index){
        //Checks if chosen Hotel is in the array of Hotels
        if(index < 1 || index > this.Hotels.size()){
            System.out.println("Hotel Not Found\n");
            return false;
        }

        int hotelIndex = index - 1;
        //Creates a new Hotel object for the chosen Hotel
        Hotel hotel = this.Hotels.get(hotelIndex);

        //Displays Hotel details such as its Name, number of Rooms and Earnings
        System.out.println("\nHotel Name: " + hotel.getName());
        System.out.println("Number of Rooms: " + hotel.getNumRooms());
        System.out.println("Earnings for the Month: " + hotel.getEarnings());

        Scanner scan = new Scanner(System.in);

        int lowInfo;

        do{
            //Displays optional information for checking the Room's Details and Reservation Details
            System.out.println("\nSelect Information to View:");
            System.out.println("(1) Room Availability");
            System.out.println("(2) Room Information");
            System.out.println("(3) Reservations");
            System.out.println("(4) Back to Main Menu\n");
            System.out.print("Enter Option: ");
            //Checks if user inputs a String instead of an int
            if(!scan.hasNextInt()) {
                scan.next();
                lowInfo = -1;
            }
            else{
                lowInfo = scan.nextInt();
                scan.nextLine();
            }

            switch(lowInfo){
                //This case displays all of the Hotel's Rooms' availability on a chosen day
                case 1:
                    int day;
                    do{
                        System.out.print("\nSelect a Day (1-31): ");
                        //Checks if user inputted a String instead of int
                        if(!scan.hasNextInt()) {
                            scan.next();
                            day = -1;
                        }
                        else{
                            day = scan.nextInt();
                            scan.nextLine();
                        }
                        //Checks if chosen day is within range 
                        if(day < 1 || day > 31){
                             System.out.println("\nSelected Day is Invalid");
                        }
                    }while(day < 1 || day > 31);

                    //Displays the available Rooms in the day
                    System.out.println("\nRooms Available on Day " + day + ":");
                    for(int i = 0; i < hotel.getNumRooms(); i++){
                        if(hotel.getRooms().get(i).isAvailable(day) == true){
                            System.out.print(" | " + hotel.getRooms().get(i).getName());
                        }
                        if(i > 0 && i % 5 == 0){
                            System.out.println(" | ");
                        }
                    }
                    System.out.println();
                    break;
                //This case displays a chosen Room's information
                case 2:
                    String roomName;
                    int roomFound = -1;
                    //Displays an array of Rooms
                    do{
                        System.out.println("\nRoom List:");
                        for(int i = 0; i < hotel.getNumRooms(); i++){                          
                            System.out.print(" | " + hotel.getRooms().get(i).getName());
                            
                            if(i > 0 && i % 5 == 0){
                                System.out.println(" | ");
                            }
                        }
                        System.out.println();

                        System.out.print("Enter Room Name: ");
                        roomName = scan.nextLine();

                        //Checks if chosen Room is found within the array
                        for(int i = 0; i < hotel.getNumRooms(); i++){
                            if(roomName.equals(hotel.getRooms().get(i).getName())){
                                roomFound = i;
                            }
                        }
                        
                        if(roomFound == -1){
                            System.out.println("\nRoom Not Found");
                        }

                    }while(roomFound == -1);

                    //Puts chosen Room in a new Room object
                    Room room = hotel.getRooms().get(roomFound);

                    //Displays Room details
                    System.out.println("\nRoom Name: " + room.getName());
                    System.out.println("Room Price per Night: " + room.getPrice());
                    System.out.print("Room Availability (Days Available): \n");

                    int numAvail = 0;
                    //Checks for Room's availability
                    for(int i = 0; i < 31; i++){
                        if(room.isAvailable(i) == true){
                            numAvail++;
                            System.out.print("["+ (i+1) + "] ");
                        }
                        if(i > 0 && i % 5 == 0){
                            System.out.println();
                        }
                    }

                    if(numAvail == 0){
                        System.out.println("\n" + room.getName() + " is Unavailable for the Rest of the Month");
                    }
                    break;
                //This case checks for a Reservation under a guest's name
                case 3:
                    System.out.print("\nEnter Guest Name: ");    
                    String name = scan.nextLine();
                    int nameFound = -1;
                    int roomIndex = -1;

                    //Checks if Room and guest's Reservation is found
                    for(int i = 0; i < hotel.getNumRooms(); i++){
                        for(int j = 0; j < hotel.getRooms().get(i).getReservations().size(); j++){
                            if(name.equals(hotel.getRooms().get(i).getReservations().get(j).getGuestName())){
                                roomIndex = i;
                                nameFound = j;
                            }
                        }
                    }
                    if(nameFound == -1){
                        System.out.println("\nReservation Not Found. No Matching Guest Names Found");
                        break;
                    }
                    
                    Reservation reservation = hotel.getRooms().get(roomIndex).getReservations().get(nameFound);
                    //Displays Reservation details
                    System.out.println("\nGuest Name: " + reservation.getGuestName());
                    System.out.println("Reserved Room: " + reservation.getRoom().getName());
                    System.out.println("Check-In Day: " + reservation.getCheckIn());
                    System.out.println("Check-Out Day: " + reservation.getCheckOut());
                    System.out.println("Total Price: " + reservation.getTotalPrice());
                    System.out.println("Price Breakdown:");
                    for (int i = reservation.getCheckIn(); i < reservation.getCheckOut(); i++) {
                        System.out.println("Day " + i + ": " + reservation.getRoom().getPrice());
                    }
                    break;
                //Returns to hotelManager main menu
                case 4:
                    System.out.println();
                    break;
                
                default:
                    System.out.println("\nInvalid Option");
            }
        }while(lowInfo != 4);

        scan.close();
        return true;
    }

    /**
     * This method gives the user the functionality to manage a Hotel,
     * it gives options such as changing the name of the Hotel, adding a Room, removing
     * a Room, changing the Price of the Rooms, removing a Reservation and removing the Hotel itself
     * @param index the index of the chosen Hotel in the array
     * @return true {Hotel is found within the array}
     * false {Hotel is not found within the array}
     */
    public boolean manageHotel(int index){
        //Checks if Chosen Hotel is found
        if(index < 1 || index > this.Hotels.size()){
            System.out.println("\nHotel Not Found\n");
            return false;
        }

        Scanner scan = new Scanner(System.in);

        int hotelIndex = index - 1;
        //Puts chosen Hotel in a new Hotel Object
        Hotel hotel = this.Hotels.get(hotelIndex);

        int option;
        String optionString;

        //Display options for managing the Hotel
        do{
            System.out.println("\nWelcome to " + hotel.getName() + "'s Management System!\n");
            System.out.println("(1) Change Hotel Name");
            System.out.println("(2) Add Rooms");
            System.out.println("(3) Remove Rooms");
            System.out.println("(4) Update the Price of the Rooms");
            System.out.println("(5) Remove Reservation");
            System.out.println("(6) Remove Hotel");
            System.out.println("(7) Exit the Management System");
            System.out.print("\nEnter Option: ");

            //Checks if user inputted String instead of int
            if(!scan.hasNextInt()) {
                scan.next();
                option = -1;
              }
              else{
                option = scan.nextInt();
                scan.nextLine();
              }

            String roomName;

            switch(option){
                //This case changes the Hotel's name
                case 1:
                    boolean nameAvailable;
                    String name;

                    do{
                        //User enters new name of the Hotel
                        System.out.print("\nEnter a New Name for the Hotel: ");
                        name = scan.nextLine();

                        nameAvailable = true;

                        for(int i = 0; i < this.Hotels.size(); i++){
                            //Checks if Hotel name is valid
                            if(name.equals(this.Hotels.get(i).getName())){
                                nameAvailable = false;
                            }
                        }

                        if(nameAvailable == false){
                            System.out.println("\nThat Name is Already Exists");
                        }

                    }while(nameAvailable == false);
                    
                    do{
                        //Asks if user still wants to proceed
                        System.out.print("\nAre You Sure You Want to Change the Hotel Name? [Y/N]: ");
                        optionString = scan.nextLine();

                        switch(optionString.toUpperCase()){
                            //Hotel's name is Changed
                            case "Y":
                                hotel.setName(name);
                                System.out.println("\nThe Hotel's Name is Succesfully Changed to " + hotel.getName());
                                break;
                            //Hotel's name does not change
                            case "N":
                                System.out.println("\nOperation Cancelled. Returning to the management menu...");
                                break;
                            
                            default:
                                System.out.println("\nNot a Valid Option!");;
                        }
                    }while(!optionString.equalsIgnoreCase("Y") && !optionString.equalsIgnoreCase("N"));
                    break;
                //This case adds a new Room to the Hotel
                case 2:
                    do{
                        //Asks if user still wants to proceed
                        System.out.print("\nAre You Sure You Want to Add a New Room? [Y/N]: ");
                        optionString = scan.nextLine();

                        switch(optionString.toUpperCase()){
                            //Room is added
                            case "Y":
                                hotel.addRoom();
                                break;
                            //Room is not added
                            case "N":
                                System.out.println("\nRoom Will Not Be Added. Returning to the Management Menu...");
                                break;
                            
                            default:System.out.println("\nNot a Valid Option!");
                        }
                    }while(!optionString.equalsIgnoreCase("Y") && !optionString.equalsIgnoreCase("N"));
                    break;
                //This case removes a Room of the Hotel
                case 3:
                    //Displays list of Rooms
                    for(int i = 0; i < hotel.getNumRooms(); i++){
                        System.out.print(" | " + hotel.getRooms().get(i).getName());
                        if(i > 0 && i % 5 == 0){
                            System.out.println(" | ");
                        }
                    }
                    //User inputs the Room they wish to remove
                    System.out.print("\nEnter the Room Name You Want to Remove: ");
                    roomName = scan.nextLine();                    
                    
                    do{
                        //Asks if user still wants to procced
                        System.out.print("\nAre You Sure You Want to Remove That Room? [Y/N]: ");
                        optionString = scan.nextLine();

                        switch(optionString.toUpperCase()){
                            //Room is succesfully removed
                            case "Y":
                                hotel.removeRoom(roomName);
                                break;
                            //Room is not removed
                            case "N":
                                System.out.println("\nRoom Will Not Be Removed. Returning to the Management Menu...");
                                break;
                            
                            default:System.out.println("\nNot a Valid Option!");
                        }
                    }while(!optionString.equalsIgnoreCase("Y") && !optionString.equalsIgnoreCase("N"));
                    break;
                //This case changes the base price of the Room
                case 4:
                    double amount;

                    do{
                        //Asks user to input new price
                        System.out.print("\nEnter a New Room Price: ");
                        amount = scan.nextDouble();
                        scan.nextLine();

                        //Checks if new price is greater than or equal to 100
                        if(amount < 100){
                            System.out.println("\nError, Rooms Must Cost at Least 100");
                        }

                    }while(amount < 100); 

                    do{
                        //Asks the user if they still wish to proceed
                        System.out.print("\nAre You Sure You Want to Set a New Price? [Y/N]: ");
                        optionString = scan.nextLine();

                        switch(optionString.toUpperCase()){
                            //Price changed
                            case "Y":
                                hotel.setRoomPrice(amount);
                                System.out.println("\nPrice succesfully set");
                                break;
                            //Price not changed
                            case "N":
                                System.out.println("\nPrice Will Not Be Changed, Returning to the Management Menu...");
                                break;
                            
                            default:System.out.println("\nNot a Valid Option!");
                        }
                    }while(!optionString.equalsIgnoreCase("Y") && !optionString.equalsIgnoreCase("N"));
                    break;
                //This case removes a Reservation from a Room chosen by the user
                case 5:
                    int opti0n;

                    int roomIndex = -1;

                    do{
                        //Displays the Rooms
                        for(int i = 0; i < hotel.getNumRooms(); i++){
                            if(i > 0 && i % 10 == 0){
                                System.out.println();
                            }
                            System.out.println("("+ (i+1) +") " + hotel.getRooms().get(i).getName());
                        }
                        //Asks the Room where the Reservation will be removed
                        System.out.print("\nEnter Room Name to Remove a Reservation From: ");
                        roomName = scan.nextLine();

                        //Checks if Room exists within the Array
                        for(int i = 0; i < hotel.getNumRooms(); i++){
                            if(roomName.equals(hotel.getRooms().get(i).getName())){
                                roomIndex = i;
                            }
                        }

                        if(roomIndex == -1){
                            System.out.println("\nRoom Not Found, Enter another room...\n");
                        }
                    }while(roomIndex == -1);
                    
                    Room room = hotel.getRooms().get(roomIndex);

                    //Checks if Room has Reservations
                    if(room.getReservations().isEmpty()){
                        System.out.println("\nSelected Room Has no Reservations");
                        break;
                    }

                    do{
                        //Asks if user still wishes to proceed
                        System.out.print("\nAre You Sure You Want to Remove that Room's Reservation/s? [Y/N]: ");
                        optionString = scan.nextLine();
                        
                        switch(optionString.toUpperCase()){
                            //User will still remove a Reservation
                            case "Y":
                                do{
                                    //Displays option to remove all Reservations or only one Reservation
                                    System.out.println("\nPlease Select an Option: ");
                                    System.out.println("1. Remove All Reservations In " + hotel.getRooms().get(roomIndex).getName());
                                    System.out.println("2. Remove One Reservation In " + hotel.getRooms().get(roomIndex).getName());

                                    System.out.print("\nOption: ");
                                    //Checks if user inputted a String instead of int
                                    if(!scan.hasNextInt()) {
                                        scan.next();
                                        opti0n = -1;
                                    }
                                    else{
                                        opti0n = scan.nextInt();
                                        scan.nextLine();
                                    }

                                        switch(opti0n){
                                            //Removes all Reservations in the Room
                                            case 1:
                                                for(int i = 0; i < room.getReservations().size(); i++){
                                                    room.removeReservation(room.getReservations().get(i));
                                                }
                                                System.out.println("\nAll the Reservations in the Room Are Removed!");
                                                break;
                                            //Removes a single Reservation of the Room
                                            case 2:
                                                String guestName;    
                                                //Asks for guest's name
                                                System.out.print("\nEnter the Guest's Name of the Reservation You Want to Remove: ");
                                                guestName = scan.nextLine();
                                            
                                                int reservationIndex = -1;
                                                //Looks for guest's name within the Reservation
                                                for(int i = 0; i < room.getReservations().size(); i++){
                                                    if(guestName.equals(room.getReservations().get(i).getGuestName())){
                                                        reservationIndex = i;
                                                        break;
                                                    }
                                                }
                                                //Check if the name exists
                                                if(reservationIndex == -1){
                                                    System.out.println("\nA Reservation With that Guest Name Does not Exist");
                                                    break;
                                                }

                                                //Creates a Room object 
                                                Reservation reservation = room.getReservations().get(reservationIndex);
                                                //Removes the Reservation
                                                room.removeReservation(reservation);
                                                break;

                                            default:
                                                System.out.println("\nNot a valid option");
                                        }
                                }while(opti0n != 1 && opti0n != 2 && opti0n == -1);
                                break;
                            //Reservation/s is/are not removed
                            case "N":
                                System.out.println("\nReservation/s Not Removed, Returning to the Management Menu...");
                                break;
                            
                            default:
                                System.out.println("\nNot a valid option!");
                        }
                    }while(!optionString.equalsIgnoreCase("Y") && !optionString.equalsIgnoreCase("N"));
                    break;
                //This case removes the hotel from the Hotel array
                case 6:
                    do{
                        //Asks if user still wishes to proceed
                        System.out.print("\nAre You Sure You Want to Remove " + hotel.getName() + " From the List of Hotels? [Y/N]: ");
                        optionString = scan.nextLine();

                        switch(optionString.toUpperCase()){
                            //Hotel is removed and goes to option 7 which returns to the Main Menu
                            case "Y":
                                this.Hotels.remove(hotelIndex);

                                System.out.println("\nHotel Succesfully Deleted\n");
                                option = 7;
                                break;
                            //Hotel is not removed
                            case "N":
                                System.out.println("\n" + this.Hotels.get(hotelIndex).getName() + " Will Not Be Deleted...");
                                break;
                            
                            default:
                                System.out.println("\nNot a valid option!");
                        }
                    }while(!optionString.equalsIgnoreCase("Y") && !optionString.equalsIgnoreCase("N"));
                    break;
                //Returns to Main Menu
                case 7:
                    System.out.println("\nThank you for using " + this.Hotels.get(hotelIndex).getName() + "'s Management System\n");
                    break;

                default:
                    System.out.println("\nNot a valid option");
            }
        }while(option != 7);
        
        return true;
    }   

    /**
     * This method books a Reservation for a chosen Room in a Hotel
     * @param index the index of the chosen Hotel in the array
     * @return true {Hotel is found within the array and the Room is booked}
     * false {Hotel is not found within the array or the Room is not booked or the Room is not available}
     */
    public boolean bookRoom(int index){
        //Finds if chosen Hotel exists
        if(index < 1 || index > this.Hotels.size()){
            System.out.println("Hotel Not Found");
            return false;
        }

        int hotelIndex = index - 1;
        //Puts chosen Hotel in a new Hotel object
        Hotel hotel = this.Hotels.get(hotelIndex);

        Scanner scan = new Scanner(System.in);
        
        String roomName;
        boolean roomAvailable = false;
        int roomFound;
        
        do{
            //Displays the Rooms available for Reservation
            System.out.println("Available Rooms in " + hotel.getName() + ":");
            for(int i = 0; i < hotel.getNumRooms(); i++){
                for(int j = 0; j < 31; i++){
                    if(hotel.getRooms().get(i).isAvailable(j) == true){
                        roomAvailable = true;
                        break;
                    }
                }
                if(roomAvailable){
                    System.out.print(" | " + hotel.getRooms().get(i).getName());
                }
                if(i > 0 && i % 5 == 0 && roomAvailable){
                    System.out.println(" | ");
                }
            }
            System.out.println();
            //Checks if there are still Rooms available
            if(!roomAvailable){
                System.out.println("This Hotel Has No More Rooms Available");

                scan.close();
                return false;
            }
            //Asks user for desired Room
            System.out.print("Enter Selected Room: ");
            roomName = scan.nextLine();

            roomFound = -1;
            //Checks if Room exists within the Array
            for(int i = 0; i < hotel.getNumRooms(); i++){
                if(roomName.equals(hotel.getRooms().get(i).getName())){
                    roomFound = i;
                }
            }

            if(roomFound == -1){
                System.out.println("\nRoom Not Found\n");
            }

        }while(roomFound == -1);

        //Puts chosen Room in a new Room object
        Room room = hotel.getRooms().get(roomFound);
        //Asks for user's name
        System.out.print("\nEnter Guest Name: ");
        String guestName = scan.nextLine();

        int checkIn;
        int checkOut;

        do{
            //Asks user for the Check in Day
            System.out.print("Enter Check-In Day: ");
            //Checks if user inputted String instead of int
            if(!scan.hasNextInt()) {
                scan.next();
                checkIn = -1;
            }
            else{
                checkIn = scan.nextInt();
                scan.nextLine();
            }

            //Asks user for the Check out Day
            System.out.print("Enter Check-Out Day: ");
            //Checks if user inputted String instead of int
            if(!scan.hasNextInt()) {
                scan.next();
                checkOut = -1;
            }
            else{
                checkOut = scan.nextInt();
                scan.nextLine();
            }
            //Creates a new Reservation in the Room
        }while(!room.addReservation(new Reservation(guestName, checkIn, checkOut, room)));

        return true;
    }
}
