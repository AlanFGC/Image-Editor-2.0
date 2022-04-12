package imageview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * This is a listener for ImageGui implementation.
 */
public class GeneralListener implements ActionListener, KeyListener {

  //Fields
  ImageGui gui;

  GeneralListener(ImageGui gui){
    this.gui = gui;
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
        gui.loadNewImage();
        break;
      case "save-image":
        gui.saveImage();
        break;
      case "exit-program":
        gui.exitProgram();
        break;
      case "blur-image":
        gui.blur();
        break;
      case "sharpen-image":
        gui.sharpen();
        break;
      case "grayscale-image":
        gui.grayscale();
        break;
      case "sepia-image":
        gui.sepia();
        break;
      case "dither-image":
        gui.dither();
        break;
      case "mosaic-image":
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

  }
}
