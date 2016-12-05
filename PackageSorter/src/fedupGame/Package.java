package fedupGame;

/*********************************************************
 * This class creates a package. 
 * 
 * @author Briana Moore 
 * 
 * Max Cardillo implemented the getSender, setEgg, and getEgg
 * methods 
 * 
 * Laura Riffo
 **********************************************************/
public class Package {
	private int weight;
	private String postage;
	private String description;
	private boolean status;
	private String sender = "";
	private boolean eeStatus = false;
    
	public Package(int weight, String postage, String descript, boolean status ) {
		this.weight = weight;
		this.postage = postage;
		this.description = descript;
		this.status = status;
	}
	
	public Package(int weight, String postage, String descript, String sender, boolean status){
		this.weight = weight;
		this.postage = postage;
		this.description = descript;
		this.sender = sender;
		this.status = status;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public String getPostage(){
		return postage;
	}
	
	public String getDescription(){
		return description;
	}
	
	public boolean getStatus(){
		return status;
	}
	
	public String getSender(){
		return sender;
	}
	
	public void setEgg(boolean input){
		eeStatus = input;
	}
	
	public boolean getEgg(){
		return eeStatus;
	}
}
