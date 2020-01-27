package nl.ipwrc.models;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
	private MailModel mailModel = DataModel.getApplicationModel().getServers().get(0).getMail();
	

	public PasswordAuthentication getPasswordAuthentication() {
        String username = mailModel.getUsername();
        String password = mailModel.getPassword();
        return new PasswordAuthentication(username, password);
    }
}
