package nl.ipwrc.junit;


import org.junit.jupiter.api.Test;

import nl.ipwrc.services.AccountController;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Register {

	@Test
	void testInOutputValidator() {
		AccountController accountController  = new 	AccountController ();
		String email = "info@anthonyscheeres.nl";
		String password = "";
		assertEquals(false, accountController.checkInputValide(email, password));
	}

}
