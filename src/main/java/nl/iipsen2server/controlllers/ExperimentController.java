package main.java.nl.iipsen2server.controlllers;

import main.java.nl.iipsen2server.dao.ExperimentDatabase;

import main.java.nl.iipsen2server.dao.ExperimentDAO;
import main.java.nl.iipsen2server.models.ExperimentModel;



public class ExperimentController {

    private AuthenticationController authenticationController = new AuthenticationController();
    private TokenController tokenController = new TokenController();
    private ExperimentDAO experimentDAO = new ExperimentDAO();
/**
 * @author Jesse Poleij
 *
 */
    public void deleteExperiment(ExperimentModel project, String token) {
        String userID = tokenController.tokenToUserId(token);
        if(authenticationController.hasPermission(Long.parseLong(userID), "DELETE")){
            experimentDAO.deleteExperiment(project);
        }
    }

    /**
     * @author AnthonySchuijleburg
     */
    public String showExperiments(){
        ExperimentDatabase experimentDatabase = new ExperimentDatabase();
        return experimentDatabase.showExperiments();
    }

    /**
     * @author AnthonySchuijleburg
     */
    public String showSingleExperiment(int id){
        ExperimentDatabase experimentDatabase = new ExperimentDatabase();
        return experimentDatabase.showExperiment(id);
    }
  /* @author Jesse Poleij
  *
  */
    public void showOverview(String token) {
        String userID = tokenController.tokenToUserId(token);
        authenticationController.hasPermission(Long.parseLong(userID), "READ");
    }

}
