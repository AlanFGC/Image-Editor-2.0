package imageview;

import java.awt.image.BufferedImage;

import images.ImageModel;

/**
 * This is a controller that interacts with the image model and
 * a graphical user interface.
 */
public class ImageGuiController implements ImageFeatures {
  //Fields
  private ImageModel model;
  private ImageViewGuiInterface view;

  ImageGuiController(ImageModel model){
    this.model = model;
    this.view  = null;
  }

  /**
   * Starts the program.
   *
   * @param view
   */
  @Override
  public void go(ImageViewGuiInterface view) {
    this.view = view;
    this.view.addFeatures(this);
  }

  /**
   * Tells the controller to load an image.
   *
   * @param path path of image.
   * @return true or false if process was successful.
   */
  @Override
  public boolean loadImage(String path) {
    try {
      model.loadImage(path);
    }catch (IllegalArgumentException e){
      return false;
    }
    this.view.updateImage(this.model.getImage());
    return true;
  }

  /**
   * Tells the controller to save an image.
   *
   * @param path path of image.
   * @return true or false if process was successful.
   */
  @Override
  public boolean saveImage(String path) {
    try {
      model.saveImage(path);
    }catch (IllegalArgumentException e){
      return false;
    }
    return true;
  }

  /**
   * Tells the controller to apply the blur
   * filter.
   */
  @Override
  public void blur() {
    this.model.applyBlur();
    this.view.updateImage(this.model.getImage());
  }

  /**
   * Tells the controller to apply the sharpening
   * filter.
   */
  @Override
  public void sharpen() {
    this.model.applySharpen();
    this.view.updateImage(this.model.getImage());
  }

  /**
   * Tells the controller to apply the grayscale
   * filter.
   */
  @Override
  public void grayscale() {
    this.model.applyGrayscale();
    this.view.updateImage(this.model.getImage());
  }

  /**
   * Tells the controller to apply the sepia
   * filter.
   */
  @Override
  public void sepia() {
    this.model.applySepia();
    this.view.updateImage(this.model.getImage());
  }

  /**
   * Tells the controller to apply the dither
   * filter.
   */
  @Override
  public void dither() {
    this.model.applyDither();
    this.view.updateImage(this.model.getImage());
  }

  /**
   * Tells the controller to apply the mosaic
   * filter.
   *
   * @param seed
   */
  @Override
  public boolean mosaic(int seed) {
    try{
      this.model.applyMosaic(seed);
    }catch (IllegalArgumentException e){
      return false;
    }
    this.view.updateImage(this.model.getImage());
    return true;
  }

  /**
   * Gets the current image from the model, if not
   * it returns an arbitrary value.
   *
   * @returns BufferedImage the current image used by the model.
   */
  @Override
  public BufferedImage getCurrentImage() {
    return model.getImage();
  }

  /**
   * Exits the program.
   */
  @Override
  public void exitProgram() {
    System.exit(0);
  }
}
