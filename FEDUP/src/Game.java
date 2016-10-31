import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//import java.sql.*;

public class Game {
	
	int points = 0;
	boolean notWin = true;
	private boolean notLose = true;
	//Package packages[] = new Package[10];
	Package p;
	//int length = packages.length;
	//int start = 0;
	boolean playAns = false;
	Scanner kb;
	static final int DB_LENGTH = 3;
	static String weight;
	static String postage;
	static String pass;
	//static int counter = 1;
	//private static int pts = 0;  //Player in game points
	private static int cit = 0; //Player in game citations
	private static String name; //Player name
	Player player;

	//call gui class
	
	public boolean play() throws Exception{
		//call newGameWindow from GUI
		//if new game selected
		
		kb = new Scanner(System.in);
		System.out.println("Enter your name...");
	    name = kb.nextLine();
	    
	    player = new Player(name, points, cit);
	    System.out.println(player.getPlayerName());
	    //System.out.println(", ");
		/*
		packages[0] = new Package(70, "present", true);
		packages[1] = new Package(24, "excessive", false); //labels
		packages[2] = new Package(90, "missing", false); //labels
		packages[3] = new Package(36, "present", true);
		packages[4] = new Package(58, "missing", false); //labels
		packages[5] = new Package(110, "present", false); //too heavy
		packages[6] = new Package(42, "present", true);
		packages[7] = new Package(12, "present", true);
		packages[8] = new Package(31, "excessive", false); //labels
		packages[9] = new Package(62, "missing", false); //labels
		*/
		
		//Brie code...	
		//ResultSet resultWeight = getConnectionWeight();
	    //ResultSet resultPostage = getConnectionPostage();
		     
	    
		//end Brie code...
		
		System.out.println("Welcome to FEDUP!\nYou are a worker at the local FEDUP office.\nYou are in charge of checking " +
				"each package and discerning whether it is safe to deliver.\nIf it is safe, then click SHIP. If not, click " +
				"DON'T SHIP.\nYour points will be based on the choices you make: 1 pt for each correct decision, 1 citation " +
				"for each wrong decision.\nBut be aware, if you ship something dangerous, bad things could happen.");
		System.out.println();
		System.out.println("Guidelines: Weight has to be less than or equal to 150 lbs. Postage has to be present or correct-price.");
		System.out.println();
		
		//while notWin && notLose
		while (notWin && notLose){
			//get next package from array
			//probably need to also check length - for when to start back through array
			//getNextBox();
			ResultSet resultWeight = getConnectionWeight();
		    ResultSet resultPostage = getConnectionPostage();
			p = packageElms(resultWeight, resultPostage);
			//get package information
			System.out.println("Package Details:");
			System.out.println("Postage: " + p.getPostage());
			System.out.println("Weight: " + p.getWeight());
			System.out.println();
			
			//let player select Ship or Don't Ship
			System.out.println("Type Yes for ship and No for Do Not Ship");
			String answer = kb.nextLine();
			if (answer.equalsIgnoreCase("Yes"))
				playAns = true;
			else if (answer.equalsIgnoreCase("No"))
				playAns = false;
			else
				playAns = false;
			
			//Check player's answer
			//add points were necessary
			if (playAns == p.getStatus())
				addPoints();
			else
				addCit();
			//or add citations
			
			getPoints();
			getCit();
			System.out.println();
			
			//check win condition
			checkWin();
			checkLose();
			
			//update start position
			//start = start+1;
			p=null;
		}
		
		if(notLose == false)
			return false;
		else
			return true;
	}
	
	public void addPoints(){
		points = points+1;
		player.AddPoints();
	}
	
	public void addCit(){
		//points = points-1;
		cit = cit++;
		player.AddCitation();
	}
	
	public void getPoints(){
		 System.out.println("Points: " + player.getPoints());
	}
	
	public void getCit(){
		 System.out.println("Citations: " + player.getCitations());
	}
	
	public void checkWin(){
		if (player.getPoints() == 5)
			this.notWin = false;
		else
			this.notWin = true;
	}
	
	public void checkLose(){
		if(player.getCitations() == 5)
			this.notLose = false;
		else
			this.notLose = true;
	}
	/*
	public void getNextBox(){
		if (start != length - 1)
			p = packages[start];
		else {
			start = 0;
			p=packages[start]; // makes infinite loop through Packages
		}
	}
	*/
	
	public static ResultSet getConnectionWeight() throws Exception {
		ResultSet rs = null;
		
		try{
			String url = "jdbc:ucanaccess://C:/Users/Laura/Desktop/Boxes.accdb";
			String username = "";
			String password = "";
			
			Connection conn = DriverManager.getConnection(url, username, password);
			//System.out.println("Connected");
			Statement s = conn.createStatement();
	        rs = s.executeQuery("SELECT Weight FROM Weight");
	

			return rs;
			
		}catch(Exception e){System.out.println(e);}
		
		return null;
		
	}
	

	public static ResultSet getConnectionPostage() throws Exception {
		ResultSet rs = null;
		
		try{
			String url = "jdbc:ucanaccess://C:/Users/Laura/Desktop/Boxes.accdb";
			String username = "";
			String password = "";
			
			Connection conn = DriverManager.getConnection(url, username, password);
			//System.out.println("Connected");
			Statement s = conn.createStatement();
	        rs = s.executeQuery("SELECT Postage FROM Postage");

			return rs;
			
		}catch(Exception e){System.out.println(e);}
		
		return null;
		
	}
	
	
	public static void printDB(ResultSet result)
	{
		  
	      try
	      {
	         while (result.next()) 
	            {
	               System.out.print(result.getString(1) + " ");
	            }
	        
	      }
	       catch (SQLException e) 
	      {
	         e.printStackTrace();
	      }
	      
	     

	   }
	   
	//@SuppressWarnings("null")
	public static Package packageElms(ResultSet resultWeight, ResultSet resultPostage)
	{
		ArrayList<Integer> weight = new ArrayList<>();
		ArrayList<String> postage = new ArrayList<>();
		//weight = null;
		//postage = null;
		  
	      try
	      {
	         while (resultWeight.next()) 
	            {
	               weight.add(resultWeight.getInt(1));
	               
	            }
	         
	         
	      }catch (SQLException e) 
	      {
	         e.printStackTrace();
	      }

	      try
	      {
	         while (resultPostage.next()) 
	            {
	               postage.add(resultPostage.getString(1));
	               
	            }
	         
	         
	      }catch (SQLException e) 
	      {
	         e.printStackTrace();
	      }
	      
	      Random rand = new Random();
	      int randomNum = rand.nextInt(5);
	      int pWeight = weight.get(randomNum);
	      
	      Random rand2 = new Random();
	      int randomPostage = rand2.nextInt(4);
	      String pPostage = postage.get(randomPostage);
	      
	   
	      boolean pPass;
	      
	      if(pPostage.equals("missing") || pPostage.equals("excessive") || pWeight > 150){
	    	  
	    	  pPass = false;
	    	  
	      }
	      else {
	    	  
	    	  pPass = true;
	      }
	      
	    Package package1 = new Package(pWeight, pPostage, pPass);
		//System.out.println(package1.getWeight());
		//System.out.println(package1.getPostage());
		//System.out.println(package1.getStatus());
	    weight = null;
	    postage = null;
	    return package1;
	      
	   }
	
}
