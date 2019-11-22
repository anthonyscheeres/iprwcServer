package main.java.nl.iipsen2server.dao;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.nl.iipsen2server.models.ApplicationModel;
import main.java.nl.iipsen2server.models.RefrigeratorModel;
import main.java.nl.iipsen2server.models.UserModel;
import main.java.nl.iipsen2server.models.DashboardModel;

/**
 * @author Anthony Scheeres
 */
class JsonConverterUtilities {


    /**
     * @author Anthony Scheeres
     */
    public JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        while (resultSet.next()) {
            int numColumns = rsmd.getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 1; i <= numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, resultSet.getObject(column_name));
            }
            json.put(obj);
        }
        return json;
    }


    /**
     * @author Anthony Scheeres
     */
    public RefrigeratorModel convertToRefrigeratorModel(String jsonInString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonInString, RefrigeratorModel.class);
    }

    /**
     * @author Anthony Scheeres
     */
    public ApplicationModel convertToAppModel(String jsonInString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonInString, ApplicationModel.class);
    }

    /**
     * @author Anthony Scheeres
     */
    public String convertToJson(ApplicationModel applicationModel) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(applicationModel);
    }

    /**
     * @author Anthony Scheeres
     */
    public String convertToJson(DashboardModel dashboardModel) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dashboardModel);
    }

    /**
     * @author Anthony Scheeres
     */
    public DashboardModel convertToDashboardModel(String jsonInString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonInString, DashboardModel.class);
    }

    /**
     * @author Anthony Scheeres
     */
    public UserModel convertToUserModel(String jsonInString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonInString, UserModel.class);
    }
}
