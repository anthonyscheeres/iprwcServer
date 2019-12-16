package nl.iipsen2server.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.DatabaseModel;
import main.java.nl.iipsen2server.models.ProductModel;
import main.java.nl.iipsen2server.models.UserModel;
import main.java.nl.iipsen2server.services.MailController;
import main.java.nl.iipsen2server.services.UserController;

public class ProductDAO {
	private String tableName = "product";
	private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);
	private String table = "product";
	   /**
     * @author Anthony Scheeres
     */
    public String showProducts() throws Exception {
        String query = String.format("select * from %s;", tableName);
        System.out.println(query);
        DatabaseUtilities d = new DatabaseUtilities();
        return d.connectThisDatabase2(databaseModel, query);
    }

    
    /**
     * @author Anthony Scheeres
     */
    public HashMap<String, List<String>> getProducts() throws Exception {
        DatabaseUtilities d = new DatabaseUtilities();
        String query = String.format("select name, id from %s;", tableName);
        return d.connectThisDatabase(databaseModel, query);
    }

    
    
    
	public void addProduct(ProductModel product) throws Exception {
	        PreparedStatmentDatabaseUtilities pUtilites = new PreparedStatmentDatabaseUtilities();
	        String query2 = "INSERT INTO product(name_p, price) VALUES (" +
	                "?," +
	                "?" +
	                ");";
	       
	        List<String> variables = new ArrayList<>();
	        variables.add(product.getName_p());
	        variables.add(Integer.toString(product.getPrice()));
	   
	        pUtilites.connectDatabaseJson(databaseModel, query2, variables, false);
	    }

	public void changeImg(ProductModel product) {
		byte img = product.getImg();
		long id = product.getId();
		String query = String.format("UPDATE %s\r\n" + 
				"   img\r\n" + 
				"SET %s"
				+ "where id = %d", table , img, id);
	}
		
	}
    
    
    
