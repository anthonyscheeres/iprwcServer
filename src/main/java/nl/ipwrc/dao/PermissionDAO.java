package nl.ipwrc.dao;

import java.util.ArrayList;
import java.util.List;

import nl.ipwrc.models.AccountModel;
import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.models.Permission;
import nl.ipwrc.services.AuthenticationController;

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
	 public boolean giveReadInDatabase(String u) {
		  Enum permissionOnData = Permission.READ;

		  return autorizeGivePermissionOverApiInDatabase(u, permissionOnData);
	 }


	 public boolean hasPermissionOnApi (String username, Enum permissionOnApi) {
		String queryForPermissionOfUser = "select permission from app_user where username=?;";
		  
		boolean hasSuperHere = !userDatabase.hasPermission(permissionOnApi.toString(), username, queryForPermissionOfUser);
		return hasSuperHere;
	 }
	 /**
	  *
	  * @author Anthony Scheeres
	 * @return 
	  * 
	  *
	  */
	 public boolean autorizeGivePermissionOverApiInDatabase(String username, Enum permissionOnApi) {
	boolean hasSuperOverApi = hasPermissionOnApi(username, permissionOnApi);
	 if (hasSuperOverApi) {
			  catchHandleGivePermissionOverApiInDatabase(username, permissionOnApi);  
	 }return hasSuperOverApi;
	 }
	 
	 
	 
	 

	 /**
	  *
	  * @author Anthony Scheeres
	 * @return 
	  * 
	  *
	  */
	 public void catchHandleGivePermissionOverApiInDatabase(String username, Enum permissionOnApi){
		  try {
			  autorizeGivePermissionOverApiInDatabase(username, permissionOnApi);
		} catch (Exception e) {
		
		}
	 }
	 
	 
	 
	 
	 
	 
	 /**
	 *
	 * @author Anthony Scheeres
	 *  
	 * 
	 *
	 */
	 public boolean giveWrite2(String u) {
		  Enum permission = Permission.WRITE;
		  return autorizeGivePermissionOverApiInDatabase(u, permission);
	 }

	 


	 /**
	  * @author Anthony Scheeres
	  */
	 public boolean giveDelete2(String u) {
		  Enum permission = Permission.DELETE;

		  return autorizeGivePermissionOverApiInDatabase(u, permission);
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
