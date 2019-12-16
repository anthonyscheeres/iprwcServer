package nl.iipsen2server.services;

import java.util.HashMap;
import java.util.List;

import main.java.nl.iipsen2server.dao.ProductDAO;
import main.java.nl.iipsen2server.models.ProductModel;

public class ProductController {
	ProductDAO DAO= new ProductDAO();
	AuthenticationController authenticationController = new AuthenticationController();
	TokenController tokkenController = new TokenController();
	
	/**
	*
	* @author Anthony Scheeres
	 * @throws Exception 
	* 
	*/
	public String handleGetAllProducts() throws Exception {
	return DAO.showProducts();
	}
	/**
	*
	* @author Anthony Scheeres
	 * @throws Exception 
	* 
	*/
	public void handleCreateProduct(ProductModel u, String token) throws Exception {
		
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!authenticationController.hasSuperPermission(employeeId)) {
			return;
		}
		
		
		
		UserController userController  = new UserController ();
		 HashMap<String, List<String>> hashmap;
		 hashmap = DAO.getProducts();
		
		
		long id = userController.createUserId2(hashmap.get("id"));
		
		u.setId(id);
		
		DAO.addProduct(u);
		
	}
	/**
	*
	* @author Anthony Scheeres
	 * @throws Exception 
	* 
	*/
	public void handleChangeImgProduct(ProductModel u, String token) {
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!authenticationController.hasSuperPermission(employeeId)) {
			return;
		}
		DAO.changeImg(u);
	}

}
