package imageview;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * This is an interface for any implementation that is compatible with
 * ImageGuiController, it must make use of all of its features.
 */
public interface ImageViewGuiInt {

  void resetFocus();

  /**
   * Sets-up the controller and all action listeners necessary.
   *
   * @param controller  controller of the mvc application.
   * @param actListener ActionListener Object.
   * @param keyListener KeyListener Object.
   */
  void setController(ImageGuiControlInt controller, ActionListener actListener,
                     KeyListener keyListener);

  /**
   * Displays a give message to the user.
   *
   * @param msg a message in string form.
   */
  void displayMessage(String msg);

  /**
   * This method tells the controller to update the current image.
   */
  public void updateImage(BufferedImage image);


  /**
   * Opens the pop-up menu for the mosaic filter.
   */
  public void mosaicMenu();


  /**
   * Sends back the value from mosaic popup
   * window.
   *
   * @return how many seeds are needed for the filter.
   */
  public int mosaicGetValue();

  /**
   * Tells the controller which image should load.
   *
   * @return path of the selected file.
   */
  public String getImagePathLoad();


  String getScriptPath();

  /**
   * Tells the controller the current path.
   *
   * @return path to save image.
   */
  public String getImagePathSave();
}
