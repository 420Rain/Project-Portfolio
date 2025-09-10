import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
* This class is the Controller for setting up the interactable options when booking a room
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class BookRoomController {
    /**
     * This variable is the model used for the controller
     */
    private BookRoomModel brModel;

    /**
     * This variable is the view used for the controller
     */
    private BookRoomView brView;

    /**
     * This is the constructor for a BookRoomController instance and it initializes the ActionEvents for the buttons in the view
     * @param brModel model for the controller
     * @param brView view for the controller
     */
    public BookRoomController(BookRoomModel brModel, BookRoomView brView){
        this.brModel = brModel;
        this.brView = brView;

        this.brView.setSelectRoomBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ArrayList<Room> rooms = brModel.getHotel().getRooms();
                Room chosen;
                String roomName = brView.getSelectRoomTf();
                int index = brModel.findRoom(brModel.getHotel(), roomName);

                if(index > -1){
                    chosen = rooms.get(index);
                    if(brModel.checkAvailability(chosen)){
                        brView.displayResInput();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Room is Fully Booked for Whole Month !", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No Room exists in this Hotel !", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.brView.setBookBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String discountCode = "";
                discountCode = brView.getDiscountTf();

                Reservation reservation = new Reservation(brView.getNameTf(), Integer.parseInt(brView.getInTf()), Integer.parseInt(brView.getOutTf()), brModel.getRoom(), discountCode);
                int n = brModel.addReservation(reservation, brModel.getRoom());
                if(n == -1){
                    JOptionPane.showMessageDialog(null, "Not within date range", "Book a Room ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else if(n == 0){
                    JOptionPane.showMessageDialog(null, "Dates overlapped", "Book a Room ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Room booked succesfully!", "Book a Room", n);

                    brView.closePopUpFrame();
                }
            }
        });

        this.brView.setReturnMenuBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                brView.dispose();
            }
        });
    }

}
