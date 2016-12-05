package fedupGame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class GameV2 implements CallbackListener{

	Player player;
	PackageStack<Package> packages = new PackageStack<>();
	boolean notWin = true;
	boolean notLose = true;
	Package p;
	boolean choiceMade = true;
	boolean eeAppear = false;
	boolean eeChoice;
	String playerName;
	MainGameWindow playing;
	MainGameWindow playing2;
	MainGameWindow playing3;
	
	public boolean play(){
		
		GameEnterScreen begin = new GameEnterScreen();
		while(begin.getChoice()){
			System.out.flush();
		}
		
		if(!begin.loaded()){
			NewPlayerScreen user = new NewPlayerScreen(this);
			while(user.getChoice()){
				System.out.flush();
			}
			player = new Player(playerName);
		}
		else{
			player = begin.getLoad();
		}
			
		for(int i = 0; i < 15; i++){
	    	
			ResultSet resultWeight = null;
			try {
				resultWeight = getConnectionWeight();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		    ResultSet resultPostage = null;
			try {
				resultPostage = getConnectionPostage();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ResultSet resultDescriptor = null;
			try{
				resultDescriptor = getConnectionDescriptors();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			packages.push(packageElms(resultWeight, resultPostage, resultDescriptor));
		}
		
		if(player.getLevel() == 1){
			playing = new MainGameWindow(this);
			while(player.getPoints() <= 9 && player.getCitations() < 5 && !eeAppear){
				setEggEnd(playing.eeAppears(), playing.eeChoosen());
				System.out.flush();
			}
			playing.makeInvis();
		}
		
		//System.out.println(packages.onStack());
		if(player.getPoints() >= 10 && player.getCitations() < 5){
			
			for(int i = 0; i < 20; i++){
		    	
				ResultSet resultWeight = null;
				try {
					resultWeight = getConnectionWeight();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			    ResultSet resultPostage = null;
				try {
					resultPostage = getConnectionPostage();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ResultSet resultDescriptor = null;
				try{
					resultDescriptor = getConnectionDescriptors();
				} catch(Exception e) {
					e.printStackTrace();
				}
				//packages = new PackageStack<Package>();
				packages.push(packageElms(resultWeight, resultPostage, resultDescriptor));
				//System.out.println(packages.onStack());
			}
			player.incLevel();
			player.accumulate();
			player.setPoints(0);
			player.setCitations(0);
			
		}
		
		
		//System.out.println(packages.onStack());
		//System.out.println(player.getPoints());
		//System.out.println(eeAppear);
		if(player.getLevel() == 2){
			playing2 = new MainGameWindow(this);
			while(player.getPoints() <= 14 && player.getCitations() < 5 && !eeAppear){
				setEggEnd(playing2.eeAppears(), playing2.eeChoosen());
				//System.out.println(player.getPoints());
				System.out.flush();
			}
			playing2.makeInvis();
		}
		
		System.out.println(player.getPoints());
		if(player.getPoints() >= 15 && player.getCitations() < 5){
			
			for(int i = 0; i < 25; i++){
		    	
				ResultSet resultWeight = null;
				try {
					resultWeight = getConnectionWeight();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			    ResultSet resultPostage = null;
				try {
					resultPostage = getConnectionPostage();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ResultSet resultDescriptor = null;
				try{
					resultDescriptor = getConnectionDescriptors();
				} catch(Exception e) {
					e.printStackTrace();
				}
				//packages = new PackageStack<Package>();
				packages.push(packageElms(resultWeight, resultPostage, resultDescriptor));
			}
			player.incLevel();
			player.accumulate();
			player.setPoints(0);
			player.setCitations(0);
			
		}
		
		
		if(player.getLevel() == 3){
			playing3 = new MainGameWindow(this);
			while(player.getPoints() <= 19 && player.getCitations() < 5 && !eeAppear){
				setEggEnd(playing3.eeAppears(), playing3.eeChoosen());
				System.out.flush();
			}
			playing3.makeInvis();
		}
		
		/*while(notWin && notLose){
			
			//GameEnterScreen begin = new GameEnterScreen();
			/*while(choiceMade){
				choiceMade = begin.getChoice();
			}
			//begin.closeWindow();
			choiceMade = false;
			MainGameWindow playing = new MainGameWindow(this);
			/*while(packages.hasNext()){
				while(choiceMade){
					playing.feedPackage(packages.pop());
					System.out.println(1);
				}
			}
			
		}*/
		
		if(player.getCitations() > 4)
			return false;
		else if(player.getLevel() == 3 && player.getPoints() > 19)
			return true;
		else
			return false;
	}
	
	
	public static ResultSet getConnectionWeight() throws Exception {
		ResultSet rs = null;
		
		try{
			//Use local location of Boxes database
			String url = "jdbc:ucanaccess://Boxes (1).accdb";  //Macs:?
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
			//Use local location of Boxes database
			String url = "jdbc:ucanaccess://Boxes (1).accdb"; //Macs?
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
	
	public static ResultSet getConnectionDescriptors() throws Exception {
		ResultSet rs = null;
		
		try{
			String url = "jdbc:ucanaccess://Boxes (1).accdb";
			String username = "";
			String password = "";
			
			Connection conn = DriverManager.getConnection(url, username, password);
			//System.out.println("Connected");
			Statement s = conn.createStatement();
	        rs = s.executeQuery("SELECT Descriptor FROM Descriptor");
	

			return rs;
			
		}catch(Exception e){System.out.println(e);}
		
		return null;
		
	}
	
	
	public Package packageElms(ResultSet resultWeight, ResultSet resultPostage, ResultSet resultDescriptor)
	{
		ArrayList<Integer> weight = new ArrayList<>();
		ArrayList<String> postage = new ArrayList<>();
		ArrayList<String> descript = new ArrayList<>();
		Random eeRand = new Random();
		Package package1;
		int eeNum = eeRand.nextInt(100);
		//weight = null;
		//postage = null;
		if(eeNum == 13){
			package1 = new Package(100, "present", "ticking and bad smelling", "KKK", false);
			package1.setEgg(true);
		}
		else{
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
	    
			try
			{
				while (resultDescriptor.next())
					descript.add(resultDescriptor.getString(1));
			}catch (SQLException e)
			{
				e.printStackTrace();
			}
	      
			Random rand = new Random();
			int randomNum = rand.nextInt(200);
			int pWeight = weight.get(randomNum);
	      
			Random rand2 = new Random();
			int randomPostage = rand2.nextInt(5);
			String pPostage = postage.get(randomPostage);
	    
			Random rand3 = new Random();
			int randomDes = rand3.nextInt(100);
			String pDesc = descript.get(randomDes);
	      
	   
			boolean pPass;
	      
			if(checkPostage(pPostage) || pWeight > 150 || checkDesc(pDesc)){
				pPass = false; 
			}
			else {
				pPass = true;
			}
	      
			package1 = new Package(pWeight, pPostage, pDesc, pPass);
			//System.out.println(package1.getWeight());
			//System.out.println(package1.getPostage());
			//System.out.println(package1.getStatus());
			weight = null;
			postage = null;
			descript = null;
		}
	    return package1;
	      
	}
	
	public boolean checkPostage(String com){
		if(com.equalsIgnoreCase("missing"))
			return true;
		else if(com.equalsIgnoreCase("excessive"))
			return true;
		else if(com.equalsIgnoreCase("mismatched price to ship"))
			return true;
		else
			return false;
	}
	
	public boolean checkDesc(String com){
		String[] descriptions = new String[]{"ticking", "foul smelling", "oozing mysterious liquid", "barking", "soaked", 
				"loudly hissing", "desperately whining", "illegally marked", "protruding odd wires", "labeled 'toxic'",
				"covered in white powder", "literally shocking",
				"very, very hot", "wrapped in aluminum foil", "stained with an oily liquid", "discolored",
				"addressed to Anonymous", "labeled 'private'", "postmarked incorrectly",
				"labeled 'danger'", "electrified", "labeled as asbestos",
				"labeled as confined space", "labeled 'no smoking'"};
		
		for(int i = 0; i < descriptions.length; i++){
			if(com.equalsIgnoreCase(descriptions[i]))
				return true;
		}
		
		return false;
	}
	
	public void winning(){
		if(player.getPoints() >= 10)
			notWin = false;
	}
	
	public void losing(){
		if(player.getCitations() >= 5)
			notLose = false;
	}
	
	public Package nextPackage(){
		return packages.pop();
	}
	
	public Player passPlayer(){
		return player;
	}
	
	public boolean checkStack(){
		if(packages.hasNext())
			return true;
		else
			return false;
	}
	
	public void retrieveName(String name){
		playerName = name;
	}
	
	public void saveGame(){
		try{
			FileOutputStream outStream = new FileOutputStream("SavedGames.txt");    
			ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream); 

			objectOutputFile.writeObject(player);     
        
			objectOutputFile.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void setEggEnd(boolean eeAppear, boolean eeChoice){
		this.eeAppear = eeAppear;
		this.eeChoice = eeChoice;
	}
	
	public boolean eeAppeared(){
		return eeAppear;
	}
	
	public boolean eeChoose(){
		return eeChoice;
	}
	
}
