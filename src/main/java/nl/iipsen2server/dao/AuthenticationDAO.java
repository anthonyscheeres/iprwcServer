package main.java.nl.iipsen2server.dao;

import java.util.ArrayList;
import java.util.List;

import main.java.nl.iipsen2server.controlllers.AuthenticationController;
import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;
import main.java.nl.iipsen2server.models.Permission;
import main.java.nl.iipsen2server.models.AccountModel;

public class AuthenticationDAO {

	 String tableName = "app_user";
	 private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
	 AuthenticationController autenticationController = new AuthenticationController();
	 private UserDatabase userDatabase = new UserDatabase();




	 /**
	  *
	  * @author Anthony Scheeres
	 * @return 
	  * 
	  *
	  */
	 public boolean giveRead2(AccountModel u) {
		  String query2 = "select permission from app_user where username=?;";
	 if (!userDatabase.hasPermission("READ", u.getUsername(), query2)) {
		  try {
			givePermission(u, Permission.READ);
			 return true;
		} catch (Exception e) {
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
	 if (!userDatabase.hasPermission("WRITE", u.getUsername(), query2)) {
		  try {
			  givePermission(u, Permission.WRITE);
			 return true;
		} catch (Exception e) {
		
		}
		  
	 }return false;
	 }

	 


	 /**
	  * @author Anthony Scheeres
	  */
	 public boolean giveDelete2(AccountModel accountModel) {
		  String query2 = "select permission from app_user where username=?;";
		  if (!userDatabase.hasPermission("DELETE", accountModel.getUsername(), query2)) {
		 	  try {
		 		  givePermission(accountModel, Permission.DELETE);
		 		 return true;
		 	} catch (Exception e) {
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
		  databaseController.connectDatabaseJson(databaseModel, query2, list, false);
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
			return databaseController.connectDatabaseJson(databaseModel, query, list, false);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "fail";
	}
}
