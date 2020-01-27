package nl.ipwrc.junit;


import org.junit.jupiter.api.Test;

import nl.ipwrc.services.AccountController;
import nl.ipwrc.services.CredentialController;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Register {

	@Test
	void testInOutputValidator() {
		CredentialController accountController  = new CredentialController ();
		String email = "info@anthonyscheeres.nl";
		String password = "";
		assertEquals(false, accountController.checkInputValide(email, password));
	}

}
