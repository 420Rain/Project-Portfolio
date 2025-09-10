import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;

/**
* This class is the View for showing the graphical user interface when viewing a hotel
* @author Rainier A. Dulatre
* @author Patrick Hans A. Perez
* @version 1.0
*/
public class ViewHotelView extends JFrame {
    /**
     * This variable is the chosen hotel to view
     */
    private Hotel hotelViewed;

    /**
     * These variables are the labels used for prompting the user
     */
    private JLabel promptLbl, promptLbl2, promptLbl3, promptLbl4;

    /**
     * These variables are the labels used for prompting or displaying information
     */
    private JLabel rLabel, rPlaceholder, pLabel, pPlaceholder, tLabel, tPlaceholder, dateLabel;

    /**
     * These variables are the labels used for prompting or displaying information
     */
    private JLabel nPlaceholder, resPlaceholder, iPlaceholder, oPlaceholder, tpPlaceholder;

    /**
     * These variables are the buttons used in the main interface when viewing a hotel
     */
    private JButton roomAvailBtn, roomDetailBtn, reservationsBtn, backBtn;

    /**
     * These variables are the buttons used in the different options for viewing a hotel
     */
    private JButton returnBtn, returnBtn2, returnBtn3, selectDayBtn, selectRoomBtn, returnSelectBtn, returnSelectBtn2, selectGuestBtn;

    /**
     * These variables are the text fields for user input
     */
    private JTextField availTf, roomTf, guestTf;
<<<<<<< HEAD

    /**
     * These variable are the text area for displaying rooms
     */
    private JTextArea availabilityTextArea, displayRoomsTextArea, displayPBTextArea;

    /**
     * These variables are panels used in viewing a hotel
     */
=======
    private JTextArea availabilityTextArea, displayRoomsTextArea, displayPBTextArea;
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    private JPanel mainPanel, daysPanel;

    /**
     * This variable is a cardLayout for switching panels
     */
    private CardLayout cardLayout;

    /**
     * This variable stores label for the days
     */
    private ArrayList<JLabel> labelList;    

    /**
     * This is the constructor for a ViewHotelView instance
     * @param hotel chosen hotel to view
     */
    public ViewHotelView(Hotel hotel) {
        super("Viewing " + hotel.getName());
        this.hotelViewed = hotel;
        this.labelList = new ArrayList<JLabel>();
        setLayout(new BorderLayout());
        setSize(700, 400);

        initialize();

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method displays the main menu interface for viewing a hotel
     */
    public void initialize() {
        // Left Panel
        Dimension buttonDim = new Dimension(150, 30);

        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 10, 5, 10);

        promptLbl = new JLabel("Please select an option!");
        westPanel.add(promptLbl, gbc);

        gbc.gridy++;
        roomAvailBtn = new JButton("Room Availability");
        roomAvailBtn.setPreferredSize(buttonDim);
        westPanel.add(roomAvailBtn, gbc);

        gbc.gridy++;
        roomDetailBtn = new JButton("Room Information");
        roomDetailBtn.setPreferredSize(buttonDim);
        westPanel.add(roomDetailBtn, gbc);

        gbc.gridy++;
        reservationsBtn = new JButton("Reservations");
        reservationsBtn.setPreferredSize(buttonDim);
        westPanel.add(reservationsBtn, gbc);

        gbc.gridy++;
        backBtn = new JButton("Back to Menu");
        backBtn.setPreferredSize(buttonDim);
        westPanel.add(backBtn, gbc);

        gbc.gridy++;
        gbc.weighty = 1.0;
        westPanel.add(Box.createVerticalGlue(), gbc);

        westPanel.setBackground(Color.decode("#98c1d9"));
        this.add(westPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1));

        promptLbl2 = new JLabel("Hotel: " + hotelViewed.getName(), SwingConstants.CENTER);
        promptLbl3 = new JLabel("Number of Rooms: " + hotelViewed.getNumRooms(), SwingConstants.CENTER);
        promptLbl4 = new JLabel("Earnings this month: Php" + hotelViewed.getEarnings(), SwingConstants.CENTER);

        centerPanel.add(promptLbl2);
        centerPanel.add(promptLbl3);
        centerPanel.add(promptLbl4);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        mainPanel.add(centerPanel, "mainView");
        mainPanel.add(createViewAvailability(), "availView");
        mainPanel.add(createSelectRoomInfo(), "selectRoomView");
        mainPanel.add(createDisplayRoomInfo(), "displayRoomView");
        mainPanel.add(createReservationSelect(), "selectReservationView");
        mainPanel.add(createReservationDisplay(), "displayReservationView");

        this.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * This method displays the window to show the availability of rooms on specific days
     * @return a panel for displaying room availability information
     */
    public JPanel createViewAvailability() {

        JPanel availPanel = new JPanel();
        promptLbl4 = new JLabel("Select a day to view (1-31): ");

        availTf = new JTextField();
        availTf.setColumns(2);

        selectDayBtn = new JButton("Select");
        selectDayBtn.setPreferredSize(new Dimension(100, 30));
        returnBtn = new JButton("Return to Main View");
        returnBtn.setPreferredSize(new Dimension(170, 30));

        JPanel inputPanel = new JPanel();
        inputPanel.add(promptLbl4);
        inputPanel.add(availTf);
        inputPanel.add(selectDayBtn);

        availabilityTextArea = new JTextArea(10, 40);
        availabilityTextArea.setEditable(false);
        availabilityTextArea.setFocusable(false);

        availPanel.add(inputPanel, BorderLayout.CENTER);
        availPanel.add(returnBtn, BorderLayout.CENTER);
        availPanel.add(availabilityTextArea, BorderLayout.SOUTH);

        return availPanel;
    }

    /**
     * This method displays the room infomation of a specific room
     * @return a panel for displaying room information
     */
    public JPanel createSelectRoomInfo(){
        Dimension buttonDim = new Dimension(150, 30);

        JPanel roomPanel = new JPanel();
        displayRoomsTextArea = new JTextArea(10, 40);
        displayRoomsTextArea.setEditable(false);
        displayRoomsTextArea.setFocusable(false);

        promptLbl4 = new JLabel("Select a Room to view from the available above: ");
        roomTf = new JTextField();
        roomTf.setColumns(7);
        selectRoomBtn = new JButton("Select");
        selectRoomBtn.setPreferredSize(buttonDim);  
        returnBtn2 = new JButton("Return to Main View");
        returnBtn2.setPreferredSize(buttonDim);

        roomPanel.add(displayRoomsTextArea, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(promptLbl4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(roomTf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(selectRoomBtn, gbc);

        gbc.gridy = 2;
        inputPanel.add(returnBtn2, gbc);

        roomPanel.add(inputPanel, BorderLayout.SOUTH);

        return roomPanel;
    }

<<<<<<< HEAD
    /**
     * This method creates the panel for the display room information
     * @return a panel for displaying room information
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    public JPanel createDisplayRoomInfo() {
        Dimension buttonDim = new Dimension(150, 30);
        
        JPanel roomPanel2 = new JPanel(new BorderLayout());
        
        JPanel textPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        rLabel = new JLabel("Room Name: ");
        rPlaceholder = new JLabel();
        textPanel.add(rLabel);
        textPanel.add(rPlaceholder);
        tLabel = new JLabel("Room Type: ");
        tPlaceholder = new JLabel();
        textPanel.add(tLabel);
        textPanel.add(tPlaceholder);
        pLabel = new JLabel("Room Price: ");
        pPlaceholder = new JLabel();
        textPanel.add(pLabel);
        textPanel.add(pPlaceholder);
        
        roomPanel2.add(textPanel, BorderLayout.NORTH);
        
        JPanel textPanel2 = new JPanel();
        textPanel2.setLayout(new BorderLayout());
        
        JPanel legendPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        dateLabel = new JLabel("Dates Available: ");
        legendPanel.add(dateLabel);
        
        JLabel availableBox = new JLabel();
        availableBox.setPreferredSize(new Dimension(10, 10));
        availableBox.setBackground(new Color(0, 128, 0));
        availableBox.setOpaque(true);
        legendPanel.add(availableBox);
        
        JLabel textA = new JLabel(" - Available");
        legendPanel.add(textA);
        
        JLabel unavailableBox = new JLabel();
        unavailableBox.setPreferredSize(new Dimension(10, 10));
        unavailableBox.setBackground(new Color(128, 0, 0));
        unavailableBox.setOpaque(true);
        legendPanel.add(unavailableBox);
        
        JLabel textU = new JLabel(" - Unavailable");
        legendPanel.add(textU);
        
        textPanel2.add(legendPanel, BorderLayout.NORTH);
        
        daysPanel = new JPanel(new GridLayout(5, 7, 5, 5));
        
        labelList.clear();
        
        for (JLabel label : labelList) {
            daysPanel.add(label);
        }
        
        textPanel2.add(daysPanel, BorderLayout.CENTER);
        
        roomPanel2.add(textPanel2, BorderLayout.CENTER);
        
        returnSelectBtn = new JButton("Return to Selection");
        returnSelectBtn.setPreferredSize(buttonDim);
        
        roomPanel2.add(returnSelectBtn, BorderLayout.SOUTH);
        
        return roomPanel2;
    }

    /**
     * This method creates the panel for selecting a reservation to view
     * @return a panel for displaying selecting reservation
     */
    public JPanel createReservationSelect(){
        Dimension buttonDim = new Dimension(150, 30);

        JPanel selectGuest = new JPanel();
        selectGuest.setLayout(new BorderLayout());

        JLabel text = new JLabel("Type the Guest's name for their current Reservation");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        selectGuest.add(text, BorderLayout.CENTER);

        JPanel input = new JPanel();
        guestTf = new JTextField();
        guestTf.setColumns(10);
        input.add(guestTf, BorderLayout.NORTH);

        selectGuestBtn = new JButton("Select");
        selectGuestBtn.setPreferredSize(buttonDim);
        input.add(selectGuestBtn, BorderLayout.CENTER);

        returnBtn3 = new JButton("Return to Main View");
        returnBtn3.setPreferredSize(buttonDim);
        input.add(returnBtn3, BorderLayout.CENTER);

        selectGuest.add(input, BorderLayout.SOUTH);

        return selectGuest;
    }

<<<<<<< HEAD
    /**
     * This method creates the panel for displaying a selected reservation's information
     * @return a panel for displaying reservation information
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    public JPanel createReservationDisplay() {
        JPanel displayPanel = new JPanel(new BorderLayout());
    
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Viewing Reservation");
        titlePanel.add(titleLabel);
        displayPanel.add(titlePanel, BorderLayout.NORTH);
        
        JPanel necessaryInfo = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
    
        JLabel nameLabel = new JLabel("Guest Name: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        necessaryInfo.add(nameLabel, gbc);
    
        nPlaceholder = new JLabel();
        gbc.gridx = 1;
        necessaryInfo.add(nPlaceholder, gbc);
    
        JLabel roomLabel = new JLabel("Room: ");
        gbc.gridx = 2;
        necessaryInfo.add(roomLabel, gbc);
    
        resPlaceholder = new JLabel();
        gbc.gridx = 3;
        necessaryInfo.add(resPlaceholder, gbc);
    
        JLabel checkInLabel = new JLabel("Check-In Day: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        necessaryInfo.add(checkInLabel, gbc);
    
        iPlaceholder = new JLabel();
        gbc.gridx = 1;
        necessaryInfo.add(iPlaceholder, gbc);
    
        JLabel checkOutLabel = new JLabel("Check-Out Day: ");
        gbc.gridx = 2;
        necessaryInfo.add(checkOutLabel, gbc);
    
        oPlaceholder = new JLabel();
        gbc.gridx = 3;
        necessaryInfo.add(oPlaceholder, gbc);
    
        JLabel totalPriceLabel = new JLabel("Total Price for Reservation: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        necessaryInfo.add(totalPriceLabel, gbc);
    
        tpPlaceholder = new JLabel();
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        necessaryInfo.add(tpPlaceholder, gbc);
    
        JLabel priceBreakdownLabel = new JLabel("Price Breakdown: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        necessaryInfo.add(priceBreakdownLabel, gbc);
    
        gbc.gridy = 4;
        displayPBTextArea = new JTextArea(5, 40);
        displayPBTextArea.setEditable(false);
        displayPBTextArea.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(displayPBTextArea);
        necessaryInfo.add(scrollPane, gbc);
        displayPanel.add(necessaryInfo, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        returnSelectBtn2 = new JButton("Return to Select Guest");
        returnSelectBtn2.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(returnSelectBtn2);
        displayPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        return displayPanel;
    }

    /**
     * This method sets up an action listener for checking the room availability option
     * @param actionListener actionListener for the room available button
     */
    public void setRoomAvailBtnListener(ActionListener actionListener) {
        roomAvailBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for the room information option
     * @param actionListener actionListener for the room detail button
     */
    public void setRoomDetailBtnListener(ActionListener actionListener) {
        roomDetailBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for checking a reservation option
     * @param actionListener actionListener for the reservations button
     */
    public void setReservationsBtnListener(ActionListener actionListener) {
        reservationsBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for returning to the main menu option
     * @param actionListener actionListener for the back button
     */
    public void setBackBtnListener(ActionListener actionListener) {
        backBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for selecting the day to view availability
     * @param actionListener actionListener for the select day button
     */
    public void setSelectDayBtnListener(ActionListener actionListener) {
        selectDayBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for selecting the room to view
     * @param actionListener actionListener for the select room button
     */
    public void setSelectRoomBtnListener(ActionListener actionListener){
        selectRoomBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for selecting a guest's reservation to view
     * @param actionListener actionListener for the select guest button
     */
    public void setSelectGuestBtnListener(ActionListener actionListener){
        selectGuestBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for returning from a view option
     * @param actionListener actionListener for the return button
     */
    public void setReturnBtnListener(ActionListener actionListener) {
        returnBtn.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for returning from a view option
     * @param actionListener actionListener for the return2 button
     */
    public void setReturnBtn2Listener(ActionListener actionListener) {
        returnBtn2.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for returning from a view option
     * @param actionListener actionListener for the return3 button
     */
    public void setReturnBtn3Listener(ActionListener actionListener) {
        returnBtn3.addActionListener(actionListener);
    }

    /**
     * This method sets up an action listener for returning from a view option
     * @param actionListener actionListener for the return select button
     */
    public void setReturnSelectBtn(ActionListener actionListener){
        returnSelectBtn.addActionListener(actionListener);
    }

<<<<<<< HEAD
    /**
     * This method sets up an action listener for returning from a view option
     * @param actionListener actionListener for the return select2 button
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    public void setReturnSelectBtn2(ActionListener actionListener){
        returnSelectBtn2.addActionListener(actionListener);
    }

    /**
     * This method sets label colors to green
     * @param label lable to color
     */
    public void setGreen(JLabel label){
        label.setBackground(new Color(255, 255, 255));
        label.setForeground(new Color(0, 128, 0));
        label.setOpaque(true);
    }

    /**
     * This method sets label colors to red
     * @param label label to color
     */
    public void setRed(JLabel label){
        label.setBackground(new Color(255, 255, 255));
        label.setForeground(new Color(128, 0, 0));
        label.setOpaque(true);
    }

    /**
     * This methods gets the label room placeholder
     * @return a label placeholder
     */
    public JLabel getRPlaceHolder(){
        return rPlaceholder;
    }

    /**
     * This method gets the label price placeholder
     * @return a label placeholder
     */
    public JLabel getPPlaceHolder(){
        return pPlaceholder;
    }

<<<<<<< HEAD
    /**
     * This method gets the type placeholder
     * @return a label placeholder
     */
=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    public JLabel getTPlaceholder(){
        return tPlaceholder;
    }

    /**
     * This method gets the arrayList of labels
     * @return a JLabel arrayList
     */
    public ArrayList<JLabel> getLabelList(){
        return labelList;
    }

    /**
     * This method gets the text area for displaying the available rooms
     * @return a text area
     */
    public JTextArea getAvailabilityTA() {
        return availabilityTextArea;
    }

    /**
     * This method gets a text from a text field
     * @return user input String from the text field
     */
    public String getAvailTfText() {
        return availTf.getText();
    }

    /**
     * This method gets the text area for displaying the rooms
     * @return a text area
     */
    public JTextArea getDisplayRoomsTA() {
        return displayRoomsTextArea;
    }

    /**
     * This method gets the text area for displaying price breakdown
     * @return a text area
     */
    public JTextArea getDisplayPbTA(){
        return displayPBTextArea;
    }

    /**
     * This method gets the text from a text field for selecting a room
     * @return user input String from text field
     */
    public String getRoomTfText() {
        return roomTf.getText();
    }

    /**
     * This method gets the text from a text field for inputting a guest's name
     * @return user input String from text field
     */
    public String getGuestTfText(){
        return guestTf.getText();
    }

    /**
     * This method gets the label guest name placeholder
     * @return a label placeholder for the guest name
     */
    public JLabel getNPlaceHolder(){
        return nPlaceholder;
    }

    /**
     * This method gets the reservation placeholder
     * @return a lable placeholder for the reservation
     */
    public JLabel getResPlaceHolder(){
        return resPlaceholder;
    }

    /**
     * This method gets the check in placeholder
     * @return a lable placeholder for the check in day
     */
    public JLabel getIPlaceHolder(){
        return iPlaceholder;
    }

    /**
     * This method gets the check out placeholder
     * @return a lable placeholder for the check out day
     */
    public JLabel getOPlaceHolder(){
        return oPlaceholder;
    }

    /**
     * This method gets the total price placeholder
     * @return a lable placeholder for the total price
     */
    public JLabel getTpPlaceHolder(){
        return tpPlaceholder;
    }

    /**
     * This method displays the main panel using a card layout
     * @param panelName panel added in the card layout
     */
    public void showView(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

<<<<<<< HEAD
    /**
     * This method return the panel for displaying the available days
     * @return a panel displaying the available days
     */
=======
    public JTextArea getDisplayPbTA(){
        return displayPBTextArea;
    }

>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    public JPanel getDaysPanel(){
        return this.daysPanel;
    }
}