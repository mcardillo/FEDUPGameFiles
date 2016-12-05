package fedupGame;

/*********************************************************
 * This class creates the manual.
 * 
 * @author Max Cardillo 
 * 
 * 
 * Briana Moore wrote the manual text.
 * (retrieved from http://www.nccu.edu/health-safety/emergency/suspiciousmail.cfm)
 * 
 * Laura Riffo
 **********************************************************/
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ManuelWindow {

	private JFrame frame;
	private JScrollPane pane;
	private JTextArea area;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManuelWindow window = new ManuelWindow();
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
	public ManuelWindow(String name) {
		initialize(name);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String name) {
		frame = new JFrame(name + "'s Package Manuel");
		frame.setLayout(new FlowLayout());
		frame.setBounds(900, 300, 600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		area = new JTextArea("A package will pass if the following conditions are met:\n" + 
						"   •The weight is under 150 pounds.\n" + 
						"   •The postage is present.\n" + 
						"   •The price on the postage matches the amount of postage.\n\n" +
						"A package will not pass under the following conditions:\n" +
						"   •The look, sound, or sensations of the package may indicate the presence of a bomb.\n" +
						"   •The smell emitting from the package or labeling could indicate a possible toxic gas.\n" +
						"   •A strangle liquid is exiting the package or the package has remains of a mysterious liquid.\n" + 
						"   •Animals are prohibited from shipping from this warehouse.\n" +
						"   •The package is wet, frozen, melting, or unfit for shipping.\n" +
						"   •The Postal Service guidelines for packages are violated, which include:\n" +
						"      oExcessive postage\n" +
						"      oMisspellings of common words\n" +
						"      oExcessive weight\n" +
						"      oRigid envelope\n" +
						"      oArrival via foreign mail or some form of expedited delivery\n" +
						"      oHandwritten or poorly typed address\n" +
						"      oRestrictive markings such as 'confidential' or 'personal'\n" +
						"      oAn excessive amount of securing material used, such as masking tape or string\n" +
						"      oIncorrect titles\n" +
						"      oOily stains or discolorations\n" +
						"      oVisual distractions\n" +
						"      oLopsided or uneven writing\n" +
						"      oTitles but no names\n" +
						"      oNo return address\n" +
						"      oProtruding wires or foil\n" +
						"	•Possible indication of a poisonous powder such as Anthrax.\n" +
						"	•The labeling is very suspicious.\n" +
						"	•The package indicates that it is possibly flammable.");
		area.setEditable(false);
		
		pane = new JScrollPane(area);
		frame.add(pane);
	}

}
