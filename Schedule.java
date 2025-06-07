package nyp2025Proje_AtaBas;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


public class Schedule implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	private Doctor doctor;
	private LinkedList<Rendezvous> sessions;
    private int maxPatientPerDay;

    
    public Schedule(int maxPatientPerDay) {
    
    	this.maxPatientPerDay = maxPatientPerDay;
    	sessions = new LinkedList<>();
    }

	
    
    
    public LinkedList<Rendezvous> getSessions() {
		return sessions;
	}

	public void setSessions(LinkedList<Rendezvous> sessions) {
		this.sessions = sessions;
	}

	public int getMaxPatientPerDay() {
		return maxPatientPerDay;
	}

	public void setMaxPatientPerDay(int maxPatientPerDay) {
		this.maxPatientPerDay = maxPatientPerDay;
	}




	@SuppressWarnings("null")
	public boolean addRendezvous(Patient patient,Date desiredDate) {
	        int dailyCount = 0;

	        for (Rendezvous rend : sessions) {
	            if (isSameDay(rend.getDateTime(),desiredDate)) {
	                dailyCount++;
	            }
	        }
	        
	        if (dailyCount >= maxPatientPerDay) {
	            System.out.println("Appointment limit reached");
	        	return false;
	        }
	        else {
	        	Rendezvous rendezvous = new Rendezvous(desiredDate, patient, doctor);
		        sessions.add(rendezvous);
		        return true;
	        }
	    }

	////////////
	public boolean removeRendezvous(Date date, Patient patient) {
        for (Rendezvous rendezvous : sessions) {
            if (isSameDay(rendezvous.getDateTime(), date) && rendezvous.getPatient().equals(patient)) {
                sessions.remove(rendezvous);
                System.out.println("Rendezvous removed successfully.");
                return true;
            }
        }
        System.out.println("Rendezvous not found.");
        return false;
    }

    public void listRendezvous() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        if (sessions.isEmpty()) {
            System.out.println("No rendezvous scheduled.");
        } 
        else {
            for (Rendezvous rendezvous : sessions) {
                System.out.println("Date:    " + sdf.format(rendezvous.getDateTime()) + 
                                 "\nPatient: " + rendezvous.getPatient().getFirstName() + " " + rendezvous.getPatient().getLastName());
            }
        }
    }

    public LinkedList<Rendezvous> searchRendezvous(Date date, long patientNationalId) {
        
    	LinkedList<Rendezvous> result = new LinkedList<>();
        for (Rendezvous rendezvous : sessions) {
            
        	if (isSameDay(rendezvous.getDateTime(), date) && rendezvous.getPatient().getNationalID() == patientNationalId) {
                result.add(rendezvous);
            }
        }
        return result;
    }

    /////////////
	
	
	private boolean isSameDay(Date date1, Date date2) {
	        Calendar cal1 = Calendar.getInstance();
	        Calendar cal2 = Calendar.getInstance();
	        cal1.setTime(date1);
	        cal2.setTime(date2);
	        
	        if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
	        	return true;
	        }
	        else {
	        	return false;
	        }
	    
	    }
	
	
	

    
}
