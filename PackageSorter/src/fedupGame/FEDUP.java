package fedupGame;

/*********************************************************
 * This is the main driver for the game.
 * 
 * @author Max Cardillo
 * 
 * Briana Moore, Laura Riffo 
 *
 **********************************************************/
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
