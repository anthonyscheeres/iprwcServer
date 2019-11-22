package main.java.nl.iipsen2server.controlllers;



import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;




import main.java.nl.iipsen2server.models.ApplicationModel;
import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.LogModel;
import main.java.nl.iipsen2server.models.OrganisationModel;
import main.java.nl.iipsen2server.models.ServerModel;
import main.java.nl.iipsen2server.models.UserModel;








/**
 * @author Anthony Scheeres
 */
public class ApplicationController {
	
	
	
	/**
	 * @author Anthony Scheeres
	 * @return 
	 */	
	public ApplicationModel createNewApplicationModel(String name) {
		ApplicationModel applicationModel= createApplication(
				new ArrayList<UserModel>(), 
				new ArrayList<LogModel>(),
				new ArrayList<ServerModel>(), 
				new ArrayList<OrganisationModel>(), 
				name, 
				null
				);
	DataModel.setApplicationModel(applicationModel); 
	return applicationModel;
	}
	
	
	
	/**
	 * @author Anthony Scheeres
	 * @param currentUser 
	 */
	private ApplicationModel createApplication(List<UserModel> users, List<LogModel> logs, @NotNull List<ServerModel> servers,
											   List<OrganisationModel> organisaties, String name, UserModel currentUser){
		return new  ApplicationModel(users, logs, servers, organisaties, name, currentUser);
	}
	
	
	 
	 /**
	 *
	 * @author Anthony Scheeres
	 *
	 */
	public void addUser(UserModel u2, ApplicationModel app){
		app.getUsers().add(u2);
	 }
	
	
	
	/**
	 * @author Anthony Scheeres
	 */
	public void add(ServerModel s , ApplicationModel a){

		a.getServers().add(s);
		
	}	
}
