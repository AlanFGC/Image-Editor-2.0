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
    ImageGuiControlInt controller;
    model = new ConcreteImageModel();
    view = new ImageGui();
    controller = new ImageGuiController(model);
    controller.go(view);
  }
}
