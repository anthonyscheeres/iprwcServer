package nl.ipwrc.main;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import nl.ipwrc.models.ApplicationModel;
import nl.ipwrc.models.DataModel;
import nl.ipwrc.models.ServerModel;
import nl.ipwrc.resources.LogResource;
import nl.ipwrc.resources.ProductResource;
import nl.ipwrc.resources.UserResource;
import nl.ipwrc.services.ApplicationController;
import nl.ipwrc.services.DatabaseController;
import nl.ipwrc.services.DirectoryController;
import nl.ipwrc.services.LoggerController;
import nl.ipwrc.services.MailController;
import nl.ipwrc.services.RestApiController;
import nl.ipwrc.services.ServerController;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Anthony Scheeres
 */
class IpwrcServerApplication extends Application<Configuration> {
	/**
	 * @author Anthony Scheeres
	 */ 
	    public static void main(String[] args) throws Exception {

	        new IpwrcServerApplication().run(new String[] { "server" });
	       
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
	    	DirectoryController directoryController = new DirectoryController();
	    	   	
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
	    DirectoryController directoryController  = new DirectoryController ();
	    LoggerController  loggerController  = new  LoggerController ();
	    directoryController.intializeConfigSettings();
	    loggerController.logger();
	    }
    
	}

