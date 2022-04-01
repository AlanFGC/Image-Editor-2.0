import images.ImageModel;

public class MockImageModeLogged implements ImageModel {
  private StringBuilder log;

  MockImageModeLogged(StringBuilder log){
    this.log = log;
  }

  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    log.append(filename);
  }

  @Override
  public void saveImage(String filename) throws IllegalArgumentException {
    log.append(filename);
  }

  @Override
  public void applyBlur() {
    log.append("-blur");
  }

  @Override
  public void applySharpen() {
    log.append("-sharpen");
  }

  @Override
  public void applyGrayscale() {
    log.append("-grayscale");

  }

  @Override
  public void applySepia() {
    log.append("-sepia");
  }

  @Override
  public void applyDither() {
    log.append("-dither");
  }

  @Override
  public void applyMosaic(int seeds) throws IllegalArgumentException {
    log.append("-mosaic");
  }
}
