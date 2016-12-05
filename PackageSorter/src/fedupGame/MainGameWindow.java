package fedupGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainGameWindow {

	private JFrame frame;
	private JTextArea packDesc;
	private JTextArea points;
	private JTextArea citations;
	private JPanel panelSouth;
	private JPanel panelWest;
	private JPanel panelCenter;
	private JPanel panelNorth;
	private JPanel innerPanelNorth;
	private JLabel box;
	private JMenuItem saver;
	private JMenu list;
	private JMenuBar bar;
	private JLabel levelText;
	public boolean choice;
	public boolean eeAppear = false;
	public boolean eeChoice;
	Package p;
	Player user;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameWindow window = new MainGameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public MainGameWindow(CallbackListener call) {
		initialize(call);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CallbackListener call) {
		user = call.passPlayer();
		frame = new JFrame("FedUP - Level " + user.getLevel() + " - " + getTitle() +  " " + user.getPlayerName());
		frame.setLayout(new BorderLayout());
		Toolkit tk = Toolkit.getDefaultToolkit();
		int width = (int)tk.getScreenSize().getWidth();
		int length = (int)tk.getScreenSize().getHeight();
		frame.setSize(width, length - 50);
		//frame.setBounds(650, 300, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelSouth = new JPanel(new FlowLayout());
		panelWest = new JPanel();
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.PAGE_AXIS));
		panelNorth = new JPanel(new BorderLayout());
		panelCenter = new JPanel(new FlowLayout());
		innerPanelNorth = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		bar = new JMenuBar();
		list = new JMenu("File");
		saver = new JMenuItem("Save");
		innerPanelNorth.add(bar);
		
		if(user.getLevel() == 3)
			levelText = new JLabel("Level 3 - You need to correctly sort 20 packages");
		else if(user.getLevel() == 2)
			levelText = new JLabel("Level 2 - You need to correctly sort 15 packages");
		else
			levelText = new JLabel("Level 1 - You need to correctly sort 10 packages");
		innerPanelNorth.add(levelText);
		panelNorth.add(innerPanelNorth, BorderLayout.PAGE_START);
		
		
		bar.add(list);
		list.add(saver);
		saver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					FileOutputStream outStream = new FileOutputStream("SavedGame.txt");    
					ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream); 

					objectOutputFile.writeObject(user);     
		        
					objectOutputFile.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		p = call.nextPackage();
		
		JButton pass = new JButton("Ship");
		JButton fail = new JButton("Don't Ship");
		JButton manual = new JButton("Manual");
		
		Dimension h = new Dimension(300, 80);
		Font g = new Font("Arial", 1, 20);
		pass.setPreferredSize(h);
		pass.setFont(g);
		fail.setPreferredSize(h);
		fail.setFont(g);
		manual.setPreferredSize(h);
		manual.setFont(g);
		
		if(p.getEgg()){
			packDesc = new JTextArea("The package weighs " + p.getWeight() + " lbs.\nThe package has " + p.getPostage() + " postage.\n" 
				+ "The package is " + p.getDescription() + ".\nThe return address is the " + p.getSender() + ".");
			packDesc.setEditable(false);
			packDesc.setFont(new Font("Times New Roman", 1, 30));
			packDesc.setBackground(new Color(252, 161, 118));
		}
		else{
			packDesc = new JTextArea("The package weighs " + p.getWeight() + " lbs.\nThe package has " + p.getPostage() + " postage.\n" 
					+ "The package is " + p.getDescription() + ".");
			packDesc.setEditable(false);
			packDesc.setFont(new Font("Times New Roman", 1, 30));
			packDesc.setBackground(new Color(252, 161, 118));
		}
		
		panelCenter.add(packDesc);
		frame.add(panelCenter, BorderLayout.CENTER);
		
		//manual button setup
		panelSouth.add(manual);
		manual.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new ManuelWindow(user.getPlayerName());
			}
		});
		
		//pass button setup
		panelSouth.add(pass);
		pass.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(p.getEgg()){
					eeChoice = true;
					eeAppear = true;
				}
				else{
					if(p.getStatus() == true)
						user.addPoints();
					if(p.getStatus() == false)
						user.addCitation();
					if(call.checkStack())
						p = call.nextPackage();
					else{
						frame.dispose();
					}
					updateText();
				}
			}
		});
		
		//fail button setup
		panelSouth.add(fail);
		fail.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(p.getEgg()){
					eeChoice = false;
					eeAppear = true;
				}
				else{
					if(p.getStatus() == true)
						user.addCitation();
					if(p.getStatus() == false)
						user.addPoints();
					if(call.checkStack())
						p = call.nextPackage();
					else{
						frame.dispose();
					}	
					updateText();
				}
			}
		});
		
		frame.add(panelSouth, BorderLayout.PAGE_END);
		
		points = new JTextArea("Current points: " + user.getPoints());
		points.setEditable(false);
		points.setFont(new Font("Times New Roman", 1, 20));
		points.setBackground(new Color(252, 161, 118));
		panelWest.add(points);
		
		citations = new JTextArea("Current citations: " + user.getCitations());
		citations.setEditable(false);
		citations.setFont(new Font("Times New Roman", 1, 20));
		citations.setBackground(new Color(252, 161, 118));
		panelWest.add(citations);
		frame.add(panelWest, BorderLayout.LINE_START);
		
		try {
			BufferedImage boxPic = ImageIO.read(new File("C:/Users/Josh/git/FEDUP/PackageSorter/package2.png"));
			ImageIcon pkgPic = new ImageIcon(boxPic);
			Image pic = pkgPic.getImage();
			Image newPic = pic.getScaledInstance(320, 220, java.awt.Image.SCALE_SMOOTH);
			pkgPic = new ImageIcon(newPic);
			box = new JLabel(pkgPic);
			panelNorth.add(box, BorderLayout.CENTER);
			frame.add(panelNorth, BorderLayout.PAGE_START);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		innerPanelNorth.setBackground(new Color(252, 161, 118));
		panelNorth.setBackground(new Color(252, 161, 118));
		panelCenter.setBackground(new Color(252, 161, 118));
		panelWest.setBackground(new Color(252, 161, 118));
		panelSouth.setBackground(new Color(252, 161, 118));
		
	}
	
	private void updateText(){
		panelWest.removeAll();
		
		points.setText("Current points: " + user.getPoints());
		//points.setEditable(false);
		//points.setFont(new Font("Times New Roman", 1, 20));
		//points.setBackground(new Color(252, 161, 118));
		panelWest.add(points);
		
		citations.setText("Current citations: " + user.getCitations());
		//citations.setEditable(false);
		//citations.setFont(new Font("Times New Roman", 1, 20));
		//citations.setBackground(new Color(252, 161, 118));
		panelWest.add(citations);
		
		panelWest.repaint();
		
		panelCenter.removeAll();
		
		if(p.getEgg()){
			packDesc.setText("The package weighs " + p.getWeight() + " lbs.\nThe package has " + p.getPostage() + " postage.\n" 
				+ "The package is " + p.getDescription() + ".\nThe return address is the " + p.getSender() + ".");
			//packDesc.setEditable(false);
			//packDesc.setFont(new Font("Times New Roman", 1, 30));
			//packDesc.setBackground(new Color(252, 161, 118));
		}
		else{
			packDesc.setText("The package weighs " + p.getWeight() + " lbs.\nThe package has " + p.getPostage() + " postage.\n" 
					+ "The package is " + p.getDescription() + ".");
			//packDesc.setEditable(false);
			//packDesc.setFont(new Font("Times New Roman", 1, 30));
			//packDesc.setBackground(new Color(252, 161, 118));
		}
		panelCenter.add(packDesc);
		
		panelCenter.repaint();
	}
	
	protected void feedPackage(Package p){
		this.p = p;
	}
	
	protected void makeInvis(){
		frame.setVisible(false);
	}
	
	protected void makeVis(){
		frame.setVisible(true);
	}
	
	public String getTitle(){
		String title;
		if(user.getLevel() == 3)
			title = "Chief Package Inspector";
		else if(user.getLevel() == 2)
			title = "Salaried Examining Robot";
		else
			title = "Intern";
		return title;
	}
	
	public boolean eeAppears(){
		return eeAppear;
	}
	
	public boolean eeChoosen(){
		return eeChoice;
	}

}
