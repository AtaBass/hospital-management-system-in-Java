package nyp2025Proje_AtaBas;




public class Doctor extends Person {
   
	private static final long serialVersionUID = 1L;
	
	private final int diplomaID; 
    private Schedule schedule; 


    public Doctor(String firstName, String lastName, long nationalID, String gender, String dateOfBirth,int diplomaID,Schedule schedule) {
		
		super(firstName, lastName, nationalID, gender, dateOfBirth);
		this.diplomaID=diplomaID;
		this.schedule=schedule;
		
	}

  
    public int getDiplomaID() {
        return diplomaID;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    
    public String toString() {
		String information ="";
		information = "First Name:    " + getFirstName() +
					"\nLast Name:     " + getLastName() +
					"\nNational ID:   " + getNationalID() +
					"\nGender:        " + getGender() +
					"\nDate of Birth: " + getDateOfBirth() +
					"\nDiploma ID:  " + diplomaID;
					
		return information;
	}
}

