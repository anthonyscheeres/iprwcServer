package main.java.nl.iipsen2server.controlllers;

import java.util.HashMap;
import java.util.List;

import main.java.nl.iipsen2server.dao.DatabaseUtilities;
import main.java.nl.iipsen2server.dao.UserDatabase;
import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;

public class TokenController {
	 
		/**
		*
		* @author Anthony Scheeres
		 *  
		* get tokens from database
		*
		*/
		private HashMap<String, List<String>> getTokens() throws Exception {
		 UserDatabase userDatabase = new UserDatabase();
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
		   e.printStackTrace();
		  }
		  return "fail";
	 }
	 
	 
}
