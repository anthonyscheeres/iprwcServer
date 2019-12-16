package nl.iipsen2server.services;



import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;




import nl.iipsen2server.models.ApplicationModel;
import nl.iipsen2server.models.DataModel;
import nl.iipsen2server.models.LogModel;
import nl.iipsen2server.models.ServerModel;
import nl.iipsen2server.models.UserModel;








/**
 * @author Anthony Scheeres
 */
public class ApplicationController {
	
	
	
	
	
	 
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