package nl.ipwrc.services;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

public class LoggerController {
	 private static final Logger LOGGER = Logger.getLogger(LoggerController.class.getName());
	  	String url = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    	String folder = "webshopServer";
    	String file = "errors.log";
    	String path = url +"/" + folder +"/"+ file;
    	
    	
    public void logger() {
    	
        Handler consoleHandler = null;
        Handler fileHandler  = null;
        try{
            //Creating consoleHandler and fileHandler
            consoleHandler = new ConsoleHandler();
            fileHandler  = new FileHandler(path);
             
            //Assigning handlers to LOGGER object
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);
             
            //Setting levels to handlers and LOGGER
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
             
            LOGGER.config("Configuration done.");
             
            //Console handler removed
            LOGGER.removeHandler(consoleHandler);
             
           // LOGGER.log(Level.FINE, "Finer logged");
            
        }catch(IOException exception){
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
        }
        fileHandler.close();
      //  LOGGER.finer("Finest example on LOGGER handler completed.");
         
    	
    }
 
}
