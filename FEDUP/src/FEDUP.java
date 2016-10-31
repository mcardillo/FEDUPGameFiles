public class FEDUP {
	public static void main(String[] args) throws Exception {
		Game fedup = new Game();
		boolean a = fedup.play();
		if(a == false)
			System.out.println("You Lose!");
		else
			System.out.println("You Win!");
	}
}
