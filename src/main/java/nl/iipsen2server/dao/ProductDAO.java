package main.java.nl.iipsen2server.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.nl.iipsen2server.controlllers.MailController;
import main.java.nl.iipsen2server.controlllers.UserController;
import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;
import main.java.nl.iipsen2server.models.ProductModel;
import main.java.nl.iipsen2server.models.UserModel;

public class ProductDAO {
	private String tableName = "product";
	private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);

	   /**
     * @author Anthony Scheeres
     */
    public String showProducts() throws Exception {
        String query = String.format("select * from %s;", tableName);
        System.out.println(query);
        DatabaseUtilities d = new DatabaseUtilities();
        return d.connectThisDatabase2(databaseModel, query);
    }

	public void addProduct(ProductModel product) throws Exception {
		   /**
	     * @author Anthony Scheeres
	     */

	        PreparedStatmentDatabaseUtilities pUtilites = new PreparedStatmentDatabaseUtilities();
	        String query2 = "INSERT INTO app_user(name_p, price) VALUES (" +
	                "?," +
	                "?" +
	                ");";
	       
	        List<String> variables = new ArrayList<>();
	        variables.add(product.getName_p());
	        variables.add(Integer.toString(product.getPrice()));
	  
	        pUtilites.connectDatabaseJson(databaseModel, query2, variables, false);
	    }
		
	}
    
    
    
