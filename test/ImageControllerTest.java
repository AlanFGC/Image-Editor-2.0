import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import java.io.StringReader;

import javax.sound.midi.SysexMessage;

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

  @Before
  public void setup(){
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

  @Test
  public void quitProgram(){
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
    assertEquals(modelLog.toString(), expectedM);
    //Expected view log:
    String expectedV;
    expectedV = instructions + "Goodbye!";
    assertEquals(viewLog.toString(), expectedV);
  }

  @Test
  public void grayScale(){
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
    assertEquals(modelLog.toString(), expectedM);
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Applying grayscale filter...Filter successfully applied.Goodbye!";
    assertEquals(viewLog.toString(), expectedV);
  }


  @Test
  public void blur(){
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
    assertEquals(modelLog.toString(), expectedM);
    //Expected view log:
    String expectedV;
    expectedV = instructions;
    expectedV += "Enter file name:Image successfully loaded.";
    expectedV += "Applying blur filter...Filter successfully applied.Goodbye!";
    assertEquals(viewLog.toString(), expectedV);
  }


}