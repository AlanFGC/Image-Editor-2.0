package imageview;

import images.ConcreteImageModel;
import images.ImageModel;

public class Driver {
  public static void main(String[] args) {
    ImageViewGuiInterface view;
    ImageModel model;
    ImageFeatures controller;
    model = new ConcreteImageModel();
    view = new ImageGui("Image Editor");
    controller = new ImageGuiController(model);
    controller.go(view);
  }
}
