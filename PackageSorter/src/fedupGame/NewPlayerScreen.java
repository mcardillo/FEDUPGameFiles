package fedupGame;

/*********************************************************
 * This class controls the main game window.
 * 
 * @author Max Cardillo 
 * 
 * 
 * Briana Moore and Laura Riffo wrote the text.
 **********************************************************/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewPlayerScreen {

	private JFrame frame;
	private JTextArea title;
	private JTextArea area;
	private JLabel label1;
	private JTextField line;
	private JButton submit;
	private JPanel panelDown;
	boolean choice = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewPlayerScreen window = new NewPlayerScreen(CallbackListener call);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}

	/**
	 * Create the application.
	 */
	public NewPlayerScreen(CallbackListener call) {
		initialize(call);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CallbackListener call) {
		frame = new JFrame("Player Setup");
		frame.setLayout(new BorderLayout());
		Toolkit tk = Toolkit.getDefaultToolkit();
		int width = (int)tk.getScreenSize().getWidth();
		int length = (int)tk.getScreenSize().getHeight();
		frame.setSize(width, length - 50);
		//frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		title = new JTextArea("\t         DIRECTIONS");
		title.setEditable(false);
		title.setFont(new Font("Rockwell", 1, 35));
		title.setBackground(new Color(252, 161, 118));
		frame.add(title, BorderLayout.PAGE_START);
		
		area = new JTextArea("\n\nIf you feel that the package is safe to ship, then click the SHIP button, " + 
				"if you feel that the \npackage is unfit to ship, click the DON’T SHIP button. Your points " + 
				"will be based on the choices \nyou make. You will receive 1 point for each correct decision " + 
				"to ship and for every wrong decision, \nyour supervisor will give you 1 citation. A citation " + 
				"will be given to management at the end of your \namount of packages. If you have too many " + 
				"citations, you will be fired and lose the game. If you \nmake it through your packages with " + 
				"less than 5 citations, and you receive 10 points, you will \nwin the game. Just a warning to " + 
				"you as the new employee, beware of the packages that you \nchoose to ship, sometimes the package " + 
				"is more than it appears to be and if you ship something \ndangerous, bad things could happen not " + 
				"only to you but to our great country of America.");
		area.setEditable(false);
		area.setLineWrap(true);
		area.setFont(new Font("Times New Roman", 1, 23));
		area.setMargin(new Insets(10, 10, 10, 10));
		area.setBackground(new Color(252, 161, 118));
		frame.add(area, BorderLayout.CENTER);
		
		Font g = new Font("Arial", 1, 20);
		Dimension h = new Dimension(300, 100);
		panelDown = new JPanel(new FlowLayout());
		panelDown.setBackground(new Color(252, 161, 118));
		label1 = new JLabel("Please enter your name:");
		label1.setPreferredSize(h);
		label1.setFont(g);
		
		line = new JTextField(10);
		line.setPreferredSize(new Dimension(100, 40));
		line.setFont(g);
		
		submit = new JButton("Submit");
		submit.setPreferredSize(h);
		submit.setFont(g);
		
		panelDown.add(label1);
		panelDown.add(line);
		panelDown.add(submit);
		frame.add(panelDown, BorderLayout.PAGE_END);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				choice = false;
				String text = line.getText();
				if(text.length() == 0)
					call.retrieveName("SVETLANA");
				else
					call.retrieveName(text);
				
				makeInvis();
			}
		});
	}
	
	protected boolean getChoice(){
		return choice;
	}
	
	protected void makeInvis(){
		frame.setVisible(false);
	}

}
