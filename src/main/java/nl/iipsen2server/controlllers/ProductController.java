package main.java.nl.iipsen2server.controlllers;

import main.java.nl.iipsen2server.dao.ProductDAO;
import main.java.nl.iipsen2server.models.ProductModel;

public class ProductController {
	ProductDAO DAO= new ProductDAO();

	public String handleGetAllProducts() throws Exception {
	return DAO.showProducts();
	}

	public void handleCreateProduct(ProductModel u) throws Exception {
		DAO.addProduct(u);
		
	}

}
