package imageview;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * This is an interface for a GUI implementation that is compatible with
 * ImageGuiController, it must make use of all of its features.
 */
public interface ImageViewGuiInt {

  /**
   * Resets focus.
   */
  void resetFocus();

  /**
   * Displays a give message to the user.
   *
   * @param msg a message in string form.
   */
  void displayMessage(String msg);

  /**
   * Updates current image.
   */
  void updateImage(BufferedImage image);


  /**
   * Opens the pop-up menu for the mosaic filter.
   */
  void mosaicMenu();


  /**
   * Sends back the value from mosaic popup
   * window.
   *
   * @return how many seeds are needed for the filter.
   */
  int mosaicGetValue();

  /**
   * Tells the controller which image should load.
   *
   * @return path of the selected file.
   */
  String getImagePathLoad();


  /**
   * Gets path of a script file.
   * @return path of script file
   */
  String getScriptPath();

  /**
   * Tells the controller the current path.
   *
   * @return path to save image.
   */
  String getImagePathSave();

  /**
   * This method sets-up all action listeners necessary.
   *
   * @param actListener ActionListener Object.
   * @param keyListener KeyListener Object.
   */
  void setController(ActionListener actListener, KeyListener keyListener);

  /**
   * Starts the process for image cropping.
   */
  void cropImagePopUp();

  /**
   * Tells the controller to crop the image.
   */
  void cropImage();

  /**
   * Clears current window from any graphics.
   */
  void clearCanvas();
}
