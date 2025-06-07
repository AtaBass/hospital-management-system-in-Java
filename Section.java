

package nyp2025Proje_AtaBas;

import java.io.Serializable;
import java.util.LinkedList;

public class Section implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final int id;
	private String name;
	private LinkedList<Doctor> doctors;
	
	public Section(int id, String name) {
		
		this.id = id;
		this.name = name;
		doctors = new LinkedList<>();
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(LinkedList<Doctor> doctors) {
		this.doctors = doctors;
	}

	public int getId() {
		return id;
	}
	
	
	 public void addDoctor(Doctor doctor) throws DuplicateInfoException {
	        
		 	
	        for (Doctor doc : doctors) {
	            if (doc.getDiplomaID() == doctor.getDiplomaID()) {
	                throw new DuplicateInfoException("There is already a doctor with this diploma ID");
	            }
	        }

	        doctors.add(doctor);
	 }

	   
	 public Doctor getDoctor(int diplomaID) throws IDException {
	        for (Doctor doctor : doctors) {
	            if (doctor.getDiplomaID() == diplomaID) {
	                return doctor;
	            }
	        }

	        throw new IDException("The doctor with the specified diploma ID could not be found");
	 }
	 
	 
	 public void listDoctor() {
	        if (doctors.isEmpty()) {
	            System.out.println("No doctors in this section");
	        } 
	        else {
	            for (Doctor doc : doctors) {
	                System.out.println(doc);
	            }
	        }
	 }
	 

	 public void removeDoctor(int diplomaID) throws IDException {
		    for (Doctor doctor : doctors) {
		        if (doctor.getDiplomaID() == diplomaID) {
		            doctors.remove(doctor);
		            System.out.println("Doctor with Diploma ID " + diplomaID + " has been removed successfully.");
		            return;
		        }
		    }
		    throw new IDException("The doctor with the specified diploma ID could not be found");
		}
	 
	 
	 
	 
	 
}
