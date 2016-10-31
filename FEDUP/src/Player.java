
public class Player {


		String PlayerName;
		int points = 0;
		int citations = 0;
		
		
		public Player(String name, int pts, int cit)
		  {
		   PlayerName = name;
		   points = pts;
		   citations = cit;
		  }
		
		
		public void AddPoints(){
			points++;
		}
		
		public void RemovePoints(int pts){
			pts--;
		}
		
		public void AddCitation(){
			citations++;
		}
		
		public void RemoveCitation(int cit){
			cit--;
		}
		
		public void setPlayerName(String name)
		   {
		    PlayerName = name;  
		   }
		 
		 public  String getPlayerName()
		  {
		   return PlayerName;
		  }
		 
		 public void setPoints(int pts)
		 {
			 points = pts;
		 }
		 
		 public int getPoints()
		 {
			 return points;
		 }
		 
		 public void setCitations(int cit)
		 {
			 citations = cit;
		 }
		 
		 public int getCitations()
		 {
			 return citations;
		 }
	}


