package nyp2025Proje_AtaBas;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;


public class UnitTesting {

	 @Test
	    public void testSectionAddDoctor() {
	        Section section = new Section(1, "Cardiology");
	        Doctor doctor = new Doctor("John", "Doe", 12345678901L, "Male", "1970-01-01", 101, new Schedule(5));

	        assertDoesNotThrow(() -> section.addDoctor(doctor));
	        assertEquals(1, section.getDoctors().size());
	        assertEquals(doctor, section.getDoctors().getFirst());
	    }

	    @Test
	    public void testSectionAddDuplicateDoctorThrowsException() {
	        Section section = new Section(1, "Cardiology");
	        Doctor doctor = new Doctor("John", "Doe", 12345678901L, "Male", "1970-01-01", 101, new Schedule(5));
	        section.addDoctor(doctor);

	        assertThrows(DuplicateInfoException.class, () -> section.addDoctor(doctor));
	    }

	    @Test
	    public void testScheduleAddRendezvous() {
	        Doctor doctor = new Doctor("John", "Doe", 12345678901L, "Male", "1970-01-01", 101, new Schedule(1));
	        Patient patient = new Patient("Jane", "Smith", 98765432100L, "Female", "1985-02-14");
	        Date date = new GregorianCalendar(2025, Calendar.JANUARY, 11).getTime();

	        boolean added = doctor.getSchedule().addRendezvous(patient, date);
	        assertTrue(added);
	        assertEquals(1, doctor.getSchedule().getSessions().size());
	    }

	    @Test
	    public void testScheduleExceedsDailyLimit() {
	        Doctor doctor = new Doctor("John", "Doe", 12345678901L, "Male", "1970-01-01", 101, new Schedule(1));
	        Patient patient1 = new Patient("Jane", "Smith", 98765432100L, "Female", "1985-02-14");
	        Patient patient2 = new Patient("Alice", "Johnson", 12312312312L, "Female", "1990-07-21");
	        Date date = new GregorianCalendar(2025, Calendar.JANUARY, 11).getTime();

	        assertTrue(doctor.getSchedule().addRendezvous(patient1, date));
	        assertFalse(doctor.getSchedule().addRendezvous(patient2, date));
	    }

	    @Test
	    public void testHospitalAddSection() {
	        Hospital hospital = new Hospital(1, "City Hospital");
	        Section section = new Section(1, "Cardiology");

	        assertDoesNotThrow(() -> hospital.addSection(section));
	        assertEquals(1, hospital.getSections().size());
	    }

	    @Test
	    public void testHospitalDuplicateSectionThrowsException() {
	        Hospital hospital = new Hospital(1, "City Hospital");
	        Section section1 = new Section(1, "Cardiology");
	        Section section2 = new Section(2, "Cardiology");

	        hospital.addSection(section1);
	        assertThrows(DuplicateInfoException.class, () -> hospital.addSection(section2));
	    }

	    @Test
	    public void testPatientToString() {
	        Patient patient = new Patient("Jane", "Doe", 987654321L, "Female", "1985-02-14", "555-1234", "jane@example.com", "123 Street");

	        String expected = "First Name:    Jane\nLast Name:     Doe\nNational ID:   987654321\nGender:        Female\nDate of Birth: 1985-02-14\nPhone Number:  555-1234\nE-Mail:        jane@example.com\nAddress:       123 Street";
	        assertEquals(expected, patient.toString());
	    }

	    @Test
	    public void testPatientSetAndGetAttributes() {
	        Patient patient = new Patient("John", "Doe", 123456789L, "Male", "1990-01-01", "555-6789", "john@example.com", "456 Avenue");

	        patient.setPhoneNumber("555-9999");
	        patient.seteMail("john.doe@example.com");
	        patient.setAddress("789 Boulevard");

	        assertEquals("555-9999", patient.getPhoneNumber());
	        assertEquals("john.doe@example.com", patient.geteMail());
	        assertEquals("789 Boulevard", patient.getAddress());
	    }

	    @Test
	    public void testPersonToString() {
	        Person person = new Person("Alice", "Smith", 987654321L, "Female", "1985-02-14");

	        String expected = "First Name:    Alice\nLast Name:     Smith\nNational ID:   987654321\nGender:        Female\nDate of Birth: 1985-02-14";
	        assertEquals(expected, person.toString());
	    }

	    @Test
	    public void testPersonSetAttributes() {
	        Person person = new Person("Bob", "Brown", 123456789L, "Male", "1990-01-01");

	        person.setFirstName("Robert");
	        person.setLastName("Black");
	        person.setGender("Other");
	        person.setDateOfBirth("1995-05-15");

	        assertEquals("Robert", person.getFirstName());
	        assertEquals("Black", person.getLastName());
	        assertEquals("Other", person.getGender());
	        assertEquals("1995-05-15", person.getDateOfBirth());
	    }

	    @Test
	    public void testRendezvousToString() {
	        Doctor doctor = new Doctor("Dr. John", "Doe", 12345678901L, "Male", "1970-01-01", 101, new Schedule(5));
	        Patient patient = new Patient("Jane", "Smith", 98765432100L, "Female", "1985-02-14");
	        Date date = new GregorianCalendar(2025, Calendar.JANUARY, 11).getTime();
	        Rendezvous rendezvous = new Rendezvous(date, patient, doctor);

	        String expected = "Date:    " + date + "\nPatient: Jane Smith\nDoctor:  Dr. John Doe";
	        assertEquals(expected, rendezvous.toString());
	    }

	    @Test
	    public void testRendezvousSetAttributes() {
	        Doctor doctor = new Doctor("Dr. John", "Doe", 12345678901L, "Male", "1970-01-01", 101, new Schedule(5));
	        Patient patient = new Patient("Jane", "Smith", 98765432100L, "Female", "1985-02-14");
	        Date date = new GregorianCalendar(2025, Calendar.JANUARY, 11).getTime();
	        Rendezvous rendezvous = new Rendezvous(date, patient, doctor);

	        Doctor newDoctor = new Doctor("Dr. Alice", "Brown", 98765432102L, "Female", "1980-05-20", 102, new Schedule(3));
	        rendezvous.setDoctor(newDoctor);

	        assertEquals(newDoctor, rendezvous.getDoctor());
	    }

	    @Test
	    public void testCRSAddPatient() {
	        CRS crs = new CRS();
	        Patient patient = new Patient("John", "Smith", 123456789L, "Male", "1990-01-01", "555-6789", "john@example.com", "456 Avenue");

	        assertDoesNotThrow(() -> crs.addPatient(patient));
	        assertEquals(1, crs.getPatients().size());
	        assertEquals(patient, crs.getPatients().get(123456789L));
	    }

	    @Test
	    public void testCRSAddDuplicatePatientThrowsException() {
	        CRS crs = new CRS();
	        Patient patient = new Patient("John", "Smith", 123456789L, "Male", "1990-01-01", "555-6789", "john@example.com", "456 Avenue");
	        crs.addPatient(patient);

	        assertThrows(DuplicateInfoException.class, () -> crs.addPatient(patient));
	    }
	
	
}
