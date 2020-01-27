package nl.ipwrc.services;

public class CredentialController {
	 

	 /**
	  *
	  * @author Anthony Scheeres
	  *  
	  * 
	  *
	  */
	 public boolean checkCredentials(String username,String username2, String password, String password2){
	  return username.equals(username2) && password.equals(password2);

	 }
	 
	   /**
	     * @author Anthony Scheeres
	     */
	    public boolean checkInputValide(String email, String password) {
	        MailController m = new MailController();
	        if (!m.isValidEmailAddress(email)) {
	            return false;
	        }

	        if (password.length() == 0) {
	            return false;
	        }
	        return true;
	    }
	 
	 
}
