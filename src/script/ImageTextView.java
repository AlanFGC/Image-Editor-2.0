package script;

import java.io.IOException;

public class ImageTextView implements ImageView {
  Appendable output;

  ImageTextView(Appendable out) {
    output = out;
  }

  @Override
  public void displayInstructions(String inst) {
    String Space = "====================================\n";
    try {
      output.append(Space);
      output.append(inst);
      output.append(Space);
    } catch (IOException e) {
      throw new IllegalStateException("Could not display instructions");
    }
  }

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

  @Override
  public void displayMsg(String msg) {
    try {
      output.append(msg + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not display message");
    }
  }

}
