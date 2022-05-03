package imageview;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;

import javax.swing.*;

/**
 * This is the graphical user interface view for the Image Editor.
 * It's composed mainly by just one window that displays options,
 * current image, and any message that needs to be displayed to the
 * user.
 */
public class ImageGui extends JFrame implements ImageViewGuiInt {

  //controller
  Features controller;
  //default Font
  public Font defaultFont;
  public Font buttonFont;
  //Event Listener
  private ActionListener actListener;
  private KeyListener keyListener;
  // Top Menu:
  private JMenuItem loadTop;
  private JMenuItem saveTop;
  private JMenuItem runScript;
  private JMenuItem cropImage;
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
  private JButton sobel;
  private JButton equalize;

  //mosaic
  private MosaicPopup mosaicPopup;
  private MessagePopUp messagePopUp;
  // Crop preview
  private CropPreview preview;

  // crop image
  private Point selectStart;
  private Point selectEnd;
  private boolean croppingImage;

  /**
   * Creates the ui and all of its components.
   */
  public ImageGui(Features controller) {
    super("Image Editor");
    this.controller = controller;
    //Set listener
    croppingImage = false;
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


    // crop menu
    JMenu cropMenu;
    cropMenu = new JMenu("Crop");
    cropMenu.setPreferredSize(new Dimension(80, 70));
    cropMenu.setFont(defaultFont);
    cropMenu.setVisible(true);
    cropImage = new JMenuItem("Crop Image");
    cropImage.setActionCommand("crop-image");
    cropImage.setFont(defaultFont);
    cropMenu.add(cropImage);


    // ADD ITEMS TO MENU BAR;
    bar.add(fileMenu);
    bar.add(cropMenu);


    //create image pane
    ImageIcon tempImage;
    //this needs to be defined in a different way, doesn't work with compiled version
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
    // edge panel
    sobel = new JButton("Sobel");
    sobel.setPreferredSize(buttonSize);
    sobel.setFont(buttonFont);
    sobel.setActionCommand("sobel-image");

    // equalize panel
    equalize = new JButton("Equalize B&W");
    equalize.setPreferredSize(buttonSize);
    equalize.setFont(buttonFont);
    equalize.setActionCommand("equalize-image");

    //preview window
    preview = new CropPreview(this);

    //ADD BUTTONS TO Sidebar
    sideBar.add(blur);
    sideBar.add(sharpen);
    sideBar.add(grayscale);
    sideBar.add(sepia);
    sideBar.add(dither);
    sideBar.add(mosaic);
    sideBar.add(sobel);
    sideBar.add(equalize);

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
      clearCanvas();
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
   * Updates current image.
   */
  @Override
  public void updateImage(BufferedImage image) {
    if (image != null) {
      ImageIcon icon;
      icon = new ImageIcon(image);
      imageLabel.setIcon(icon);
      imageLabel.setVerticalAlignment(SwingConstants.TOP);
      imageLabel.setHorizontalAlignment(SwingConstants.LEFT);
      pack();
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
   * Resets focus to the main JFrame.
   */
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }


  /**
   * This method sets-up all action listeners necessary.
   *
   * @param actListener ActionListener Object.
   * @param keyListener KeyListener Object.
   */
  @Override
  public void setController(ActionListener actListener, KeyListener keyListener) {
    this.actListener = actListener;
    this.keyListener = keyListener;

    //PopUP window
    mosaicPopup = new MosaicPopup(this, "Mosaic Settings", actListener);
    //Buttons
    loadTop.addActionListener(actListener);
    saveTop.addActionListener(actListener);
    runScript.addActionListener(actListener);
    exitTop.addActionListener(actListener);
    cropImage.addActionListener(ev -> {
      croppingImage = true;
      displayMessage("please select an area to crop");
    });

    blur.addActionListener(actListener);
    sharpen.addActionListener(actListener);
    grayscale.addActionListener(actListener);
    sepia.addActionListener(actListener);
    dither.addActionListener(actListener);
    mosaic.addActionListener(actListener);
    sobel.addActionListener(actListener);
    equalize.addActionListener(actListener);
    //Key Strokes
    this.addKeyListener(keyListener);
    //mouse
    imageLabel.addMouseListener(new MouseListener() {
      private Point start;
      private Point end;


      @Override
      public void mouseClicked(MouseEvent e) {
        start = null;
        end = null;
        imageLabel.repaint();
      }

      @Override
      public void mousePressed(MouseEvent e) {
        start = new Point(e.getX(), e.getY());
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        end = new Point(e.getX(), e.getY());
        if (end != null && start != null && croppingImage) {
          Graphics rect;
          selectStart = start;
          selectEnd = end;
          rect = imageLabel.getGraphics();
          rect.setColor(new Color(250, 0, 0));
          rect.drawRect(start.x, start.y, Math.abs(end.x - start.x), Math.abs(end.y - start.x));
          imageLabel.paintComponents(rect);
          croppingImage = false;
          cropMenu();
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }
    });
  }

  /**
   * Starts the crop menu.
   */
  private void cropMenu() {
    BufferedImage current = controller.getImage();
    if (current == null) {
      clearCanvas();
      return;
    }
    try {
      preview.show(current.getSubimage(selectStart.x, selectStart.y
              , Math.abs(selectEnd.x - selectStart.x), Math.abs(selectEnd.y - selectStart.x)));
    } catch (RasterFormatException e) {
      clearCanvas();
      displayMessage("Invalid area or too small");
    }
  }


  /**
   * Starts the process for image cropping.
   */
  @Override
  public void cropImagePopUp() {
    croppingImage = true;
    displayMessage("please select an area to crop");
  }

  /**
   * Crops current image.
   */
  @Override
  public void cropImage() {
    controller.cropImage(selectStart.x, selectStart.y, Math.abs(selectEnd.x - selectStart.x),
            Math.abs(selectEnd.y - selectStart.x));
    pack();
  }

  /**
   * Clears current canvas.
   */
  public void clearCanvas() {
    imageLabel.repaint();
  }
}
