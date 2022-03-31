package images;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a cluster of pixels with one of them
 * being the center.
 * Each pixel is represented by row/column and rgb value.
 * The relation between coordinate and rgb value is
 * not guaranteed to be accurate.
 */
public class Cluster implements ClusterInterface {
  private int rowCenter;
  private int colCenter;
  private List<int[]> coordinates;
  private List<int[]> colors;
  private int[] average;

  /**
   * This method generates a new cluster using a center
   * pixel.
   *
   * @param row row where the center pixel is located
   * @param col column where the center pixel is located
   * @param r   red value of the center pixel
   * @param g   green value of the center pixel
   * @param b   blue value of the center pixel
   */
  public Cluster(int row, int col, int r, int g, int b) {
    rowCenter = row;
    colCenter = col;
    coordinates = new LinkedList<>();
    colors = new LinkedList<>();
    average = null;
    // add center to cluster
    add(row, col, r, g, b);
  }

  /**
   * This method adds a pixel to a cluster.
   *
   * @param row row where the pixel is located
   * @param col column where the pixel is located
   * @param r   red value of the pixel
   * @param g   green value of the pixel
   * @param b   blue value of the pixel
   */
  @Override
  public void add(int row, int col, int r, int g, int b) {
    int[] rgb;
    rgb = new int[3];
    // colors
    rgb[0] = r;
    rgb[1] = g;
    rgb[2] = b;
    colors.add(rgb);
    // coordinates
    int[] coordinates;
    coordinates = new int[2];
    coordinates[0] = row;
    coordinates[1] = col;
    this.coordinates.add(coordinates);
  }

  /**
   * Calculates the average rgb value of the whole cluster
   * including its center pixel.
   *
   * @return rgb value in the form of an array of size 3
   */
  @Override
  public int[] calculateAverage() {
    int[] rgb;
    rgb = new int[3];
    int sumR;
    int sumG;
    int sumB;
    sumR = 0;
    sumG = 0;
    sumB = 0;

    // calculate average
    for (int[] member : colors) {
      sumR += member[0];
      sumG += member[1];
      sumB += member[2];
    }
    int size;
    size = colors.size();
    sumR = sumR / size;
    sumG = sumG / size;
    sumB = sumB / size;

    rgb[0] = sumR;
    rgb[1] = sumG;
    rgb[2] = sumB;

    average = rgb;
    return average;
  }

  /**
   * Getter method for all the list of all the coordinates inside
   * the cluster.
   *
   * @return a list of arrays of size 2 containing the coordinates of each pixel
   */
  @Override
  public List<int[]> getAllCoordinates() {
    return coordinates;
  }

  /**
   * Getter method for the coordinates of the center pixel.
   *
   * @return an array of size 2 with the coordinates of the center pixel (row/column).
   */
  @Override
  public int[] getCenter() {
    int[] center;
    center = new int[2];
    center[0] = this.rowCenter;
    center[1] = this.colCenter;
    return center;
  }

}
