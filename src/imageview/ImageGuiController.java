package imageview;

import images.ImageModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import script.ImageController;
import script.ImageTextView;

/**
 * This is a controller that interacts with the image model and
 * a graphical user interface.
 */
public class ImageGuiController implements Features {
  //Fields
  private ImageModel model;
  private ImageViewGuiInt view;
  private boolean control;
  private boolean imageLoaded;


  /**
   * Standard constructor for an image gui controller.
   *
   * @param model current model to be used.
   */
  public ImageGuiController(ImageModel model) {
    this.model = model;
    this.view = null;
    imageLoaded = false;
  }

  /**
   * Starts the program using a view.
   * Cannot work without running this method first.
   *
   * @param view view implementation.
   */
  @Override
  public void go(ImageViewGuiInt view) {
    this.view = view;
    view.setController(this, this);
  }

  /**
   * Tells the controller to load an image.
   */
  public void loadImage() {
    String path;
    path = view.getImagePathLoad();
    if (path != null) {
      try {
        model.loadImage(path);
        imageLoaded = true;
      } catch (IllegalArgumentException e) {
        this.view.displayMessage("Did not open any document, wrong path or document type");
        return;
      }
    }
  }

  /**
   * Tells the controller to save an image.
   */
  public void saveImage() {
    String path;
    path = view.getImagePathSave();
    if (path != null) {
      try {
        model.saveImage(path);
        this.view.displayMessage("Image was saved successfully!");
      } catch (IllegalArgumentException e) {
        this.view.displayMessage("Failed to save image");
        return;
      }
    }
  }

  /**
   * Tells the controller to save an image.
   */
  public void loadScript() {
    String path;
    path = view.getScriptPath();
    if (path != null) {
      try {
        runScript(path);
      } catch (IllegalArgumentException e) {
        this.view.displayMessage("Something went wrong when running a script file");
        return;
      }
    }
    this.view.updateImage(this.model.getImage());
  }


  private void runScript(String path) {
    try {
      File file;
      file = new File(path);
      Readable fileReader;
      fileReader = new FileReader(file);
      ImageController controller = new ImageController(fileReader);
      controller.go(new ImageTextView(System.out), model);
      this.view.displayMessage("Script was loaded and ran successfully!");
    } catch (FileNotFoundException e) {
      System.out.println("File was not found, exiting now.");
      this.view.displayMessage("File was not found, exiting now.");
    }
  }


  /**
   * Tells the model to apply the mosaic
   * filter.
   */
  public void mosaic() {
    int seed;
    seed = view.mosaicGetValue();
    try {
      this.model.applyMosaic(seed);
    } catch (IllegalArgumentException e) {
      this.view.displayMessage("Could not apply mosaic filter, wrong arguments");
      return;
    }
    this.view.updateImage(this.model.getImage());
  }


  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "load-image":
        this.loadImage();
        this.view.updateImage(this.model.getImage());
        view.resetFocus();
        break;
      case "save-image":
        this.saveImage();
        view.resetFocus();
        break;
      case "exit-program":
        System.exit(0);
        break;
      case "blur-image":
        this.model.applyBlur();
        this.view.updateImage(this.model.getImage());
        view.resetFocus();
        break;
      case "sharpen-image":
        this.model.applySharpen();
        this.view.updateImage(this.model.getImage());
        view.resetFocus();
        break;
      case "grayscale-image":
        this.model.applyGrayscale();
        this.view.updateImage(this.model.getImage());
        view.resetFocus();
        break;
      case "sepia-image":
        this.model.applySepia();
        this.view.updateImage(this.model.getImage());
        view.resetFocus();
        break;
      case "dither-image":
        this.model.applyDither();
        this.view.updateImage(this.model.getImage());
        view.resetFocus();
        break;
      case "mosaic-open-menu":
        view.mosaicMenu();
        view.resetFocus();
        break;
      case "mosaic-send":
        this.mosaic();
        view.resetFocus();
        break;
      case "sobel-image":
        model.applySobel();
        this.view.updateImage(this.model.getImage());
        break;
      case "equalize-image":
        model.applyEqualization();
        this.view.updateImage(this.model.getImage());
        break;
      case "run-script":
        loadScript();
        break;
      default:
        break;
    }
  }

  /**
   * Invoked when a key has been typed.
   * See the class description for {@link KeyEvent} for a definition of
   * a key typed event.
   *
   * @param e the event to be processed
   */
  @Override
  public void keyTyped(KeyEvent e) {


  }

  /**
   * Invoked when a key has been pressed.
   * See the class description for {@link KeyEvent} for a definition of
   * a key pressed event.
   *
   * @param e the event to be processed
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
      control = true;
    }
  }

  /**
   * Invoked when a key has been released.
   * See the class description for {@link KeyEvent} for a definition of
   * a key released event.
   *
   * @param e the event to be processed
   */
  @Override
  public void keyReleased(KeyEvent e) {
    if (control) {
      control = false;
      switch (e.getKeyCode()) {
        case KeyEvent.VK_S:
          saveImage();
          break;
        case KeyEvent.VK_L:
          loadImage();
          break;
        case KeyEvent.VK_B:
          this.model.applyBlur();
          this.view.updateImage(this.model.getImage());
          view.resetFocus();
          break;
        case KeyEvent.VK_H:
          this.model.applySharpen();
          this.view.updateImage(this.model.getImage());
          view.resetFocus();
          break;
        case KeyEvent.VK_G:
          this.model.applyGrayscale();
          this.view.updateImage(this.model.getImage());
          view.resetFocus();
          break;
        case KeyEvent.VK_E:
          this.model.applySepia();
          this.view.updateImage(this.model.getImage());
          view.resetFocus();
          break;
        case KeyEvent.VK_D:
          this.model.applyDither();
          this.view.updateImage(this.model.getImage());
          view.resetFocus();
          break;
        case KeyEvent.VK_M:
          view.mosaicMenu();
          this.view.updateImage(this.model.getImage());
          view.resetFocus();
          break;
        case KeyEvent.VK_O:
          model.applySobel();
          this.view.updateImage(this.model.getImage());
          view.resetFocus();
        case KeyEvent.VK_Q:
          model.applyEqualization();
          this.view.updateImage(this.model.getImage());
          view.resetFocus();
        case KeyEvent.VK_C:
          view.cropImagePopUp();
        default:
          break;
      }
    }
  }

  /**
   * Getter method for the current image.
   * @return an image
   */
  @Override
  public BufferedImage getImage(){
    return this.model.getImage();
  }

  /**
   * Crops current image.
   *
   * @param x      x coordinate
   * @param y      y coordinate
   * @param width  total width
   * @param height total height
   */
  @Override
  public void cropImage(int x, int y, int width, int height) {
    if (imageLoaded){
      model.cropImage(x, y, width, height);
      view.updateImage(this.model.getImage());
    }
  }
}