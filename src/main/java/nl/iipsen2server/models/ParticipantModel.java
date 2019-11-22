package main.java.nl.iipsen2server.models;



import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

class ParticipantModel {
	@Pattern(regexp="^[0-9]*$")
	private long id;
	private String preposition; 
	@NotBlank @Length(min=2, max=255)
	private String firstName;
	 
	@NotBlank @Length(min=2, max=255)
	private String lastName;
	 
	@Pattern(regexp=".+@.+\\.[a-z]+")
	private String email;

	public ParticipantModel(@Pattern(regexp = "^[0-9]*$") long id, String preposition,
			@NotBlank @Length(min = 2, max = 255) String firstName,
			@NotBlank @Length(min = 2, max = 255) String lastName, @Pattern(regexp = ".+@.+\\.[a-z]+") String email) {
		super();
		this.id = id;
		this.preposition = preposition;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPreposition() {
		return preposition;
	}

	public void setPreposition(String preposition) {
		this.preposition = preposition;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
