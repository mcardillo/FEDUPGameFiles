package fedupGame;

import java.util.NoSuchElementException;

public class PackageStack <T>{
	
	int capacity = 0;
	Object[] elements;
	int numElms = 0;
	
	public PackageStack(){
		//capacity determined by what level they are on
		capacity = 10;
		elements = new Object[capacity];
	}
	
	public boolean push(T e) throws NullPointerException{
		if(e == null)
			throw new NullPointerException();
		if(numElms == capacity){
			capacity = capacity + 10;
			Object[] newArray = new Object[capacity];
			
			for(int i = 0; i < numElms; i++)
				newArray[i] = elements[i];
			elements = newArray;
		}
		
		//System.out.println(numElms);
		elements[numElms++] = e;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public T pop() throws NoSuchElementException{
		//System.out.println(numElms);
		if(numElms == 0)
			throw new NoSuchElementException();
		return (T) elements[--numElms];
	}
	
	public void clear(){
		numElms = 0;
	}
	
	public void setCap(int input){
		capacity = input;
	}
	
	public boolean hasNext(){
		if(numElms == 0)
			return false;
		return true;
	}
	
	public int onStack(){
		return numElms;
	}
}
