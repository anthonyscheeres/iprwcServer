package main.java.nl.iipsen2server.controlllers;

import main.java.nl.iipsen2server.dao.ProductDAO;
import main.java.nl.iipsen2server.models.ProductModel;

public class ProductController {
	ProductDAO DAO= new ProductDAO();
	AuthenticationController authenticationController = new AuthenticationController();
	TokenController tokkenController = new TokenController();
	
	
	public String handleGetAllProducts() throws Exception {
	return DAO.showProducts();
	}

	public void handleCreateProduct(ProductModel u, String token) throws Exception {
		
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!authenticationController.hasSuperPermission(employeeId)) {
			return;
		}
		
		DAO.addProduct(u);
		
	}

}
