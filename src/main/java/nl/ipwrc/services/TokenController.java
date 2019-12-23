package nl.ipwrc.services;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.ipwrc.dao.DatabaseUtilities;
import nl.ipwrc.dao.UserDAO;
import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.models.Response;

public class TokenController {

    private static final Logger LOGGER = Logger.getLogger(LoggerController.class.getName());
		/**
		*
		* @author Anthony Scheeres
		 *  
		* get tokens from database
		*
		*/
		private HashMap<String, List<String>> getTokens() throws Exception {
		 UserDAO userDatabase = new UserDAO();
		 return userDatabase.getTokens();
	 }
	 
	 
	 private boolean isStringEmty(String token){
		  if (token.length()!=0) {
			  return true;
		  }
		  return false;
	 }
	 
	 
	 
		/**
		*
		* @author Anthony Scheeres
		 *  
		* 
		*looks if token exist in hashmap
		*/
		private String findValideTokenInHashmap(HashMap<String, List<String>> hashmap, String token) {
		 //System.out.println(hashmap.get("token").get(0));
		 String result = null ;
		 
		 
		   for (int index = 0; index <hashmap.get("token").size(); index++) {
			  String myToken =  hashmap.get("token").get(index);
			   if (isStringEmty(myToken)) {
				   if (myToken.equals(token)) {
					   result = hashmap.get("user_id").get(index);; 
				   }
			   }
			   }
		   return result;
	 }
	
	 
	 
	 /**
	 *
	 * @author Anthony Scheeres
	 *  
	 * 
	 *
	 */
	 public String tokenToUserId(String token) {
		  HashMap < String, List < String >> hashmap = null;
		  try {
			  hashmap = getTokens();
		   return findValideTokenInHashmap( hashmap, token);
		   }
		   catch (Exception e) {

				  LOGGER.log(Level.SEVERE, "Exception occur", e);
		  }
		  return Response.fail.toString();
	 }
	 
	 
}
