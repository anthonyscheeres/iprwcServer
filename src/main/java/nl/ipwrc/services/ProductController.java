package nl.ipwrc.services;

import java.util.HashMap;
import java.util.List;

import nl.ipwrc.dao.ProductDAO;
import nl.ipwrc.models.ProductModel;
import nl.ipwrc.models.Response;

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
	 * @return 
	 * @throws Exception 
	* 
	*/
	public String handleChangeImgProduct(ProductModel u, String token) throws Exception {
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!authenticationController.hasSuperPermission(employeeId)) {
	
			return Response.fail.toString();
		}
		DAO.changeImg(u);
	
		return Response.success.toString();
	}
	public String handleRemoveProduct(ProductModel productModel, String token) {
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!authenticationController.hasSuperPermission(employeeId)) {
			
			return Response.fail.toString();
		}
		DAO.removeProduct(productModel);
		return Response.success.toString();
	}
}
