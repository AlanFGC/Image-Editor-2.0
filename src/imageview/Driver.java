package imageview;

import images.ConcreteImageModel;
import images.ImageModel;

public class Driver {
  public static void main(String[] args) {
    ImageViewGuiInt view;
    ImageModel model;
    ImageGuiControlInt controller;
    model = new ConcreteImageModel();
    view = new ImageGui("Image Editor");
    controller = new ImageGuiController(model);
    controller.go(view);
  }
}
