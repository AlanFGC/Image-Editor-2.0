package images;

import java.util.List;

/**
 * This interface represents a cluster of pixels.
 */
public interface ClusterInterface {
  /**
   * This method adds a pixel to a cluster.
   *
   * @param row row where the pixel is located
   * @param col column where the pixel is located
   * @param r   red value of the pixel
   * @param g   green value of the pixel
   * @param b   blue value of the pixel
   */
  public void add(int row, int col, int r, int g, int b);

  /**
   * Calculates the average rgb value of the whole cluster
   * including its center pixel.
   *
   * @return rgb value in the form of an array of size 3
   */
  public int[] calculateAverage();

  /**
   * Getter method for all the list of all the coordinates inside
   * the cluster.
   *
   * @return a list of arrays of size 2 containing the coordinates of each pixel
   */
  public List<int[]> getAllCoordinates();


  /**
   * Getter method for the coordinates of the center pixel.
   *
   * @return an array of size 2 with the coordinates of the center pixel (row/column).
   */
  public int[] getCenter();
}
