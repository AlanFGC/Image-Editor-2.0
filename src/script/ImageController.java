package script;

import java.util.Scanner;
import images.ImageModel;

/**
 * Controller for image editor.
 * Can use readable inputs and
 * file inputs.
 */
public class ImageController {
  // Fields
  private ImageView view;
  private ImageModel model;
  private Scanner input;


  /**
   * Standard constructor for ImageController, uses a standard
   * readable object.
   *
   * @param input readable type object for inputs.
   */
  public ImageController(Readable input) {
    model = null;
    view = null;
    this.input = new Scanner(input);
  }

  /**
   * Starts the program.
   *
   * @param v a compatible ImageView object
   * @param m a compatible ModelView object
   */
  public void go(ImageView v, ImageModel m) {
    model = m;
    view = v;
    //Declare instructions
    String inst;
    inst = "options - shows all available commands\n";
    inst += "load - loads an image\n";
    inst += "save - saves current image\n";
    inst += "Once an image is loaded:\n";
    inst += "gray - grayscale - to apply sepia filter\n";
    inst += "sepia - to apply sepia filter\n";
    inst += "sharp - to apply sharpen filter\n";
    inst += "blur - to apply blur filter\n";
    inst += "dither - to apply dither filter\n";
    inst += "mosaic - to apply mosaic filter\n";
    String cmd;
    cmd = "";
    String error[];
    error = null;
    // show instructions
    view.displayInstructions(inst);
    // start loop
    while (!(cmd.equals("quit"))) {
      cmd = input.next();
      try {
        switch (cmd) {
          case "options":
            view.displayInstructions(inst);
            break;
          case "load":
            view.displayMsg("Enter file name:");
            cmd = input.next();
            model.loadImage(cmd);
            view.displayMsg("Image successfully loaded.");
            break;
          case "save":
            view.displayMsg("Enter file name:");
            cmd = input.next();
            model.saveImage(cmd);
            view.displayMsg("Image successfully saved.");
            break;
          case "quit":
            view.displayMsg("Goodbye!");
            break;
          case "gray":
            view.displayMsg("Applying grayscale filter...");
            model.applyGrayscale();
            view.displayMsg("Filter successfully applied.");
            break;
          case "sepia":
            view.displayMsg("Applying sepia filter...");
            model.applySepia();
            view.displayMsg("Filter successfully applied.");
            break;
          case "sharp":
            view.displayMsg("Applying sharpening filter...");
            model.applySharpen();
            view.displayMsg("Filter successfully applied.");
            break;
          case "blur":
            view.displayMsg("Applying blur filter...");
            model.applyBlur();
            view.displayMsg("Filter successfully applied.");
            break;
          case "dither":
            view.displayMsg("Applying dithering filter...");
            model.applyDither();
            view.displayMsg("Filter successfully applied.");
            break;
          case "mosaic":
            view.displayMsg("Please enter the number of seeds:");
            cmd = input.next();
            int seeds;
            seeds = Integer.parseInt(cmd);
            view.displayMsg("Applying mosaic filter...");
            model.applyMosaic(seeds);
            view.displayMsg("Filter successfully applied.");
            break;
          default:
            view.displayMsg("Please enter a valid command or value:");
        }

      } catch (IllegalStateException e) {
        error = e.toString().split(": ");
        view.displayMsg(error[1]);
      } catch (IllegalArgumentException e) {
        error = e.toString().split(": ");
        view.displayMsg(error[1]);
      }
    }
  }
}
