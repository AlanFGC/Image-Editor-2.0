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
    //New image
    int[][][] newImage;
    newImage = new int[this.width][this.height][3];
    //Matrix
    double[][] blur;
    blur = new double[3][3];
    blur[0][0] = 1/16;
    blur[0][1] = 1/8;
    blur[0][2] = 1/16;

    blur[1][0] = 1/8;
    blur[1][1] = 1/4;
    blur[1][2] = 1/8;

    blur[2][0] = 1/16;
    blur[2][1] = 1/8;
    blur[2][2] = 1/16;

    //iterate through every pixel
    int[] rgb;
    for(int row = 0; row < this.height; row++){
      for (int col = 0; col < this.width; col++){
        rgb = kernelToPixel(blur, col, row);
        newImage[col][row][0] = rgb[0];
        newImage[col][row][1] = rgb[1];
        newImage[col][row][2] = rgb[2];
      }
    }
    this.image = newImage;
  }

  @Override
  public void applySharpen() {

  }

  /**
   * This is a helper function that applies a 2D matrix into
   * every rgb channel of a pixel.
   * @param kernel 2d matrix of any size as long as all columns have the same
   *               length.
   * @param coorY is the column of the current pixel
   * @param coorX is the row of the current pixel.
   * @throws IllegalArgumentException if the col or row are out of bounds.
   */
  private int[] kernelToPixel(double[][] kernel, int coorY, int coorX) throws
          IllegalArgumentException{
    System.out.println(String.format("Current PIXEL : (%dx, %dy)", coorX, coorY));

    //check out of bounds
    if (coorX < 0 || coorY < 0 || coorX > this.height || coorY > this.width){
      throw new IllegalArgumentException("Column or Row out of bounds");
    }

    // kernel center
    int centerY;
    centerY = kernel.length/2;
    int centerX;
    centerX = kernel[0].length/2;

    if (centerX != centerY){
      System.out.println(String.format("Kernel center: %d, %d\n", centerX, centerY));
      throw new IllegalArgumentException("Kernel dimensions are incorrect");
    }


    //results
    int[] rgb;
    rgb = new int[3];
    rgb[0] = 0;
    rgb[1] = 0;
    rgb[2] = 0;

    int offsetY;
    offsetY = coorY - centerY;
    int offsetX;
    offsetX = coorX - centerX;
    //sum
    int rowLen;
    rowLen = kernel.length;
    int colLen;

    for (int row = 0; row < rowLen; row++){
      colLen = kernel[row].length;
      for (int col = 0; col < colLen; col++){
        int currentX;
        currentX = offsetX + col;

        int currentY;
        currentY = offsetY + row;
        if (currentY > this.height || currentX > this.width || currentX < 0 || currentY < 0){
          continue;
        } else {
          rgb[0] = 255;
          rgb[1] = 255;
          rgb[2] = 255;
        }
      }
    }
    return rgb;
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

        int red = image[j][i][0];
        int blue = image[j][i][1];
        int green = image[j][i][1];
        //red channel
        pixelValR = (int) (( red * rgb[0][0]) + (green * rgb[0][1])
                + (blue * rgb[0][2]));
        if (pixelValR < 0) {
          pixelValR = 0;
        }
        if (pixelValR > 255) {
          pixelValR = 255;
        }
        image[j][i][0] = pixelValR;

        //green channel
        pixelValG = (int) ((red  * rgb[1][0]) + (green * rgb[1][1])
                + (blue * rgb[1][2]));
        if (pixelValG < 0) {
          pixelValG = 0;
        }
        if (pixelValG > 255) {
          pixelValG = 255;
        }
        image[j][i][1] = pixelValG;

        //blue channel
        pixelValB = (int) ((red * rgb[2][0]) + (green * rgb[2][1])
                + (blue * rgb[2][2]));
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
