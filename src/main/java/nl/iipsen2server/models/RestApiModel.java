package main.java.nl.iipsen2server.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RestApiModel {
	@Pattern(regexp="^[0-9]*$")
	private int portNumber;
	@NotNull
	private String hostName;
	@Pattern(regexp="^[0-9]*$")
	private long id;
	
	RestApiModel(){
		
	}
	
	public RestApiModel(@Pattern(regexp = "^[0-9]*$") int portNumber, @NotNull String hostName,
			@Pattern(regexp = "^[0-9]*$") long id) {
		super();
		this.portNumber = portNumber;
		this.hostName = hostName;
		this.id = id;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
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
