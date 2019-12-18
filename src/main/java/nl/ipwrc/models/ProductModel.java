package nl.ipwrc.models;

public class ProductModel {
	private String name_p;
	private int price;
	private String img ;
	private long id ;

	
	public ProductModel() {
	
	}


	public ProductModel(String name_p, int price, String img, long id) {
		super();
		this.name_p = name_p;
		this.price = price;
		this.img = img;
		this.id = id;
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


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	
}
