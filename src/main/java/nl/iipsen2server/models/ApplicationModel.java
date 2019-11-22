package main.java.nl.iipsen2server.models;

import java.util.List;

import javax.validation.constraints.NotNull;

public class ApplicationModel {
	private List<UserModel> users;
	private List<LogModel> logs;
	@NotNull
	private List<ServerModel> servers;
	private List<OrganisationModel> organisaties;
	private String name;
	private UserModel currentUser;
	public List<UserModel> getUsers() {
		return users;
	}
	
public ApplicationModel() {
		
	}
	
	public ApplicationModel(List<UserModel> users, List<LogModel> logs, @NotNull List<ServerModel> servers,
			List<OrganisationModel> organisaties, String name, UserModel currentUser) {
		super();
		this.users = users;
		this.logs = logs;
		this.servers = servers;
		this.organisaties = organisaties;
		this.name = name;
		this.currentUser = currentUser;
	}



	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
	public List<LogModel> getLogs() {
		return logs;
	}
	public void setLogs(List<LogModel> logs) {
		this.logs = logs;
	}
	public List<ServerModel> getServers() {
		return servers;
	}
	public void setServers(List<ServerModel> servers) {
		this.servers = servers;
	}
	public List<OrganisationModel> getOrganisaties() {
		return organisaties;
	}
	public void setOrganisaties(List<OrganisationModel> organisaties) {
		this.organisaties = organisaties;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserModel getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(UserModel currentUser) {
		this.currentUser = currentUser;
	}
	
	
	

	
	
}
