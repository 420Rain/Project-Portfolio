import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
* This class is the Controller for setting up the interactable options when managing a hotel
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
=======
import javax.swing.JOptionPane;

>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
public class ManageHotelController{
    /**
     * This variable is the model used for the controller
     */
    private ManageHotelModel MHmodel;

    /**
     * This variable is the view used for the controller
     */
    private ManageHotelView MHview;

    /**
     * This variable is the chosen hotel to manage
     */
    private Hotel hotel;

    /**
     * This is the constructor for a ManageHotelController instance and it initializes the ActionEvents for the buttons in the view
     * @param model model used to manage a hotel
     * @param view view used to manage a hotel
     * @param hotel chosen hotel to manage
     */
    public ManageHotelController(ManageHotelModel model, ManageHotelView view, Hotel hotel){
        this.MHmodel = model;
        this.MHview = view;
        this.hotel = hotel;

        this.MHview.setChangeNameBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                MHview.changeNameDisplay(hotel);
            }
        });

        this.MHview.setNameBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String name = MHview.getInputTf();

                if(MHmodel.changeName(hotel, name)){
                    MHview.setNameLblText(name);
                    MHview.closeNameFrame();
                }
                else{
                    MHview.setFeedbackLblText("That Hotel Name Already Exists");
                }
            }
        });

        this.MHview.setAddRoomBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                MHview.selectRoomType();
            }
        });

        this.MHview.setAddStdBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(MHmodel.addRoom(hotel, 1) == true){
                    MHview.setLogLblText("Room Added Successfully");
                }
                else{
                    MHview.setLogLblText("Cannot Add Room. Max Number of Rooms Reached");
                }
                MHview.closeRoomTypeFrame();
            }
        });

        this.MHview.setAddDlxBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(MHmodel.addRoom(hotel, 2) == true){
                    MHview.setLogLblText("Room Added Successfully");
                }
                else{
                    MHview.setLogLblText("Cannot Add Room. Max Number of Rooms Reached");
                }
                MHview.closeRoomTypeFrame();
            }
        });

        this.MHview.setAddExcBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(MHmodel.addRoom(hotel, 3) == true){
                    MHview.setLogLblText("Room Added Successfully");
                }
                else{
                    MHview.setLogLblText("Cannot Add Room. Max Number of Rooms Reached");
                }
                MHview.closeRoomTypeFrame();
            }
        });

        this.MHview.setRemoveRoomBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(hotel.getNumRooms() > 1){
                    MHview.clearHotelButtons();
                    for(Room room: hotel.getRooms()){
                        String roomName = room.getName();
                        MHview.setButtonList(roomName, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e){
                                if(MHmodel.removeRoom(hotel, roomName)){
                                    MHview.closeRoomFrame();
                                    MHview.setLogLblText("Room Successfully Removed");
                                }
                                else{
                                    MHview.setFeedbackLblText("Cannot Remove. Selected Room Has Reservation/s");
                                }
                            }
                        });
                    }
                    MHview.roomsDisplay(0);
                }
                else{
                    MHview.setLogLblText("Cannot Remove a Room. Hotel Only Has One Room");
                }    
            }
        });

        this.MHview.setUpdPriceBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                MHview.updPriceDisplay(hotel);
            }
        });

        this.MHview.setPriceBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    double price = Double.parseDouble(MHview.getInputTf());

                    if (price >= 100.0) {
                        if (MHmodel.updatePrice(hotel, price)) {
                            MHview.setLogLblText("Successfully Changed the Room Prices");
                            MHview.clearTextFields();
                            MHview.closeUpdPriceFrame();
                        } else {
                            MHview.setFeedbackLblText("Could Not Change Price. Some Rooms Have Reservations");
                            MHview.clearTextFields();
                        }
                    } else {
                        MHview.setFeedbackLblText("Could Not Change Price. Rooms Must Cost at Least 100");
                        MHview.clearTextFields();
                    }
                } catch (NumberFormatException ex) {
                    MHview.setFeedbackLblText("Invalid Price. Please Enter a Valid Number");
                }  
            }
        });

        this.MHview.setDpModifyBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                MHview.modifyDpDisplay();
            }
        });

        this.MHview.setApplyMdBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Integer day = MHview.getSelectedDay();
                Double percent = Double.parseDouble(MHview.getInputTf())/100.00;

                if(day > 0 && day < 32){
                    if(percent > 0.4999 && percent < 1.5100){
                        MHmodel.setModifier(day, percent, hotel);
                        JOptionPane.showMessageDialog(null, "Modifier Set !", "SUCCESS", 1);
                        MHview.closeModFrame();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Not within range of 50% - 150%", "ERROR", 0);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Not within range of 1 - 31", "ERROR", 0);
                }
<<<<<<< HEAD

=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
            }
        });

        this.MHview.setRemoveRsvBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MHview.clearHotelButtons();
                MHview.setLogLblText("");
                for (Room room : hotel.getRooms()) {
                    MHview.setButtonList(room.getName(), new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (!room.getReservations().isEmpty()) {
                                MHview.closeRoomFrame();
                                MHview.removeRsvDisplay(room);
<<<<<<< HEAD
=======

>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
                                MHview.clearRemoveReservationButtons();

                                removeOneRsv(room);
                                removeAllRsv(room);
                            } else {
                                MHview.setFeedbackLblText("Selected Room Has No Reservations");
                            }
                        }
                    });
                }
                MHview.roomsDisplay(1);
            }
        });

        this.MHview.setRemoveHotelBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                MHmodel.removeHotel(hotel);
                MHview.closeManageHFrame();
            }
        });

        this.MHview.setBackBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                MHview.closeManageHFrame();
            }
        });

        this.MHview.manageHotelDisplay(hotel);
    }

    /**
     * This method sets up the ActionEvent for removing one reservation in a room
     * @param room chosen room to remove one reservation from
     */
    public void removeOneRsv(Room room){
        this.MHview.setRemoveOneBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                MHview.findGuestDisplay();
                MHview.closeRemoveRsvFrame();
                removeGuestRsv(room);
            }
        });
    }

    /**
     * This method sets up the ActionEvent for finding the reservation given a guest name and removing it
     * @param room chosen room to remove one reservation from
     */
    public void removeGuestRsv(Room room){
        this.MHview.setGuestBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String name = MHview.getInputTf();
                int reservationIndex = -1;
                
                for(int i = 0; i < room.getReservations().size(); i++){
                    if(name.equals(room.getReservations().get(i).getGuestName())){
                        reservationIndex = i;
                        break;
                    }
                }

                if(reservationIndex > -1){
                    MHmodel.removeReservation(room, room.getReservations().get(reservationIndex));
                    MHview.setLogLblText("Successfully Removed " + name + "'s Reservation");
                    MHview.closeGuestFrame();
                }
                else{
                    MHview.setFeedbackLblText("Guest Name Not Found");
                }
            }
        });
    }

    /**
     * This method sets up the ActionEvent for removing all the reservations in a room
     * @param room chsoen room to remove all reservations from
     */
    public void removeAllRsv(Room room){
        this.MHview.setRemoveAllBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                for(int i = 0; i < room.getReservations().size(); i++){
                    MHmodel.removeReservation(room, room.getReservations().get(i));
                }
                MHview.closeRemoveRsvFrame();
                MHview.setLogLblText("Removed All Reservations in " + room.getName());
            }
        });
    }

}