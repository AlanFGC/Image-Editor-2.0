package imageview;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;


/**
 * This is the graphical user interface view for the Image Editor.
 * It's composed mainly by just one window that displays options,
 * current image, and any message that needs to be displayed to the
 * user.
 */
public class ImageGui extends JFrame implements ImageViewGuiInterface {
  // field
  private ImageFeatures controller;
  //Event Listener
  GeneralListener listener;
  // Top Menu:
  private JMenuItem loadTop;
  private JMenuItem saveTop;
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

  //default Font
  public Font defaultFont;
  public Font buttonFont;


  /**
   * Constructor method.
   */
  ImageGui(String caption){
    super(caption);

    //Set listener
    listener = null;
    //set File chooser
    fileChooser = new JFileChooser();

    // Fonts
    defaultFont = new Font("sans-serif", Font.PLAIN, 28);
    buttonFont = new Font("sans-serif", Font.PLAIN, 20);

    //set initial parameters
    this.setSize(1500, 1000);
    this.setLocation(400,400);
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
   * Tells the controller to apply blur filter.
   */
  public void blur(){
    controller.blur();
  }

  /**
   * Tells the controller to apply sharpen filter.
   */
  public void sharpen(){
    controller.sharpen();
  }

  /**
   * Tells the controller to apply grayscale filter.
   */
  public void grayscale(){
    controller.grayscale();
  }

  /**
   * Tells the controller to apply sepia filter.
   */
  public void sepia(){
    controller.sepia();
  }

  /**
   * Tells the controller to apply dither filter.
   */
  public void dither(){
    controller.dither();
  }

  public void mosaicMenu(){
    mosaicPopup.openPopup();
  }


  /**
   * Tells the controller to apply mosaic filter.
   */
  public void mosaic(){
    int seed;
    seed = mosaicPopup.getValue();
    controller.mosaic(seed);
  }

  /**
   * Loads new image using JFileChooser and tells the controller
   * to load that image using path.
   */
  public void loadNewImage(){
    int result;
    result = fileChooser.showOpenDialog(this);
    if (result == fileChooser.APPROVE_OPTION) {
      controller.loadImage(fileChooser.getSelectedFile().toString());
    }
  }

  /**
   * Tells the controller to save current image.
   */
  public void saveImage(){
    int result;
    result = fileChooser.showOpenDialog(this);
    if (result == fileChooser.APPROVE_OPTION) {
      controller.saveImage(fileChooser.getSelectedFile().toString());
    }
  }

  /**
   * This method tells the controller to update the current image.
   */
  public void updateImage(BufferedImage image){
      if (image != null){
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

  }


  /**
   * This method assigns all controller to the current model.
   *
   * @param features a controller that implements all the features listed.
   */
  @Override
  public void addFeatures(ImageFeatures features) {
    this.controller = features;
    listener = new GeneralListener(this);
    //PopUP window
    mosaicPopup = new MosaicPopup(this, "Mosaic Settings", listener);
    //Buttons
    loadTop.addActionListener(listener);
    saveTop.addActionListener(listener);
    exitTop.addActionListener(listener);
    blur.addActionListener(listener);
    sharpen.addActionListener(listener);
    grayscale.addActionListener(listener);
    sepia.addActionListener(listener);
    dither.addActionListener(listener);
    mosaic.addActionListener(listener);
  }

  /**
   * Tells the controller to exit the program.
   */
  public void exitProgram(){
    controller.exitProgram();
  }
}
