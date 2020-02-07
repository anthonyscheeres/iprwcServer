package nl.ipwrc.services;


import nl.ipwrc.dao.AuthenticationDAO;
import nl.ipwrc.dao.PermissionDAO;
import nl.ipwrc.dao.PreparedStatmentDatabaseUtilities;
import nl.ipwrc.models.AccountModel;
import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.models.LogModel;
import nl.ipwrc.models.Permission;
import nl.ipwrc.models.Response;
import nl.ipwrc.models.UserModel;


/**
*
* @author Anthony Scheeres
*  
* 
*
*/
public class AuthenticationController {
	 /**
	  *
	  * @author Anthony Scheeres 
	  *
	  */
	public boolean hasReadPermission(long employeeId) {
		AuthenticationDAO authenticationDAO = new AuthenticationDAO();
		return authenticationDAO.hasEnumHandeler(employeeId, Permission.READ.toString());
		
	}
	 /**
	  *
	  * @author Anthony Scheeres
	  *  
	  * 
	  *
	  */
	public String handleGiveReadOverAPiIfTokenIIsFromAdmin(String u, String token) {
		AccountController accountController = new AccountController();
		LoggingController loggingController = new LoggingController();
		TokenController tokenController = new TokenController();
		long employeeId = Long.parseLong(tokenController.tokenToUserId(token));
		if (!hasSuperPermissionOverApi(employeeId)) {
			return Response.fail.toString();
		}
		if (accountController.giveReadToAccountByUsername(u)) {

	return Response.success.toString();
		
		}return Response.fail.toString();
		}


	 /**
	  *
	  * @author Anthony Scheeres
	  *  
	  * 
	  *
	  */
	public String handleGiveWriteOverAPiIfTokenIIsFromAdmin(String u,String token) {
		LoggingController loggingController = new LoggingController();
		AccountController accountController = new AccountController();
		TokenController tokkenController = new TokenController();
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!hasSuperPermissionOverApi(employeeId)) {
			return Response.fail.toString();
		}
		
		
	accountController.giveWrite2(u);

		return Response.success.toString();
	}
	



	 /**
	  *
	  * @author Anthony Scheeres 
	  *
	  */
	public boolean hasSuperPermissionOverApi(long employeeId) {
		AuthenticationDAO authenticationDAO = new AuthenticationDAO();
		if (authenticationDAO.hasEnumHandeler(employeeId, Permission.WRITE.toString()) && authenticationDAO.hasEnumHandeler(employeeId, Permission.READ.toString()) && authenticationDAO.hasEnumHandeler(employeeId, Permission.DELETE.toString())){
			return true;
		}
		return false;
	}




	 /**
	  *
	  * @author Anthony Scheeres
	  *
	  */
	public String handleGiveDeleteOverAPiIfTokenIIsFromAdmin(String u, String token) {
		AccountController accountController = new AccountController();
		TokenController tokkenController = new TokenController();
		long employeeId = Long.parseLong(tokkenController.tokenToUserId(token));
		if (!hasSuperPermissionOverApi(employeeId)) {
			return Response.fail.toString();
		}
		accountController.giveDelete2(u);
			return Response.success.toString();
	
	}


/**
*
* @author Anthony Scheeres
 *  
* 
*
*/
public boolean validateTokenForPermission(String token, String permission) {
	TokenController tokenController = new TokenController();
	AuthenticationDAO authenticationDAO = new AuthenticationDAO();
	long employeeId = Long.parseLong(tokenController.tokenToUserId(token));
	return authenticationDAO.hasEnumHandeler(employeeId, permission);
}
/**
*
* @author Anthony Scheeres
*
*
*/
	public void userIDtoUsername(String userID) {
		AuthenticationDAO authenticationDAO = new AuthenticationDAO();

		String resultset = authenticationDAO.userIDtoUsername(userID);



	}
	/**
	 *
	 * @author Anthony Scheeres
	 *
	 *
	 */
	public boolean hasPermission(Long userID, String permission) {
		AuthenticationDAO authenticationDAO = new AuthenticationDAO();

		return authenticationDAO.hasEnumHandeler(userID, permission);
	}

}
