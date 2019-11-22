package main.java.nl.iipsen2server.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


/**
 * @author Anthony Scheeres
 */
public class LogModel {

    private Timestamp time;
    @NotNull
    private String title;
    private String description;
    @NotNull
    private UserModel byUser;
    @Pattern(regexp = "^[0-9]*$")
    private long id;
    private int project_id;

    /**
     * @author Anthony Scheeres
     */
    public LogModel() {

    }

    /**
     * @author AnthonySchuijlenburg
     */
    public LogModel(Timestamp time, @NotNull String title, String description, @NotNull UserModel byUser,
                    @Pattern(regexp = "^[0-9]*$") long id) {
        super();
        this.time = time;
        this.title = title;
        this.description = description;
        this.byUser = byUser;
        this.id = id;

    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getByUser() {
        return byUser;
    }

    public void setByUser(UserModel byUser) {
        this.byUser = byUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }
}
