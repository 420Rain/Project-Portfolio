import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;

/**
* This class is the View for showing the graphical user interface when managing a hotel
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class ManageHotelView extends JFrame{
<<<<<<< HEAD
    /**
     * These are the frames used for each option when managing a hotel
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    private JFrame updPriceFrame, nameFrame, roomFrame, removeRsvFrame, guestFrame, roomTypeFrame, modFrame;

    /**
     * These are the panels used for the main menu of the manage hotel display
     */
    private JPanel northPanel, centerPanel;

    /**
     * These are the labels used to show system updates and for prompting the user
     */
    private JLabel logLbl, nameLbl, promptLbl, promptLbl2, feedbackLbl;

    /**
     * These are the buttons used for each option when managing a hotel
     */
    private JButton changeNameBtn, addRoomBtn, removeRoomBtn, updPriceBtn, dpModifyBtn, removeRsvBtn, removeHotelBtn, 
    backBtn, addStdBtn, addDlxBtn, addExcBtn;

    /**
     * This list contains a button for each room in the hotel
     */
    private ArrayList<JButton> roomList = new ArrayList<JButton>();

    /**
     * This is a text field used for the change name, update price, find guest name, and modify day price options
     */
    private JTextField inputTf;

    /**
     * These are the extra buttons for features within an option in managing a hotel
     */
    private JButton priceBtn, nameBtn, removeOneBtn, removeAllBtn, guestBtn,applyMdBtn;
<<<<<<< HEAD
    
    /**
     * This is a combo box for the date price modifier option
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    private JComboBox<Integer> daysCB;
    
    /**
     * This is a constructor for a ManageHotelView instance
     */
    public ManageHotelView(){
        super("Manage Hotel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(300,500);

        Dimension buttonDim = new Dimension(170, 30);

        //Change name option buttons
        this.changeNameBtn = new JButton("Change Hotel Name");
        this.changeNameBtn.setPreferredSize(buttonDim);

        this.nameBtn = new JButton("Change Name");
        this.nameBtn.setPreferredSize(new Dimension(120, 30));
  
        //Add room option buttons
        this.addRoomBtn = new JButton("Add a Room");
        this.addRoomBtn.setPreferredSize(buttonDim);

        this.addStdBtn = new JButton("Add a Standard Room");
        this.addStdBtn.setPreferredSize(buttonDim);
        
        this.addDlxBtn = new JButton("Add a Deluxe Room");
        this.addDlxBtn.setPreferredSize(buttonDim);

        this.addExcBtn = new JButton("Add an Executive Room");
        this.addExcBtn.setPreferredSize(buttonDim);
  
        //Remove room option button
        this.removeRoomBtn = new JButton("Remove a Room");
        this.removeRoomBtn.setPreferredSize(buttonDim);
  
        //Update price option buttons
        this.updPriceBtn = new JButton("Update Room Prices");
        this.updPriceBtn.setPreferredSize(buttonDim);

        this.priceBtn = new JButton("Change Price");
        this.priceBtn.setPreferredSize(new Dimension(120, 30));

        //Date price modifier option buttons
        this.dpModifyBtn = new JButton("Modify Date Pricing");
        this.dpModifyBtn.setPreferredSize(buttonDim);

        this.applyMdBtn = new JButton("Apply Modification");
        this.applyMdBtn.setPreferredSize(new Dimension(150, 30));
  
        //Remove reservation option buttons
        this.removeRsvBtn = new JButton("Cancel A Reservation");
        this.removeRsvBtn.setPreferredSize(buttonDim);

        this.removeOneBtn = new JButton("Remove One");
        this.removeOneBtn.setPreferredSize(new Dimension(130, 30));

        this.removeAllBtn = new JButton("Remove All");
        this.removeAllBtn.setPreferredSize(new Dimension(130, 30));

        this.guestBtn = new JButton("Check Name");
        this.guestBtn.setPreferredSize(new Dimension(120, 30));
  
        //Remove hotel option button
        this.removeHotelBtn = new JButton("Remove This Hotel");
        this.removeHotelBtn.setPreferredSize(buttonDim);

        //Return to main menu option button
        this.backBtn = new JButton("Back to Main Menu");
        this.backBtn.setPreferredSize(buttonDim);

    }

    /**
     * This method displays the main interface for managing a hotel
     * @param hotel chosen hotel to manage
     */
    public void manageHotelDisplay(Hotel hotel){
        //Top panel
        northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        nameLbl = new JLabel("~ " + hotel.getName() + " ~", SwingConstants.CENTER);
        nameLbl.setFont(new Font("Dialog", Font.BOLD, 18));
        nameLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        northPanel.add(nameLbl, BorderLayout.NORTH);

        promptLbl2 = new JLabel("What Would You Like to do With this Hotel?", SwingConstants.CENTER);
        northPanel.add(promptLbl2, BorderLayout.CENTER);

        this.add(northPanel, BorderLayout.NORTH);

        //Center Panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 50, 5, 50);

        gbc.gridy++;
        centerPanel.add(changeNameBtn, gbc);

        gbc.gridy++;
        centerPanel.add(addRoomBtn, gbc);

        gbc.gridy++;
        centerPanel.add(removeRoomBtn, gbc);

        gbc.gridy++;
        centerPanel.add(updPriceBtn, gbc);

        gbc.gridy++;
        centerPanel.add(dpModifyBtn, gbc);

        gbc.gridy++;
        centerPanel.add(removeRsvBtn, gbc);

        gbc.gridy++;
        centerPanel.add(removeHotelBtn, gbc);

        gbc.gridy++;
        centerPanel.add(backBtn, gbc);

        this.add(centerPanel, BorderLayout.CENTER);

        logLbl = new JLabel("", SwingConstants.CENTER);
        logLbl.setFont(new Font("Dialog", Font.PLAIN, 12));
        logLbl.setBorder(new EmptyBorder(0,0, 10, 0));
        logLbl.setPreferredSize(new Dimension(200, 30));
      
        this.add(logLbl, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /**
     * This method displays the window used for changing the name of a hotel
     * @param hotel chosen hotel to display the current name of
     */
    public void changeNameDisplay(Hotel hotel){
        nameFrame = new JFrame("Change Hotel Name");
        nameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nameFrame.setLayout(new BorderLayout());
        nameFrame.setSize(new Dimension(380, 150));

        promptLbl = new JLabel("Enter a New Name for the Hotel", SwingConstants.CENTER);
        promptLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        nameFrame.add(promptLbl, BorderLayout.NORTH);

        feedbackLbl = new JLabel("Current Hotel Name: " + hotel.getName(), SwingConstants.CENTER);
        feedbackLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        feedbackLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        nameFrame.add(feedbackLbl, BorderLayout.SOUTH);

        //Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        inputTf = new JTextField(19);

        gbc.gridx++;
        centerPanel.add(inputTf, gbc);

        gbc.gridx++;
        centerPanel.add(nameBtn, gbc);

        nameFrame.add(centerPanel, BorderLayout.CENTER);

        nameFrame.setLocationRelativeTo(null);
        nameFrame.setVisible(true);
        nameFrame.setResizable(false);
    }

    /**
     * This method displays the window used to select the type of room to add to the hotel
     */
    public void selectRoomType(){
        roomTypeFrame = new JFrame("Select Room");
        roomTypeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        roomTypeFrame.setLayout(new BorderLayout());
        roomTypeFrame.setSize(new Dimension(250, 220));

        promptLbl = new JLabel("Choose a Type of Room to Add", SwingConstants.CENTER);
        promptLbl.setBorder(new EmptyBorder(5, 5, 5, 5));
        roomTypeFrame.add(promptLbl, BorderLayout.NORTH);

        //Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;

        centerPanel.add(addStdBtn, gbc);

        gbc.gridy++;
        centerPanel.add(addDlxBtn, gbc);

        gbc.gridy++;
        centerPanel.add(addExcBtn, gbc);

        roomTypeFrame.add(centerPanel, BorderLayout.CENTER);

        roomTypeFrame.setLocationRelativeTo(null);
        roomTypeFrame.setVisible(true);
        roomTypeFrame.setResizable(false);
    }

    /**
     * This method displays all the rooms in a hotel as interactable buttons
     * @param index changes the prompt depending on the option chosen (Remove Hotel or Remove Reservation)
     */
    public void roomsDisplay(int index){
        roomFrame = new JFrame("Select Room");
        roomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        roomFrame.setLayout(new BorderLayout());
        roomFrame.setSize(new Dimension(450, 400));

        if(index == 0){
            promptLbl = new JLabel("Select a Hotel Room to Remove", SwingConstants.CENTER);
        }
        else{
            promptLbl = new JLabel("Select a Hotel Room", SwingConstants.CENTER);
        }
        promptLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        roomFrame.add(promptLbl, BorderLayout.NORTH);

        feedbackLbl = new JLabel("", SwingConstants.CENTER);
        feedbackLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        feedbackLbl.setFont(new Font("Dialog", Font.BOLD, 12));
        roomFrame.add(feedbackLbl, BorderLayout.SOUTH);

        // Center panel with grid layout
        JPanel centerPanel = new JPanel();
        int rows = (int) Math.ceil(roomList.size() / 3.0);
        centerPanel.setLayout(new GridLayout(rows, 3, 10, 10));

        for (JButton hButton : roomList) {
            hButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            hButton.setPreferredSize(new Dimension(100, 50));
            centerPanel.add(hButton);
        }

        JScrollPane scrollPane = new JScrollPane(centerPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(6);
        scrollPane.getVerticalScrollBar().setBlockIncrement(40);
        roomFrame.add(scrollPane, BorderLayout.CENTER);

        // White space borders
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(20, 0));
        roomFrame.add(westPanel, BorderLayout.WEST);

        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(20, 0));
        roomFrame.add(eastPanel, BorderLayout.EAST);

        roomFrame.setLocationRelativeTo(null);
        roomFrame.setVisible(true);
        roomFrame.setResizable(false);
    }

    /**
     * This method displays the window used for changing the price of the hotel rooms
     * @param hotel chosen hotel to display the current room price of
     */
    public void updPriceDisplay(Hotel hotel){
        updPriceFrame = new JFrame("Change Hotel Price");
        updPriceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    
        updPriceFrame.setLayout(new BorderLayout());
        updPriceFrame.setSize(new Dimension(330, 150));

        promptLbl = new JLabel("Enter a New Price for the Hotel", SwingConstants.CENTER);
        promptLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        updPriceFrame.add(promptLbl, BorderLayout.NORTH);

        feedbackLbl = new JLabel("Current General Room Price: " + hotel.getPrice(), SwingConstants.CENTER);
        feedbackLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        feedbackLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        updPriceFrame.add(feedbackLbl, BorderLayout.SOUTH);
        
        //Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        inputTf = new JTextField(12);

        gbc.gridx++;
        centerPanel.add(inputTf, gbc);

        gbc.gridx++;
        centerPanel.add(priceBtn, gbc);

        updPriceFrame.add(centerPanel, BorderLayout.CENTER);

        updPriceFrame.setLocationRelativeTo(null);
        updPriceFrame.setVisible(true);
        updPriceFrame.setResizable(false);
    }

    /**
     * This method displays the window used for modifying the price of a specific date
     */
    public void modifyDpDisplay(){
        modFrame = new JFrame("Date Price Modifier");
        modFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    
        modFrame.setLayout(new BorderLayout());
        modFrame.setSize(new Dimension(500, 150));

        promptLbl = new JLabel("Select a day to place the modifier", SwingConstants.CENTER);
        promptLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        modFrame.add(promptLbl, BorderLayout.NORTH);

        daysCB = new JComboBox<Integer>();

        for(int i = 1; i < 32; i++){
            daysCB.addItem(i);
        }
        modFrame.add(daysCB, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
<<<<<<< HEAD

=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        inputTf = new JTextField(3);

        gbc.gridx++;
        centerPanel.add(new JLabel("Place desired percentage: "), gbc);

        gbc.gridx++;
        centerPanel.add(inputTf, gbc);

        gbc.gridx++;
        centerPanel.add(new JLabel("%"), gbc);

        gbc.gridx++;
        centerPanel.add(applyMdBtn, gbc);

        modFrame.add(centerPanel, BorderLayout.SOUTH);

        modFrame.setLocationRelativeTo(null);
        modFrame.setVisible(true);
        modFrame.setResizable(false);
    }

    /**
     * This method displays the window used for remove the reservations of a room
     * @param room chosen room to display the removed reservation
     */
    public void removeRsvDisplay(Room room){
        removeRsvFrame = new JFrame("Remove Room Reservation");
        removeRsvFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeRsvFrame.setLayout(new BorderLayout());
        removeRsvFrame.setSize(new Dimension(480, 140));

        promptLbl = new JLabel("Would You Like to Remove ONE Reservation or ALL Reservations For " + room.getName() + "?", SwingConstants.CENTER);
        promptLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        promptLbl.setBorder(new EmptyBorder(10, 10, 5, 10));
        removeRsvFrame.add(promptLbl, BorderLayout.NORTH);

        //Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridx++;
        centerPanel.add(removeOneBtn, gbc);

        gbc.gridx++;
        centerPanel.add(removeAllBtn, gbc);

        removeRsvFrame.add(centerPanel, BorderLayout.CENTER);

        removeRsvFrame.setLocationRelativeTo(null);
        removeRsvFrame.setVisible(true);
        removeRsvFrame.setResizable(false);
    }

    /**
     * This method displays the window used for the finding the guest name of a reservation
     */
    public void findGuestDisplay(){
        guestFrame = new JFrame("Change Hotel Name");
        guestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        guestFrame.setLayout(new BorderLayout());
        guestFrame.setSize(new Dimension(380, 150));

        promptLbl = new JLabel("Enter the Guest's Name to Find the Reservation", SwingConstants.CENTER);
        promptLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        guestFrame.add(promptLbl, BorderLayout.NORTH);

        feedbackLbl = new JLabel("", SwingConstants.CENTER);
        feedbackLbl.setFont(new Font("Dialog", Font.BOLD, 11));
        feedbackLbl.setBorder(new EmptyBorder(10, 10, 10, 10));
        guestFrame.add(feedbackLbl, BorderLayout.SOUTH);

        //Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        inputTf = new JTextField(19);

        gbc.gridx++;
        centerPanel.add(inputTf, gbc);

        gbc.gridx++;
        centerPanel.add(guestBtn, gbc);

        guestFrame.add(centerPanel, BorderLayout.CENTER);

        guestFrame.setLocationRelativeTo(null);
        guestFrame.setVisible(true);
        guestFrame.setResizable(false);
    }

    //ActionListeners for change name
    /**
     * This method sets up an actionListener for the change name option when managing a hotel
     * @param actionListener actionListener for the change name option button
     */
    public void setChangeNameBtn(ActionListener actionListener){
        this.changeNameBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an actionListener for the button used to change the hotel name
     * @param actionListener actionListener for the name button
     */
    public void setNameBtn(ActionListener actionListener){
        this.nameBtn.addActionListener(actionListener);
    }
  
    //ActionListeners for add room
    /**
     * This method sets up an actionListener for the add Room option when managing a hotel
     * @param actionListener actionListener for the add room option button
     */
    public void setAddRoomBtn(ActionListener actionListener){
        this.addRoomBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an actionListener for the button used to add a standard room in the hotel
     * @param actionListener actionListener for the add standard room button
     */
    public void setAddStdBtn(ActionListener actionListener){
        this.addStdBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an actionListener for the button used to add a deluxe room in the hotel
     * @param actionListener actionListener for the add deluxe room button
     */
    public void setAddDlxBtn(ActionListener actionListener){
        this.addDlxBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an actionListener for the button used to add an executive room in the hotel
     * @param actionListener actionListener for the add executive room button
     */
    public void setAddExcBtn(ActionListener actionListener){
        this.addExcBtn.addActionListener(actionListener);
    }
  
    //ActionListener for remove room
    /**
     * This method sets up an actionListener for the button used to remove a room in the hotel
     * @param actionListener actionListener for the remove room button
     */
    public void setRemoveRoomBtn(ActionListener actionListener){
        this.removeRoomBtn.addActionListener(actionListener);
    }

    //ActionListeners for update price
    /**
     * This method sets up an actionListener for the update price option when managing a hotel
     * @param actionListener actionListener for the update price option button
     */
    public void setUpdPriceBtn(ActionListener actionListener){
        this.updPriceBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an actionListener for the button used to change the price of the hotel rooms
     * @param actionListener actionListener for the update price button
     */
    public void setPriceBtn(ActionListener actionListener){
        this.priceBtn.addActionListener(actionListener);
    }

    //ActionListeners for date price modifier
<<<<<<< HEAD
    /**
     * This method sets up an actionListener for the date price modifier option when managing a hotel
     * @param actionListener actionListener for the date price modifier option button
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    public void setDpModifyBtn(ActionListener actionListener){
        this.dpModifyBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an actionListener for the button used to apply the modified price to a date
     * @param actionListener actionListener for the apply modifier button
     */
    public void setApplyMdBtn(ActionListener actionListener){
        this.applyMdBtn.addActionListener(actionListener);
    }
  
    //ActionListeners for remove reservation
    /**
     * This method sets up an actionListener for the remove reservation option when managing a hotel
     * @param actionListener actionListener for the remove reservation option button
     */
    public void setRemoveRsvBtn(ActionListener actionListener){
        this.removeRsvBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an actionListener for the button used to remove one reservation in a room
     * @param actionListener actionListener for the remove one reservation button
     */
    public void setRemoveOneBtn(ActionListener actionListener){
        this.removeOneBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an actionListener for the button used to remove all the reservations in a room
     * @param actionListener actionListener for the remove all reservations button
     */
    public void setRemoveAllBtn(ActionListener actionListener){
        this.removeAllBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an actionListener for the button used to find the guest name and remove their reservation
     * @param actionListener actionListener for the find guest button
     */
    public void setGuestBtn(ActionListener actionListener){
        this.guestBtn.addActionListener(actionListener);
    }
  
    //ActionListener for remove hotel
    /**
     * This method sets up an actionListener for the button used to remove the hotel from the management system
     * @param actionListener actionListener for the remove hotel button
     */
    public void setRemoveHotelBtn(ActionListener actionListener){
        this.removeHotelBtn.addActionListener(actionListener);
    }
  
    //ActionListener for returning to main menu
    /**
     * This method sets up an actionListener for the option to go back to the reservation system's main menu
     * @param actionListener actionListener for the back button
     */
    public void setBackBtn(ActionListener actionListener){
        this.backBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up the actionListeners for the buttons corresponding to every room in the hotel
     * @param name name of the room in the hotel
     * @param actionListener actionListener for the room button
     */
    public void setButtonList(String name, ActionListener actionListener){
        JButton button = new JButton(name);
        button.addActionListener(actionListener);
        roomList.add(button);
    }

    /**
     * This method clears the buttons corresponding to every room and is used for updating the room buttons
     */
    public void clearHotelButtons() {
        // Remove all buttons from the frame or panel
        for (JButton button : roomList) {
            roomFrame.remove(button);
        }
        roomList.clear();
        
        if(roomFrame != null){
            roomFrame.revalidate();
            roomFrame.repaint();
        }
    }

    /**
     * This method clears the actionListeners for the remove reservation option
     */
    public void clearRemoveReservationButtons() {
        for (ActionListener al : removeOneBtn.getActionListeners()) {
            removeOneBtn.removeActionListener(al);
        }
        for (ActionListener al : removeAllBtn.getActionListeners()) {
            removeAllBtn.removeActionListener(al);
        }
    }

    //Methods for text
    /**
     * This method updates the text of the feedback label
     * @param text updated text for actions done when managing a hotel
     */
    public void setFeedbackLblText(String text) {
        this.feedbackLbl.setText(text);
    }

    /**
     * This method updates the display name of the hotel in the main interface of the manage hotel
     * @param text updated name of the hotel
     */
    public void setNameLblText(String text){
        this.nameLbl.setText("~ " + text + " ~");
    }

    /**
     * This method updates the log label to indicate actions done when managing a hotel
     * @param text updated text used as an indicator
     */
    public void setLogLblText(String text){
        this.logLbl.setText(text);
    }

    /**
     * This method clears the text fields used when managing a hotel
     */
    public void clearTextFields() {
        this.inputTf.setText("");
    }

    /**
     * This method gets the user input from a text field
     * @return String input from the text field
     */
    public String getInputTf(){
        return this.inputTf.getText();
    }

    /**
     * This method gets the user input from the combo box
     * @return day selected for date price modifier option
     */
    public Integer getSelectedDay() {
        return (Integer) this.daysCB.getSelectedItem();
    }

    //Methods for closing frames
<<<<<<< HEAD
    /**
     * This methods closes the main interface window of the manage hotel option
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    public void closeManageHFrame(){
        this.dispose();
    }

    /**
     * This methods closes the window used for changing the hotel name
     */
    public void closeNameFrame(){
        this.nameFrame.dispose();
    }

    /**
     * This methods closes the window used for updating the hotel's room prices
     */
    public void closeUpdPriceFrame(){
        this.updPriceFrame.dispose();
    }

    /**
     * This methods closes the window used for modifying the price of a date
     */
    public void closeModFrame(){
        this.modFrame.dispose();
    }

    /**
     * This methods closes the window used for displaying the rooms of a hotel
     */
    public void closeRoomFrame(){
        this.roomFrame.dispose();
    }

    /**
     * This methods closes the window used for removing the reservation of a room
     */
    public void closeRemoveRsvFrame(){
        this.removeRsvFrame.dispose();
    }

    /**
     * This methods closes the window used for finding the reservation of a guest
     */
    public void closeGuestFrame(){
        this.guestFrame.dispose();
    }

    /**
     * This methods closes the window used for choosing the type of room to add
     */
    public void closeRoomTypeFrame(){
        this.roomTypeFrame.dispose();
    }

}