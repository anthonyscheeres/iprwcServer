package nl.ipwrc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.DatabaseModel;
import nl.ipwrc.models.ProductModel;
import nl.ipwrc.models.UserModel;
import nl.ipwrc.services.MailController;
import nl.ipwrc.services.UserController;

public class ProductDAO {
	private String tableName = "product";
	private DatabaseModel databaseModel = DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0);

	   /**
     * @author Anthony Scheeres
     */
    public String showProducts() throws Exception {
        String query = String.format("select * from %s order by name_p;", tableName);
        
        DatabaseUtilities d = new DatabaseUtilities();
        return d.connectThisDatabaseJson(databaseModel, query, false);
    }

    
    /**
     * @author Anthony Scheeres
     */
    public HashMap<String, List<String>> getProducts() throws Exception {
        DatabaseUtilities databaseUtilites = new DatabaseUtilities();
        String query = String.format("select name_p, id from %s order by name_p;", tableName);
        return databaseUtilites.connectThisDatabaseHashMap(databaseModel, query);
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

	public void changeImg(ProductModel product) throws Exception {
	     DatabaseUtilities d = new DatabaseUtilities();
		String img = product.getImg();
	
		long id = product.getId();
		String query2 = String.format(
				"UPDATE %s "
				+ "SET img = ? "
				+ "where id = ?; "
				, tableName);
		
		
		  PreparedStatmentDatabaseUtilities pUtilites = new PreparedStatmentDatabaseUtilities();
		    List<String> variables = new ArrayList<>();
	        variables.add(img);
	        variables.add(Long.toString(id));
		 pUtilites.connectDatabaseJson(databaseModel, query2, variables, false);
		
		
	}
	}
    
    
    
