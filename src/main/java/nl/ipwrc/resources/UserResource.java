package nl.ipwrc.resources;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.ipwrc.dao.UserDAO;
import nl.ipwrc.models.AccountModel;
import nl.ipwrc.models.Permission;
import nl.ipwrc.models.UserModel;
import nl.ipwrc.services.AccountController;
import nl.ipwrc.services.AuthenticationController;
import nl.ipwrc.services.TokenController;
import nl.ipwrc.services.UserController;




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
	public String giveReadOverAPiIfTokenIIsFromAdmin(@PathParam("token") String tokenCheckThisOneFirst, AccountModel accountModel)  {
		return authenticationController.handleGiveReadOverAPiIfTokenIIsFromAdmin(accountModel.getUsername(), tokenCheckThisOneFirst);
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
	public String giveWriteOverAPiIfTokenIIsFromAdmin(@PathParam("token") String tokenCheckThisOneFirst,AccountModel u)  {
		return authenticationController.handleGiveWriteOverAPiIfTokenIIsFromAdmin(u.getUsername(), tokenCheckThisOneFirst);
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
	public String giveDeleteOverAPiIfTokenIIsFromAdmin(@PathParam("token") String tokenCheckThisOneFirst,AccountModel accountModel)  {

		return authenticationController.handleGiveDeleteOverAPiIfTokenIIsFromAdmin(accountModel.getUsername(), tokenCheckThisOneFirst);
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
	public Object hasRead(@PathParam("token") String tokenCheckThisOneFirst)  {
		return authenticationController.validateTokenForPermission(tokenCheckThisOneFirst,Permission.READ.toString());
		
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
	public Object hasWrite(@PathParam("token") String tokentokenCheckThisOneFirst)  {
		return authenticationController.validateTokenForPermission(tokentokenCheckThisOneFirst, Permission.WRITE.toString());
		
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
		return authenticationController.validateTokenForPermission(token, Permission.DELETE.toString());
		
	}
	

	/**
	*
	* @author Anthony Scheeres
	 * @return 
	* 
	*
	*/	
	@POST
	@Path("/{token}/hasAdmin")
	public boolean hasAdmin(@PathParam("token") String token)  {
		TokenController tokenController = new TokenController();
		long employeeId = Long.parseLong(tokenController.tokenToUserId(token));
		return authenticationController.hasSuperPermissionOverApi(employeeId);
		
	}
	
	

	/**
	*
	* @author Anthony Scheeres
	* 
	*/
	@DELETE
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
	public String createUserModelRegisterUserInDatabase(UserModel u )  {
		return accountController.handleCreateUserModelIndatabase(u);
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
	 * @throws Exception 
	*
	*/
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public String checkLogin(UserModel u)   {
		return accountController.handleCheckLogin(u);
	}
	
	/**
	*
	* @author Anthony Scheeres
	*
	*/
	@GET
	@Path("/{token}/showAllUsers")
	@Produces(MediaType.TEXT_PLAIN)
	public String showUsers(@PathParam("token") String token) throws Exception {
		UserController userController = new UserController();
		return userController.showUsersASAJsonString(token);
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