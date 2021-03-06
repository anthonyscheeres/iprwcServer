package nl.ipwrc.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.services.LoggerController;

public class PreparedStatmentDatabaseUtilities {


    private static final Logger LOGGER = Logger.getLogger(LoggerController.class.getName());

    
    
    /**
    *
    * @author Anthony Scheeres
    * @return  
    *
    */
   //use a database object to connect to database and perform a query
   public String connectDatabaseJson(
   		DatabaseModel databaseModel, 
   		String query, 
   		List < String > values
   		) throws Exception {
	   
	   boolean isUpdate = false;
	   
	   return  connectDatabaseThrowQueryReturnsJsonString(databaseModel, query, values,isUpdate );
   }
   
   
    
    /**
     *
     * @author Anthony Scheeres
     * @return  
     *
     */
    //use a database object to connect to database and perform a query
    public String connectDatabaseThrowQueryReturnsJsonString(
    		DatabaseModel databaseModel, 
    		String query, 
    		List < String > values,
            boolean isUpdate 
    		) throws Exception {
    	
        return connectToDatabaseJson(
        		databaseModel.getUsername(),
        		databaseModel.getPassword(),
        		databaseModel.getPortNumber(),
        		databaseModel.getDatabaseName(),
        		databaseModel.getHostName(),
        		query,
        		values,
                isUpdate
        		);
    }


    /**
     *
     * @author Anthony Scheeres
     * @return 
     *
     */
    //use a database object to connect to database and perform a query
    public HashMap < String, List < String >> connectDatabaseHashmap(
    		DatabaseModel databaseModel, 
    		String query, 
    		List < String > values) 
    				throws Exception {

        return connectToDatabaseHashmap(
        		databaseModel.getUsername(),
        		databaseModel.getPassword(),
        		databaseModel.getPortNumber(),
        		databaseModel.getDatabaseName(),
        		databaseModel.getHostName(),
        		query,
        		values
        		);
    }





    /**
     *
     * @author Anthony Scheeres
     * @return 
     * @throws Exception 
     *
     */
    private String connectToDatabaseJson(
            String username,
            String password, int portNumber,
            String databaseName,
            String hostName,
            String query,
            List<String> values,
            boolean isUpdate
    ) throws Exception {
    	  String result = null;
        DatabaseUtilities dataUtilities = new DatabaseUtilities();
        String url = dataUtilities.createUrl(portNumber, databaseName, hostName);
        // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within 
        // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
        //     Class.forName("org.postgresql.Driver"); 


        try  {
          //  "Java JDBC PostgreSQL: " + databaseName);
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement pstmt = connection.prepareStatement(query);
            int counter = 0;
            for (int index = 0; index < values.size(); index++) {
                counter = index + 1;
                
                if(!values.equals(null)) {
                if (isNumeric(values.get(index))) {
                    pstmt.setInt(counter, Integer.parseInt(values.get(index)));
                } else {

                    pstmt.setString(counter, values.get(index));
                }
                }
            }

            if(isUpdate){
                pstmt.executeUpdate();
                
                result = "Update was succecfull";
            }else{
                ResultSet r = pstmt.executeQuery();
                JsonConverterUtilities jsonConverter = new JsonConverterUtilities();
                result= jsonConverter.convertToJSON(r).toString();
                connection.close();
                pstmt.close();
                ;
            }

        } catch (SQLException err) {

            LOGGER.log(Level.SEVERE, "Exception occur", err);
        }

        return result;




    }




    /**
     *
     * @author Anthony Scheeres
     * @return 
     * @throws Exception 
     *
     */
    private HashMap < String, List < String >> connectToDatabaseHashmap(
            String username,
            String password, int portNumber,
            String databaseName,
            String hostName,
            String query,
            List<String> values
    ) throws Exception {
    	  HashMap<String, List<String>> result = null;
        DatabaseUtilities dUtilities = new DatabaseUtilities();
        String url = dUtilities.createUrl(portNumber, databaseName, hostName);
        // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within 
        // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
        //     Class.forName("org.postgresql.Driver"); 
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
         //   "Java JDBC PostgreSQL: " + databaseName);
            PreparedStatement pstmt = connection.prepareStatement(query);
            int counter = 0;
            for (int index = 0; index < values.size(); index++) {
                counter = index + 1;
  
                if (isNumeric(values.get(index))) {
                    pstmt.setInt(counter, Integer.parseInt(values.get(index)));
                } else {
                    pstmt.setString(counter, values.get(index));
                }
            }
   
            ResultSet r = pstmt.executeQuery();
            DatabaseUtilities g = new DatabaseUtilities();
            HashMap < String, List < String >> hashmap = g.getTableContents2(r);
            connection.close();
            result = hashmap;
        } catch (SQLException err) {
        
            LOGGER.log(Level.SEVERE, "Exception occur", err);
        }

        return result;




    }


    /**
     *
     * @author Anthony Scheeres
     * @return 
     * @throws Exception 
     *
     */
    public static boolean isNumeric(String strNum) {
        try {
            double integer = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }


}