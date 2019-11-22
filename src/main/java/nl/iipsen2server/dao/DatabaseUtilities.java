package main.java.nl.iipsen2server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.nl.iipsen2server.models.DatabaseModel;




public class DatabaseUtilities {

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
            e1.printStackTrace();
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
    public HashMap < String, List < String >> connectThisDatabase(DatabaseModel databaseModel, String query) throws Exception {

        HashMap < String, List < String >> hashmap = connectToDatabase2(
            databaseModel.getUsername(),
            databaseModel.getPassword(),
            databaseModel.getPortNumber(),
            databaseModel.getDatabaseName(),
            databaseModel.getHostName(),
            query
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
    public String connectThisDatabase2(DatabaseModel databaseModel, String query) throws Exception {

        return connectToDatabase(
        		databaseModel.getUsername(),
        		databaseModel.getPassword(),
        		databaseModel.getPortNumber(),
        		databaseModel.getDatabaseName(),
        		databaseModel.getHostName(),
        		query
        		);
    }

    public String createUrl(int portNumber, String databaseName, String hostName) {
        return String.format("jdbc:postgresql://%s:%s/%s", hostName, portNumber, databaseName);
    }



    /**
     *
     * @author Anthony Scheeres
     * @throws Exception 
     *
     */
    private String connectToDatabase(
            String username,
            String password,
            int portNumber,
            String databaseName,
            String hostName,
            String query
    ) throws Exception {
    	  String result = null;
        String url = createUrl(portNumber, databaseName, hostName);
        HashMap < String, List < String >> hashmap = new HashMap < String, List < String >> ();
        // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within 
        // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
        //     Class.forName("org.postgresql.Driver"); 
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Java JDBC PostgreSQL: " + databaseName);

            ResultSet resultSet = this.enterQuery(connection, query);
            JsonConverterUtilities jsonConverer = new JsonConverterUtilities();
            String json = jsonConverer.convertToJSON(resultSet).toString();
            connection.close();
            result = json;
        } catch (SQLException err) {
            System.out.println("Connection failure.");
            err.printStackTrace();
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
    	  HashMap<String, List<String>> result = null;
        String url = createUrl(portNumber, databaseName, hostName);
        HashMap < String, List < String >> e = new HashMap < String, List < String >> ();
        // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within 
        // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
        //     Class.forName("org.postgresql.Driver"); 
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Java JDBC PostgreSQL: " + databaseName);
            ResultSet resultSet = this.enterQuery(connection, query);
            HashMap < String, List < String >> hashmap = getTableContents2(resultSet);
            connection.close();
            result =  hashmap;
        } catch (SQLException err) {
            System.out.println("Connection failure.");
            err.printStackTrace();
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
            e.printStackTrace();
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
        System.out.println(query);

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {

            e.printStackTrace();
        }
            return result;
        



    }

    /**
     *
     * @author Anthony Scheeres
     *
     */
    // this methode can be used to insert an query
    public int enterUpdate(Connection connection, String query) {
        Statement statement;
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}