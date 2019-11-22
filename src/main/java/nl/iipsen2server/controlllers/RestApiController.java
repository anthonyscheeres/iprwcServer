package main.java.nl.iipsen2server.controlllers;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.RestApiModel;
import main.java.nl.iipsen2server.models.ServerModel;

/**
 * @author Anthony Scheeres
 */
public class RestApiController {
  private RestApiModel createRestApi(@Pattern(regexp = "^[0-9]*$") int portNumber, @NotNull String hostName,
									 @Pattern(regexp = "^[0-9]*$") long id) {
	  return new RestApiModel( portNumber,  hostName, id);
  }
  
  
  /**
  *
  * @author Anthony Scheeres
  *
  */
  public RestApiModel createNewRest(@Pattern(regexp = "^[0-9]*$") int portNumber, @NotNull String hostName, ServerModel serverModel) {
  	ServerController s = new ServerController();
  	long id = createRestId(serverModel.getRestApi());
  	RestApiModel u = createRestApi(portNumber,  hostName, id);
  	
  	s.addRestApi(u, DataModel.getApplicationModel().getServers().get(s.getServerById(DataModel.getApplicationModel().getServers(), serverModel.getId())));
  	return u;
  }

  
  
  
  
  /**
  *
  * @author Anthony Scheeres
  *
  */
  private long createRestId(List<RestApiModel> list){
  	 long id = 1;
  	 for (RestApiModel databaseModel : list) {
  		 if (id <= databaseModel.getId()) {
  			 id = databaseModel.getId()+1;
  		 }
  	 }
  	 return id;
  }
  	
}
