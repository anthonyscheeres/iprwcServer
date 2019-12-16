package nl.ipwrc.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    
    
  

/**
*
* @author Anthony Scheeres
*
*/
	  public String writeFileToDocuments(String folder, String fileName, String value){
	    	String url = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
	    	System.out.println(url);
	    	  String result = null;
	    	try {
				result = writeFile(url, folder, fileName, value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return result;
	    
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
	    	        System.out.println("Directory created!");
	    	    } else {
	    	        System.out.println("Failed to create directory");
	    	    }
	    	}
	    	path = url +"/"+ folder+ "/" + fileName;
	    	System.out.println(path);
	    	File files = new File(path);
	    	if(!files.exists()){
	    		files.createNewFile();
    	        FileWriter fw = new FileWriter(path);
    	        BufferedWriter bw = new BufferedWriter(fw);
    	        bw.write(value);
    	        bw.close();
	    		}else{
	    		  System.out.println("File already exists");
	    		}
	    		
	
    	 return path;  
	    	
	    }
}
