package nl.ipwrc.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import nl.ipwrc.models.ApplicationModel;
import nl.ipwrc.models.ServerModel;

public class DirectoryController {
		

    private static final Logger LOGGER = Logger.getLogger(LoggerController.class.getName());
  

/**
*
* @author Anthony Scheeres
*
*/
	  public String writeFileToDocuments(String folder, String fileName, String value){
	    	String url = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	  
	    	  String result = null;
	    	try {
				result = writeFile(url, folder, fileName, value);
			} catch (IOException e) {

				  LOGGER.log(Level.SEVERE, "Exception occur", e);
			}
	    	return result;
	    
	    }
	    
	    
	    public void intializeConfigSettings() throws JsonProcessingException {
	    	ApplicationController a = new ApplicationController();
	    	ServerController e =new ServerController();
	    	RestApiController r = new RestApiController();
	    	DatabaseController f = new DatabaseController();
	    	DirectoryController y = new DirectoryController();
	    	ApplicationModel p = a.createNewApplicationModel("webshop");
	    	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	    	String url = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	    	String folder = "webshopServer";
	    	String file = "config.yml";
	    	String path = url +"/" + folder +"/"+ file;
	        try {
	        	
	        	mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	        	ServerModel server = mapper.readValue(new File(path), ServerModel.class);
	            ReflectionToStringBuilder.toString(server,ToStringStyle.MULTI_LINE_STYLE);
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
	  
	    		y.writeFileToDocuments(folder, file, yaml);
	        }
	    }

/**
*
* @author Anthony Scheeres
*
*/
private String writeFile(String url, String folder, String fileName, String value) throws IOException {
	    	File file = new File(url +"/"+ folder);
	    	String path = null;
	    	if (!file.exists()) {
	    	    if (file.mkdir()) {
	    	 
	    	    } else {
	    	
	    	    }
	    	}
	    	path = url +"/"+ folder+ "/" + fileName;
	  
	    	File files = new File(path);
	    	if(!files.exists()){
	    		files.createNewFile();
    	        FileWriter fw = new FileWriter(path);
    	        BufferedWriter bw = new BufferedWriter(fw);
    	        bw.write(value);
    	        bw.close();
	    		}
	    		
	
    	 return path;  
	    	
	    }
}
