package images;

import static images.ImageUtilities.convertImage;
import static images.ImageUtilities.getHeight;
import static images.ImageUtilities.getWidth;
import static images.ImageUtilities.readImage;
import static images.ImageUtilities.writeImage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * This class is the model implementation of a system that
 * applies different filters to an image. Loads an image to memory then saves
 * it, the original file is not modified unless so by the saveImage() method
 * within this class.
 */
public class ConcreteImageModel implements ImageModel {
  //Fields
  private int[][][] image;
  private int width;
  private int height;


  /**
   * Empty constructor for Concrete Image Model.
   */
  public ConcreteImageModel() {
    image = new int[0][0][0];
    width = 0;
    height = 0;
  }


  /**
   * This class loads an image to memory.
   *
   * @param filename the name of the file containing the image
   * @throws IllegalArgumentException if the file is not found or an error occurs
   */
  @Override
  public void loadImage(String filename) throws IllegalArgumentException {
    try {
      image = readImage(filename);
      this.width = getWidth(filename);
      this.height = getHeight(filename);

    } catch (IOException e) {
      throw new IllegalArgumentException("error when reading a file");
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("No file with that name was accessible");
    }


  }

  /**
   * This methods saves an image to a specified location.
   *
   * @param filename the name of the file to save to
   * @throws IllegalArgumentException if it fails to save the image
   */
  @Override
  public void saveImage(String filename) throws IllegalArgumentException {
    try {
      writeImage(image, filename);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Could not save the file with that name.");
    }
  }

  /**
   * This method applies a 'blur' effect to the current image.
   */
  @Override
  public void applyBlur() {
    if (width == 0 && height == 0) {
      return;
    }
    //New image
    int[][][] newImage;
    newImage = new int[this.height][this.width][3];
    //Matrix
    double[][] blur;
    blur = new double[3][3];
    blur[0][0] = 0.0625;
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

  /**
   * This method applies a 'sharpen' effect to the current image.
   */
  @Override
  public void applySharpen() {
    if (width == 0 && height == 0) {
      return;
    }

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
   * the current image's desired pixel.
   * This operation is formally called a convolution.
   *
   * @param kernel 2d matrix of any size as long as all columns have the same
   *               length.
   * @param coorY  is the row of the current pixel
   * @param coorX  is the column of the current pixel.
   * @throws IllegalArgumentException if the col or row are out of bounds or the kernel
   *                                  matrix lacks a center location
   */
  private int[] kernelToPixel(double[][] kernel, int coorY, int coorX) throws
          IllegalArgumentException {
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
    return rgb;
  }

  /**
   * This method applies a grayscale effect to a loaded image.
   */
  @Override
  public void applyGrayscale() {
    if (width == 0 && height == 0) {
      return;
    }
    double r = 0.2126;
    double g = 0.7152;
    double b = 0.0722;
    int pixelValue;
    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        pixelValue = (int) ((image[j][i][0] * r)
                + (image[j][i][1] * g) + (image[j][i][2] * b));
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

  /**
   * This method applies a Sepia effect to a loaded image.
   */
  @Override
  public void applySepia() {
    if (width == 0 && height == 0) {
      return;
    }
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


  /**
   * This method applies a dither effect to a loaded image.
   */
  @Override
  public void applyDither() throws IllegalStateException {
    if (width == 0 && height == 0) {
      return;
    }
    this.applyGrayscale();
    //apply dither
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        // main algorithm
        int oldColor;
        oldColor = this.image[row][col][0];
        int newColor;
        if (oldColor < 127) {
          newColor = 0;
        } else {
          newColor = 255;
        }
        int error;
        error = oldColor - newColor;
        this.image[row][col][0] = newColor;
        this.image[row][col][1] = newColor;
        this.image[row][col][2] = newColor;
        // right
        addColor(row, col + 1, error * 0.4375);
        // next-row-left
        addColor(row + 1, col - 1, error * 0.1875);
        // next row
        addColor(row + 1, col, error * 0.3125);
        // next-row-right
        addColor(row + 1, col + 1, error * 0.0625);
      }
    }
  }

  /*
  Helper function for the dither effect.
   */
  private void addColor(int row, int col, double value) {
    if (col < 0 || row < 0 || row >= this.height || col >= this.width) {
      return;
    }
    //add value to all colors
    image[row][col][0] += (int) value;
    image[row][col][1] += (int) value;
    image[row][col][2] += (int) value;
  }

  /**
   * This functions applies  a 'mosaic' effect to the current image
   * by placing random seeds and creating clusters of pixels and averaging
   * the colors of each cluster.
   *
   * @param seeds the number of seeds to use in the mosaic
   * @throws IllegalArgumentException if the number of seeds is not positive
   */
  @Override
  public void applyMosaic(int seeds) throws IllegalArgumentException {
    if (width == 0 && height == 0) {
      return;
    }
    if (seeds < 0) {
      throw new IllegalArgumentException("Number of seeds is not positive");
    }
    int totalPixels;
    totalPixels = this.width * this.height;
    if (seeds >= totalPixels || seeds == 0) {
      // filter will have no effect.
      return;
    }

    // MAIN PROCESS
    List<int[]> coordinates;
    coordinates = new LinkedList<>();
    //create lists of all possible coordinates
    for (int r = 0; r < this.height; r++) {
      for (int c = 0; c < this.width; c++) {
        int[] coor;
        coor = new int[2];
        coor[0] = r;
        coor[1] = c;
        coordinates.add(coor);
      }
    }

    // create all clusters
    List<Cluster> clusters;
    clusters = new LinkedList<>();
    while (seeds > 0) {
      int randomPick;
      randomPick = (int) (totalPixels * Math.random());
      totalPixels -= 1;
      int[] current;
      current = coordinates.remove(randomPick);
      int red;
      int green;
      int blue;
      red = this.image[current[0]][current[1]][0];
      green = this.image[current[0]][current[1]][1];
      blue = this.image[current[0]][current[1]][2];

      // create new cluster
      clusters.add(new Cluster(current[0], current[1], red, green, blue));
      seeds -= 1;
    }

    // assign each of the remaining pixels to a cluster
    for (int[] pixel : coordinates) {
      int row;
      int col;
      row = pixel[0];
      col = pixel[1];
      Cluster closestCluster;
      closestCluster = null;
      int closestDistance;
      closestDistance = 999999999;

      // calculate closest cluster center
      for (Cluster currentCluster : clusters) {
        int[] center;
        center = currentCluster.getCenter();
        int currentDistance;
        currentDistance = calculateDistance(row, col, center[0], center[1]);
        if (currentDistance < closestDistance) {
          closestCluster = currentCluster;
          closestDistance = currentDistance;
        }
      }

      // add pixel to closest cluster
      int pixelR;
      int pixelG;
      int pixelB;
      pixelR = this.image[row][col][0];
      pixelG = this.image[row][col][1];
      pixelB = this.image[row][col][2];
      closestCluster.add(row, col, pixelR, pixelG, pixelB);
    }

    // calculate averages and modify image;
    for (Cluster currentCluster : clusters) {
      int[] clusterAverage;
      clusterAverage = currentCluster.calculateAverage();
      for (int[] currentPixel : currentCluster.getAllCoordinates()) {
        this.image[currentPixel[0]][currentPixel[1]][0] = clusterAverage[0];
        this.image[currentPixel[0]][currentPixel[1]][1] = clusterAverage[1];
        this.image[currentPixel[0]][currentPixel[1]][2] = clusterAverage[2];
      }
    }
  }

  /**
   * Returns the current image that is stored in memory.
   *
   * @return a BufferedImage that is currently loaded into memory.
   */
  @Override
  public BufferedImage getImage() {
    if (this.image.length == 0) {
      return null;
    }
    return convertImage(this.image);
  }


  /**
   * Crops current image.
   *
   * @param x      starting point in the x-axis
   * @param y      starting point in the y-axis
   * @param width  width of the final image.
   * @param height height of the final image.
   * @throws IllegalArgumentException if the image is larger than the designated area.
   */
  @Override
  public void cropImage(int x, int y, int width, int height) throws IllegalArgumentException {
    if (x + width > this.width || y + height > this.height || y + height < 1 || x + width < 1) {
      throw new IllegalArgumentException("invalid arguments for a new cropped image");
    } else if (height < 1 || width < 1) {
      throw new IllegalArgumentException("Invalid height or width");
    } else if (this.image.length < 1) {
      return;
    }

    int[][][] newImage;
    newImage = new int[height][width][3];

    int cropHeight;
    cropHeight = height + y;
    int cropWidth;
    cropWidth = width + x;

    int i;
    int j;
    i = 0;
    j = 0;
    for (int r = y; r < cropHeight; r++) {
      for (int c = x; c < cropWidth; c++) {
        newImage[i][j][0] = this.image[r][c][0];
        newImage[i][j][1] = this.image[r][c][1];
        newImage[i][j][2] = this.image[r][c][2];
        j++;
      }
      i++;
      j = 0;
    }

    this.width = width;
    this.height = height;
    this.image = newImage;
  }


  /*
  helper function that calculates the closest distance between two points;
   */
  private int calculateDistance(int p1, int p2, int q1, int q2) {
    int distance;
    distance = (int) Math.sqrt(Math.pow((q1 - p1), 2) + Math.pow((q2 - p2), 2));
    return distance;
  }

  /**
   * Applies sobel edge detection algorithm to the current image.
   */
  @Override
  public void applySobel() {
    if (this.image.length < 1) {
      return;
    }

    // create new images:
    int[][][] imageX;
    imageX = new int[height][width][3];

    int[][][] imageY;
    imageY = new int[height][width][3];


    // get two different images;
    int[][] kernelGx;
    kernelGx = new int[3][3];
    kernelGx[0][0] = 1;
    kernelGx[0][1] = 0;
    kernelGx[0][2] = -1;
    kernelGx[1][0] = 2;
    kernelGx[1][1] = 0;
    kernelGx[1][2] = -2;
    kernelGx[2][0] = 1;
    kernelGx[2][1] = 0;
    kernelGx[2][2] = -1;

    int[][] kernelGy;
    kernelGy = new int[3][3];
    kernelGy[0][0] = -1;
    kernelGy[0][1] = -2;
    kernelGy[0][2] = -1;
    kernelGy[1][0] = 0;
    kernelGy[1][1] = 0;
    kernelGy[1][2] = 0;
    kernelGy[2][0] = 1;
    kernelGy[2][1] = 2;
    kernelGy[2][2] = 1;


    int[] resultX;
    int[] resultY;

    for (int r = 0; r < this.height; r++) {
      for (int c = 0; c < this.width; c++) {
        resultX = kernelToPixel(kernelGx, r, c, imageX);
        imageX[r][c][0] = resultX[0];
        imageX[r][c][1] = resultX[1];
        imageX[r][c][2] = resultX[2];
        resultY = kernelToPixel(kernelGy, r, c, imageY);
        imageY[r][c][0] = resultY[0];
        imageY[r][c][1] = resultY[1];
        imageY[r][c][2] = resultY[2];
      }
    }



    // final calculation
    for (int r = 0; r < this.height; r++) {
      for (int c = 0; c < this.width; c++) {
        image[r][c][0] = (int) Math.sqrt( Math.pow(imageX[r][c][0], 2)
                + Math.pow(imageY[r][c][0], 2) );
        image[r][c][1] = (int) Math.sqrt( Math.pow(imageX[r][c][1], 2)
                + Math.pow(imageY[r][c][1], 2) );
        image[r][c][2] = (int) Math.sqrt( Math.pow(imageX[r][c][2], 2)
                + Math.pow(imageY[r][c][2], 2) );
      }
    }

    normalizeImage(this.image);
    this.applyGrayscale();
  }

  private void normalizeImage(int[][][] image){
    normalizeColor(image, 0);
    normalizeColor(image, 1);
    normalizeColor(image, 2);
  }

  private void normalizeColor(int[][][] image, int color) {
    if (image.length < 1) {
      return;
    } else if (color < 0 || color > 2) {
      throw new IllegalArgumentException("Color integer is incorrect");
    }
    int min;
    int max;
    min = 999999999;
    max = -99999999;

    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {
        if (image[r][c][color] < min) {
          min = image[r][c][color];
        }
        if (image[r][c][color] > max) {
          max = image[r][c][color];
        }
      }
    }

    double result;
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {
        result = ((image[r][c][color] - min) * 255) / (max - min);
        image[r][c][color] = (int) result;
      }
    }
  }


  private int[] kernelToPixel(int[][] kernel, int coorY, int coorX, int[][][] newImage) throws
          IllegalArgumentException {
    //check out of bounds
    if (coorX < 0 || coorY < 0 || coorY > newImage.length || coorX > newImage[0].length) {
      throw new IllegalArgumentException("Column or Row out of bounds");
    }

    // kernel center
    int centerY;
    centerY = kernel.length / 2;
    int centerX;
    centerX = kernel[0].length / 2;

    if (centerX != centerY) {
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
        if (currentY >= newImage.length || currentX >= newImage[0].length || currentX < 0 || currentY < 0) {
          continue;
        } else {
          sumR += image[currentY][currentX][0] * kernel[col][row];
          sumG += image[currentY][currentX][1] * kernel[col][row];
          sumB += image[currentY][currentX][2] * kernel[col][row];
        }
      }
    }



    rgb[0] = (int) sumR;
    rgb[1] = (int) sumG;
    rgb[2] = (int) sumB;
    return rgb;
  }


}
