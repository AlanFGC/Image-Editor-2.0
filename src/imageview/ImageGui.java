package imageview;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.*;


/**
 * This is the graphical user interface view for the Image Editor.
 * It's composed mainly by just one window that displays options,
 * current image, and any message that needs to be displayed to the
 * user.
 */
public class ImageGui extends JFrame implements ImageViewGuiInt {
  // field
  private ImageGuiControlInt controller;
  //Event Listener
  private ActionListener actListener;
  private KeyListener keyListener;
  // Top Menu:
  private JMenuItem loadTop;
  private JMenuItem saveTop;
  private JMenuItem runScript;
  private JMenuItem exitTop;
  // Image area
  private JLabel imageLabel;
  private JFileChooser fileChooser;
  // sidebar
  private JButton blur;
  private JButton sharpen;
  private JButton grayscale;
  private JButton sepia;
  private JButton dither;
  private JButton mosaic;
  private MosaicPopup mosaicPopup;
  private MessagePopUp messagePopUp;

  //default Font
  public Font defaultFont;
  public Font buttonFont;


  /**
   * Creates the ui and all its components.
   */
  public ImageGui(String caption) {
    super(caption);

    //Set listener and controller
    controller = null;
    actListener = null;
    keyListener = null;
    //set File chooser
    fileChooser = new JFileChooser(System.getProperty("user.dir"));

    // Fonts
    defaultFont = new Font("sans-serif", Font.PLAIN, 28);
    buttonFont = new Font("sans-serif", Font.PLAIN, 20);

    // message pup up
    messagePopUp = new MessagePopUp(this, "New message");

    //set initial parameters
    this.setSize(1500, 1000);
    this.setLocation(400, 400);
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    //menu bar
    JMenuBar bar;
    bar = new JMenuBar();
    bar.setBorderPainted(true);
    bar.setPreferredSize(new Dimension(250, 50));

    //J menu items
    loadTop = new JMenuItem("Load Image");
    loadTop.setActionCommand("load-image");
    loadTop.setFont(defaultFont);
    saveTop = new JMenuItem("Save Image");
    saveTop.setActionCommand("save-image");
    saveTop.setFont(defaultFont);
    runScript = new JMenuItem("Load Script");
    runScript.setActionCommand("run-script");
    runScript.setFont(defaultFont);
    exitTop = new JMenuItem("Exit Program");
    exitTop.setActionCommand("exit-program");
    exitTop.setFont(defaultFont);

    //fileMenu
    JMenu fileMenu;
    fileMenu = new JMenu("File");
    fileMenu.setPreferredSize(new Dimension(70, 70));
    fileMenu.setFont(defaultFont);
    fileMenu.setVisible(true);
    fileMenu.add(loadTop);
    fileMenu.add(saveTop);
    fileMenu.add(runScript);
    fileMenu.add(exitTop);

    // ADD ITEMS TO MENU BAR;
    bar.add(fileMenu);

    //create image pane
    ImageIcon tempImage;
    tempImage = new ImageIcon("res/no-image.png");
    imageLabel = new JLabel(tempImage);
    JScrollPane imagePane;
    imagePane = new JScrollPane(imageLabel);

    // sidebar
    JPanel sideBar;
    sideBar = new JPanel(new FlowLayout());
    sideBar.setBackground(new Color(255, 255, 255));
    sideBar.setPreferredSize(new Dimension(300, 600));
    sideBar.setMaximumSize(new Dimension(300, 600));

    //SideBar buttons
    Dimension buttonSize;
    buttonSize = new Dimension(180, 50);
    //BUTTONS
    blur = new JButton("Blur effect");
    blur.setPreferredSize(buttonSize);
    blur.setFont(buttonFont);
    blur.setActionCommand("blur-image");
    // sharpen
    sharpen = new JButton("Sharpen");
    sharpen.setPreferredSize(buttonSize);
    sharpen.setFont(buttonFont);
    sharpen.setActionCommand("sharpen-image");
    // grayscale
    grayscale = new JButton("Grayscale");
    grayscale.setPreferredSize(buttonSize);
    grayscale.setFont(buttonFont);
    grayscale.setActionCommand("grayscale-image");
    // sepia
    sepia = new JButton("Sepia");
    sepia.setPreferredSize(buttonSize);
    sepia.setFont(buttonFont);
    sepia.setActionCommand("sepia-image");
    // dither
    dither = new JButton("Dither");
    dither.setPreferredSize(buttonSize);
    dither.setFont(buttonFont);
    dither.setActionCommand("dither-image");
    // mosaic panel
    mosaic = new JButton("Mosaic");
    mosaic.setPreferredSize(buttonSize);
    mosaic.setFont(buttonFont);
    mosaic.setActionCommand("mosaic-open-menu");
    mosaicPopup = null;

    //ADD BUTTONS TO Sidebar
    sideBar.add(blur);
    sideBar.add(sharpen);
    sideBar.add(grayscale);
    sideBar.add(sepia);
    sideBar.add(dither);
    sideBar.add(mosaic);

    //Add all components to frame
    this.setJMenuBar(bar);
    this.add(imagePane, BorderLayout.CENTER);
    this.add(sideBar, BorderLayout.LINE_END);
    setVisible(true);
  }


  /**
   * Opens the pop-up menu for the mosaic filter.
   */
  @Override
  public void mosaicMenu() {
    mosaicPopup.requestFocus();
    mosaicPopup.openPopup();
  }


  /**
   * Sends back the value from mosaic popup
   * window.
   *
   * @return how many seeds are needed for the filter.
   */
  @Override
  public int mosaicGetValue() {
    return mosaicPopup.getValue();
  }

  /**
   * Tells the controller which image should load.
   *
   * @return path of the selected file.
   */
  @Override
  public String getImagePathLoad() {
    int result;
    fileChooser.setDialogTitle("Load a new image");
    result = fileChooser.showOpenDialog(this);
    if (result == fileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().toString();
    }
    return null;
  }


  /**
   * Tells the controller which script should load.
   *
   * @return path of the selected file.
   */
  @Override
  public String getScriptPath() {
    int result;
    fileChooser.setDialogTitle("Load a new script");
    result = fileChooser.showOpenDialog(this);
    if (result == fileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().toString();
    }
    return null;
  }

  /**
   * Tells the controller the current path.
   *
   * @return path to save image.
   */
  @Override
  public String getImagePathSave() {
    int result;
    fileChooser.setDialogTitle("Save current image");
    result = fileChooser.showOpenDialog(this);
    if (result == fileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().toString();
    }
    return null;
  }

  /**
   * This method tells the controller to update the current image.
   */
  @Override
  public void updateImage(BufferedImage image) {
    if (image != null) {
      ImageIcon icon;
      icon = new ImageIcon(image);
      this.imageLabel.setIcon(icon);
    }
  }

  /**
   * Displays a give message to the user.
   *
   * @param msg a message in string form.
   */
  @Override
  public void displayMessage(String msg) {
    messagePopUp.requestFocus();
    this.messagePopUp.displayMessage(msg);
  }

  /**
   * Resets focus to main JFrame.
   */
  @Override
  public void resetFocus(){
    this.setFocusable(true);
    this.requestFocus();
  }

  /**
   * This method sets-up the controller and all action listeners necessary.
   *
   * @param controller  controller of the mvc application.
   * @param actListener ActionListener Object.
   * @param keyListener KeyListener Object.
   */
  @Override
  public void setController(ImageGuiControlInt controller, ActionListener actListener,
                            KeyListener keyListener) {
    this.controller = controller;
    this.actListener = actListener;
    this.keyListener = keyListener;

    //PopUP window
    mosaicPopup = new MosaicPopup(this, "Mosaic Settings", actListener);
    //Buttons
    loadTop.addActionListener(actListener);
    saveTop.addActionListener(actListener);
    runScript.addActionListener(actListener);
    exitTop.addActionListener(actListener);
    blur.addActionListener(actListener);
    sharpen.addActionListener(actListener);
    grayscale.addActionListener(actListener);
    sepia.addActionListener(actListener);
    dither.addActionListener(actListener);
    mosaic.addActionListener(actListener);
    //Key Strokes
    this.addKeyListener(keyListener);
  }
}
