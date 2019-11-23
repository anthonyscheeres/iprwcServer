package main.java.nl.iipsen2server.models;

public class ProductModel {
	private String name_p;
	private int price;
	public ProductModel(String name_p, int price) {
		super();
		this.name_p = name_p;
		this.price = price;
	}
	public String getName_p() {
		return name_p;
	}
	public void setName_p(String name_p) {
		this.name_p = name_p;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
