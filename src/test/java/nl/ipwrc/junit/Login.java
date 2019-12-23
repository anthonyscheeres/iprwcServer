package nl.ipwrc.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import nl.ipwrc.services.AccountController;

class Login {

	
	 /**
     * @author Anthony Scheeres
     */
	@Test
	void testCompareLoginCredentials() {
		AccountController accountController  = new AccountController ();
		String username = "Anthony";
		String username2 = "Anthony";
		String password = "passw0rd"; 
		String password2 = "passw0rd";
		assertEquals(true, accountController.checkCredentials(username, username2, password, password2));
	}

}
