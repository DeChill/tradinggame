package core;
public class Share {

	public final String name;
	private long price;

	public Share(String name, long price) {
		this.name = name;
		this.price = price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
	
	public long getPrice(){
		return price;
	}
	public String getName(){
		return name;
	}
	public boolean equals(Object test){
		if(!(test instanceof Share)){
			return false;
		}
//		if(test == this){
//			return false;
//		}
		if (((Share)test).name.equals(this.name)){
			if(((Share)test).price == this.price){
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return getName()+ " " + getPrice();
	}
	
}
