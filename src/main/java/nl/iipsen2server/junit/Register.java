package main.java.nl.iipsen2server.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.nl.iipsen2server.controlllers.AccountController;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class Register {

	@Test
	void testInOutputValidator() {
		AccountController accountController  = new 	AccountController ();
		String email = "info@anthonyscheeres.nl";
		String password = "";
		assertEquals(false, accountController.checkInputValide(email, password));
	}

}
