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
	public void handleCreateProduct(ProductModel userModel, String token) throws Exception {
		
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!authenticationController.hasSuperPermissionOverApi(employeeId)) {
			return;
		}
		
		
		
		UserController userController  = new UserController ();
		 HashMap<String, List<String>> hashmap;
		 hashmap = DAO.getProducts();
		
		
		long id = userController.createUniqueUserIdForIdentification(hashmap.get("id"));
		
		userModel.setId(id);
		
		DAO.addProduct(userModel);
		
	}
	/**
	*
	* @author Anthony Scheeres
	 * @return 
	 * @throws Exception 
	* 
	*/
	public String handleChangeImgProduct(ProductModel product, String token) throws Exception {
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!authenticationController.hasSuperPermissionOverApi(employeeId)) {
	
			return Response.fail.toString();
		}
		DAO.changeImgForAProduct(product);
	
		return Response.success.toString();
	}
	public String handleRemoveProduct(ProductModel productModel, String token) {
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!authenticationController.hasSuperPermissionOverApi(employeeId)) {
			
			return Response.fail.toString();
		}
		DAO.removeProductFromDatabase(productModel);
		return Response.success.toString();
	}
}
