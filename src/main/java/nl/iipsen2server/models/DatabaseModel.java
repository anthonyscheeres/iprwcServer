package main.java.nl.iipsen2server.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DatabaseModel  {
	@NotNull
	private String username;
	@NotNull
	private String password;
	@Pattern(regexp="^[0-9]*$")
	private int portNumber;
	@NotNull
	private String databaseName;
	@NotNull
	private String hostName;
	@Pattern(regexp="^[0-9]*$")
	private long id;
	public DatabaseModel(@NotNull String username, @NotNull String password,
			@Pattern(regexp = "^[0-9]*$") int portNumber, @NotNull String databaseName, @NotNull String hostName,
			@Pattern(regexp = "^[0-9]*$") long id) {
		super();
		this.username = username;
		this.password = password;
		this.portNumber = portNumber;
		this.databaseName = databaseName;
		this.hostName = hostName;
		this.id = id;
	}
	
	public DatabaseModel() {
		
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
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	

	
	


}
