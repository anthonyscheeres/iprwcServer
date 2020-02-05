package nl.ipwrc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.ipwrc.models.AccountModel;
import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.models.Permission;
import nl.ipwrc.services.AuthenticationController;
import nl.ipwrc.services.LoggerController;

public class PermissionDAO {

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
	 public boolean giveReadInDatabase(String u) {
		  Enum permissionOnData = Permission.READ;

		  return autorizeGivePermissionOverApiInDatabase(u, permissionOnData);
	 }


	 public boolean hasPermissionOnApi (String username, Enum permissionOnApi) {
		String queryForPermissionOfUser = "select permission from app_user where username=?;";
		  System.out.println("hi");
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
	  System.out.println("hi2");
	 if (hasSuperOverApi) {
		  System.out.println("hi3");
		  try {
			givePermission(username, permissionOnApi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
			  return hasSuperOverApi;
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
		 new Thread(() -> {
	    
	    
		  try {
			
			  givePermission(username, permissionOnApi);
		} catch (Exception e) {

      	  LOGGER.log(Level.SEVERE, "Exception occur", e);
		}
		 	}).start();
	 }
	 
	 
	 
	 
	 
	 
	 /**
	 *
	 * @author Anthony Scheeres
	 *  
	 * 
	 *
	 */
	 public void giveWrite2(String u) {
		  Enum permission = Permission.WRITE;
		  System.out.println("hi1");
		 try {
			givePermission(u, permission);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	 


	 /**
	  * @author Anthony Scheeres
	  */
	 public void giveDelete2(String u) {
		  Enum permission = Permission.DELETE;

		 try {
			givePermission(u, permission);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
