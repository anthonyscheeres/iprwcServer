package main.java.nl.iipsen2server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.nl.iipsen2server.controlllers.ProductController;
import main.java.nl.iipsen2server.models.ProductModel;
import main.java.nl.iipsen2server.models.UserModel;

/**
*
* @author Anthony Scheeres
*
*/
@Path("/product")
public class ProductResource {
	ProductController productController = new ProductController();
	/**
	*
	* @author Anthony Scheeres
	*
	*/
	@GET
	@Path("/show")
	@Produces(MediaType.TEXT_PLAIN)
	public String showProducts() throws Exception {
		return productController.handleGetAllProducts();
	}
	
	/**
	*
	* @author Anthony Scheeres
	 * @throws Exception 
	* 
	*/
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createUserModel(ProductModel u ) throws Exception  {
		 productController.handleCreateProduct(u);
	}
	
}
