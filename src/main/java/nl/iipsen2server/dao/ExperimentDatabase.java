package main.java.nl.iipsen2server.dao;

import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;

/**
 * @author AnthonySchuijlenburg
 */
public class ExperimentDatabase {

    private String tableName = "projects";
    private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);

    /**
     * @author AnthonySchuijleburg
     */
    public String showExperiments(){
        String query = String.format("select id, project_name, experiment_leader, status from %s;", tableName);
        System.out.println(query);
        return ConnectToDatabase(query);
    }
  
    /**
     * @author AnthonySchuijleburg
     */
    public String showExperiment(int id){
        String query = String.format("SELECT * FROM %s WHERE id = '%d';", tableName, id);
        System.out.println(query);
        return ConnectToDatabase(query);
    }

    /**
     * @author AnthonySchuijleburg
     */

    private String ConnectToDatabase(String url){
        DatabaseUtilities d = new DatabaseUtilities();
        String e1 = null;
        try {
            e1 = d.connectThisDatabase2(databaseModel, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return e1;
    }
  
    /**
     * @author Anthony Scheeres
     */
	public String showAllExperimentHashmap() throws Exception {
		  String query = String.format("select * from %s;", tableName);
	     //   System.out.println(query);
	        DatabaseUtilities d = new DatabaseUtilities();
        return d.connectThisDatabase(databaseModel, query).toString();
	}
	
    /**
     * @author Anthony Scheeres
     */
	public String showAllExperimentJson() throws Exception {
		  String query = String.format("select * from %s;", tableName);
	     //   System.out.println(query);
	        DatabaseUtilities d = new DatabaseUtilities();
        return d.connectThisDatabase2(databaseModel, query);
	}
	
	
	
}
