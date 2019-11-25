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
import main.java.nl.iipsen2server.dao.UserDAO;
import main.java.nl.iipsen2server.models.UserModel;
import main.java.nl.iipsen2server.models.AccountModel;
import main.java.nl.iipsen2server.models.Permission;




/**
*
* @author Anthony Scheeres
*
*/
@Path("/user")
public class UserResource {
	private AccountController accountController = new AccountController();
	private AuthenticationController authenticationController = new AuthenticationController();
	
	/**
	*
	* @author Anthony Scheeres
	 * @return 
	* 
	*
	*/	
	@POST
	@Path("/{token}/read")
	@Consumes(MediaType.APPLICATION_JSON)
	public String giveRead(@PathParam("token") String token, AccountModel u)  {
		return authenticationController.handleGiveRead(u.getUsername(), token);
	}
	
	
	/**
	*
	* @author Anthony Scheeres
	 * @return 
	* 
	*
	*/
	@POST
	@Path("/{token}/write")
	@Consumes(MediaType.APPLICATION_JSON)
	public String giveWrite(@PathParam("token") String token,AccountModel u)  {
		return authenticationController.handleGiveWrite(u.getUsername(), token);
	}
	
	
	/**
	*
	* @author Anthony Scheeres
	 * @return 
	*
	*/
	@POST
	@Path("/{token}/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public String giveDelete(@PathParam("token") String token,AccountModel u)  {
		TokenController tokenController = new TokenController();
		long employeeId = Long.parseLong(tokenController.tokenToUserId(token));
		return authenticationController.handleGiveDelete(u.getUsername(), employeeId);
		}
	
	
	
	
	
	/**
	*
	* @author Anthony Scheeres
	 * @return 
	* 
	*
	*/	
	@POST
	@Path("/{token}/hasRead")
	public Object hasRead(@PathParam("token") String token)  {
		return authenticationController.validate(token,Permission.READ.toString());
		
	}
	
	

	/**
	*
	* @author Anthony Scheeres
	 * @return 
	* 
	*
	*/	
	@POST
	@Path("/{token}/hasWrite")
	public Object hasWrite(@PathParam("token") String token)  {
		return authenticationController.validate(token, Permission.WRITE.toString());
		
	}
	
	

	/**
	*
	* @author Anthony Scheeres
	 * @return 
	* 
	*
	*/	
	@POST
	@Path("/{token}/hasDelete")
	public boolean hasDelete(@PathParam("token") String token)  {
		return authenticationController.validate(token, Permission.DELETE.toString());
		
	}
	
	
	
	
	/**
	*
	* @author Jesse Poleij
	* 
	*
	*/
	@POST
	@Path("/{token}/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeUserModel(@PathParam("token") String employeeId, AccountModel u)  {
		accountController.handleRemoveUser(u, employeeId);
	}
	
	
	
	/**
	*
	* @author Anthony Scheeres
	* 
	*/
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createUserModel(UserModel u )  {
		return accountController.handleCreateUserModel2(u);
	}
	
	/**
	*
	* @author Anthony Scheeres
	 * @return 
	* 
	*/
	@GET
	@Path("/{token}/token")
	public String validateToken(@PathParam("token") String token){
		return accountController.validateToken(token);
	}
	
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
	
	/**
	*
	* @author Anthony Scheeres
	*
	*/
	@GET
	@Path("/show")
	@Produces(MediaType.TEXT_PLAIN)
	public String showUsers() throws Exception {
		UserDAO userDatabase = new UserDAO ();
		return userDatabase.showUser();
	}
	
	/**
	*
	* @author Anthony Scheeres
	*
	*/
	@GET
	@Path("/showU")
	@Produces(MediaType.TEXT_PLAIN)
	public String showUser(AccountModel u){
		UserDAO userDatabase = new UserDAO();
		return userDatabase.showOneUserPermission(u);
	}
}	