import script.ImageView;

/**
 * Mock Image view implementation for testing.
 */
public class MockImageViewText implements ImageView {
  private StringBuilder log;

  /**
   * Constructor for this object.
   *
   * @param log a log to append inputs to.
   */
  MockImageViewText(StringBuilder log) {
    this.log = log;
  }

  /**
   * displays instructions.
   *
   * @param inst input string
   */
  @Override
  public void displayInstructions(String inst) {
    log.append(inst);
  }

  /**
   * Prompts the user for a file name.
   */
  @Override
  public void askForFile() {
    log.append("-asked for a filename");
  }

  /**
   * DisplaysMessage.
   *
   * @param s message to be displayed.
   */
  @Override
  public void displayMsg(String s) {
    log.append(s);
  }
}
