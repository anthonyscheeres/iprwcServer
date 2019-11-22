package main.java.nl.iipsen2server.models;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
	private MailModel mailModel = DataModel.getApplicationModel().getServers().get(0).getMail();
	

	public PasswordAuthentication getPasswordAuthentication() {
        String username = mailModel.getUsername();
        String password = mailModel.getPassword();
        System.out.println("username : "+username+"\n password : "+ password);
        return new PasswordAuthentication(username, password);
    }
}
