package script;

public interface ImageView {
  /**
   * Displays Instructions for the program.
   * Has a different format compared to displaying
   * a message.
   */
  public void displayInstructions(String inst);

  /**
   * Displays a message
   * that specifically asks
   * the user to input a file.
   */
  public void askForFile();

  /**
   * Displays a given message.
   * @param s message to be displayed.
   */
  public void displayMsg(String s);

}
