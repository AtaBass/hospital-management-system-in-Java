package nyp2025Proje_AtaBas;

import java.io.Serializable;
import java.util.Date;

public class Rendezvous implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date dateTime;
	private Patient patient;
	private Doctor doctor;
	
	public Rendezvous(Date dateTime, Patient patient, Doctor doctor) {
		
		this.dateTime = dateTime;
		this.patient = patient;
		this.doctor = doctor;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
	
	
	public String toString() {
		String information ="";
		information = "Date:    " + dateTime +
                    "\nPatient: " + patient.getFirstName() + " " + patient.getLastName() +
                    "\nDoctor:  " + doctor.getFirstName() + " " + doctor.getLastName();
              
		return information;
    }
	

	
	
}
