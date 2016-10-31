public class Package {
	private int weight;
	private String postage;
	private boolean status;
    
	public Package(int weight, String postage, boolean status ) {
		this.weight = weight;
		this.postage = postage;
		this.status = status;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public String getPostage(){
		return postage;
	}
	
	public boolean getStatus(){
		return status;
	}
}
