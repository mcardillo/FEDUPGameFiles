package fedupGame;

import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class Player implements Serializable{


	String playerName;
	int points = 0;
	int citations = 0;
	int level = 1;
	int totalPoints = 0;
	int totalCits = 0;
	
	public Player(String name){
		playerName = name;
	}
	
	
	public Player(String name, int pts, int cit)
	  {
	   playerName = name;
	   points = pts;
	   citations = cit;
	  }
	
	
	public void addPoints(){
		points++;
	}
	
	public void removePoints(){
		points--;
	}
	
	public void addCitation(){
		citations++;
	}
	
	public void removeCitation(){
		citations--;
	}
	
	public void setPlayerName(String name)
	   {
	    playerName = name;  
	   }
	 
	 public  String getPlayerName()
	  {
	   return playerName;
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
	 
	 public void incLevel(){
		 level++;
	 }
	 
	 public int getLevel(){
		 return level;
	 }
	 
	 public void accumulate(){
		 totalPoints = totalPoints + points;
		 totalCits = totalCits + citations;
	 }
}



