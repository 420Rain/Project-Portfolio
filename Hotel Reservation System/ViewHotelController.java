import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
* This class is the Controller for setting up the interactable options when booking a room
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class ViewHotelController {
    /**
     * This variable is the model used for the controller
     */
    private ViewHotelModel vhModel;

    /**
     * This variable is the view used for the controller
     */
    private ViewHotelView vhView;

    /**
     * This is the constructor for a ViewHotelController instance and it initializes the ActionEvents for the buttons in the view
     * @param vhModel model for the controller
     * @param vhView view for the controller
     */
    public ViewHotelController(ViewHotelModel vhModel, ViewHotelView vhView) {
        this.vhModel = vhModel;
        this.vhView = vhView;

        this.vhView.setRoomAvailBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vhView.showView("availView");
            }
        });

        this.vhView.setRoomDetailBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hotel hotel = vhModel.getHotel();
                Iterator<Room> hotelRooms = hotel.getRooms().iterator();
                JTextArea textArea = vhView.getDisplayRoomsTA();
                int count = 0;
                textArea.setText("");

                while(hotelRooms.hasNext()){
                    count++;
                    Room room = hotelRooms.next();
                    textArea.append(String.format("%-15s", room.getName()));
                    if(count % 7 == 0){
                        textArea.append("\n");
                    }else if(count < 10){
                        textArea.append(" ");
                    }
                }

                vhView.showView("selectRoomView");
            }
        });

        this.vhView.setReservationsBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vhView.showView("selectReservationView");
            }
        });

        this.vhView.setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vhView.dispose();
            }
        });

        this.vhView.setSelectDayBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = Integer.parseInt(vhView.getAvailTfText());
                Iterator<Room> availableRooms = vhModel.getAvailableRooms(day).iterator();
                JTextArea textArea = vhView.getAvailabilityTA();
                int count = 0;
                textArea.setText("");
                
                while(availableRooms.hasNext()){
                    count++;
                    Room room = availableRooms.next();
                    textArea.append(String.format("%-15s", room.getName()));
                    if(count % 7 == 0){
                        textArea.append("\n");
                    }else if(count < 10){
                        textArea.append(" ");
                    }
                }
                
            }
        });

        this.vhView.setSelectRoomBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Room room = vhModel.findRoom(vhModel.getHotel().getRooms(), vhView.getRoomTfText());
                ArrayList<JLabel> labelList = vhView.getLabelList();
                ArrayList<Boolean> availableList = room.getAvailability();
        
                JLabel name = vhView.getRPlaceHolder();
                JLabel price = vhView.getPPlaceHolder();
                JLabel type = vhView.getTPlaceholder();
        
                name.setText(room.getName());
                price.setText(String.valueOf(room.getPrice()));
<<<<<<< HEAD

=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
                if(room instanceof Deluxe){
                    type.setText("Deluxe");
                }
                else if(room instanceof Executive){
                    type.setText("Executive");
                }
                else{
                    type.setText("Standard");
                }
        
                labelList.clear();
        
                for(int i = 0; i < availableList.size(); i++){
                    JLabel label = new JLabel("" + (i + 1), SwingConstants.CENTER);
                    if(availableList.get(i)){
                        vhView.setGreen(label);
                    } else {
                        vhView.setRed(label);
                    }
                    label.setPreferredSize(new Dimension(50, 50));
                    labelList.add(label);
                }
        
                // Ensure the panel updates with new labels
                JPanel daysPanel = vhView.getDaysPanel();
                daysPanel.removeAll();
                for (JLabel label : labelList) {
                    daysPanel.add(label);
                }
                daysPanel.revalidate();
                daysPanel.repaint();
                
                vhView.showView("displayRoomView");
            }
        });

        this.vhView.setSelectGuestBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                JTextArea textArea = vhView.getDisplayPbTA();
                String guestName = vhView.getGuestTfText();
                Integer roomIndex[] = {-1};
                Integer resIndex[] = {-1};

                if(vhModel.findGuestRes(guestName, roomIndex, resIndex)){
                    Reservation reservation = vhModel.getHotel().getRooms().get(roomIndex[0]).getReservations().get(resIndex[0]);
                    vhView.getNPlaceHolder().setText(reservation.getGuestName());
                    vhView.getResPlaceHolder().setText(reservation.getRoom().getName());
                    vhView.getIPlaceHolder().setText("Day " + reservation.getCheckIn());
                    vhView.getOPlaceHolder().setText("Day " + reservation.getCheckOut());
                    if(!reservation.getDiscountCode().equals(null)){
                        vhView.getTpPlaceHolder().setText("Php " + reservation.getTotalPrice());
                    }
                    else{
                        vhView.getTpPlaceHolder().setText("Php " + reservation.getTotalPrice() + " (Discount applied !)");
                    }

                    Iterator<String> pbIterator = reservation.getPriceBreakdown().iterator();
<<<<<<< HEAD

=======
                    
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
                    textArea.setText("");

                    while(pbIterator.hasNext()){
                        textArea.append(pbIterator.next() + "\n");
                    }

                    vhView.showView("displayReservationView");
                }
            }
        });

        this.vhView.setReturnBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vhView.showView("mainView");
            }
        });

        this.vhView.setReturnBtn2Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vhView.showView("mainView");
            }
        });

        this.vhView.setReturnBtn3Listener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vhView.showView("mainView");
            }
        });

        this.vhView.setReturnSelectBtn(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                vhView.showView("selectRoomView");
            }
        });

        this.vhView.setReturnSelectBtn2(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                vhView.showView("selectReservationView");
            }
        });
    }
}