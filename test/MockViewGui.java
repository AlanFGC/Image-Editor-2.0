import imageview.ImageGuiControlInt;
import imageview.ImageViewGuiInt;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Mock Image view implementation for testing.
 */
public class MockViewGui implements ImageViewGuiInt {
  private StringBuilder log;

  /**
   * mock constructor.
   *
   * @param log log.
   */
  MockViewGui(StringBuilder log) {
    this.log = log;
  }

  /**
   * does nothing.
   */
  @Override
  public void resetFocus() {
    log.append("-resetFocus");
  }

  /**
   * Sets-up the controller and all action listeners necessary.
   *
   * @param actListener ActionListener Object.
   * @param keyListener KeyListener Object.
   */
  @Override
  public void setController(ActionListener actListener, KeyListener keyListener) {
    log.append("-setup");
  }

  /**
   * does nothing.
   *
   * @param msg a message in string form.
   */
  @Override
  public void displayMessage(String msg) {
    log.append(msg);
  }

  /**
   * Does nothing.
   *
   * @param image an image
   */
  @Override
  public void updateImage(BufferedImage image) {
    log.append("-updateImage");
  }

  /**
   * does nothing.
   */
  @Override
  public void mosaicMenu() {
    log.append("-mosaicMenu");
  }

  /**
   * does nothing.
   *
   * @return how many seeds are needed for the filter.
   */
  @Override
  public int mosaicGetValue() {
    log.append("-getValueM");
    return 9421;
  }

  /**
   * does nothing.
   *
   * @return path of the selected file.
   */
  @Override
  public String getImagePathLoad() {
    log.append("-loadImage");
    return "2318";
  }

  /**
   * does nothing.
   *
   * @return null
   */
  @Override
  public String getScriptPath() {
    log.append("-getScriptPath");
    return "3123";
  }

  /**
   * does nothing.
   *
   * @return path to save image.
   */
  @Override
  public String getImagePathSave() {
    log.append("-getImagePathSave");
    return "4212";
  }
}