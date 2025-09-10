import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;

public class HotelReservationSystemView extends JFrame {
    private JFrame createHFrame, noHFrame, selectHFrame; 
    private ArrayList<JButton> hotelList = new ArrayList<JButton>();
    private JLabel promptLbl, promptLbl2, feedbackLbl;
    private JTextField hotelNameTf, numRoomTf;
    private JButton createHotelBtn, viewHotelBtn, manageHotelBtn, bookRoomBtn, exitBtn, createBtn, viewBtn, okayBtn;
    private JTextArea hotelListTextArea;
    private JPanel westPanel, centerPanel;

    public HotelReservationSystemView(){
        super("Hotel Reservation System");
        setLayout(new BorderLayout());
        setSize(700, 400);
        
        westPanel = new JPanel();
        westPanel.setLayout(new GridBagLayout());

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        initialize();

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.hotelListTextArea = new JTextArea();
        this.hotelListTextArea.setPreferredSize(new Dimension(220, 170));
        this.hotelListTextArea.setEditable(false);
        this.hotelListTextArea.setFocusable(false);

        //for createHFrame
        this.createBtn = new JButton("Create");
        this.createBtn.setPreferredSize(new Dimension(100, 30));

        this.viewBtn = new JButton("View");
        this.viewBtn.setPreferredSize(new Dimension(100, 30));

        //for noHFrame
        this.okayBtn = new JButton("Okay");
        this.okayBtn.setPreferredSize(new Dimension(100, 20));
<<<<<<< HEAD
=======
        
        //removes the blue thing around the text of the button but needs to applied to all buttons :(
        this.okayBtn.setFocusPainted(false);
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
    }

    public void initialize(){
        Dimension buttonDim = new Dimension(170, 30);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 10, 5, 10);

        promptLbl = new JLabel("Select an Option", SwingConstants.CENTER);
        westPanel.add(promptLbl, gbc);

        gbc.gridy++;
        createHotelBtn = new JButton("Create a Hotel");
        createHotelBtn.setPreferredSize(buttonDim);
        westPanel.add(createHotelBtn, gbc);

        gbc.gridy++;
        viewHotelBtn = new JButton("View Hotels");
        viewHotelBtn.setPreferredSize(buttonDim);
        westPanel.add(viewHotelBtn, gbc);

        gbc.gridy++;
        manageHotelBtn = new JButton("Manage a Hotel");
        manageHotelBtn.setPreferredSize(buttonDim);
        westPanel.add(manageHotelBtn, gbc);

        gbc.gridy++;
        bookRoomBtn = new JButton("Book a Room");
        bookRoomBtn.setPreferredSize(buttonDim);
        westPanel.add(bookRoomBtn, gbc);

        gbc.gridy++;
        exitBtn = new JButton("Exit the System");
        exitBtn.setPreferredSize(buttonDim);
        westPanel.add(exitBtn, gbc);

        gbc.gridy++;
        gbc.weighty = 1.0;
        westPanel.add(Box.createVerticalGlue(), gbc);

        westPanel.setBackground(Color.decode("#98c1d9"));

        this.add(westPanel, BorderLayout.WEST);

        promptLbl2 = new JLabel("Welcome to the Hotel Reservation System!");
        promptLbl2.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(promptLbl2, BorderLayout.CENTER);

        this.add(centerPanel, BorderLayout.CENTER);

        westPanel.revalidate();
        westPanel.repaint();
        centerPanel.revalidate();
        centerPanel.repaint();
    }
    
    public void setCreateHotelBtnListener(ActionListener actionListener) {
		  this.createHotelBtn.addActionListener(actionListener);
	  }

    public void setViewHotelBtnListener(ActionListener actionListener) {
		  this.viewHotelBtn.addActionListener(actionListener);
	  }

    public void setManageHotelBtnListener(ActionListener actionListener) {
		  this.manageHotelBtn.addActionListener(actionListener);
	  }

    public void setBookRoomListener(ActionListener actionListener) {
		  this.bookRoomBtn.addActionListener(actionListener);
	  }

    public void setExitBtnListener(ActionListener actionListener) {
		  this.exitBtn.addActionListener(actionListener);
	  }

    //for create hotel window
    public void setCreateBtnListener(ActionListener actionListener){
      this.createBtn.addActionListener(actionListener);
    }

    //for view hotel window
    public void setViewBtnListener(ActionListener actionListener){
      this.viewBtn.addActionListener(actionListener);
    }

    //for no hotel window
    public void setOkayBtnListener(ActionListener actionListener){
      this.okayBtn.addActionListener(actionListener);
    }

    //for text stuff
    public void setFeedbackLblText(String text) {
      this.feedbackLbl.setText(text);
    }

    public void setButtonList(String name, ActionListener actionListener){
      JButton button = new JButton(name);
      button.addActionListener(actionListener);
      button.setPreferredSize(new Dimension(220, 30));
      hotelList.add(button);
    }

    public String getHotelNameTfText() {
      return this.hotelNameTf.getText();
    }
  
    public String getNumRoomTfText() {
      return this.numRoomTf.getText();
    }
  
    public void clearTextFields() {
      this.hotelNameTf.setText("");
      this.numRoomTf.setText("");
    }

    public void clearHotelButtons() {
      // Remove all buttons from the frame or panel
      for (JButton button : hotelList) {
          selectHFrame.remove(button);
      }
      hotelList.clear();
      
      if(selectHFrame != null){
        selectHFrame.revalidate();
        selectHFrame.repaint();
      }
    }

    public void createHotelDisplay(){
      createHFrame = new JFrame("Create a Hotel");

      createHFrame = new JFrame("Create a Hotel");
      createHFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      createHFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
      createHFrame.setSize(600, 125);

      this.promptLbl = new JLabel("Enter Hotel Name: ");
      this.promptLbl2 = new JLabel("Enter # of Rooms: ");
      this.feedbackLbl = new JLabel("", SwingConstants.CENTER);
      this.feedbackLbl.setPreferredSize(new Dimension(500, 30));
      
      this.hotelNameTf = new JTextField();
		  this.hotelNameTf.setColumns(10);
		  this.numRoomTf = new JTextField();
		  this.numRoomTf.setColumns(2);

      JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      panel.add(promptLbl);
      panel.add(hotelNameTf);
      panel.setPreferredSize(new Dimension(220, 30));
      createHFrame.add(panel);

      panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      panel.add(promptLbl2);
      panel.add(numRoomTf);
      panel.setPreferredSize(new Dimension(220, 30));
      createHFrame.add(panel);

      createHFrame.add(createBtn);
      createHFrame.add(feedbackLbl);

      createHFrame.setLocationRelativeTo(null);
      createHFrame.setVisible(true);
    }

    public void noHotelDisplay(){
      noHFrame = new JFrame("Hotel Reservation System");

      noHFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      noHFrame.setLayout(new BorderLayout());
      noHFrame.setSize(450, 100);

      promptLbl = new JLabel("Cannot Proceed. No Hotels Found. Click Create Hotel to Create One", SwingConstants.CENTER);

      JPanel centerPanel = new JPanel();
      centerPanel.setLayout(new BorderLayout());
      centerPanel.add(promptLbl);

      noHFrame.add(centerPanel, BorderLayout.CENTER);

      JPanel southPanel = new JPanel();
      southPanel.setLayout(new FlowLayout());
      okayBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
      southPanel.add(okayBtn);

      noHFrame.add(southPanel, BorderLayout.SOUTH);
      
      noHFrame.setLocationRelativeTo(null);
      noHFrame.setVisible(true);
      noHFrame.setResizable(false);
    }

    public void selectHotel(int index){
      selectHFrame = new JFrame();
      
      if(index == 0){
        promptLbl = new JLabel("Select a Hotel to view!");
        promptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectHFrame.setTitle("View Hotel");
      }
      if(index == 1){
        promptLbl = new JLabel("Select a Hotel to manage!");
        promptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectHFrame.setTitle("Manage Hotel");
      }
      if(index == 2){
        promptLbl = new JLabel("Select a Hotel to Book In!");
        promptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectHFrame.setTitle("Book A Room");
      }

      selectHFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		  selectHFrame.setLayout(new BoxLayout(selectHFrame.getContentPane(), BoxLayout.Y_AXIS));
      selectHFrame.setSize(260, 300);   

      selectHFrame.add(Box.createVerticalStrut(15));
      selectHFrame.add(promptLbl);
      selectHFrame.add(Box.createVerticalStrut(10));

      Iterator<JButton> button = hotelList.iterator();
      while (button.hasNext()) {
        JButton hButton = button.next();
        hButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectHFrame.add(hButton);
      }
      
      selectHFrame.setLocationRelativeTo(null);
      selectHFrame.setVisible(true);
      selectHFrame.setResizable(false);
    }

    public void closeCreateHFrame(){
      createHFrame.dispose();
    }

    public void closeSelectHFrame(){
      selectHFrame.dispose();
    }

    public void closeNoHFrame(){
      noHFrame.dispose();
    }
<<<<<<< HEAD

=======
>>>>>>> 4ce1ffba72a3990dfb1c288ce153ad971f414324
}
