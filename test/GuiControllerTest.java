import static org.junit.Assert.assertEquals;

import images.ImageModel;
import imageview.Features;
import imageview.ImageGuiController;
import imageview.ImageViewGuiInt;
import java.awt.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;

/**
 * General gui controller test.
 */
public class GuiControllerTest {

  StringBuilder modelLog;
  StringBuilder viewLog;
  Features controller;
  ImageViewGuiInt view;
  ImageModel model;

  /**
   * Setup.
   */
  @Before
  public void setup() {
    modelLog = new StringBuilder();
    viewLog = new StringBuilder();
    model = new MockModelGui(modelLog);
    view = new MockViewGui(viewLog);
    controller = new ImageGuiController(model);
    controller.go(view);
  }


  /**
   * Tests setup between view and controller.
   */
  @Test
  public void go() {
    String expectedLog;
    expectedLog = "-setup";
    assertEquals(expectedLog, viewLog.toString());
  }

  /**
   * load image test.
   */
  @Test
  public void loadImageTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "load-image");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-loadImage-updateImage-resetFocus";
    assertEquals(expectedLog, viewLog.toString());
    expectedLog = "2318-getImage";
    assertEquals(expectedLog, modelLog.toString());
  }

  /**
   * Save image test.
   */
  @Test
  public void saveImageTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "save-image");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-getImagePathSaveImage was saved successfully!-resetFocus";
    assertEquals(expectedLog, viewLog.toString());
    expectedLog = "4212";
    assertEquals(expectedLog, modelLog.toString());
  }

  /**
   * Load script test. (no file)
   */
  @Test
  public void loadScriptTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "run-script");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-getScriptPathFile was not found, exiting now.-updateImage";
    assertEquals(expectedLog, viewLog.toString());
    expectedLog = "-getImage";
    assertEquals(expectedLog, modelLog.toString());
  }

  /**
   * Open menu test.
   */
  @Test
  public void mosaicOpenMenuTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "mosaic-open-menu");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-mosaicMenu-resetFocus";
    assertEquals(expectedLog, viewLog.toString());
  }

  /**
   * mosaic menu test.
   */
  @Test
  public void mosaicSendTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "mosaic-send");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-getValueM-updateImage-resetFocus";
    assertEquals(expectedLog, viewLog.toString());
    expectedLog = "-mosaic-9421-getImage";
    assertEquals(expectedLog, modelLog.toString());
  }


  /**
   * gray test.
   */
  @Test
  public void grayscaleTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "grayscale-image");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-updateImage-resetFocus";
    assertEquals(expectedLog, viewLog.toString());
    expectedLog = "-grayscale-getImage";
    assertEquals(expectedLog, modelLog.toString());
  }


  /**
   * sepia test.
   */
  @Test
  public void sepiaTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "sepia-image");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-updateImage-resetFocus";
    assertEquals(expectedLog, viewLog.toString());
    expectedLog = "-sepia-getImage";
    assertEquals(expectedLog, modelLog.toString());
  }

  /**
   * blur test.
   */
  @Test
  public void blurTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "blur-image");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-updateImage-resetFocus";
    assertEquals(expectedLog, viewLog.toString());
    expectedLog = "-blur-getImage";
    assertEquals(expectedLog, modelLog.toString());
  }


  /**
   * sharpen test.
   */
  @Test
  public void sharpenTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "sharpen-image");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-updateImage-resetFocus";
    assertEquals(expectedLog, viewLog.toString());
    expectedLog = "-sharpen-getImage";
    assertEquals(expectedLog, modelLog.toString());
  }


  /**
   * dither test.
   */
  @Test
  public void ditherTest() {
    ActionEvent action;
    action = new ActionEvent(new Object(), 1, "dither-image");
    controller.actionPerformed(action);
    String expectedLog;
    expectedLog = "-setup-updateImage-resetFocus";
    assertEquals(expectedLog, viewLog.toString());
    expectedLog = "-dither-getImage";
    assertEquals(expectedLog, modelLog.toString());
  }
}