package fedupGame;

/*********************************************************
 * This class controls the main game window.
 * 
 * @author Max Cardillo 
 * 
 * The Easter Egg code was completed by Laura Riffo.
 * 
 * Briana Moore and Laura Riffo wrote the text.
 **********************************************************/
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GameEndWindow {

	private JFrame frame;
	boolean choice = true;
	private JTextArea label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameEndWindow window = new GameEndWindow(false, true, false);
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
	public GameEndWindow(boolean flag, boolean eeFlag, boolean eeEnd) {
		initialize(flag, eeFlag, eeEnd);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean flag, boolean eeFlag, boolean eeEnd) {
		frame = new JFrame("GAME OVER");
		frame.setBounds(550, 200, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		if(eeFlag){
			if(eeEnd)
				label = new JTextArea("Bad decision! Since you decided to ship the package, the \npackage contained " + 
						"a bomb that was sent to the White House. \nThe bomb exploded, killing thousands of people " + 
						"and \ndestroying much of Washington D.C. As a result, the terrorist \norganization thanks " + 
						"you for your service and have given you \nmembership to their organization and as a bonus, " + 
						"have spared \nyour life. On a bad note, you have been fired from the post \noffice and " + 
						"have therefore lost the game. Thanks for playing.");
			else
				label = new JTextArea("Bad decision! Since you rejected shipment of the package, \nwhich contained a " + 
						"bomb planted by a terrorist organization, the \nterrorist organization has kidnapped you from " + 
						"your job and \ndecides to interrogate you. Since you did not obey, the \norganization tortured " + 
						"you for a week then proceeded to \nmurder you. While you kept your job at the post office and \n" + 
						"saved your country, you lost your life and the game. \nThanks for playing.");
		}
		else{
			if(flag)
				label = new JTextArea("You win! You have become a professional at post office \nmanagement and have used your " + 
						"judgment to the best of your\n ability. As a result, the United States government has \nrequested " + 
						"that you be promoted to checking packages \nthat enter the White House. Good luck in your future \n" + 
						"endeavors and thank you for playing!");
			else
				label = new JTextArea("You lose! Your careless decisions and poor judgement \nhave caused you too many citations. " + 
						"Your supervisor decided \nthat you are unfit for the job and not worth the \nrisk to the post office. " + 
						"Sorry but thanks for playing.");
		}
		label.setFont(new Font("Times New Roman", 1, 20));
		label.setEditable(false);
		label.setLineWrap(true);
		label.setBackground(new Color(252, 161, 118));
		frame.add(label);
	}
	
	protected boolean getChoice(){
		return choice;
	}
	
	protected void makeInvis(){
		frame.setVisible(false);
	}
	
	protected void end(){
		frame.dispose();
	}

}
