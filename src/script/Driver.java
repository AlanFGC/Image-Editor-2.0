package script;

import images.ConcreteImageModel;
import images.ImageModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Main driver of the script controller-view.
 */
public class Driver {

  /**
   * Main method of the program that uses scripts.
   *
   * @param args name of the file containing scripts for this program.
   */
  public static void main(String[] args) {
    try {
      File file = new File(args[0]);
      Readable fileReader = new FileReader(file);
      ImageController controller = new ImageController(fileReader);
      ImageView view;
      view = new ImageTextView(System.out);
      ImageModel model;
      model = new ConcreteImageModel();
      controller.go(view, model);
    } catch (FileNotFoundException e) {
      System.out.println("File was not found, exiting now.");
      return;
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Error when reading arguments for this program, exiting now.");
      return;
    }
  }
}
