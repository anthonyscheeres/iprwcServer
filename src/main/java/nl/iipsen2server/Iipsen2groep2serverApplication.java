package nl.iipsen2server;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.iipsen2server.models.ApplicationModel;
import nl.iipsen2server.models.DataModel;
import nl.iipsen2server.ServerModel;
import nl.iipsen2server.resources.LogResource;
import nl.iipsen2server.resources.ProductResource;
import nl.iipsen2server.resources.UserResource;
import nl.iipsen2server.services.*;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.swing.*;
import java.io.File;
import java.util.EnumSet;



/**
 * @author Anthony Scheeres
 */
class Iipsen2groep2serverApplication extends Application<Configuration> {
	/**
	 * @author Anthony Scheeres
	 */ 
	    public static void main(String[] args) throws Exception {

	        new Iipsen2groep2serverApplication().run(new String[] { "server" });
	        DataModel.getApplicationModel().getServers().get(0).getDatabase().get(0).getPassword();
	    }


	    /**
	     * @author Anthony Scheeres
	     */
	    @Override
	    public void initialize(Bootstrap<Configuration> bootstrap) {
	       
	    }
	    /**
	     * @author Anthony Scheeres, AnthonySchuijlenburg
	     */
	    @Override
	    public void run(Configuration configuration, Environment environment) throws Exception {
	    
	    	
	    	intializeSettings();
	    	 // Enable CORS headers
	        final FilterRegistration.Dynamic cors =
	            environment.servlets().addFilter("CORS", CrossOriginFilter.class);

	        // Configure CORS parameters
	        cors.setInitParameter("allowedOrigins", "*");
	        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
	        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

	        // Add URL mapping
	        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
	        JerseyEnvironment jersey = environment.jersey();
			jersey.register(new UserResource());
			jersey.register(new LogResource());
			jersey.register(new ProductResource());
	    
	    }
	    
	    
	    
	    public void intializeSettings() throws JsonProcessingException {
	    	ApplicationController a = new ApplicationController();
	    	ServerController e =new ServerController();
	    	RestApiController r = new RestApiController();
	    	DatabaseController f = new DatabaseController();
	    	DirectoryController y = new DirectoryController();
	    	ApplicationModel p = new ApplicationModel();  	
	    	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	    	String url = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	    	String folder = "webshopServer";
	    	String file = "config.yml";
	    	String path = url +"/" + folder +"/"+ file;
	    	p.setName("WebshopApp");
	        try {
	        	mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	        	ServerModel server = mapper.readValue(new File(path), ServerModel.class);
	            System.out.println(ReflectionToStringBuilder.toString(server,ToStringStyle.MULTI_LINE_STYLE));
	            ApplicationController i = new ApplicationController();
	            i.add(server, p);
	            System.out.print(server);
	        } catch (Exception e1) {
	        	 MailController m = new MailController();
	        	ServerModel g = e.createNewServer();
	        	r.createNewRest(8080, "localhost", g);
	    		f.createNewDatabase("postgres","",5432,"postgres", "localhost", e.createNewServer()); 
	    		
	    		m.createNewMailModel("****@gmail.com", "******", g);
	    		// Write object as YAML file
	    		String yaml = mapper.writeValueAsString(g);
	    		y.writeFileToDocuments(folder, file, yaml);
	        }
	    	
	    		
	    	  
	    }
	    
	    
	  
	    
	 
	}

