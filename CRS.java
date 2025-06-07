package nyp2025Proje_AtaBas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class CRS implements Serializable {

    private static final long serialVersionUID = 1L;

    private HashMap<Long, Patient> patients;
    private LinkedList<Rendezvous> rendezvous;
    private HashMap<Integer, Hospital> hospitals;

    public CRS() {
        patients = new HashMap<>();
        rendezvous = new LinkedList<>();
        hospitals = new HashMap<>();
    }

    public boolean makeRandezvous(long patientID, int hospitalID, int sectionID, int diplomaID, Date desiredDate) {
        try {
            Patient patient = patients.get(patientID);
            if (patient == null) {
                System.out.print("Please enter the informations about patient.\nFirst Name: ");
                @SuppressWarnings("resource")
				Scanner scan = new Scanner(System.in);
                String firstName = scan.nextLine();
                System.out.print("Last Name: ");
                String lastName = scan.nextLine();
                System.out.print("Gender: ");
                String gender = scan.nextLine();
                System.out.print("Date of Birth: ");
                String dob = scan.nextLine();

                patient = new Patient(firstName, lastName, patientID, gender, dob);
                patients.put(patientID, patient);
            }

            Hospital hospital = hospitals.get(hospitalID);
            if (hospital == null) {
                throw new IDException("Invalid hospital.");
            }

            Section section = hospital.getSection(sectionID);
            if (section == null) {
                throw new IDException("Invalid section ID.");
            }

            Doctor doctor = section.getDoctor(diplomaID);
            if (doctor == null) {
                throw new IDException("Doctor with diploma ID " + diplomaID + " not found.");
            }

            Schedule schedule = doctor.getSchedule();
            if (!schedule.addRendezvous(patient, desiredDate)) {
                return false;
            } 
            else {
                Rendezvous newRendezvous = new Rendezvous(desiredDate, patient, doctor);
                rendezvous.add(newRendezvous);
                return true;
            }

        } 
        catch (IDException e) {
            System.err.println("ID Error: " + e.getMessage());
            return false;
        }
    }

    public void saveTableToDisk(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("CRS data saved to disk.");
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CRS loadTableToDisk(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            CRS loadedCRS = (CRS) ois.readObject();
            System.out.println("CRS data loaded from disk.");
            return loadedCRS;
        } 
        
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
       
        
        }
    
    
    }
    
    
    
    /////////////////////////////////////
    
    public void addHospital(Hospital hospital) throws DuplicateInfoException {
        if (hospitals.containsKey(hospital.getId())) {
            throw new DuplicateInfoException("A hospital with this ID already exists: " + hospital.getId());
        }
        hospitals.put(hospital.getId(), hospital);
        System.out.println("Hospital added successfully: " + hospital.getName());
    }

    
    public void removeHospital(int hospitalID) throws IDException {
        if (!hospitals.containsKey(hospitalID)) {
            throw new IDException("No hospital found with ID: " + hospitalID);
        }
        hospitals.remove(hospitalID);
        System.out.println("Hospital removed successfully.");
    }

    
    public void listHospital() {
        if (hospitals.isEmpty()) {
            System.out.println("No hospitals available.");
        } 
        else {
            for (Hospital hospital : hospitals.values()) {
                System.out.println("Hospital ID: " + hospital.getId() + ", Name: " + hospital.getName());
            }
        }
    }

    
    public Hospital searchHospital(String name) throws IDException {
        for (Hospital hospital : hospitals.values()) {
            if (hospital.getName().equalsIgnoreCase(name)) {
                return hospital;
            }
        }
        throw new IDException("No hospital found with this name: " + name );
    }

    
    public void addPatient(Patient patient) throws DuplicateInfoException {
        if (patients.containsKey(patient.getNationalID())) {
            throw new DuplicateInfoException("A patient with this ID already exists: " + patient.getNationalID());
        }
        patients.put(patient.getNationalID(), patient);
        System.out.println("Patient added successfully: " + patient.getFirstName() + " " + patient.getLastName());
    }

    
    public void removePatient(long nationalID) throws IDException {
        if (!patients.containsKey(nationalID)) {
            throw new IDException("No patient found with National ID: " + nationalID);
        }
        patients.remove(nationalID);
        System.out.println("Patient removed successfully.");
    }

    
    public void listPatient() {
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        } 
        else {
            for (Patient patient : patients.values()) {
                System.out.println("Patient ID: " + patient.getNationalID() + ", Name: " +
                        patient.getFirstName() + " " + patient.getLastName());
            }
        }
    }

    
    public Patient searchPatient(long nationalID) throws IDException {
        Patient patient = patients.get(nationalID);
        if (patient == null) {
            throw new IDException("No patient found with National ID: " + nationalID);
        }
        return patient;
    }
    
    
    /////////////////////////////////////////////////////////////

	public HashMap<Long, Patient> getPatients() {
		return patients;
	}

	public void setPatients(HashMap<Long, Patient> patients) {
		this.patients = patients;
	}

	public LinkedList<Rendezvous> getRendezvous() {
		return rendezvous;
	}

	public void setRendezvous(LinkedList<Rendezvous> rendezvous) {
		this.rendezvous = rendezvous;
	}

	public HashMap<Integer, Hospital> getHospitals() {
		return hospitals;
	}

	public void setHospitals(HashMap<Integer, Hospital> hospitals) {
		this.hospitals = hospitals;
	}
    
    
    
    


}
