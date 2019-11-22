package main.java.nl.iipsen2server.models;



import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

class BusinessOwnerModel extends ParticipantModel{
	
	

	public BusinessOwnerModel(@Pattern(regexp = "^[0-9]*$") long id, String tussenvoegsel,
			@NotBlank @Length(min = 2, max = 255) String firstName,
			@NotBlank @Length(min = 2, max = 255) String lastName, @Pattern(regexp = ".+@.+\\.[a-z]+") String email) {
		super(id, tussenvoegsel, firstName, lastName, email);
		// TODO Auto-generated constructor stub
	}	
}
