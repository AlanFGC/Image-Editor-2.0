package script;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import images.ConcreteImageModel;
import images.ImageModel;

/**
 * Main driver of the script controller-view.
 */
public class Driver {
  public static void main(String[] args) throws FileNotFoundException {
    File file = new File(args[0]);
    Readable fileReader = new FileReader(file);
    ImageController controller = new ImageController(fileReader);
    ImageView view;
    view = new ImageTextView(System.out);
    ImageModel model;
    model = new ConcreteImageModel();
    controller.go(view, model);
  }
}
