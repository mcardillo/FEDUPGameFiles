package fedupGame;

/*********************************************************
 * This creates the first screen that appears when the game
 * is played.
 * 
 * @author Max Cardillo
 * 
 * Briana Moore wrote the code for the load game method
 * Briana Moore and Laura Riffo wrote the text to appear. 
 *
 **********************************************************/
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameEnterScreen {

	private JFrame frame;
	private JTextArea label1;
	private JTextArea titleLabel;
	private JPanel panelSouth;
	private JPanel panelCenter;
	private JPanel panelNorth;
	private JLabel truck;
	boolean choice = true;
	boolean load = true;
	Player user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameEnterScreen window = new GameEnterScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameEnterScreen() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("FedUP GUI Alpha");
		frame.setLayout(new BorderLayout());
		//(x-axis, y-axis, width, length)
		Toolkit tk = Toolkit.getDefaultToolkit();
		int width = (int)tk.getScreenSize().getWidth();
		int length = (int)tk.getScreenSize().getHeight();
		frame.setSize(width, length - 50);
		//frame.setBounds(550, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JButton newGameButton = new JButton("New Game");
		JButton loadGameButton = new JButton("Load Game");
		panelSouth = new JPanel(new FlowLayout());
		panelCenter = new JPanel();
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.PAGE_AXIS));
		panelNorth = new JPanel();
		
		titleLabel = new JTextArea("WELCOME TO FEDUP!");
		titleLabel.setEditable(false);
		titleLabel.setFont(new Font("Rockwell", 1, 35));
		titleLabel.setBackground(new Color(252, 161, 118));
		panelNorth.add(titleLabel);
		label1 = new JTextArea("It is the year 3058 and the companies FedEx and UPS have now merged into the shipping \ncompany FEDUP." + 
				" We are located in Moab, Utah as we are trying to bring more jobs to the \nsmall town of 5,100." + 
				" We have recently built our main warehouse and headquarters in the town \nand you are a brand new employee." + 
				" You are in charge of checking each package and discerning \nwhether it is safe to deliver." + 
				" You will be using specific guidelines, your senses, and moral judgement \nto determine if a package will ship." + 
				" The main characteristics that you will be checking are the \nweight, which is given to you," + 
				" the postage on the package, and finally a brief description describing characteristics of the package.\n" + 
				" The guidelines can be found in the manual button on the screen \nand will be available throughout the game. ");
		label1.setMargin(new Insets(10, 10, 10, 10));
		
		 
		label1.setLineWrap(true);
		label1.setEditable(false);
		label1.setFont(new Font("Times New Roman", 1, 23));
		label1.setColumns(45);
		
		panelCenter.setBackground(new Color(252, 161, 118));
		label1.setBackground(new Color(252, 161, 118));
		
		Dimension h = new Dimension(500, 120);
		loadGameButton.setPreferredSize(h);
		loadGameButton.setFont(new Font("Arial", 1, 20));
		newGameButton.setPreferredSize(h);
		newGameButton.setFont(new Font("Arial", 1, 20));
		panelSouth.add(loadGameButton);
		loadGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				loadGame();
				choice = false;
				makeInvis();
			}
		});
		panelSouth.add(newGameButton);
		
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					choice = false;
					load = false;
					makeInvis();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panelSouth.setBackground(new Color(252, 161, 118));
		
		try {
			BufferedImage truckPic = ImageIO.read(new File("truck.png"));
			ImageIcon trkPic = new ImageIcon(truckPic);
			Image pic = trkPic.getImage();
			Image newPic = pic.getScaledInstance(220,170, java.awt.Image.SCALE_SMOOTH);
			trkPic = new ImageIcon(newPic);
			truck = new JLabel(trkPic);
			panelCenter.add(truck);
			panelNorth.setBackground(new Color(252, 161, 118));
		} catch (IOException e) {
			e.printStackTrace();
		}
		panelCenter.add(label1);
		frame.add(panelCenter, BorderLayout.CENTER);
		frame.add(panelSouth, BorderLayout.PAGE_END);
		frame.add(panelNorth, BorderLayout.PAGE_START);
		
	}
	
	protected boolean getChoice(){
		return choice;
	}
	
	protected boolean loaded(){
		return load;
	}
	
	protected void makeInvis(){
		frame.setVisible(false);
	}
	
	protected void loadGame(){
	
		try {
			FileInputStream inStream = new FileInputStream("SavedGame.txt");
			ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
			
			user = (Player)objectInputFile.readObject();
			
			objectInputFile.close();
		} catch (Exception e) {
			System.out.println("File Not Found or something");
			System.out.println("Continuing on to New Game");
		}
		
	}
	
	protected Player getLoad(){
		return user;
	}

}
