package nyp2025Proje_AtaBas;




public class Patient extends Person {
	
	private static final long serialVersionUID = 1L;
	
	private String phoneNumber;
	private String eMail;
	private String address;
	
	
	
	
	
	public Patient(String firstName, String lastName, long nationalID, String gender, String dateOfBirth,String phoneNumber,String eMail,String address) {
		
		super(firstName, lastName, nationalID, gender, dateOfBirth);
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
		this.address = address;
		
	}
	
	
	public Patient(String firstName, String lastName, long nationalID, String gender, String dateOfBirth) {
		
		super(firstName, lastName, nationalID, gender, dateOfBirth);
		
	}
	

	
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
	public String toString() {
		String information ="";
		information = "First Name:    " + getFirstName() +
					"\nLast Name:     " + getLastName() +
					"\nNational ID:   " + getNationalID() +
					"\nGender:        " + getGender() +
					"\nDate of Birth: " + getDateOfBirth() +
					"\nPhone Number:  " + phoneNumber +
					"\nE-Mail:        " + eMail +
					"\nAddress:       " + address;
		
		return information;
	}
	
	
	
}
