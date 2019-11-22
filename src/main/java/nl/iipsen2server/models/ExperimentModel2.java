package main.java.nl.iipsen2server.models;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ExperimentModel2 {
    @Pattern(regexp="^[0-9]*$")
    private long id;
    @NotNull
    private String name;
    private String description;
    private Enum fasen;
    private BufferedImage img;
    private Enum status;
    private String experimentleaders;
    private String experimentTeam;
    private String Organisations;
    private String businessOwners;
    private String inovationCost;
    private String moneySource;

    public ExperimentModel2() {

    }

    public ExperimentModel2(@Pattern(regexp = "^[0-9]*$") long id, @NotNull String name,String experimentleaders,
                        String description, String organisations, String businessOwners, Enum status, String inovationCost,
                        String moneySource) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
//		this.fasen = fasen;
//		this.img = img;
        this.status = status;
        this.experimentleaders = experimentleaders;
//		this.experimentTeam = experimentTeam;
        Organisations = organisations;
        this.businessOwners = businessOwners;
        this.inovationCost = inovationCost;
        this.moneySource = moneySource;
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
    public Enum getStatus() {
        return status;
    }
    public void setStatus(Enum status) {
        this.status = status;
    }
    public String getExperimentleaders() {
        return experimentleaders;
    }
    public void setExperimentleaders(String experimentleaders) {
        this.experimentleaders = experimentleaders;
    }


    public String getExperimentTeam() {
        return experimentTeam;
    }
    public void setExperimentTeam(String experimentTeam) {
        this.experimentTeam = experimentTeam;
    }
    public String getOrganisations() {
        return Organisations;
    }
    public void setOrganisations(String organisations) {
        Organisations = organisations;
    }
    public String getBusinessOwners() {
        return businessOwners;
    }
    public void setBusinessOwners(String businessOwners) {
        this.businessOwners = businessOwners;
    }
    public String getInovationCost() {
        return inovationCost;
    }
    public void setInovationCost(String inovationCost) {
        this.inovationCost = inovationCost;
    }
    public String getMoneySource() {
        return moneySource;
    }
    public void setMoneySource(String moneySource) {
        this.moneySource = moneySource;
    }


}
