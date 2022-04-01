package images;

/**
 * Main driver class for the program.
 */
public class TestingDriver {

  /**
   * This is the main driver of the program.
   *
   * @param args no arguments needed.
   */
  public static void main(String[] args) {

    String filename = "res/squirrel";

    ImageModel model = new ConcreteImageModel();

    model.loadImage(filename + ".jpg");

    model.applyBlur();
    model.saveImage(filename + "-blurred.jpg");
    model.applyBlur();
    model.saveImage(filename + "-blurred-2.jpg");

    model.loadImage(filename + ".jpg");
    model.applySharpen();
    model.saveImage(filename + "-sharpen.jpg");
    model.applySharpen();
    model.saveImage(filename + "-sharpen-2.jpg");

    model.loadImage(filename + ".jpg");
    model.applyGrayscale();
    model.saveImage(filename + "-grayscale.jpg");

    model.loadImage(filename + ".jpg");
    model.applySepia();
    model.saveImage(filename + "-sepia.jpg");

    model.loadImage(filename + ".jpg");
    model.applyDither();
    model.saveImage(filename + "-dither.jpg");

    model.loadImage(filename + ".jpg");
    model.applyMosaic(15000);
    model.saveImage(filename + "-mosaic-15000.jpg");

    model.loadImage(filename + ".jpg");
    model.applyMosaic(8000);
    model.saveImage(filename + "-mosaic-8000.jpg");

    model.loadImage(filename + ".jpg");
    model.applyMosaic(4000);
    model.saveImage(filename + "-mosaic-4000.jpg");

    model.loadImage(filename + ".jpg");
    model.applyMosaic(1000);
    model.saveImage(filename + "-mosaic-1000.jpg");


  }
}
