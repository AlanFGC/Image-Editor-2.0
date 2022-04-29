package imageview;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * This interface serves as a list of features that a gui controller
 * should implement.
 */
public interface Features extends ActionListener, KeyListener {

  /**
   * Starts the program.
   *
   * @param view current view to be used in the application.s
   */
  void go(ImageViewGuiInt view);

  /**
   * Getter method for the current image.
   * @return current image.
   */
  BufferedImage getImage();

  /**
   * Crops current image.
   * @param x x coordinate
   * @param y y coordinate
   * @param width total width
   * @param height total height
   */
  void cropImage(int x, int y, int width, int height);
}
