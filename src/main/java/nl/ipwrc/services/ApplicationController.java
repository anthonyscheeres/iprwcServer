package nl.ipwrc.services;



import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import nl.ipwrc.models.ApplicationModel;
import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.LogModel;
import nl.ipwrc.models.ServerModel;
import nl.ipwrc.models.UserModel;








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
