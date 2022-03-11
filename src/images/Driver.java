package images;

public class Driver {

  public static void main(String[] args) {
    
    String filename = "res/squirrel";
    
    ImageModel model = new ConcreteImageModel();
    
    model.loadImage(filename + ".jpg");
    model.applyBlur();
    model.saveImage(filename + "-nothing.jpg");
    /**
    model.applyBlur();
    model.saveImage(filename + "-blurred.png");
    model.applyBlur();
    model.saveImage(filename + "-blurred-2.png");

    model.loadImage(filename + ".png");
    model.applySharpen();
    model.saveImage(filename + "-sharpen.png");
    model.applySharpen();
    model.saveImage(filename + "-sharpen-2.png");

    model.loadImage(filename);
    model.applyGrayscale();
    model.saveImage("manhattan-small-grayscale.png");

    model.loadImage(filename);
    model.applySepia();
    model.saveImage("manhattan-small-sepia.png");

    model.loadImage(filename);
    model.applyDither();
    model.saveImage("manhattan-small-dither.png");

    model.loadImage(filename);
    model.applyMosaic(15000);
    model.saveImage("manhattan-small-mosaic-15000.png");

    model.loadImage(filename);
    model.applyMosaic(8000);
    model.saveImage("manhattan-small-mosaic-8000.png");

    model.loadImage(filename);
    model.applyMosaic(4000);
    model.saveImage("manhattan-small-mosaic-4000.png");

    model.loadImage(filename);
    model.applyMosaic(1000);
    model.saveImage("manhattan-small-mosaic-1000.png");
     */

  }
}
