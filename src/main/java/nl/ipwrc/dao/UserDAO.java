package nl.ipwrc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.ipwrc.dao.DatabaseUtilities;
import nl.ipwrc.models.*;
import nl.ipwrc.services.LoggerController;
import nl.ipwrc.services.MailController;
import nl.ipwrc.services.UserController;


public class UserDAO {

	private String tableName = "app_user";
	private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
    private static final Logger LOGGER = Logger.getLogger(LoggerController.class.getName());
	/**
	 * @author Anthony Scheeres
	 * <p>
	 * get tokens from database
	 */
	public HashMap<String, List<String>> getTokens() throws Exception {
		DatabaseUtilities databaseUtilities = new DatabaseUtilities();
		String query = String.format("select user_id, token from %s order by user_id;", tableName);
		return databaseUtilities.connectThisDatabaseHashMap(databaseModel, query, false);
	}
	/**
	 * @author Anthony Scheeres
	 */
	public boolean hasEnumHandeler(long employeeId, String permission) {
		String query2 = "select permission from app_user where user_id=?;";
		UserDAO userDatabase = new UserDAO();
		return userDatabase.hasPermission(permission, Long.toString(employeeId), query2);
	}
    /**
     * @author Anthony Scheeres
     */

    public boolean hasPermission(String permission, String u, String query2) {
        PreparedStatmentDatabaseUtilities dUtilities = new PreparedStatmentDatabaseUtilities();
        HashMap<String, List<String>> hashMap;
        List<String> array = new ArrayList<String>();
        array.add(u);
        try {
            hashMap = dUtilities.connectDatabaseHashmap(databaseModel, query2, array);
            boolean hasPermission = hashMap.get("permission").contains(null);
            if (hasPermission) {
                return false;
            }
            if (!hashMap.get("permission").get(0).contains(permission)) {
                return false;
            }
        } catch (Exception e) {
        	  LOGGER.log(Level.SEVERE, "Exception occur", e);
        }
        return true;
    }


    /**
     * @author Anthony Scheeres
     */
    public void changeToken(String token, int id) {
        String query = String.format(""
                + "UPDATE %s "
                + "SET token = '%s' "
                + "WHERE user_id = %d;", tableName, token, id);
        
        DatabaseUtilities databaseUtilities = new DatabaseUtilities();
        try {
            databaseUtilities.connectThisDatabaseJson(databaseModel, query, true);

        } catch (Exception e) {
        	  LOGGER.log(Level.SEVERE, "Exception occur", e);
        }
    }


    /**
     * @author Anthony Scheeres
     */
    public String showUser() throws Exception {
        String query = String.format("select username,permission from %s order by username;", tableName);
      
        DatabaseUtilities datebaseUtilities = new DatabaseUtilities();
        return datebaseUtilities.connectThisDatabaseJson(databaseModel, query, false);
    }


    /**
     * @author Anthony Scheeres
     */
    public String showOneUserPermission(AccountModel u) {
        PreparedStatmentDatabaseUtilities f = new PreparedStatmentDatabaseUtilities();
        String result = null;
        String query =
                "select username, permission FROM app_user\r\n" +
                        "WHERE username = ? ;";
        List<String> array = new ArrayList<String>();
        array.add(u.getUsername());
        try {
            result = f.connectDatabaseThrowQueryReturnsJsonString(databaseModel, query, array, false);
        } catch (Exception e) {
        	  LOGGER.log(Level.SEVERE, "Exception occur", e);
        }
        return result;
    }


    /**
     * @author Anthony Scheeres
     */
    public HashMap<String, List<String>> getUserInfo() throws Exception {
        DatabaseUtilities d = new DatabaseUtilities();
        String query = String.format("select username, password, user_id, token, permission from %s order by user_id;", tableName);
        return d.connectThisDatabaseHashMap(databaseModel, query, false);

    }


    /**
     * @author Anthony Scheeres
     */
    public HashMap<String, List<String>> getUsers() throws Exception {
        DatabaseUtilities d = new DatabaseUtilities();
        String query = String.format("select username, user_id from %s order by user_id;", tableName);
        return d.connectThisDatabaseHashMap(databaseModel, query, false);
    }


    /**
     * @author Anthony Scheeres
     */
    public String insertInDatabaseHandlerUser(HashMap<String, List<String>> e1, UserModel u) throws Exception {
        PreparedStatmentDatabaseUtilities pUtilites = new PreparedStatmentDatabaseUtilities();
        MailController m = new MailController();
        UserController r = new UserController();
        long id = r.createUniqueUserIdForIdentification(e1.get("user_id"));
        String query2 = "INSERT INTO app_user(username, password, user_id, email, token) VALUES (" +
                "?," +
                "?," +
                "?," +
                "?," +
                "?" +
                ");";
        String token = m.generateToken();
        List<String> variables = new ArrayList<>();
        variables.add(u.getUsername());
        variables.add(u.getPassword());
        variables.add(String.format("%d", id));
        variables.add(u.getEmail());
        variables.add(token);
        pUtilites.connectDatabaseThrowQueryReturnsJsonString(databaseModel, query2, variables, false);
        return token;
    }

    /**
     * @author Anthony Scheeres
     */
    public void removeUserModel2(AccountModel u) {
        PreparedStatmentDatabaseUtilities f = new PreparedStatmentDatabaseUtilities();
        String query =
                "DELETE FROM app_user\r\n" +
                        "WHERE username = ? ;";
        List<String> f1 = new ArrayList<String>();
        f1.add(u.getUsername());
        try {
            f.connectDatabaseThrowQueryReturnsJsonString(databaseModel, query, f1, false);
        } catch (Exception e) {
        	  LOGGER.log(Level.SEVERE, "Exception occur "+ query, e);
        }
    }


    /**
     * @author Anthony Scheeres
     */
    public String showUsers() throws Exception {
        String query = String.format(
                "SELECT username, " +
                        "permission FROM %s"
                        + " order by username;", tableName);
        DatabaseUtilities d = new DatabaseUtilities();
        String json = d.connectThisDatabaseJson(databaseModel, query, false);
        
        return json;
    }

/**
 *
 * @author Anthony Scheeres
 * 
 *
 */
public void removeUserMode(AccountModel u) {
	PreparedStatmentDatabaseUtilities preparedStatmentDatabaseUtilities = new PreparedStatmentDatabaseUtilities();
	String deletequery =
			"DELETE FROM app_user\r\n" +
					"WHERE username = ?;";

	List<String> f1 = new ArrayList<String>();
	f1.add(u.getUsername());
	List<String> f2 = new ArrayList<String>();
	f2.add(u.getId());
	try {
		preparedStatmentDatabaseUtilities.connectDatabaseThrowQueryReturnsJsonString(databaseModel, deletequery, f1, false);
	} catch (Exception e) {
		  LOGGER.log(Level.SEVERE, "Exception occur", e);
	}
}
}