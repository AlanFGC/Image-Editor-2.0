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
  private JMenuBar bar;
  private JMenuItem loadTop;
  private JMenuItem saveTop;
  private JMenuItem exitTop;
  private JMenu fileMenu;
  // Image area
  private JScrollPane imagePane;
  private JLabel imageLabel;
  private JFileChooser fileChooser;
  // sidebar
  private JPanel sideBar;
  private JButton blur;
  private JButton sharpen;
  private JButton grayscale;
  private JButton sepia;
  private JButton dither;
  private JPanel mosaicPanel;
  private JButton mosaic;
  //default Font
  private Font defaultFont;
  private Font buttonFont;


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
    this.setSize(1200, 800);
    this.setLocation(400,400);
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    //menu bar
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
    imagePane = new JScrollPane(imageLabel);

    // sidebar
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
    mosaicPanel = new JPanel();
    mosaicPanel.setPreferredSize(new Dimension(300, 300));
    mosaic = new JButton("Mosaic");
    mosaic.setPreferredSize(buttonSize);
    mosaic.setFont(buttonFont);
    mosaic.setActionCommand("mosaic-open-menu");
    JLabel seedLabel;
    seedLabel = new JLabel("Seeds:");
    JTextField seedText;
    seedText = new JTextField("10");
    mosaicPanel.add(mosaic);
    mosaicPanel.add(seedLabel);
    mosaicPanel.add(seedText);

    //ADD BUTTONS TO Sidebar
    sideBar.add(blur);
    sideBar.add(sharpen);
    sideBar.add(grayscale);
    sideBar.add(sepia);
    sideBar.add(dither);
    sideBar.add(mosaicPanel);

    //Add all components to frame
    this.setJMenuBar(bar);
    this.add(imagePane, BorderLayout.CENTER);
    this.add(sideBar, BorderLayout.LINE_END);
    setVisible(true);
  }

  public void blur(){
    controller.blur();
  }

  public void sharpen(){
    controller.sharpen();
  }

  public void grayscale(){
    controller.grayscale();
  }

  public void sepia(){
    controller.sepia();
  }

  public void dither(){
    controller.dither();
  }

  public void mosaic(int seeds){
    controller.mosaic(seeds);
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
      ImageIcon icon;
      icon = new ImageIcon(image);
      this.imageLabel.setIcon(icon);
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
   * Displays a give message to the user.
   *
   * @param msg a message in string form.
   */
  @Override
  public void displayMessage(String msg) {

  }

  public void exitProgram(){
    controller.exitProgram();
  }
}
