package main.java.nl.iipsen2server.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OrganisationModel {
	@Pattern(regexp="^[0-9]*$")
	private long id;
	@NotNull
	private String name;
	private String description;
	
public OrganisationModel() {
		
	}
	
	public OrganisationModel(@Pattern(regexp = "^[0-9]*$") long id, @NotNull String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
	
	
	
	
}
