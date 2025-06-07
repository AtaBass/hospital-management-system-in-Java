package nyp2025Proje_AtaBas;

import java.io.Serializable;


public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String firstName;
	private String lastName;
	private final long nationalID;
	private String gender;
	private String dateOfBirth;
	
	
	public Person(String firstName, String lastName, long nationalID, String gender, String dateOfBirth) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalID = nationalID;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	
	public long getNationalID() {
		return nationalID;
	}



	public String toString() {
		String information ="";
		information = "First Name:    " + firstName +
					"\nLast Name:     " + lastName +
					"\nNational ID:   " + nationalID +
					"\nGender:        " + gender +
					"\nDate of Birth: " + dateOfBirth;
		
		return information;
	}
	
	
	
	
	
	
	
	
	
	
	












}
