package images;

import java.io.IOException;

import static images.ImageUtilities.getHeight;
import static images.ImageUtilities.getWidth;
import static images.ImageUtilities.readImage;
import static images.ImageUtilities.writeImage;

public class ConcreteImageModel implements ImageModel {
  //Fields
  private int[][][] image;
  private int width;
  private int height;


  //Constructors
  public ConcreteImageModel() {
    image = new int[0][0][0];
  }


  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    try {
      this.width = getWidth(filename);
      this.height = getHeight(filename);
      image = new int[this.width][this.height][3];
      image = readImage(filename);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      return;
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(String.format("Successfully loaded %s", filename));
  }

  @Override
  public void saveImage(String filename) throws IllegalArgumentException {
    try {
      writeImage(image, filename);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
      return;
    }
    System.out.println(String.format("Successfully saved image to %s file", filename));
  }

  @Override
  public void applyBlur() {

  }

  @Override
  public void applySharpen() {

  }

  @Override
  public void applyGrayscale() {
    double r = 0.2126;
    double g = 0.7152;
    double b = 0.0722;
    int pixelValue;
    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        pixelValue = (int) ((image[j][i][0] * r) +
                        (image[j][i][1] * g) + (image[j][i][2] * b));
        if (pixelValue < 0) {
          pixelValue = 0;
        }
        if (pixelValue > 255) {
          pixelValue = 255;
        }
        image[j][i][0] = pixelValue;
        image[j][i][1] = pixelValue;
        image[j][i][2] = pixelValue;
      }
    }
  }

  @Override
  public void applySepia() {
    //Matrix
    double[][] rgb = new double[3][3];
    rgb[0][0] = 0.393;
    rgb[0][1] = 0.769;
    rgb[0][2] = 0.189;

    rgb[1][0] = 0.349;
    rgb[1][1] = 0.686;
    rgb[1][2] = 0.168;

    rgb[2][0] = 0.272;
    rgb[2][1] = 0.534;
    rgb[2][2] = 0.131;

    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        int pixelValR;
        int pixelValG;
        int pixelValB;

        //red channel
        pixelValR = (int) ((image[j][i][0] * rgb[0][1]) + (image[j][i][1] * rgb[0][2])
                + (image[j][i][2] * rgb[0][2]));
        if (pixelValR < 0) {
          pixelValR = 0;
        }
        if (pixelValR > 255) {
          pixelValR = 255;
        }
        image[j][i][0] = pixelValR;

        //green channel
        pixelValG = (int) ((image[j][i][0] * rgb[1][1]) + (image[j][i][1] * rgb[1][2])
                + (image[j][i][2] * rgb[1][2]));
        if (pixelValG < 0) {
          pixelValG = 0;
        }
        if (pixelValG > 255) {
          pixelValG = 255;
        }
        image[j][i][1] = pixelValG;

        //blue channel
        pixelValB = (int) ((image[j][i][0] * rgb[2][1]) + (image[j][i][1] * rgb[2][2])
                + (image[j][i][2] * rgb[2][2]));
        if (pixelValB < 0) {
          pixelValB = 0;
        }
        if (pixelValB > 255) {
          pixelValB = 255;
        }
        image[j][i][2] = pixelValB;
      }
    }

  }

  @Override
  public void applyDither() {

  }

  @Override
  public void applyMosaic(int seeds) throws IllegalArgumentException {

  }
}
