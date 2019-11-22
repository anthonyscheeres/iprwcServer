package main.java.nl.iipsen2server.models;

public class MailModel {
	private String username;
	private String password;
	public MailModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public MailModel() {
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
