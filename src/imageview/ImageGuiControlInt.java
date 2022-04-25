package imageview;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * This interface serves as a list of features that a gui controller
 * should implement.
 */
public interface ImageGuiControlInt extends ActionListener, KeyListener {

  /**
   * Starts the program.
   *
   * @param view current view to be used in the application.s
   */
  void go(ImageViewGuiInt view);
}
