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
	 public boolean giveReadOverApi(String u) {
		  Enum permission = Permission.READ;

		  return handleGivePermissionWriteOverApi(u, permission);
	 }

	 
	 public boolean handleGivePermissionWriteOverApi(String u, Enum permission) {
		  String query2 = "select permission from app_user where username=?;";
		boolean hasAlreadyThisPermissionWriteOverApi = !userDatabase.hasPermission(permission.toString(), u, query2);
		
		
		
	 if (hasAlreadyThisPermissionWriteOverApi) {
		  try {
			  givePermissionWriteOverApi(u, permission);
		} catch (Exception error) {

            LOGGER.log(Level.SEVERE, "Exception occur", error);
		}
		  
	 }return hasAlreadyThisPermissionWriteOverApi;
	 }
	 /**
	 *
	 * @author Anthony Scheeres
	 *  
	 * 
	 *
	 */
	 public boolean giveWriteOverApi(String u) {
		  Enum permission = Permission.WRITE;
		  return handleGivePermissionWriteOverApi(u, permission);
	 }

	 


	 /**
	  * @author Anthony Scheeres
	  */
	 public boolean giveDeleteOverApi(String u) {
		  Enum permission = Permission.DELETE;

		  return handleGivePermissionWriteOverApi(u, permission);
	 }







	 /**
	  *
	  * @author Anthony Scheeres
	  *
	  */
	 private void givePermissionWriteOverApi(String u, Enum e) throws Exception {
	
		  PreparedStatmentDatabaseUtilities databaseController = new PreparedStatmentDatabaseUtilities();
		  List < String > list = new ArrayList < String > ();
		  String query2 = String.format("UPDATE app_user SET permission = array_append(permission,'%s') WHERE username = ?;", e);
		  list.add(u);
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
}