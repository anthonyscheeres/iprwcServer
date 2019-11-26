package main.java.nl.iipsen2server.controlllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;
import main.java.nl.iipsen2server.models.LogModel;
import main.java.nl.iipsen2server.models.Permission;
import main.java.nl.iipsen2server.models.Response;
import main.java.nl.iipsen2server.models.User;
import main.java.nl.iipsen2server.models.UserModel;
import main.java.nl.iipsen2server.dao.DatabaseUtilities;
import main.java.nl.iipsen2server.dao.PermissionDAO;
import main.java.nl.iipsen2server.dao.PreparedStatmentDatabaseUtilities;
import main.java.nl.iipsen2server.dao.UserDAO;
import main.java.nl.iipsen2server.models.AccountModel;


/**
 * @author Anthony Scheeres
 */


public class AccountController {
private UserDAO userDatabase = new UserDAO();
private PermissionDAO permissionDatabase = new PermissionDAO();


/**
*
* @author Anthony Scheeres

*
*
*/

/**
 * @return
 * @author Anthony Scheeres
 */
    public boolean giveRead2(String username) {
        return permissionDatabase.giveRead2(username);
    }


    /**
     * @author Anthony Scheeres
     */
    public boolean giveWrite2(String user) {
        return permissionDatabase.giveWrite2(user);
    }

    /**
     * @author Anthony Scheeres
     */
    public boolean giveDelete2(String user) {
        return permissionDatabase.giveDelete2(user);
    }

    /**
     * @author Anthony Scheeres
     */
    private String createUserModel(UserModel userModel) throws Exception {
        UserController r = new UserController();
        HashMap<String, List<String>> hashmap;
        String result = null;
        hashmap = userDatabase.getUsers();
        if (r.checkIfUsernameExist(hashmap.get("username"), userModel.getUsername()) != true) {
        	  result =  userDatabase.insertHandlerUser(hashmap, userModel);
        }
        return result;
    }


    /**
     * @author Anthony Scheeres
     */
    private boolean checkInputValide(String email, String password) {
        MailController m = new MailController();
        if (!m.isValidEmailAddress(email)) {
            return false;
        }

        if (password.length() == 0) {
            return false;
        }
        return true;
    }


    /**
     * @author Anthony Scheeres
     */
    public String handleCreateUserModel2(UserModel u) {
    	String fail = Response.fail.toString();
        if (!checkInputValide(u.getEmail(), u.getPassword())) {
            return fail;
        }
        try {
            String token = createUserModel(u);
            if (!token.equals(null)) {
                validateEmail(token, u.getEmail());
                return token;
            }
            return fail;
        } catch (Exception e2) {

        }
        return fail;
    }


    /**
     * @author Anthony Scheeres
     */
    private void validateEmail(String token, String email) throws Exception {
        MailController.sendMail(String.format(
                "Open de volgende link om uw email te valideren: http://%s:%s/user/%s/token",
                DataModel.getApplicationModel().getServers().get(0).getRestApi().get(0).getHostName(),
                DataModel.getApplicationModel().getServers().get(0).getRestApi().get(0).getPortNumber(),
                token
                ),
                "testlab",
                email,
                "Valideer u email!");
    }

 /**
  *
  * @author Anthony Scheeres
  *  
  *
  */
 public String checkLogin(UserModel u) {
  HashMap < String, List < String >> hashmap;
  try {
   hashmap = userDatabase.getUserInfo();
   List<String> users = hashmap.get(User.username.toString());
   for (int index = 0; index < users.size(); index++) {
	   
	   
	   String username = hashmap.get(User.username.toString()).get(index);
	   String passwordFromDatabase = hashmap.get(User.password.toString()).get(index); 
	   String passwordFromClient = u.getPassword();
	   String token = hashmap.get(User.token.toString()).get(index);
	   String permission = hashmap.get(User.permission.toString()).get(index);
	   String UserId = hashmap.get(User.user_id.toString()).get(index);
	   
    if (checkCredentials(username, passwordFromDatabase,  passwordFromClient)) {
    
    	boolean hasPermission = permission.length() ==0;
    	if(hasPermission) {
    		return token;
    	}
    	if (permission.contains(Permission.READ.toString())) {
    		 String newToken =  askNewTokenForAccount(Integer.parseInt(UserId));
    		  return newToken;
    	}
     return token;
    }
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
  return Response.fail.toString();
 }
 
 
 /**
  * @author Anthony Scheeres
  */
private String askNewTokenForAccount(int id) {
	  MailController mailController = new MailController();
	  UserDAO userDatabase = new UserDAO();
	  String newToken = mailController.generateToken();
	  userDatabase.changeToken(newToken, id);
	  return newToken;
}
 
 
 

 /**
  *
  * @author Anthony Scheeres
  *  
  * 
  *
  */
 public boolean checkCredentials(String username, String password, String password2){
  if (username.equals(password2) && password.equals(password2)) {
   return true;
  }
  return false;
 }

    /**
     * @author Anthony Scheeres
     */
    public String validateToken(String token) {
        MailController mailController = new MailController();
        HashMap<String, List<String>> data = mailController.getTokens();
        String domain = "OM.NL";
        for (int i = 0; i < data.get(User.token.toString()).size(); i++) {
        	String email = data.get(User.email.toString()).get(i);
        	String tokenFromDatabase = data.get(User.token.toString()).get(i);
        	
        	
            if (email != null && tokenFromDatabase != null) {
                if (token.equals(tokenFromDatabase)) {
                	String yourDomain = getDomeinNameFromMail(email.toUpperCase());
                    if ( yourDomain.equals(domain)) {
                    	String accountModel = data.get(User.username.toString()).get(i); //use username to uniquely identify a user 
                        giveRead2(accountModel);
                        return Response.success.toString();
                    } else return "domein invalid, should be: " + domain;
                }
            }
        }
        return Response.fail.toString();
    }

    /**
     * @author Anthony Scheeres
     */
    private String getDomeinNameFromMail(String email){
    	return email.split("@")[1];
    }
    

    /**
     * @author Jesse Poleij, Anthony Scheeres
     */
    public void handleRemoveUser(AccountModel u, String token) {
        userDatabase.removeUserMode(u);
    }
}