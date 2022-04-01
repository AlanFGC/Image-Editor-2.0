
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import images.ImageModel;
import script.ImageController;
import script.ImageView;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the Image controller, this uses mocks to test
 * different features.
 */
public class ImageControllerTest {
  private String instructions;

  /**
   * Setup instructions.
   */
  @Before
  public void setup() {
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
    this.instructions = inst;
  }

  /**
   * Testing quitting the program.
   */
  @Test
  public void quitProgramTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions + "Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Load command test.
   */
  @Test
  public void loadTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("load image.jpg quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "image.jpg";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Save command test.
   */
  @Test
  public void saveTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("load image.jpg save image.jpg quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "image.jpgimage.jpg";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Enter file name:Image successfully saved.Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Grayscale test.
   */
  @Test
  public void grayScaleTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("load image.jpg gray quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "image.jpg-grayscale";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Applying grayscale filter...Filter successfully applied.Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Blur test.
   */
  @Test
  public void blurTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("load image.jpg blur quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "image.jpg-blur";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Applying blur filter...Filter successfully applied.Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Sharpen test.
   */
  @Test
  public void sharpenTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("load image.jpg sharp quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "image.jpg-sharpen";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Applying sharpening filter...Filter successfully applied.Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Sepia test.
   */
  @Test
  public void sepiaTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("load image.jpg sepia quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "image.jpg-sepia";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Applying sepia filter...Filter successfully applied.Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Dither test.
   */
  @Test
  public void ditherTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("load image.jpg dither quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "image.jpg-dither";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Applying dithering filter...Filter successfully applied.Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Mosaic test.
   */
  @Test
  public void mosaicTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("load image.jpg mosaic\n1000 quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "image.jpg-mosaic-1000";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Please enter the number of seeds:";
    expectedV += "Applying mosaic filter...Filter successfully applied.Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }


  /**
   * Applies multiple random filters then saves test.
   */
  @Test
  public void multipleFiltersTest() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("load image.jpg gray mosaic 9213 save NEW.jpg quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "image.jpg-grayscale-mosaic-9213NEW.jpg";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Applying grayscale filter...Filter successfully applied.";
    expectedV += "Please enter the number of seeds:Applying mosaic filter...";
    expectedV += "Filter successfully applied.Enter file name:Image successfully saved.";
    expectedV += "Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  // ERRORS

  /**
   * Garbage Input test.
   */
  @Test
  public void garbageInput() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("ijaodjwqihj1qj39123 quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions + "Please enter a valid command or value:" + "Goodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Handling IllegalArguments exception from the model.
   */
  @Test
  public void IllegalArgumentExceptionModel() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModelErrors(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageView(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("mosaic -8 quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
    //Expected model log:
    String expectedM;
    expectedM = "-mosaic--8";
    assertEquals(expectedM, modelLog.toString());
    //Expected view log:
    String expectedV;
    expectedV = instructions + "Please enter the number of seeds:Applying mosaic filter..." +
            "ERRORGoodbye!";
    assertEquals(expectedV, viewLog.toString());
  }

  /**
   * Testing IllegalState exception for when the VIEW
   * fails at doing something. This should crash the program,
   * because there is no fix in the event this happens.
   */
  @Test(expected = IllegalStateException.class)
  public void IllegalStateExceptionView() {
    StringBuilder modelLog = new StringBuilder();
    ImageModel model;
    model = new MockImageModeLogged(modelLog);
    StringBuilder viewLog = new StringBuilder();
    ImageView view = new MockImageViewErrors(viewLog);
    //INPUTS
    Readable input;
    input = new StringReader("quit");
    //Controller start and go
    ImageController controller;
    controller = new ImageController(input);
    controller.go(view, model);
  }
}