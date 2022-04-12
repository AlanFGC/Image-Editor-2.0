import java.awt.image.BufferedImage;

import images.ImageModel;

/**
 * Mock Image model for testing.
 */
public class MockImageModeLogged implements ImageModel {
  private StringBuilder log;

  /**
   * Mock constructor.
   *
   * @param log a log to append inputs to.
   */
  MockImageModeLogged(StringBuilder log) {
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
    return null;
  }
}
