package nyp2025Proje_AtaBas;

import java.sql.Date;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Main {
    
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) { 
            boolean exitProgram = false;

            while (!exitProgram) {
                System.out.println("\n===== Interface Selection =====");
                System.out.println("1. Console");
                System.out.println("2. GUI");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        
                        runConsole(scanner);
                        break;
                    case 2:
                        
                    	SwingUtilities.invokeLater(HospitalManagementGUI::new);

                        break;
                    case 0:
                        
                        exitProgram = true;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
	
	
	
	
	
	private static void runConsole(Scanner scanner) {
        CRS crs = new CRS();
        boolean exit = false;

        
        while (!exit) {
            System.out.println("\n===== Hospital Management System =====");
            System.out.println("0. Back to Interface Selection");
            System.out.println("1. Hospital Management");
            System.out.println("2. Section Management");
            System.out.println("3. Doctor Management");
            System.out.println("4. Patient Management");
            System.out.println("5. Rendezvous Management");
            System.out.println("6. Save Data to Disk");
            System.out.println("7. Load Data from Disk");
            System.out.println("8. Print All Information");
            System.out.println("9. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            	case 0:
            		return;	
            	case 1:
                    hospitalMenu(crs, scanner);
                    break;
                case 2:
                    sectionMenu(crs, scanner);
                    break;
                case 3:
                    doctorMenu(crs, scanner);
                    break;
                case 4:
                    patientMenu(crs, scanner);
                    break;
                case 5:
                    rendezvousMenu(crs, scanner);
                    break;
                case 6:
                    saveDataMenu(crs);
                    break;
                case 7:
                    loadDataMenu(crs);
                    break;
                case 8:
                    printAllInformation(crs);
                    break;
                case 9:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void loadDataMenu(CRS crs) {
        
        String filePath = "crs_dosya.txt";
        CRS loadedCRS = CRS.loadTableToDisk(filePath);
        if (loadedCRS != null) {
            System.out.println("Data loaded successfully.");
            crs.getHospitals().clear();
            crs.getHospitals().putAll(loadedCRS.getHospitals());

            crs.getPatients().clear();
            crs.getPatients().putAll(loadedCRS.getPatients());

            crs.getRendezvous().clear();
            crs.getRendezvous().addAll(loadedCRS.getRendezvous());

            System.out.println("Loaded data:");
            printAllInformation(crs);
        } 
        else {
            System.out.println("Failed to load data.");
        }
    }

    private static void printAllInformation(CRS crs) {
        System.out.println("\n--- All Recorded Information ---");

        
        System.out.println("\nHospitals:");
        for (Hospital hospital : crs.getHospitals().values()) {
            System.out.println("- Hospital ID: " + hospital.getId() + ", Name: " + hospital.getName());

            
            System.out.println("  Sections:");
            for (Section section : hospital.getSections()) { 
                System.out.println("    - Section ID: " + section.getId() + ", Name: " + section.getName());

                
                System.out.println("      Doctors:");
                for (Doctor doctor : section.getDoctors()) { 
                    System.out.println("        - Doctor Name: " + doctor.getFirstName() + " " + doctor.getLastName() +
                                       ", Diploma ID: " + doctor.getDiplomaID() +
                                       ", National ID: " + doctor.getNationalID());
                }
            }
        }

        
        System.out.println("\nPatients:");
        for (Patient patient : crs.getPatients().values()) {
            System.out.println("- Patient Name: " + patient.getFirstName() + " " + patient.getLastName() +
                               ", National ID: " + patient.getNationalID() +
                               ", Gender: " + patient.getGender() +
                               ", Date of Birth: " + patient.getDateOfBirth()); 
        }

        
        System.out.println("\nRendezvous:");
        for (Rendezvous rendezvous : crs.getRendezvous()) {
            System.out.println(rendezvous);
        }

        System.out.println("\nEnd of Information.");
    }

        
        
        
        
        
    private static void saveDataMenu(CRS crs) {
        
        String filePath = "crs_dosya.txt";
        crs.saveTableToDisk(filePath);
    }

    
    private static void hospitalMenu(CRS crs, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Hospital Management ---");
            System.out.println("1. Add Hospital");
            System.out.println("2. List Hospitals");
            System.out.println("3. Remove Hospital");
            System.out.println("4. Get Hospital");
            System.out.println("0. Back");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter hospital ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter hospital name: ");
                    String name = scanner.nextLine();
                    try {
                        crs.addHospital(new Hospital(id, name));
                    } catch (DuplicateInfoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    crs.listHospital();
                    break;
                case 3:
                    System.out.print("Enter hospital ID to remove: ");
                    id = scanner.nextInt();
                    try {
                        crs.removeHospital(id);
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter hospital name to search: ");
                    name = scanner.nextLine();
                    try {
                        Hospital hospital = crs.searchHospital(name);
                        System.out.println("Hospital found: " + hospital.getId());
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    
    private static void sectionMenu(CRS crs, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Section Management ---");
            System.out.println("1. Add Section");
            System.out.println("2. List Sections");
            System.out.println("3. Get Section");
            System.out.println("4. Remove Section");
            System.out.println("0. Back");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter hospital ID: ");
                    int hospitalID = scanner.nextInt();
                    scanner.nextLine();
                    Hospital hospital = crs.getHospitals().get(hospitalID);
                    if (hospital == null) {
                        System.out.println("Warning: Hospital with ID " + hospitalID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter section ID: ");
                    int sectionID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter section name: ");
                    String sectionName = scanner.nextLine();
                    try {
                        hospital.addSection(new Section(sectionID, sectionName));
                    } catch (DuplicateInfoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter hospital ID: ");
                    hospitalID = scanner.nextInt();
                    scanner.nextLine();
                    hospital = crs.getHospitals().get(hospitalID);
                    if (hospital == null) {
                        System.out.println("Warning: Hospital with ID " + hospitalID + " does not exist.");
                        break;
                    }
                    hospital.listSections();
                    break;
                case 3:
                    System.out.print("Enter hospital ID: ");
                    hospitalID = scanner.nextInt();
                    scanner.nextLine();
                    hospital = crs.getHospitals().get(hospitalID);
                    if (hospital == null) {
                        System.out.println("Warning: Hospital with ID " + hospitalID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter section ID to get: ");
                    sectionID = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Section section = hospital.getSection(sectionID);
                        System.out.println("Section found: " + section.getName());
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter hospital ID: ");
                    hospitalID = scanner.nextInt();
                    scanner.nextLine();
                    hospital = crs.getHospitals().get(hospitalID);
                    if (hospital == null) {
                        System.out.println("Warning: Hospital with ID " + hospitalID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter section ID to remove: ");
                    sectionID = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        hospital.removeSection(sectionID);
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    
    private static void doctorMenu(CRS crs, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Doctor Management ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. List Doctors");
            System.out.println("3. Get Doctor");
            System.out.println("4. Remove Doctor");
            System.out.println("0. Back");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter hospital ID: ");
                    int hospitalID = scanner.nextInt();
                    scanner.nextLine();
                    Hospital hospital = crs.getHospitals().get(hospitalID);
                    if (hospital == null) {
                        System.out.println("Warning: Hospital with ID " + hospitalID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter section ID: ");
                    int sectionID = scanner.nextInt();
                    scanner.nextLine();
                    Section section = hospital.getSection(sectionID);
                    if (section == null) {
                        System.out.println("Warning: Section with ID " + sectionID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter doctor diploma ID: ");
                    int diplomaID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter doctor first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter doctor last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter doctor national ID: ");
                    long nationalID = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter doctor gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter doctor date of birth: ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter max patients per day: ");
                    int maxPatients = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        section.addDoctor(new Doctor(firstName, lastName, nationalID, gender, dob, diplomaID, new Schedule(maxPatients)));
                    } catch (DuplicateInfoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter hospital ID: ");
                    hospitalID = scanner.nextInt();
                    scanner.nextLine();
                    hospital = crs.getHospitals().get(hospitalID);
                    if (hospital == null) {
                        System.out.println("Warning: Hospital with ID " + hospitalID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter section ID: ");
                    sectionID = scanner.nextInt();
                    scanner.nextLine();
                    section = hospital.getSection(sectionID);
                    if (section == null) {
                        System.out.println("Warning: Section with ID " + sectionID + " does not exist.");
                        break;
                    }

                    section.listDoctor();
                    break;
                case 3:
                    System.out.print("Enter hospital ID: ");
                    hospitalID = scanner.nextInt();
                    scanner.nextLine();
                    hospital = crs.getHospitals().get(hospitalID);
                    if (hospital == null) {
                        System.out.println("Warning: Hospital with ID " + hospitalID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter section ID: ");
                    sectionID = scanner.nextInt();
                    scanner.nextLine();
                    section = hospital.getSection(sectionID);
                    if (section == null) {
                        System.out.println("Warning: Section with ID " + sectionID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter doctor diploma ID to get: ");
                    int diplomaIDToGet = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        Doctor doctor = section.getDoctor(diplomaIDToGet);
                        System.out.println("Doctor found: " + doctor);
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter hospital ID: ");
                    hospitalID = scanner.nextInt();
                    scanner.nextLine();
                    hospital = crs.getHospitals().get(hospitalID);
                    if (hospital == null) {
                        System.out.println("Warning: Hospital with ID " + hospitalID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter section ID: ");
                    sectionID = scanner.nextInt();
                    scanner.nextLine();
                    section = hospital.getSection(sectionID);
                    if (section == null) {
                        System.out.println("Warning: Section with ID " + sectionID + " does not exist.");
                        break;
                    }

                    System.out.print("Enter doctor diploma ID to remove: ");
                    int diplomaIDToRemove = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        section.removeDoctor(diplomaIDToRemove);
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    
    private static void patientMenu(CRS crs, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Patient Management ---");
            System.out.println("1. Add Patient");
            System.out.println("2. List Patients");
            System.out.println("3. Get Patient");
            System.out.println("4. Remove Patient");
            System.out.println("0. Back");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter patient national ID: ");
                    long nationalID = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter patient first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter patient last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter patient gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter patient date of birth: ");
                    String dob = scanner.nextLine();
                    try {
                        crs.addPatient(new Patient(firstName, lastName, nationalID, gender, dob));
                    } catch (DuplicateInfoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    crs.listPatient();
                    break;
                case 3:
                    System.out.print("Enter patient national ID to get: ");
                    nationalID = scanner.nextLong();
                    try {
                        Patient patient = crs.searchPatient(nationalID);
                        System.out.println("Patient found: " + patient);
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter patient national ID to remove: ");
                    nationalID = scanner.nextLong();
                    try {
                        crs.removePatient(nationalID);
                    } catch (IDException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    
    private static void rendezvousMenu(CRS crs, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("\n--- Rendezvous Management ---");
            System.out.println("1. Add Rendezvous");
            System.out.println("2. List Rendezvous");
            System.out.println("3. Search Rendezvous");
            System.out.println("4. Remove Rendezvous");
            System.out.println("0. Back");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter patient ID: ");
                    long patientID = scanner.nextLong();
                    System.out.print("Enter hospital ID: ");
                    int hospitalID = scanner.nextInt();
                    System.out.print("Enter section ID: ");
                    int sectionID = scanner.nextInt();
                    System.out.print("Enter doctor diploma ID: ");
                    int diplomaID = scanner.nextInt();
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String dateStr = scanner.next();
                    Date date = Date.valueOf(dateStr);

                    if (crs.makeRandezvous(patientID, hospitalID, sectionID, diplomaID, date)) {
                        System.out.println("Rendezvous added successfully.");
                    } else {
                        System.out.println("Failed to add rendezvous.");
                    }
                    break;
                case 2:
                    for (Rendezvous rendezvous : crs.getRendezvous()) {
                        System.out.println(rendezvous);
                    }
                    break;
                case 3:
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    dateStr = scanner.next();
                    date = Date.valueOf(dateStr);
                    System.out.print("Enter patient national ID: ");
                    patientID = scanner.nextLong();

                    LinkedList<Rendezvous> results = searchRendezvous(crs.getRendezvous(), date, patientID);
                    if (results.isEmpty()) {
                        System.out.println("No rendezvous found for the given criteria.");
                    } else {
                        for (Rendezvous rendezvous : results) {
                            System.out.println(rendezvous);
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    dateStr = scanner.next();
                    date = Date.valueOf(dateStr);
                    System.out.print("Enter patient national ID: ");
                    patientID = scanner.nextLong();

                    boolean removed = removeRendezvous(crs.getRendezvous(), date, patientID);
                    if (removed) {
                        System.out.println("Rendezvous removed successfully.");
                    } else {
                        System.out.println("No rendezvous found to remove.");
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    

    private static LinkedList<Rendezvous> searchRendezvous(LinkedList<Rendezvous> rendezvousList, Date date, long patientID) {
        LinkedList<Rendezvous> result = new LinkedList<>();
        for (Rendezvous rendezvous : rendezvousList) {
            if (rendezvous.getDateTime().equals(date) && rendezvous.getPatient().getNationalID() == patientID) {
                result.add(rendezvous);
            }
        }
        return result;
    }

    private static boolean removeRendezvous(LinkedList<Rendezvous> rendezvousList, Date date, long patientID) {
        return rendezvousList.removeIf(rendezvous -> rendezvous.getDateTime().equals(date) && rendezvous.getPatient().getNationalID() == patientID);
    }
}
