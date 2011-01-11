package ucsc.crop.objects;

import java.io.Serializable;



public class Parameters implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name; //name
	private String code; //code
	private String location; //city
	private String price1; //price1
	private String price2; //price2
	
	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
	
	public void setName(String name ) {
	
		//String[] words = name.split(" ");
		//this.code=words[0];
		this.name =name;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String wloc ) {
		this.location = wloc;
	}
	
	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price ) {
		this.price2 = price;
	}
	
	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price ) {
		this.price1 = price;
	}
	
	
}
