package main.java.nl.iipsen2server.models;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ExperimentModel {
    private List<LogModel> logs;
    @Pattern(regexp="^[0-9]*$")
    private long id;
    @NotNull
    private String name;
    private String description;
    private Enum fasen;
    private BufferedImage img;
    private Enum priorities;
    private Enum  status;
    private List<ExperimentLeaderModel>  experimentleaders;
    private List<ExperimentTeamMemberModel> expimentTeam;
    private List<OrganisationModel> Organisations;
    private List<BusinessOwnerModel> businessOwners;
    private Double inovationCost;
    private String moneySource;

    public ExperimentModel() {

    }

    public ExperimentModel(List<LogModel> logs, @Pattern(regexp = "^[0-9]*$") long id, @NotNull String name,
                           String description, Enum fasen, BufferedImage img, Enum priorities, Enum status,
                           List<ExperimentLeaderModel> experimentleaders, List<ExperimentTeamMemberModel> expimentTeam,
                           List<OrganisationModel> organisations, List<BusinessOwnerModel> businessOwners, Double inovationCost,
                           String moneySource) {
        super();
        this.logs = logs;
        this.id = id;
        this.name = name;
        this.description = description;
        this.fasen = fasen;
        this.img = img;
        this.priorities = priorities;
        this.status = status;
        this.experimentleaders = experimentleaders;
        this.expimentTeam = expimentTeam;
        Organisations = organisations;
        this.businessOwners = businessOwners;
        this.inovationCost = inovationCost;
        this.moneySource = moneySource;
    }
    public List<LogModel> getLogs() {
        return logs;
    }
    public void setLogs(List<LogModel> logs) {
        this.logs = logs;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Enum getFasen() {
        return fasen;
    }
    public void setFasen(Enum fasen) {
        this.fasen = fasen;
    }
    public BufferedImage getImg() {
        return img;
    }
    public void setImg(BufferedImage img) {
        this.img = img;
    }
    public Enum getPriorities() {
        return priorities;
    }
    public void setPriorities(Enum priorities) {
        this.priorities = priorities;
    }
    public Enum getStatus() {
        return status;
    }
    public void setStatus(Enum status) {
        this.status = status;
    }
    public List<ExperimentLeaderModel> getExperimentleaders() {
        return experimentleaders;
    }
    public void setExperimentleaders(List<ExperimentLeaderModel> experimentleaders) {
        this.experimentleaders = experimentleaders;
    }
    public List<ExperimentTeamMemberModel> getExpimentTeam() {
        return expimentTeam;
    }
    public void setExpimentTeam(List<ExperimentTeamMemberModel> expimentTeam) {
        this.expimentTeam = expimentTeam;
    }
    public List<OrganisationModel> getOrganisations() {
        return Organisations;
    }
    public void setOrganisations(List<OrganisationModel> organisations) {
        Organisations = organisations;
    }
    public List<BusinessOwnerModel> getBusinessOwners() {
        return businessOwners;
    }
    public void setBusinessOwners(List<BusinessOwnerModel> businessOwners) {
        this.businessOwners = businessOwners;
    }
    public Double getInovationCost() {
        return inovationCost;
    }
    public void setInovationCost(Double inovationCost) {
        this.inovationCost = inovationCost;
    }
    public String getMoneySource() {
        return moneySource;
    }
    public void setMoneySource(String moneySource) {
        this.moneySource = moneySource;
    }



}