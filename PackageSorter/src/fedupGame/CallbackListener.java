package fedupGame;

/*********************************************************
 * This is the CallbackListener class.
 * 
 * @author Max Cardillo
 * 
 * Briana Moore, Laura Riffo 
 *
 **********************************************************/
interface CallbackListener {

		Package nextPackage();
		Player passPlayer();
		boolean checkStack();
		void retrieveName(String name);
		void saveGame();
		
}
