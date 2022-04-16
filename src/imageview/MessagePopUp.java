package imageview;

import java.awt.*;

import javax.swing.*;

/**
 * This is a simple interface that launches a pop-up message window.
 */
public class MessagePopUp extends JFrame {
  //Fields
  JButton okButton;
  JLabel  message;
  ImageViewGuiInt gui;

  /**
   * Constructor for a pop-up window
   */
  MessagePopUp(ImageGui gui, String caption){
    super(caption);
    this.gui = gui;

    //configure this window
    this.setLayout(new FlowLayout());
    this.setSize(800, 400);
    this.setLocation(400, 400);
    this.setDefaultCloseOperation(HIDE_ON_CLOSE);

    // Button
    okButton = new JButton("Accept");
    okButton.setPreferredSize(new Dimension(180, 50));
    okButton.setFont(gui.buttonFont);
    okButton.addActionListener(evt -> setVisible(false));
    // set ok button as the default when pressing enter key
    this.getRootPane().setDefaultButton(okButton);


    // Message
    message = new JLabel("Empty message");
    message.setPreferredSize(new Dimension(600, 90));
    message.setFont(gui.defaultFont);
    message.setHorizontalAlignment(SwingConstants.CENTER);
    message.setPreferredSize(new Dimension(800, 200));



    // add to window
    add(message);
    add(okButton);
    setVisible(false);
  }

  public void displayMessage(String msg){
    okButton.requestFocus();
    this.message.setText(msg);
    setVisible(true);
  }
}
