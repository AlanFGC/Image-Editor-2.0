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
    width = 0;
    height = 0;
  }


  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    try {
      this.width = getWidth(filename);
      this.height = getHeight(filename);
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
    newImage = new int[this.height][this.width][3];
    //Matrix
    double[][] blur;
    blur = new double[3][3];
    blur[0][0] = 0.0625;
    System.out.println(blur[0][0]);
    blur[0][1] = 0.125;
    blur[0][2] = 0.0625;

    blur[1][0] = 0.125;
    blur[1][1] = 0.25;
    blur[1][2] = 0.125;

    blur[2][0] = 0.0625;
    blur[2][1] = 0.125;
    blur[2][2] = 0.0625;

    //iterate through every pixel
    int[] rgb;
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        rgb = kernelToPixel(blur, row, col);
        newImage[row][col][0] = rgb[0];
        newImage[row][col][1] = rgb[1];
        newImage[row][col][2] = rgb[2];
      }
    }
    this.image = newImage;
  }

  @Override
  public void applySharpen() {
    //New image
    int[][][] newImage;
    newImage = new int[this.height][this.width][3];
    //Matrix
    double[][] sharpen;
    double oneEight = -0.125;
    double oneFour = 0.25;
    sharpen = new double[5][5];

    sharpen[0][0] = oneEight;
    sharpen[0][1] = oneEight;
    sharpen[0][2] = oneEight;
    sharpen[0][3] = oneEight;
    sharpen[0][4] = oneEight;

    sharpen[1][0] = oneEight;
    sharpen[1][1] = oneFour;
    sharpen[1][2] = oneFour;
    sharpen[1][3] = oneFour;
    sharpen[1][4] = oneEight;

    sharpen[2][0] = oneEight;
    sharpen[2][1] = oneFour;
    sharpen[2][2] = 1.0;
    sharpen[2][3] = oneFour;
    sharpen[2][4] = oneEight;

    sharpen[3][0] = oneEight;
    sharpen[3][1] = oneFour;
    sharpen[3][2] = oneFour;
    sharpen[3][3] = oneFour;
    sharpen[3][4] = oneEight;

    sharpen[4][0] = oneEight;
    sharpen[4][1] = oneEight;
    sharpen[4][2] = oneEight;
    sharpen[4][3] = oneEight;
    sharpen[4][4] = oneEight;

    int[] rgb;
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        rgb = kernelToPixel(sharpen, row, col);
        newImage[row][col][0] = rgb[0];
        newImage[row][col][1] = rgb[1];
        newImage[row][col][2] = rgb[2];
      }
    }
    this.image = newImage;
  }

  /**
   * This is a helper function that applies a 2D matrix into
   * every rgb channel of a pixel.
   *
   * @param kernel 2d matrix of any size as long as all columns have the same
   *               length.
   * @param coorY  is the row of the current pixel
   * @param coorX  is the column of the current pixel.
   * @throws IllegalArgumentException if the col or row are out of bounds.
   */
  private int[] kernelToPixel(double[][] kernel, int coorY, int coorX) throws
          IllegalArgumentException {
    System.out.println(String.format("Current PIXEL : (%dy, %dx)", coorY, coorX));

    //check out of bounds
    if (coorX < 0 || coorY < 0 || coorY > this.height || coorX > this.width) {
      throw new IllegalArgumentException("Column or Row out of bounds");
    }

    // kernel center
    int centerY;
    centerY = kernel.length / 2;
    int centerX;
    centerX = kernel[0].length / 2;

    if (centerX != centerY) {
      System.out.println(String.format("Kernel center: %d, %d\n", centerX, centerY));
      throw new IllegalArgumentException("Kernel dimensions are incorrect");
    }


    //results
    int[] rgb;
    rgb = new int[3];
    rgb[0] = 0;
    rgb[1] = 0;
    rgb[2] = 0;

    // current image location
    int offsetY;
    offsetY = coorY - centerY;
    int offsetX;
    offsetX = coorX - centerX;


    // System.out.println(String.format("OFFSET %dx, %dy ", offsetX, offsetY));

    //sum
    int rowLen;
    rowLen = kernel.length;
    int colLen;

    double sumR;
    sumR = 0;
    double sumG;
    sumG = 0;
    double sumB;
    sumB = 0;

    //offset
    int currentX;
    int currentY;

    for (int row = 0; row < kernel.length; row++) {
      for (int col = 0; col < kernel[row].length; col++) {
        currentX = offsetX + col;
        currentY = offsetY + row;
        if (currentY >= this.height || currentX >= this.width || currentX < 0 || currentY < 0) {
          continue;
        } else {
          sumR += image[currentY][currentX][0] * kernel[col][row];
          sumG += image[currentY][currentX][1] * kernel[col][row];
          sumB += image[currentY][currentX][2] * kernel[col][row];
        }
      }
    }

    sumR = sumR > 255 ? sumR = 255 : sumR;
    sumG = sumG > 255 ? sumG = 255 : sumG;
    sumB = sumB > 255 ? sumB = 255 : sumB;

    sumR = sumR < 0 ? sumR = 0 : sumR;
    sumG = sumG < 0 ? sumG = 0 : sumG;
    sumB = sumB < 0 ? sumB = 0 : sumB;


    rgb[0] = (int) sumR;
    rgb[1] = (int) sumG;
    rgb[2] = (int) sumB;
    // System.out.println(String.format("RGB: %d, %d, %d", rgb[0], rgb[1], rgb[2]));
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
        pixelValR = (int) ((red * rgb[0][0]) + (green * rgb[0][1])
                + (blue * rgb[0][2]));
        if (pixelValR < 0) {
          pixelValR = 0;
        }
        if (pixelValR > 255) {
          pixelValR = 255;
        }
        image[j][i][0] = pixelValR;

        //green channel
        pixelValG = (int) ((red * rgb[1][0]) + (green * rgb[1][1])
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
    this.applyGrayscale();
    //apply dither
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        // main algorithm
        int old_color;
        old_color = this.image[row][col][0];
        int newColor;
        if (old_color < 127){
          newColor = 0;
        }else{
          newColor = 255;
        }
        int error;
        error = old_color - newColor;
        this.image[row][col][0] = newColor;
        this.image[row][col][1] = newColor;
        this.image[row][col][2] = newColor;
        addColor(row, col+1, error *  0.4375);
        addColor(row+1, col-1, error *  0.1875);
        addColor(row+1, col, error *  0.3125);
        addColor(row+1, col+1, error *  0.0625);
      }
    }
  }

  private void addColor(int row, int col, double value){
    if (col < 0 || row < 0 || row >= this.height || col >= this.width) {
      return;
    }
    //add value to all colors
    image[row][col][0] += (int)value;
    image[row][col][1] += (int)value;
    image[row][col][2] += (int)value;
  }
/*
Dither algorithm
 for each position (r,c) in image (traversing row-wise) :
   old_color = red-component of pixel (r,c) //or green, or blue
   new_color = 0 or 255, whichever is closer to old_color
   error = old_color - new_color
   set color of pixel(r,c) to (new_color,new_color,new_color)

   add (7/16 * error) to pixel on the right (r,c+1)
   add (3/16 * error) to pixel on the next-row-left (r+1,c-1)
   add (5/16 * error) to pixel below in next row (r+1,c)
   add (1/16 * error) to pixel on the next-row-right (r+1,c+1)
 */


  @Override
  public void applyMosaic(int seeds) throws IllegalArgumentException {

  }
}
