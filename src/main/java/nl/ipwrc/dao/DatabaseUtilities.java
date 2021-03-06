package nl.ipwrc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.services.LoggerController;




public class DatabaseUtilities {

    private static final Logger LOGGER = Logger.getLogger(LoggerController.class.getName());
    /**
     *
     * @author Anthony Scheeres
     *
     */
    // potenially returns all data from an table added an methode that returns all column values in an 2d array!!
    public HashMap < String, List < String >> getTableContents2(ResultSet resultSet) {
        HashMap < String, List < String >> hashmap = new HashMap < String, List < String >> ();
        List < List < String >> array = new ArrayList < List < String >> ();
        List < String > singleArray = new ArrayList < String > ();
        singleArray = getColumnNames(resultSet);
        //trying to fit a table in a variable using 2d array lists
        try {
            for (int i = 0; i < singleArray.size(); i++) {
                array.add(new ArrayList < String > ());
            }
            while (resultSet.next()) {
                for (int index = 0; index < singleArray.size(); index++) {
                    array.get(index).add((resultSet.getString(singleArray.get(index))));
                }
            }
            for (int index = 0; index < singleArray.size(); index++) {
                hashmap.put(singleArray.get(index), array.get(index));
            }
        } catch (SQLException e1) {

			  LOGGER.log(Level.SEVERE, "Exception occur", e1);
        }
        return hashmap;
    }


    /**
    *
    * @author Anthony Scheeres
    * @return 
    * @throws Exception 
    *
    */
   //use a database object to connect to database and perform a query
   public HashMap < String, List < String >> connectThisDatabaseHashMap(DatabaseModel databaseModel, String query) throws Exception {
	   boolean isUpdate = false;
	   return  connectThisDatabaseHashMap(databaseModel, query, isUpdate);
   }

    /**
     *
     * @author Anthony Scheeres
     * @return 
     * @throws Exception 
     *
     */
    //use a database object to connect to database and perform a query
    public HashMap < String, List < String >> connectThisDatabaseHashMap(DatabaseModel databaseModel, String query, boolean isUpdate) throws Exception {

        HashMap < String, List < String >> hashmap = connectToDatabase2(
            databaseModel.getUsername(),
            databaseModel.getPassword(),
            databaseModel.getPortNumber(),
            databaseModel.getDatabaseName(),
            databaseModel.getHostName(),
            query,
            isUpdate
        );
        return hashmap;
    }



    /**
     *
     * @author Anthony Scheeres
     * @return 
     * @throws Exception 
     *
     */
    //use a database object to connect to database and perform a query
    public String connectThisDatabaseJson(DatabaseModel databaseModel, String query, boolean isUpdate) throws Exception {

        return connectToDatabaseReturnJson(
        		databaseModel.getUsername(),
        		databaseModel.getPassword(),
        		databaseModel.getPortNumber(),
        		databaseModel.getDatabaseName(),
        		databaseModel.getHostName(),
        		query,
        		isUpdate
        		);
    }

    public String createUrl(int portNumber, String databaseName, String hostName) {
        return String.format("jdbc:postgresql://%s:%s/%s", hostName, portNumber, databaseName);
    }



    /**
     *
     * @author Anthony Scheeres
     * @param isUpdate 
     * @throws Exception 
     *
     */
    private String connectToDatabaseReturnJson(
            String username,
            String password,
            int portNumber,
            String databaseName,
            String hostName,
            String query, boolean isUpdate
    ) throws Exception {
    	  String result = null;
    	  String json = null;
        String url = createUrl(portNumber, databaseName, hostName);
        HashMap < String, List < String >> hashmap = new HashMap < String, List < String >> ();
        // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within 
        // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
        //     Class.forName("org.postgresql.Driver"); 
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
           // "Java JDBC PostgreSQL: " + databaseName);
        	 if(isUpdate){
        		 this.enterUpdate(connection, query);
                 
                 result = "Update was succecfull";
             }else{
                 
            ResultSet resultSet = this.enterQuery(connection, query);
            JsonConverterUtilities jsonConverer = new JsonConverterUtilities();
            json = jsonConverer.convertToJSON(resultSet).toString();
             }
            connection.close();
            result = json;
        } catch (SQLException err) {
          //  "Connection failure.");

			  LOGGER.log(Level.SEVERE, "Exception occur", err);
        }
        return result;
    }



    /**
     *
     * @author Anthony Scheeres
     * @throws Exception 
     *
     */
    private HashMap < String, List < String >> connectToDatabase2(
            String username,
            String password,
            int portNumber,
            String databaseName,
            String hostName,
            String query
           
    ) throws Exception {
    	 boolean isUpdate =  false;
    	return connectToDatabase2(
    	     username,
    	          password,
    	         portNumber,
    	           databaseName,
    	            hostName,
    	            query,
    	            isUpdate
    	    );
    	 
    }

    /**
     *
     * @author Anthony Scheeres
     * @throws Exception 
     *
     */
    private HashMap < String, List < String >> connectToDatabase2(
            String username,
            String password,
            int portNumber,
            String databaseName,
            String hostName,
            String query,
            boolean isUpdate
    ) throws Exception {
    	  HashMap<String, List<String>> result = null;
        String url = createUrl(portNumber, databaseName, hostName);
        HashMap<String, List<String>> hashmap=null;
        HashMap < String, List < String >> e = new HashMap < String, List < String >> ();
        // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within 
        // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
        //     Class.forName("org.postgresql.Driver"); 
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
       //     "Java JDBC PostgreSQL: " + databaseName);
       	 if(isUpdate){
    		 this.enterUpdate(connection, query);
             
             result = null;
         }else{
            ResultSet resultSet = this.enterQuery(connection, query);
            hashmap = getTableContents2(resultSet);
         }
            connection.close();
       
			result =  hashmap;
        } catch (SQLException err) {
         //   "Connection failure.");

			  LOGGER.log(Level.SEVERE, "Exception occur", e);
        }
        return result;
    }




    /**
     *
     * @author Anthony Scheeres
     *
     */
    //connect to postgres database 
    private List < String > getColumnNames(ResultSet resultSet) {
        List < String > columnNames = new ArrayList < String > ();
        try {
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();
            for (int index = 1; index < numberOfColumns + 1; index++) {
                String columnName;
                columnName = rsMetaData.getColumnName(index);
                columnNames.add(columnName);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
             LOGGER.log(Level.SEVERE, "Error occur", e);
        }
        return columnNames;
    }

    /**
     * @author Anthony Scheeres
     */
    // this methode can be used to insert an query
    private ResultSet enterQuery(Connection connection, String query) {
        Statement statement;
        ResultSet result = null;
    

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {

			  LOGGER.log(Level.SEVERE, "Exception occur", e);
        }
            return result;
        



    }

    /**
     *
     * @author Anthony Scheeres
     *
     */
    // this methode can be used to insert an query
    public void enterUpdate(Connection connection, String query) {
        Statement statement;
        try {
            statement = connection.createStatement();
             statement.executeUpdate(query);
        } catch (SQLException e) {

			  LOGGER.log(Level.SEVERE, "Exception occur", e);
           
        }
    }

}