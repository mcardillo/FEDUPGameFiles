package fedupGame;

public class FEDUP {
	public static void main(String[] args) throws Exception {
		GameV2 fedup = new GameV2();
		boolean a = fedup.play();
		
		GameEndWindow finale = new GameEndWindow(a, fedup.eeAppeared(), fedup.eeChoose());
		while(finale.getChoice()){
			System.out.flush();
		}
		finale.end();
	}
}

//system.getProperty(userdir);
//JFileChooser?

/***********************************
 *           TO DO LIST            *
 *                                 *
 *           1. Images             *
 *    2. Finish adjusting text     *
 *       -ManualWindow             *
 *       -MainGameWindow           *
 *       -Boxes descriptors        *
 *       -Employee Titles          *
 *     3. Adjust window sizes      *
 *       -MainGameWindow           *
 *       -NewPlayerScreen          *
 *                                 *
 *                                 *
 ***********************************/
