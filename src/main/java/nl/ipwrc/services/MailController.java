package nl.ipwrc.services;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import nl.ipwrc.dao.DatabaseUtilities;
import nl.ipwrc.dao.SendEmailSMTP;
import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.models.MailModel;
import nl.ipwrc.models.ServerModel;
/**
 * @author Anthony Scheeres
 */
public class MailController {
	 private String tableName = "app_user";
	 private int tokenLength = 20;

	    private static final Logger LOGGER = Logger.getLogger(LoggerController.class.getName());
	 
	 private MailModel createMailModel(String username, String password) {
		 return new MailModel(username, password);
	 }
	 
	 
	 public static void sendMailOnDifferentThread(String text, String mailFrom, String mailTo, String subject) {
		 new Thread(() -> {
     		try {
					sendMail(text, mailFrom, mailTo, subject);
				} catch (Exception e) {

					  LOGGER.log(Level.SEVERE, "Exception occur", e);
				}
     	}).start();
	 }
	 
	 /**
	  * @author Anthony Scheeres
	  */
	 public boolean isValidEmailAddress(String email) {
			EmailValidator emailValidator = new EmailValidator();
			return emailValidator.isValid(email, null);
	 }
	 
	 private String findValideToken(HashMap<String, List<String>> e1) {
		 String token = null;
		   boolean tokenConfirmed = false;
		   
		   while (!tokenConfirmed) {
		   token = getAlphaNumericString1(tokenLength);
		   tokenConfirmed = !tokenExist(e1, token);
		   }
		   return token;
	 }
	 
	 /**
	  * @author Anthony Scheeres
	  */
	 public HashMap<String, List<String>> getTokens() {
		 String query = String.format("select username, token, email from %s;", tableName);
		  DatabaseUtilities databaseUtilites = new DatabaseUtilities();
		  HashMap<String, List<String>> result = null;
		  try {
			  DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
		   result = databaseUtilites.connectThisDatabaseHashMap(databaseModel, query, false);
		  } catch (Exception e2) {
		  }return result;
	 }
	 
	 
	 /**
	  * @author Anthony Scheeres
	  */
	 public String generateToken() {
		 HashMap<String, List<String>> hashmap = getTokens();
		   return findValideToken(hashmap);
	 }
	 /**
	  * @author Anthony Scheeres
	  */
	 private boolean tokenExist(HashMap<String, List<String>> e1, String token) {
		for(String token2: e1.get("token")) {
			if (token.equals(token2)) {
				return true;
			}
		}return false;
		
	}

	 /**
	  * @author Anthony Scheeres
	  */

	// function to generate a random string of length n 
	 private static String getAlphaNumericString1(int n)
	 { 

	     // chose a Character random from this String 
	     String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	                                 + "0123456789"
	                                 + "abcdefghijklmnopqrstuvxyz"; 

	     // create StringBuffer size of AlphaNumericString 
	     StringBuilder sb = new StringBuilder(n); 

	     for (int i = 0; i < n; i++) { 

	         // generate a random number between 
	         // 0 to AlphaNumericString variable length 
	         int index 
	             = (int)(AlphaNumericString.length() 
	                     * Math.random()); 

	         // add Character one by one in end of sb 
	         sb.append(AlphaNumericString 
	                       .charAt(index)); 
	     } 

	     return sb.toString();
}
	 
	 
	 
	 
	 
	 
	 public static void sendMail(String text, String mailFrom, String mailTo, String subject) throws Exception {
		 SendEmailSMTP f = new SendEmailSMTP();
		 f.sendMail(text, mailFrom, mailTo, subject);
	 }



	public void createNewMailModel(String username, String password, ServerModel s) {
		ServerController s2 = new ServerController();
		s2.addMail(createMailModel(username, password), s);
		
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
