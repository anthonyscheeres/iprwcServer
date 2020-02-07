package  nl.ipwrc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import nl.ipwrc.models.AccountModel;
import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.models.Permission;
import nl.ipwrc.models.Response;
import nl.ipwrc.services.AuthenticationController;
import nl.ipwrc.services.LoggerController;

import java.util.logging.Logger;
public class AuthenticationDAO {

	 String tableName = "app_user";
	 private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
	 AuthenticationController autenticationController = new AuthenticationController();
	 private UserDAO userDatabase = new UserDAO();
	    private static final Logger LOGGER = Logger.getLogger(LoggerController.class.getName());



	 /**
	  *
	  * @author Anthony Scheeres
	 * @return 
	  * 
	  *
	  */
	 public boolean giveRead2(AccountModel u) {
		  String query2 = "select permission from app_user where username=?;";
		  Enum permission = Permission.READ;
	 if (!userDatabase.hasPermission(permission.toString(), u.getUsername(), query2)) {
		  try {
			givePermission(u, permission);
			 return true;
		} catch (Exception e) {
			  LOGGER.log(Level.SEVERE, "Exception occur", e);
		}
	 }return false;
	 }


	 /**
	 *
	 * @author Anthony Scheeres
	 *  
	 * 
	 *
	 */
	 public boolean giveWrite2(AccountModel u) {
		  String query2 = "select permission from app_user where username=?;";
		  Enum permission = Permission.WRITE;
	 if (!userDatabase.hasPermission(permission.toString(), u.getUsername(), query2)) {
		  try {
			  givePermission(u, permission);
			 return true;
		} catch (Exception e) {
			  LOGGER.log(Level.SEVERE, "Exception occur", e);
		}
		  
	 }return false;
	 }

	 


	 /**
	  * @author Anthony Scheeres
	  */
	 public boolean giveDelete2(AccountModel accountModel) {
		  String query2 = "select permission from app_user where username=?;";
		  Enum permission = Permission.DELETE;
		  if (!userDatabase.hasPermission(Permission.DELETE.toString(), accountModel.getUsername(), query2)) {
		 	  try {
		 		  givePermission(accountModel, permission);
		 		 return true;
		 	} catch (Exception e) {
		 		  LOGGER.log(Level.SEVERE, "Exception occur", e);
		 	}
		 	  
		  }return false;
	 }







	 /**
	  *
	  * @author Anthony Scheeres
	  *
	  */
	 private void givePermission(AccountModel u, Enum e) throws Exception {
		  PreparedStatmentDatabaseUtilities databaseController = new PreparedStatmentDatabaseUtilities();
		  List < String > list = new ArrayList < String > ();
		  String query2 = String.format("UPDATE app_user SET permission = array_append(permission,'%s') WHERE username = ?;", e);
		  list.add(u.getUsername());
		  databaseController.connectDatabaseThrowQueryReturnsJsonString(databaseModel, query2, list, false);
	 }
	 
	 

	 /**
	  *
	  * @author Anthony Scheeres
	  *  
	  */
	public boolean hasEnumHandeler(long employeeId, String permission) {
	  	String query2 = "select permission from app_user where user_id=?;";

	  	return userDatabase.hasPermission( permission, Long.toString(employeeId), query2) ;
}
	public String userIDtoUsername(String userID){
		PreparedStatmentDatabaseUtilities databaseController = new PreparedStatmentDatabaseUtilities();
		List < String > list = new ArrayList < String > ();
		String query = "select username, user_id from app_user where user_id = ?";
		list.add(userID);

		try {
			return databaseController.connectDatabaseThrowQueryReturnsJsonString(databaseModel, query, list, false);
		} catch (Exception e) {
			 LOGGER.log(Level.SEVERE, "Error occur", e);
		}

		return Response.fail.toString();
	}
}
