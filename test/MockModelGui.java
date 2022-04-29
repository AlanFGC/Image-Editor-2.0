import images.ImageModel;
import java.awt.image.BufferedImage;


/**
 * Mock Image model for testing.
 */
public class MockModelGui implements ImageModel {
  private StringBuilder log;

  /**
   * Mock constructor.
   *
   * @param log a log to append inputs to.
   */
  MockModelGui(StringBuilder log) {
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
  public void applyGrayscale() {
    log.append("-grayscale");

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
  public void applyMosaic(int seeds) {
    log.append("-mosaic-" + String.format("%d", seeds));
  }

  /**
   * Returns the current image that is stored in memory.
   *
   * @return a BufferedImage that is currently loaded into memory.
   */
  @Override
  public BufferedImage getImage() {
    log.append("-getImage");
    return null;
  }

  /**
   * Crops current image.
   * @param x      starting point in the x-axis
   * @param y      starting point in the y-axis
   * @param width  width of the final image.
   * @param height height of the final image.
   * @throws IllegalArgumentException if the image is larger than the designated area.
   */
  @Override
  public void cropImage(int x, int y, int width, int height) throws IllegalArgumentException {

  }
}
