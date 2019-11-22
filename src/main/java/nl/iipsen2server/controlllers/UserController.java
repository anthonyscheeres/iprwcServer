package main.java.nl.iipsen2server.controlllers;


import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import main.java.nl.iipsen2server.models.DataModel;
import main.java.nl.iipsen2server.models.UserModel;


/**
 * @author Anthony Scheeres
 */
public class UserController {


    /**
     * @author Anthony Scheeres
     */
    private UserModel createJUser(@NotNull String username, @NotNull String password, @Pattern(regexp = "^[0-9]*$") long id,
                                  List<Enum> rechten) {
        return new UserModel(username, password, id,
                rechten, null);
    }


    /**
     * @author Anthony Scheeres
     */
    public int getUserById(long id, List<UserModel> list) {

        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).getId() == id) {
                return index;
            }

        }
        return 0;
    }

    /**
     * @author Anthony Scheeres
     */
    public UserModel createNewUserModel(@NotNull String username, @NotNull String password) {
        ApplicationController applicationController = new ApplicationController();
        UserModel userModel = createJUser(username, password, createUserId(DataModel.getApplicationModel().getUsers()), new ArrayList<Enum>());
        applicationController.addUser(userModel, DataModel.getApplicationModel());
        return userModel;
    }


    /**
     * @author Anthony Scheeres
     */
    private long createUserId(List<UserModel> list) {
        long id = 1;
        for (UserModel databaseModel : list) {
            if (id <= databaseModel.getId()) {
                id = databaseModel.getId() + 1;
            }
        }
        return id;
    }

    /**
     * @author Anthony Scheeres
     */
    public long createUserId2(List<String> list) {
        if (list == null) {
            return 1;
        }
        long id = 1;
        
        
        for (String databaseModel : list) {
            if (id <= Integer.valueOf(databaseModel)) {
                id = Integer.valueOf(databaseModel) + 1;
            }
        }
        return id;
    }


    /**
     * @author Anthony Scheeres
     * 
     */
    public boolean checkIfUsernameExist(List<String> list, String username) {
    	
        for (String name : list) {
            if (name.equals(username)) {

                return true;
            }

        //    System.out.println(name);

        }
        return false;
    }

}
