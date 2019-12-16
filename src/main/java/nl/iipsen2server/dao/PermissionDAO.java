package nl.iipsen2server.dao;

import java.util.ArrayList;
import java.util.List;

import nl.iipsen2server.models.DataModel;
import nl.iipsen2server.models.DatabaseModel;
import nl.iipsen2server.models.Permission;
import nl.iipsen2server.services.AuthenticationController;
import nl.iipsen2server.models.AccountModel;

public class PermissionDAO {

	 String tableName = "app_user";
	 private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
	 AuthenticationController autenticationController = new AuthenticationController();
	 private UserDAO userDatabase = new UserDAO();




	 /**
	  *
	  * @author Anthony Scheeres
	 * @return 
	  * 
	  *
	  */
	 public boolean giveRead2(String username) {
		  String query2 = "select permission from app_user where username=?;";
		  Enum permission = Permission.READ;
	 if (!userDatabase.hasPermission(permission.toString(), username, query2)) {
		  try {
			givePermission(username, permission);
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
	 public boolean giveWrite2(String u) {
		  String query2 = "select permission from app_user where username=?;";
		  Enum permission = Permission.WRITE;
	 if (!userDatabase.hasPermission(permission.toString(), u, query2)) {
		  try {
			  givePermission(u, permission);
			 return true;
		} catch (Exception e) {
		
		}
		  
	 }return false;
	 }

	 


	 /**
	  * @author Anthony Scheeres
	  */
	 public boolean giveDelete2(String u) {
		  String query2 = "select permission from app_user where username=?;";
		  Enum permission = Permission.DELETE;
		  if (!userDatabase.hasPermission(permission.toString(), u, query2)) {
		 	  try {
		 		  givePermission(u, permission);
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
	 private void givePermission(String u, Enum e) throws Exception {
		  PreparedStatmentDatabaseUtilities databaseController = new PreparedStatmentDatabaseUtilities();
		  List < String > list = new ArrayList < String > ();
		  String query2 = String.format("UPDATE app_user SET permission = array_append(permission,'%s') WHERE username = ?;", e);
		  list.add(u);
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
}
