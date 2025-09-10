import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

/**
* This class is the View for showing the graphical user interface when booking a room
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class BookRoomView extends JFrame{
    /**
     * This variable is a dialog prompt when booking a room
     */
    private JDialog popUp;

    /**
     * These are the buttons used in the main interface of booking a room option
     */
    private JButton selectRoomBtn, bookBtn, returnMenuBtn;
<<<<<<< HEAD

    /**
     * These are the text fields used for the input details when booking a room
     */
    private JTextField selectRoomTf, nameTf, inTf, outTf, discountTf;

    /**
     * This variable is the chosen hotel to book a room in
     */
=======
    private JTextField selectRoomTf, nameTf, inTf, outTf, discountTf;
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    private Hotel hotel;

    /**
     * This is the constructor for a BookRoomView instance
     * @param hotel chosen hotel to book a room in
     */
    public BookRoomView(Hotel hotel) {
        super("Booking a room in " + hotel.getName());
        this.hotel = hotel;
        this.bookBtn = new JButton("Book");
        setLayout(new BorderLayout());
        setSize(550, 420);

        initialize();

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

<<<<<<< HEAD
    /**
     * This method displays the main menu interface for booking a room
     */
    public void initialize() {
        // Center panel
=======
    public void initialize(){
        //Center panel
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
    
        JLabel stdLbl = new JLabel("Standard Rooms:");
        JTextArea stdRooms = new JTextArea(4, 40);
        stdRooms.setEditable(false);
        stdRooms.setFocusable(false);
    
        JLabel dlxLbl = new JLabel("Deluxe Rooms:");
        JTextArea dlxRooms = new JTextArea(4, 40);
        dlxRooms.setEditable(false);
        dlxRooms.setFocusable(false);
    
        JLabel excLbl = new JLabel("Executive Rooms:");
        JTextArea excRooms = new JTextArea(4, 40);
        excRooms.setEditable(false);
        excRooms.setFocusable(false);
    
        Iterator<Room> hotelRooms = hotel.getRooms().iterator();
        int stdCount = 0;
        int dlxCount = 0;
        int excCount = 0;
    
        stdRooms.setText("");
        dlxRooms.setText("");
        excRooms.setText("");
    
        while (hotelRooms.hasNext()) {
            Room room = hotelRooms.next();
            if (room instanceof Deluxe) {
                dlxCount++;
                dlxRooms.append(String.format("%-15s", room.getName()));
                if (dlxCount % 7 == 0) {
                    dlxRooms.append("\n");
                } else {
                    dlxRooms.append(" ");
                }
            } else if (room instanceof Executive) {
                excCount++;
                excRooms.append(String.format("%-15s", room.getName()));
                if (excCount % 7 == 0) {
                    excRooms.append("\n");
                } else {
                    excRooms.append(" ");
                }
            } else {
                stdCount++;
                stdRooms.append(String.format("%-15s", room.getName()));
                if (stdCount % 7 == 0) {
                    stdRooms.append("\n");
                } else {
                    stdRooms.append(" ");
                }
            }
        }
    
        centerPanel.add(stdLbl, gbc);
    
        gbc.gridy++;
        centerPanel.add(stdRooms, gbc);
    
        gbc.gridy++;
        centerPanel.add(dlxLbl, gbc);
    
        gbc.gridy++;
        centerPanel.add(dlxRooms, gbc);
    
        gbc.gridy++;
        centerPanel.add(excLbl, gbc);
    
        gbc.gridy++;
        centerPanel.add(excRooms, gbc);
    
        this.add(centerPanel, BorderLayout.NORTH);
    
        // South Panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
    
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(0, 5, 0, 5);
        gbc2.gridx = 0;
        gbc2.gridy = 0;
    
        JLabel selectLbl = new JLabel("Select a Room to Book");
        southPanel.add(selectLbl, gbc2);
    
        gbc2.gridx++;
        selectRoomTf = new JTextField();
        selectRoomTf.setColumns(8);
        southPanel.add(selectRoomTf, gbc2);
    
        gbc2.gridx++;
        selectRoomBtn = new JButton("Select");
        selectRoomBtn.setPreferredSize(new Dimension(100, 30));
        southPanel.add(selectRoomBtn, gbc2);
    
        gbc2.gridx++;
        returnMenuBtn = new JButton("Return to Main Menu");
        returnMenuBtn.setPreferredSize(new Dimension(160, 30));
        southPanel.add(returnMenuBtn, gbc2);
    
        this.add(southPanel, BorderLayout.CENTER);
    }

    /**
     * This method displays the window for inputting the details when booking a room
     */
    public void displayResInput(){
        popUp = new JDialog(this, "Booking Room", true);
        popUp.setLayout(new BorderLayout());
        popUp.setSize(400, 200);

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        JLabel label = new JLabel("Please input the necessary details: ");
        JLabel label2 = new JLabel("Name: ");
        JLabel label4 = new JLabel("Check-In Date: ");
        JLabel label5 = new JLabel("Check-Out Date: ");
        JLabel label6 = new JLabel("Discount Code (optional): ");

        nameTf = new JTextField(10);
        inTf = new JTextField(2);
        outTf = new JTextField(2);
        discountTf = new JTextField(10);
        bookBtn.setPreferredSize(new Dimension(100, 30));

        inputPanel.add(label2);
        inputPanel.add(nameTf);

        inputPanel.add(label4);
        inputPanel.add(inTf);

        inputPanel.add(label5);
        inputPanel.add(outTf);

        inputPanel.add(label6);
        inputPanel.add(discountTf);
        inputPanel.add(new JLabel());
        inputPanel.add(bookBtn);

        popUp.setLocationRelativeTo(null);
        popUp.add(label, BorderLayout.NORTH);
        popUp.add(inputPanel, BorderLayout.CENTER);
        popUp.setVisible(true);
        popUp.setResizable(false);
        popUp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    /**
     * This method sets up an action listener for selecting a room
     * @param actionListener actionListener for the select room button
     */
    public void setSelectRoomBtnListener(ActionListener actionListener){
        selectRoomBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for the button used to book a room
     * @param actionListener actionListener for the book button
     */
    public void setBookBtnListener(ActionListener actionListener){
        bookBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for returning to the main menu of the hotel reservation system
     * @param actionListener actionListener for the return to menu button
     */
    public void setReturnMenuBtnListener(ActionListener actionListener){
        returnMenuBtn.addActionListener(actionListener);
    }

    /**
     * This method gets the user input from a text field after selecting a room
     * @return String input from the text field
     */
    public String getSelectRoomTf(){
        return selectRoomTf.getText();
    }

    /**
     * This method gets the user input from a text field after entering the guest name
     * @return String input from the text field
     */
    public String getNameTf(){
        return nameTf.getText();
    }

    /**
     * This method gets the user input from a text field after inputting the check in day
     * @return String input from the text field
     */
    public String getInTf(){
        return inTf.getText();
    }

    /**
     * This method gets the user input from a text field after inputting the check out day
     * @return String input from the text field
     */
    public String getOutTf(){
        return outTf.getText();
    }

    /**
     * This method gets the user input from a text field after inputting the discount code
     * @return String input from the text field
     */
    public String getDiscountTf(){
        return discountTf.getText();
    }

    /**
     * This method closes the window used for inputting the details when booking a room
     */
    public void closePopUpFrame(){
        popUp.dispose();
      }
} 
