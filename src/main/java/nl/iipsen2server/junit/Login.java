package main.java.nl.iipsen2server.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.nl.iipsen2server.controlllers.AccountController;

class Login {

	
	 /**
     * @author Anthony Scheeres
     */
	@Test
	void testCompareLoginCredentials() {
		AccountController accountController  = new AccountController ();
		String username = "Anthony";
		String password = "passw0rd"; 
		String password2 = "passw0rd";
		assertEquals(true, accountController.checkCredentials(username, password, password2));
	}

}
