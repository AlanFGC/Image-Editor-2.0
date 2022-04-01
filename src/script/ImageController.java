package script;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import images.ImageModel;

public class ImageController {
  // Fields
  private ImageView view;
  private ImageModel model;
  private Scanner input;
  private Appendable output;


  /**
   * Constructor for ImageController, uses a script for
   * inputs.
   *
   * @param fileName readable type object for inputs.
   */
  ImageController(String fileName) throws FileNotFoundException {
    model = null;
    view = null;
    File file;
    file = new File(fileName);
    Readable fileReader;
    fileReader = new FileReader(file);
    this.input = new Scanner(fileReader);
  }

  /**
   * Standard constructor for ImageController, uses a standard
   * readable object.
   *
   * @param input readable type object for inputs.
   */
  ImageController(Readable input) {
    model = null;
    view = null;
    this.input = new Scanner(input);
  }

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
    view.displayInstructions(inst);
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
            view.displayMsg("Applying blurring filter...");
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
            view.displayMsg("Please enter a valid command:");
        }

      } catch (IllegalStateException e) {
        error = e.toString().split(": ");
        view.displayMsg(error[1]);
        cmd = "quit";
      } catch (IllegalArgumentException e){
        error = e.toString().split(": ");
        view.displayMsg(error[1]);
      }
    }
  }
}
