package imageview;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * This is a setting window for the mosaic filter.
 */
public class MosaicPopup extends JFrame {

  private GeneralListener listener;
  private int seeds;
  private JLabel prompt;
  private JSpinner spinner;
  private JButton accept;
  private JButton cancel;
  private ImageGui gui;

  MosaicPopup(ImageGui gui, String caption, GeneralListener listener) {
    super(caption);
    this.listener = listener;
    this.gui = gui;
    this.setSize(800, 500);
    this.setLocation(400, 400);
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    this.seeds = 0;
    //components
    prompt = new JLabel("Please enter the number of seeds:");
    prompt.setPreferredSize(new Dimension(600, 90));
    prompt.setFont(gui.defaultFont);
    prompt.setHorizontalAlignment(SwingConstants.CENTER);

    spinner = new JSpinner();
    spinner.setBackground(new Color(222, 222, 222));
    spinner.setMaximumSize(new Dimension(140, 160));
    spinner.setFont(gui.defaultFont);
    spinner.setModel(new SpinnerNumberModel(1, 0, 9999999, 1));

    accept = new JButton("Accept");
    accept.setPreferredSize(new Dimension(180, 50));
    accept.setFont(gui.buttonFont);

    cancel = new JButton("Cancel");
    cancel.setPreferredSize(new Dimension(180, 50));
    cancel.setFont(gui.buttonFont);


    // Text panel
    JPanel textPanel;
    textPanel = new JPanel();
    textPanel.setLayout(new FlowLayout());
    textPanel.add(spinner);
    textPanel.setBorder(new EmptyBorder(60, 0, 60, 0));

    // buttons panel
    JPanel options;
    options = new JPanel();
    options.setLayout(new FlowLayout());
    options.add(accept);
    options.add(cancel);
    options.setBorder(new EmptyBorder(50, 0, 50, 0));


    // Add to UI
    this.add(textPanel, BorderLayout.CENTER);
    this.add(prompt, BorderLayout.PAGE_START);
    this.add(options, BorderLayout.PAGE_END);

    // Add listeners
    cancel.addActionListener(evt -> setVisible(false));
    accept.addActionListener(listener);
    accept.addActionListener(evt -> setValue());
    accept.setActionCommand("mosaic-send");

    // set visible false
    setVisible(false);
  }

  public void openPopup() {
    setVisible(true);
  }

  private void setValue() {
    this.seeds = (int) spinner.getValue();
    setVisible(false);
  }

  public int getValue() {
    return this.seeds;
  }
}
