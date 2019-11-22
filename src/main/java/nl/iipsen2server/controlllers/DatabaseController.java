package main.java.nl.iipsen2server.controlllers;


import java.util.List;

import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;

import main.java.nl.iipsen2server.models.ServerModel;


/**
*
* @author Anthony Scheeres
*
*/
public class DatabaseController {
	

	
	 /**
	 *
	 * @author Anthony Scheeres
	 *
	 */
	public int getServerById( long id, List<ServerModel> list) {
		
		  for(int index=0; index< list.size(); index++){
			if (list.get(index).getId() == id) {
				return index;
			}
			
		}
		  return 0;
	}

	/**
	 *
	 * @author Anthony Scheeres
	 * @return 
	 *
	 */
	private DatabaseModel createDatabase(
			String username,
			String password,
			int portNumber,
			String databaseName,
			String hostName,
			long id
	) {
	return new DatabaseModel(username, password, portNumber, databaseName, hostName, id);
}
/**
*
* @author Anthony Scheeres
*
*/
public void createNewDatabase(
		String username, 
		String password, 
		int portNumber, 
		String databaseName,
		String hostName,
		ServerModel serverModel
		) {
	
	ServerController serverController = new ServerController();
	long id = createDatabaseId(serverModel.getDatabase());
	DatabaseModel databaseModel = createDatabase(username, password, portNumber, databaseName, hostName, id);
	serverController.addDatabase(databaseModel, DataModel.getApplicationModel().getServers().get(serverController.getServerById(DataModel.getApplicationModel().getServers(), serverModel.getId())));
}



/**
*
* @author Anthony Scheeres
*
*/
private long createDatabaseId(List<DatabaseModel> list){
	 long id = 1;
	 for (DatabaseModel databaseModel : list) {
		 if (id <= databaseModel.getId()) {
			 id = databaseModel.getId()+1;
		 }
	 }
	 return id;
}
	
	
	
	

	
 
}
