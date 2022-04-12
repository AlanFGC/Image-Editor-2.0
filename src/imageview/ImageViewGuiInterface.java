package imageview;

import java.awt.image.BufferedImage;

/**
 * This is an interface for any implementation that is compatible with
 * ImageGuiController, it must make use of all of its features.
 */
public interface ImageViewGuiInterface {

  /**
   * This method assigns all controller to the current model.
   * @param features a controller that implements all the features listed.
   */
  void addFeatures(ImageFeatures features);

  /**
   * Displays a give message to the user.
   * @param msg a message in string form.
   */
  void displayMessage(String msg);

  /**
   * This method tells the controller to update the current image.
   */
  public void updateImage(BufferedImage image);
}
