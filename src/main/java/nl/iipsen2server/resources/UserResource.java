package main.java.nl.iipsen2server.resources;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import main.java.nl.iipsen2server.controlllers.AccountController;
import main.java.nl.iipsen2server.controlllers.AuthenticationController;
import main.java.nl.iipsen2server.controlllers.TokenController;
import main.java.nl.iipsen2server.dao.UserDatabase;
import main.java.nl.iipsen2server.models.UserModel;
import main.java.nl.iipsen2server.models.AccountModel;




/**
*
* @author Anthony Scheeres
*
*/
@Path("/user")
public class UserResource {
	private AccountController accountController = new AccountController();
	
	/**
	*
	* @author Anthony Scheeres
	*
	*/
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkLogin(UserModel u)  {
		return accountController.checkLogin(u);
	}

}	