package nl.ipwrc.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.ipwrc.models.ProductModel;
import nl.ipwrc.models.UserModel;
import nl.ipwrc.services.ProductController;

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
	@Path("/{token}/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createProductModel(ProductModel u, @PathParam("token") String token) throws Exception  {
		 productController.handleCreateProduct(u, token);
	}
	
	
	
	/**
	*
	* @author Anthony Scheeres
	 * @throws Exception 
	* 
	*/
	@POST
	@Path("/{token}/changeImg")
	@Consumes(MediaType.APPLICATION_JSON)
	public void changeProductModelImg(ProductModel u, @PathParam("token") String token) throws Exception  {
		 productController.handleChangeImgProduct(u, token);
	}
	
	/**
	*
	* @author Anthony Scheeres
	 * @return 
	 * @throws Exception 
	* 
	*/
	@DELETE
	@Path("/{token}/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public String removeProductModelFromDatabase(ProductModel u, @PathParam("token") String token) throws Exception  {
		 return productController.handleRemoveProduct(u, token);
	}

}
