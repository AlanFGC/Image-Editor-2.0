package imageview;

import java.awt.image.BufferedImage;

/**
 * This interface serves as a list of features that a gui controller
 * should implement.
 */
public interface ImageFeatures {

  /**
   * Starts the program.
   * @param view
   */
  void go(ImageViewGuiInterface view);

  /**
   * Tells the controller to load an image.
   * @param path path of image.
   * @return true or false if process was successful.
   */
  boolean loadImage(String path);

  /**
   * Tells the controller to save an image.
   * @param path path of image.
   * @return true or false if process was successful.
   */
  boolean saveImage(String path);

  /**
   * Tells the controller to apply the blur
   * filter.
   */
  void blur();

  /**
   * Tells the controller to apply the sharpening
   * filter.
   */
  void sharpen();

  /**
   * Tells the controller to apply the grayscale
   * filter.
   */
  void grayscale();

  /**
   * Tells the controller to apply the sepia
   * filter.
   */
  void sepia();

  /**
   * Tells the controller to apply the dither
   * filter.
   */
  void dither();

  /**
   * Tells the controller to apply the mosaic
   * filter.
   */
  boolean mosaic(int seed);


  /**
   * Gets the current image from the model, if not
   * it returns an arbitrary image.
   * @returns BufferedImage the current image used by the model.
   */
  BufferedImage getCurrentImage();


  /**
   * Exits the program.
   */
  void exitProgram();
}
