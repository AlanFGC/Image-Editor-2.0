package imageview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * This is just a standard preview window when cropping.
 */
public class CropPreview extends JFrame {

  private JLabel imagePreview;
  private JButton cancelButton;
  private JButton acceptButton;

  /**
   * Creates a preview.
   *
   * @param gui a gui View.
   */
  CropPreview(ImageViewGuiInt gui) {
    super("Preview");
    this.setVisible(false);
    //prompt
    JLabel prompt;
    prompt = new JLabel("Continue cropping image?");
    prompt.setFont(new Font("sans-serif", Font.PLAIN, 28));
    prompt.setHorizontalAlignment(SwingConstants.CENTER);
    prompt.setBorder(new EmptyBorder(25, 0, 20, 0));
    // Buttons
    acceptButton = new JButton("Accept");
    acceptButton.setPreferredSize(new Dimension(180, 50));
    acceptButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
    acceptButton.addActionListener(evt -> setVisible(false));
    acceptButton.addActionListener(evt -> gui.cropImage());
    acceptButton.addActionListener(evt -> gui.clearCanvas());
    acceptButton.setPreferredSize(new Dimension(180, 50));
    // cancel button
    cancelButton = new JButton("Cancel");
    cancelButton.setPreferredSize(new Dimension(180, 50));
    cancelButton.setFont(new Font("sans-serif", Font.PLAIN, 20));
    cancelButton.addActionListener(evt -> setVisible(false));
    cancelButton.addActionListener(evt -> gui.clearCanvas());
    cancelButton.setPreferredSize(new Dimension(180, 50));
    // set default enter key button
    this.getRootPane().setDefaultButton(cancelButton);
    // buttons
    JPanel buttons;
    buttons = new JPanel();
    buttons.setLayout(new BorderLayout());
    buttons.add(prompt, BorderLayout.PAGE_START);
    buttons.add(cancelButton, BorderLayout.LINE_START);
    buttons.add(acceptButton, BorderLayout.LINE_END);
    buttons.setBorder(new EmptyBorder(0, 60, 40, 60));

    // preview
    imagePreview = new JLabel();

    // ADD TO MAIN FRAME
    this.setLayout(new BorderLayout());
    this.add(buttons, BorderLayout.PAGE_END);
    this.add(imagePreview, BorderLayout.CENTER);
    pack();
  }

  /**
   * Shows the current preview image.
   *
   * @param preview a buffered image
   */
  public void show(BufferedImage preview) {
    Icon image;
    image = new ImageIcon(preview);
    imagePreview.setIcon(image);
    this.pack();
    setVisible(true);
  }
}
