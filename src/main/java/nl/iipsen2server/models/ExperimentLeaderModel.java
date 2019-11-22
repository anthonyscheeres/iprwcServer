package main.java.nl.iipsen2server.models;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

class ExperimentLeaderModel extends ParticipantModel{

	private Boolean einstein = false;
	List<String> specialties;
	

	public ExperimentLeaderModel(@Pattern(regexp = "^[0-9]*$") long id, String preposition,
			@NotBlank @Length(min = 2, max = 255) String firstName,
			@NotBlank @Length(min = 2, max = 255) String lastName, @Pattern(regexp = ".+@.+\\.[a-z]+") String email) {
		super(id, preposition, firstName, lastName, email);
		// TODO Auto-generated constructor stub
	}
	public Boolean getEinstein() {
		return einstein;
	}
	public void setEinstein(Boolean einstein) {
		this.einstein = einstein;
	}
	public List<String> getSpecialties() {
		return specialties;
	}
	public void setSpecialties(List<String> specialties) {
		this.specialties = specialties;
	}
	
	
}
