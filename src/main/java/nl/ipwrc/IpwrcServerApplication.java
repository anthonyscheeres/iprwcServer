package nl.ipwrc;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.ipwrc.models.*;
import nl.ipwrc.resources.*;
import nl.ipwrc.services.*;

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
class IpwrcServerApplication extends Application<Configuration> {
	/**
	 * @author Anthony Scheeres
	 */ 
	    public static void main(String[] args) throws Exception {

	        new IpwrcServerApplication().run(new String[] { "server" });
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
	    	String name = "WebshopServer";
	    	p.setName(name);
	    	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	    	String url = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	    	String folder = name;
	    	String file = "config.yml";
	    	String path = url +"/" + folder +"/"+ file;
	        try {
	        	
	        	mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	        	ServerModel server = mapper.readValue(new File(path), ServerModel.class);
	            System.out.println(ReflectionToStringBuilder.toString(server,ToStringStyle.MULTI_LINE_STYLE));
	            ApplicationController i = new ApplicationController();
	            i.add(server, p);
	            System.out.print(server);
	        } catch (Exception e1) {
	        	 MailController mailController = new MailController();
	        	ServerModel serverModel = e.createNewServer();
	        	r.createNewRest(8080, "localhost", serverModel);
	        	//TODO Change database name to postgres2.0
	    		f.createNewDatabase("ipsen3","ipsen3",5432,"ipsen3", "85.214.16.118", e.createNewServer());
	    		mailController.createNewMailModel("****@gmail.com", "******", serverModel);
	    		// Write object as YAML file
	    		String yaml = mapper.writeValueAsString(serverModel);
	    		System.out.println(yaml);
	    		y.writeFileToDocuments(folder, file, yaml);
	        }
	    }
	    
	    
	  
	    
	 
	}

