package script;

import java.io.IOException;

/**
 * Image view, text implementation.
 */
public class ImageTextView implements ImageView {
  Appendable output;

  /**
   * View constructor.
   * @param out appendable object as an output for view.
   */
  ImageTextView(Appendable out) {
    output = out;
  }

  /**
   * This function takes a set of instructions and
   * displays them accordingly.
   * @param inst instructions of the program.
   */
  @Override
  public void displayInstructions(String inst) {
    String space = "====================================\n";
    try {
      output.append(space);
      output.append(inst);
      output.append(space);
    } catch (IOException e) {
      throw new IllegalStateException("Could not display instructions");
    }
  }

  /**
   * This method prompt the user to enter a file name.
   * @throws IllegalStateException If the appendable failed.
   */
  @Override
  public void askForFile() throws IllegalStateException {
    String msg;
    msg = "Please enter the image path you wish to edit:\n";
    try {
      output.append(msg);
    } catch (IOException e) {
      throw new IllegalStateException("Could not ask for a file");
    }
  }

  /**
   * This method displays a message given by the model.
   * @param msg message to be displayed.
   */
  @Override
  public void displayMsg(String msg) {
    try {
      output.append(msg + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not display message");
    }
  }

}
