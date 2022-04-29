package imageview;

import images.ConcreteImageModel;
import images.ImageModel;

/**
 * Startup class for the program.
 */
public class Startup {

  /**
   * main method.
   * @param args command line arguments (Not needed).
   */
  public static void main(String[] args) {
    ImageViewGuiInt view;
    ImageModel model;
    Features controller;
    model = new ConcreteImageModel();
    controller = new ImageGuiController(model);
    view = new ImageGui(controller);
    controller.go(view);
  }
}
