package core;

public abstract class Asset {
	
	private String name; 
	
	public Asset(String name){
		this.name = name;
	}
	
	public abstract long getValue();
	
	
	public String getName(){
	
		return name;
	}
}
