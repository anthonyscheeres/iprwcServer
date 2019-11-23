package main.java.nl.iipsen2server.controlllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;
import main.java.nl.iipsen2server.models.LogModel;
import main.java.nl.iipsen2server.models.Permission;
import main.java.nl.iipsen2server.models.UserModel;
import main.java.nl.iipsen2server.dao.DatabaseUtilities;
import main.java.nl.iipsen2server.dao.PermissionDAO;
import main.java.nl.iipsen2server.dao.PreparedStatmentDatabaseUtilities;
import main.java.nl.iipsen2server.dao.UserDatabase;
import main.java.nl.iipsen2server.models.AccountModel;


/**
 * @author Anthony Scheeres
 */


public class AccountController {
private UserDatabase userDatabase = new UserDatabase();
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
        if (!checkInputValide(u.getEmail(), u.getPassword())) {
            return "fail";
        }
        try {
            String token = createUserModel(u);
            if (!token.equals(null)) {
                validateEmail(token, u.getEmail());
                return token;
            }
            return "fail";
        } catch (Exception e2) {

        }
        return "fail";
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
   for (int index = 0; index < hashmap.get("username").size(); index++) {
    if (checkCredentials(hashmap.get("username").get(index), hashmap.get("password").get(index), u)) {
    	
    	boolean hasPermission = hashmap.get("permission").get(index).length() ==0     ;
    	if(hasPermission) {
    		   System.out.println("fail");
    		return hashmap.get("token").get(index);
    	}
    	if (hashmap.get("permission").get(index).contains("READ")) {
    		  MailController mailController = new MailController();
    		  UserDatabase userDatabase = new UserDatabase();
   		   String newToken = mailController.generateToken();
   		   System.out.println(newToken);
    		userDatabase.changeToken(newToken,  Integer.parseInt(hashmap.get("user_id").get(index)));
    		return newToken;
    	}
 	   System.out.println("fail2");
     return hashmap.get("token").get(index);
    }
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
  return "fail";
 }

 
 
 

 /**
  *
  * @author Anthony Scheeres
  *  
  * 
  *
  */
 private boolean checkCredentials(String username, String password, UserModel u) throws AuthenticationException {
  if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
   System.out.println("succes");
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
        for (int i = 0; i < data.get("token").size(); i++) {
            if (data.get("email").get(i) != null && data.get("token").get(i) != null) {
                if (token.equals(data.get("token").get(i))) {
                	String yourDomain = getDomeinNameFromMail(data.get("email").get(i).toUpperCase());
                    if ( yourDomain.equals(domain)) {
                    	String accountModel = data.get("username").get(i); //use username to uniquely identify a user 
                    	
              
                        giveRead2(accountModel);
                        return "success";
                    } else return "domein invalid, should be: " + domain;
                }
            }
        }
        return "fail";
    }

    
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