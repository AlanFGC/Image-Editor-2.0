import images.ImageModel;
import java.awt.image.BufferedImage;

/**
 * Mock Image model for testing. Throws exceptions for testing.
 */
public class MockImageModelErrors implements ImageModel {
  private StringBuilder log;

  /**
   * Mock constructor.
   *
   * @param log a log to append inputs to.
   */
  MockImageModelErrors(StringBuilder log) {
    this.log = log;
  }

  /**
   * Mock method.
   *
   * @param filename any string.
   */
  @Override
  public void loadImage(String filename) {
    log.append(filename);
  }

  /**
   * Mock method.
   *
   * @param filename any string.
   */
  @Override
  public void saveImage(String filename) {
    log.append(filename);
  }

  /**
   * Mock method.
   */
  @Override
  public void applyBlur() {
    log.append("-blur");
  }

  /**
   * Mock method.
   */
  @Override
  public void applySharpen() {
    log.append("-sharpen");
  }

  /**
   * Mock method.
   */
  @Override
  public void applyGrayscale() throws IllegalStateException {
    log.append("-grayscale");
    throw new IllegalStateException("ERROR");

  }

  /**
   * Mock method.
   */
  @Override
  public void applySepia() {
    log.append("-sepia");
  }

  /**
   * Mock method.
   */
  @Override
  public void applyDither() {
    log.append("-dither");
  }

  /**
   * Mock method.
   */
  @Override
  public void applyMosaic(int seeds) throws IllegalArgumentException {
    log.append("-mosaic-" + String.format("%d", seeds));
    throw new IllegalArgumentException("ERROR");
  }

  /**
   * Returns the current image that is stored in memory.
   *
   * @return a BufferedImage that is currently loaded into memory.
   */
  @Override
  public BufferedImage getImage() {
    return null;
  }

  /**
   * Crops current image.
   *
   * @param x      x coordinate
   * @param y      y coordinate
   * @param width  total width
   * @param height total height
   */
  @Override
  public void cropImage(int x, int y, int width, int height) throws IllegalArgumentException {

  }

  /**
   * Applies sobel edge detection algorithm to the current image.
   */
  @Override
  public void applySobel() {

  }

  /**
   * Applies equalization.
   */
  @Override
  public void applyEqualization() {

  }
}
