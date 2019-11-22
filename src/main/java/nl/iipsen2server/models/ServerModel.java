package main.java.nl.iipsen2server.models;

import java.util.List;

import javax.validation.constraints.NotNull;



public class ServerModel {
	private List<RestApiModel> restApi;
	private List<DatabaseModel> database;
	private MailModel mail;
	@NotNull
	private long id;
	
	public ServerModel() {
		
	}

	public ServerModel(List<RestApiModel> restApi, List<DatabaseModel> database, MailModel mail, @NotNull long id) {
		super();
		this.restApi = restApi;
		this.database = database;
		this.mail = mail;
		this.id = id;
	}

	public List<RestApiModel> getRestApi() {
		return restApi;
	}

	public void setRestApi(List<RestApiModel> restApi) {
		this.restApi = restApi;
	}

	public List<DatabaseModel> getDatabase() {
		return database;
	}

	public void setDatabase(List<DatabaseModel> database) {
		this.database = database;
	}

	public MailModel getMail() {
		return mail;
	}

	public void setMail(MailModel mail) {
		this.mail = mail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
}
