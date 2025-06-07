package nyp2025Proje_AtaBas;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.stream.Collectors;

//manager sifre = 1234

public class HospitalManagementGUI {
    private CRS crs;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public HospitalManagementGUI() {
        setCrs(new CRS());
        createMainGUI();
    }

    private void createMainGUI() {
        JFrame frame = new JFrame("Clinic Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(240, 248, 255));

        
        mainPanel.add(createHomePanel(), "Home");
        mainPanel.add(createManagementPanel(), "Management");
        mainPanel.add(createPatientPanel(), "Patient");
        mainPanel.add(createHospitalPanel(), "Hospital");
        mainPanel.add(createAddHospitalPanel(), "AddHospital");
        mainPanel.add(createRemoveHospitalPanel(), "RemoveHospital");
        mainPanel.add(createListHospitalPanel(), "ListHospital");
        mainPanel.add(createSearchHospitalPanel(), "GetHospital");
        mainPanel.add(createSaveDataPanel(), "SaveData");
        mainPanel.add(createLoadDataPanel(), "LoadData");
        
        mainPanel.add(createSectionManagementPanel(), "SectionManagement");
        mainPanel.add(createAddSectionPanel(), "AddSection");
        mainPanel.add(createListSectionPanel(), "ListSection");
        mainPanel.add(createRemoveSectionPanel(), "RemoveSection");
        mainPanel.add(createSearchSectionPanel(), "SearchSection");
        
        mainPanel.add(createDoctorManagementPanel(), "DoctorManagement");
        mainPanel.add(createAddDoctorPanel(), "AddDoctor");
        mainPanel.add(createRemoveDoctorPanel(), "RemoveDoctor");
        mainPanel.add(createSearchDoctorPanel(), "SearchDoctor");
        mainPanel.add(createListDoctorPanel(), "ListDoctor");
        
        mainPanel.add(createSignUpPanel(), "SignUp");
        mainPanel.add(createMakeAnAppointmentPanel(), "MakeAppointment");
        mainPanel.add(createDeleteRegistrationPanel(), "DeleteRegistration");
        mainPanel.add(createRemoveAppointmentPanel(), "RemoveAppointment");
        
        mainPanel.add(createPatientManagementPanel(), "PatientManagement");
        mainPanel.add(createListPatientsPanel(), "ListPatients");
        mainPanel.add(createSearchPatientsPanel(), "SearchPatients");
        
        mainPanel.add(createRendezvousManagementPanel(), "RendezvousManagement");
        mainPanel.add(createListRendezvousPanel(), "ListRendezvous");
        mainPanel.add(createSearchRendezvousPanel(), "SearchRendezvous");


        

        
        frame.add(mainPanel);
        cardLayout.show(mainPanel, "Home");
        frame.setVisible(true);
    }

    private JPanel createSaveDataPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Save Data", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");

        styleButton(saveButton);
        styleButton(backButton);

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        saveButton.addActionListener(e -> {
            String filePath = "crs_dosya.txt"; 
            try {
                crs.saveTableToDisk(filePath);
                JOptionPane.showMessageDialog(panel, "Data saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));

        return panel;
    }

    private JPanel createLoadDataPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Load Data", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));

        JButton loadButton = new JButton("Load");
        JButton backButton = new JButton("Back");

        styleButton(loadButton);
        styleButton(backButton);

        buttonPanel.add(loadButton);
        buttonPanel.add(backButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        loadButton.addActionListener(e -> {
            String filePath = "crs_dosya.txt"; 
            try {
                CRS loadedCRS = CRS.loadTableToDisk(filePath);
                if (loadedCRS != null) {
                    crs.getHospitals().clear();
                    crs.getHospitals().putAll(loadedCRS.getHospitals());
                    crs.getPatients().clear();
                    crs.getPatients().putAll(loadedCRS.getPatients());
                    crs.getRendezvous().clear();
                    crs.getRendezvous().addAll(loadedCRS.getRendezvous());
                    JOptionPane.showMessageDialog(panel, "Data loaded successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "Failed to load data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));

        return panel;
    }
    
    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Clinic Reservation System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton patientButton = new JButton("Patient");
        JButton managerButton = new JButton("Manager");
        JButton exitButton = new JButton("Exit");

        patientButton.addActionListener(e -> cardLayout.show(mainPanel, "Patient"));
        
        managerButton.addActionListener(e -> {
            
            String password = JOptionPane.showInputDialog(
                null, 
                "Please enter the manager password:", 
                "Manager Authentication",
                JOptionPane.PLAIN_MESSAGE 
            );

            
            if (password != null && password.equals("1234")) { 
                cardLayout.show(mainPanel, "Management");
            } else if (password != null) {
                JOptionPane.showMessageDialog(
                    null,
                    "Incorrect password!",
                    "Authentication Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
        
        exitButton.addActionListener(e -> {
            
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(exitButton);
            topFrame.dispose();

            
            System.out.println("GUI is closed.");
        });

        styleButton(patientButton);
        styleButton(managerButton);
        styleButton(exitButton);

        buttonPanel.add(patientButton);
        buttonPanel.add(managerButton);
        buttonPanel.add(exitButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        
        

        return panel;
    }

    private JPanel createManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Management System", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton hospitalButton = new JButton("Hospital Management");
        JButton sectionButton = new JButton("Section Management");
        JButton doctorButton = new JButton("Doctor Management");
        JButton patientManagementButton = new JButton("Patient Management");
        JButton rendezvousButton = new JButton("Rendezvous Management");
        JButton saveButton = new JButton("Save Data");
        JButton loadButton = new JButton("Load Data");
        JButton backButton = new JButton("Back");
       

        hospitalButton.addActionListener(e -> cardLayout.show(mainPanel, "Hospital"));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        saveButton.addActionListener(e -> cardLayout.show(mainPanel, "SaveData"));
        loadButton.addActionListener(e -> cardLayout.show(mainPanel, "LoadData"));
        
        sectionButton.addActionListener(e -> cardLayout.show(mainPanel, "SectionManagement"));
        
        doctorButton.addActionListener(e -> cardLayout.show(mainPanel, "DoctorManagement"));
        
        patientManagementButton.addActionListener(e -> cardLayout.show(mainPanel, "PatientManagement"));
        
        rendezvousButton.addActionListener(e -> cardLayout.show(mainPanel, "RendezvousManagement"));

        styleButton(hospitalButton);
        styleButton(sectionButton);
        styleButton(doctorButton);
        styleButton(patientManagementButton);
        styleButton(rendezvousButton);
        styleButton(saveButton);
        styleButton(loadButton);
        styleButton(backButton);

        buttonPanel.add(hospitalButton);
        buttonPanel.add(sectionButton);
        buttonPanel.add(doctorButton);
        buttonPanel.add(patientManagementButton); 
        buttonPanel.add(rendezvousButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(backButton);
       

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Patient Menu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton signUpButton = new JButton("Sign Up");
        JButton appointmentButton = new JButton("Make An Appointment");
        JButton deleteRegistrationButton = new JButton("Delete Registration");
        JButton removeAppointmentButton = new JButton("Remove Appointment");
        JButton saveButton = new JButton("Save Data");
        JButton loadButton = new JButton("Load Data");
        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        saveButton.addActionListener(e -> cardLayout.show(mainPanel, "SaveData"));
        loadButton.addActionListener(e -> cardLayout.show(mainPanel, "LoadData"));
        signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUp"));
        appointmentButton.addActionListener(e -> cardLayout.show(mainPanel, "MakeAppointment"));
        deleteRegistrationButton.addActionListener(e -> cardLayout.show(mainPanel, "DeleteRegistration"));
        removeAppointmentButton.addActionListener(e -> cardLayout.show(mainPanel, "RemoveAppointment"));
        
        styleButton(signUpButton);
        styleButton(appointmentButton);
        styleButton(deleteRegistrationButton);
        styleButton(removeAppointmentButton);
        styleButton(saveButton);
        styleButton(loadButton);
        styleButton(backButton);

        buttonPanel.add(signUpButton);
        buttonPanel.add(deleteRegistrationButton);
        buttonPanel.add(appointmentButton);
        buttonPanel.add(removeAppointmentButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(backButton);
        
        

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createHospitalPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Hospital Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton addHospitalButton = new JButton("Add Hospital");
        JButton removeHospitalButton = new JButton("Remove Hospital");
        JButton listHospitalButton = new JButton("List Hospital");
        JButton searchHospitalButton = new JButton("Search Hospital");
        JButton backButton = new JButton("Back");

        addHospitalButton.addActionListener(e -> cardLayout.show(mainPanel, "AddHospital"));
        removeHospitalButton.addActionListener(e -> cardLayout.show(mainPanel, "RemoveHospital"));
        listHospitalButton.addActionListener(e -> cardLayout.show(mainPanel, "ListHospital"));
        searchHospitalButton.addActionListener(e -> cardLayout.show(mainPanel, "GetHospital"));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Management"));

        styleButton(addHospitalButton);
        styleButton(removeHospitalButton);
        styleButton(listHospitalButton);
        styleButton(searchHospitalButton);
        styleButton(backButton);

        buttonPanel.add(addHospitalButton);
        buttonPanel.add(removeHospitalButton);
        buttonPanel.add(listHospitalButton);
        buttonPanel.add(searchHospitalButton);
        buttonPanel.add(backButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAddHospitalPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Add Hospital", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("Hospital ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField idField = new JTextField(15);

        JLabel nameLabel = new JLabel("Hospital Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField nameField = new JTextField(15);

        JButton addButton = new JButton("Add");
        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        styleButton(addButton);
        styleButton(refreshButton);
        styleButton(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(addButton, gbc);

        gbc.gridy = 3;
        formPanel.add(refreshButton, gbc);

        gbc.gridy = 4;
        formPanel.add(backButton, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(240, 248, 255));

        JLabel listTitle = new JLabel("Hospital List", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 24));
        rightPanel.add(listTitle, BorderLayout.NORTH);

        JTable hospitalTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(hospitalTable);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        updateHospitalTable(hospitalTable);

        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                crs.addHospital(new Hospital(id, name));
                crs.saveTableToDisk("crs_dosya.txt");
                updateHospitalTable(hospitalTable);
                JOptionPane.showMessageDialog(panel, "Hospital added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                idField.setText("");
                nameField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Invalid ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (DuplicateInfoException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshButton.addActionListener(e -> updateHospitalTable(hospitalTable));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Hospital"));

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        return panel;
    }


    
    private void updateHospitalTable(JTable hospitalTable) {
        String[] columnNames = {"ID", "Name"};
        String[][] data;
        try {
            CRS loadedCRS = CRS.loadTableToDisk("crs_dosya.txt"); 
            if (loadedCRS != null) {
                crs.getHospitals().clear();
                crs.getHospitals().putAll(loadedCRS.getHospitals());
                data = new String[crs.getHospitals().size()][2];
                int index = 0;
                for (Hospital hospital : crs.getHospitals().values()) {
                    data[index][0] = String.valueOf(hospital.getId());
                    data[index][1] = hospital.getName();
                    index++;
                }
            } else {
                data = new String[0][2];
            }
        } catch (Exception e) {
            data = new String[0][2];
        }

        hospitalTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    
    private JPanel createRemoveHospitalPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Remove Hospital", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel idLabel = new JLabel("Hospital ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField idField = new JTextField(15);

        JButton removeButton = new JButton("Remove");
        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        styleButton(removeButton);
        styleButton(refreshButton);
        styleButton(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(removeButton, gbc);

        gbc.gridy = 2;
        formPanel.add(refreshButton, gbc);

        gbc.gridy = 3;
        formPanel.add(backButton, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(240, 248, 255));

        JLabel listTitle = new JLabel("Hospital List", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 24));
        rightPanel.add(listTitle, BorderLayout.NORTH);

        JTable hospitalTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(hospitalTable);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        updateHospitalTable(hospitalTable);

        removeButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                crs.removeHospital(id);
                crs.saveTableToDisk("crs_dosya.txt");
                updateHospitalTable(hospitalTable);

                JOptionPane.showMessageDialog(panel, "Hospital removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                idField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Invalid ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IDException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshButton.addActionListener(e -> updateHospitalTable(hospitalTable));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Hospital"));

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createListHospitalPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("List Hospitals", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JTable hospitalTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(hospitalTable);

        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        styleButton(refreshButton);
        styleButton(backButton);

        refreshButton.addActionListener(e -> updateAllHospitalTables(hospitalTable));

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Hospital"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        
        SwingUtilities.invokeLater(() -> updateAllHospitalTables(hospitalTable));

        return panel;
    }
    
    
    private void updateAllHospitalTables(JTable hospitalTable) {
        try {
            CRS loadedCRS = CRS.loadTableToDisk("crs_dosya.txt");
            if (loadedCRS != null) {
                crs.getHospitals().clear();
                crs.getHospitals().putAll(loadedCRS.getHospitals());

                String[][] data = crs.getHospitals().values().stream()
                    .map(h -> new String[]{String.valueOf(h.getId()), h.getName()})
                    .toArray(String[][]::new);

                String[] columnNames = {"Hospital ID", "Name"};
                hospitalTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            } else {
                hospitalTable.setModel(new javax.swing.table.DefaultTableModel(new String[0][0], new String[]{"Hospital ID", "Name"}));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private JPanel createSearchHospitalPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Search Hospital", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Hospital Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField nameField = new JTextField(15);

        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");

        styleButton(searchButton);
        styleButton(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(searchButton, gbc);

        gbc.gridy = 2;
        formPanel.add(backButton, gbc);

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        searchButton.addActionListener(e -> {
            String name = nameField.getText();
            try {
                Hospital hospital = crs.searchHospital(name);
                JOptionPane.showMessageDialog(panel, "Hospital found: ID: " + hospital.getId() + ", Name: " + hospital.getName(), "Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (IDException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Hospital"));

        return panel;
    }


    ////////////////////////////////////////////////////////////////////////////////////
    
    
    private JPanel createSectionManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Section Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton addSectionButton = new JButton("Add Section");
        JButton removeSectionButton = new JButton("Remove Section");
        JButton listSectionButton = new JButton("List Sections");
        JButton searchSectionButton = new JButton("Search Sections");
        JButton backButton = new JButton("Back");
        

        styleButton(addSectionButton);
        styleButton(removeSectionButton);
        styleButton(listSectionButton);
        styleButton(searchSectionButton);
        styleButton(backButton);

        buttonPanel.add(addSectionButton);
        buttonPanel.add(removeSectionButton);
        buttonPanel.add(listSectionButton);
        buttonPanel.add(searchSectionButton);
        buttonPanel.add(backButton);

        addSectionButton.addActionListener(e -> cardLayout.show(mainPanel, "AddSection"));
        removeSectionButton.addActionListener(e -> cardLayout.show(mainPanel, "RemoveSection"));
        listSectionButton.addActionListener(e -> cardLayout.show(mainPanel, "ListSection"));
        searchSectionButton.addActionListener(e -> cardLayout.show(mainPanel, "SearchSection"));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Management"));

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    

    private JPanel createListSectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("List Sections", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JTable sectionTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(sectionTable);

        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        styleButton(refreshButton);
        styleButton(backButton);

        refreshButton.addActionListener(e -> updateAllSectionTables(sectionTable));

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "SectionManagement"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        
        SwingUtilities.invokeLater(() -> updateAllSectionTables(sectionTable));

        return panel;
    }


    private JPanel createAddSectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Add Section", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel hospitalIdLabel = new JLabel("Hospital ID:");
        hospitalIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField hospitalIdField = new JTextField(15);

        JLabel sectionIdLabel = new JLabel("Section ID:");
        sectionIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField sectionIdField = new JTextField(15);

        JLabel sectionNameLabel = new JLabel("Section Name:");
        sectionNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField sectionNameField = new JTextField(15);

        JButton addButton = new JButton("Add");
        JButton backButton = new JButton("Back");
        JButton refreshButton = new JButton("Refresh");

        styleButton(addButton);
        styleButton(backButton);
        styleButton(refreshButton);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(hospitalIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(hospitalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(sectionIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(sectionIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(sectionNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(sectionNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(addButton, gbc);

        gbc.gridy = 4;
        formPanel.add(refreshButton, gbc);

        gbc.gridy = 5;
        formPanel.add(backButton, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(240, 248, 255));

        JLabel listTitle = new JLabel("Section List", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 24));
        rightPanel.add(listTitle, BorderLayout.NORTH);

        JTable sectionTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(sectionTable);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                int hospitalId = Integer.parseInt(hospitalIdField.getText());
                int sectionId = Integer.parseInt(sectionIdField.getText());
                String sectionName = sectionNameField.getText();

                Hospital hospital = crs.getHospitals().get(hospitalId);
                if (hospital == null) {
                    JOptionPane.showMessageDialog(panel, "Hospital ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                hospital.addSection(new Section(sectionId, sectionName));
                crs.saveTableToDisk("crs_dosya.txt");
                updateSectionTable(hospitalId, sectionTable);
                JOptionPane.showMessageDialog(panel, "Section added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                hospitalIdField.setText("");
                sectionIdField.setText("");
                sectionNameField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshButton.addActionListener(e -> updateAllSectionTables(sectionTable));

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "SectionManagement"));

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        
        SwingUtilities.invokeLater(() -> updateAllSectionTables(sectionTable));

        return panel;
    }


    private JPanel createRemoveSectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Remove Section", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel hospitalIdLabel = new JLabel("Hospital ID:");
        hospitalIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField hospitalIdField = new JTextField(15);

        JLabel sectionIdLabel = new JLabel("Section ID:");
        sectionIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField sectionIdField = new JTextField(15);

        JButton removeButton = new JButton("Remove");
        JButton backButton = new JButton("Back");
        JButton refreshButton = new JButton("Refresh");

        styleButton(removeButton);
        styleButton(backButton);
        styleButton(refreshButton);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(hospitalIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(hospitalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(sectionIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(sectionIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(removeButton, gbc);

        gbc.gridy = 3;
        formPanel.add(refreshButton, gbc);

        gbc.gridy = 4;
        formPanel.add(backButton, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(240, 248, 255));

        JLabel listTitle = new JLabel("Section List", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 24));
        rightPanel.add(listTitle, BorderLayout.NORTH);

        JTable sectionTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(sectionTable);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        removeButton.addActionListener(e -> {
            try {
                int hospitalId = Integer.parseInt(hospitalIdField.getText());
                int sectionId = Integer.parseInt(sectionIdField.getText());

                Hospital hospital = crs.getHospitals().get(hospitalId);
                if (hospital == null) {
                    JOptionPane.showMessageDialog(panel, "Hospital ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                hospital.removeSection(sectionId);
                crs.saveTableToDisk("crs_dosya.txt");
                updateSectionTable(hospitalId, sectionTable);
                JOptionPane.showMessageDialog(panel, "Section removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                hospitalIdField.setText("");
                sectionIdField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "SectionManagement"));
        refreshButton.addActionListener(e -> updateAllSectionTables(sectionTable));

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

       
        SwingUtilities.invokeLater(() -> updateAllSectionTables(sectionTable));

        return panel;
    }

    
    private JPanel createSearchSectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Search Section", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel hospitalIdLabel = new JLabel("Hospital ID:");
        hospitalIdLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField hospitalIdField = new JTextField(15);

        JLabel sectionIdLabel = new JLabel("Section ID:");
        sectionIdLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField sectionIdField = new JTextField(15);

        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");

        styleButton(searchButton);
        styleButton(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(hospitalIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(hospitalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(sectionIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(sectionIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(searchButton, gbc);

        gbc.gridy = 3;
        formPanel.add(backButton, gbc);

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        searchButton.addActionListener(e -> {
            try {
                int hospitalId = Integer.parseInt(hospitalIdField.getText());
                int sectionId = Integer.parseInt(sectionIdField.getText());

                Hospital hospital = crs.getHospitals().get(hospitalId);
                if (hospital == null) {
                    JOptionPane.showMessageDialog(panel, "Hospital ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Section section = hospital.getSection(sectionId);
                if (section == null) {
                    JOptionPane.showMessageDialog(panel, "Section ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(panel, "Section found: Hospital ID: " + hospitalId + ", Section ID: " + section.getId() + ", Name: " + section.getName(), "Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter valid numerical values.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "SectionManagement"));

        return panel;
    }

    

    private void updateSectionTable(int hospitalId, JTable sectionTable) {
        String[] columnNames = {"ID", "Name"};
        String[][] data;
        try {
            Hospital hospital = crs.getHospitals().get(hospitalId);
            if (hospital != null) {
                data = new String[hospital.getSections().size()][2];
                int index = 0;
                for (Section section : hospital.getSections()) {
                    data[index][0] = String.valueOf(section.getId());
                    data[index][1] = section.getName();
                    index++;
                }
            } else {
                data = new String[0][2];
            }
        } catch (Exception e) {
            data = new String[0][2];
        }

        sectionTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private void updateAllSectionTables(JTable sectionTable) {
        String[] columnNames = {"Hospital ID", "Section ID", "Name"};
        String[][] data;
        try {
            int totalSections = crs.getHospitals().values().stream()
                    .mapToInt(hospital -> hospital.getSections().size()).sum();

            data = new String[totalSections][3];
            int index = 0;
            for (Hospital hospital : crs.getHospitals().values()) {
                for (Section section : hospital.getSections()) {
                    data[index][0] = String.valueOf(hospital.getId());
                    data[index][1] = String.valueOf(section.getId());
                    data[index][2] = section.getName();
                    index++;
                }
            }
        } catch (Exception e) {
            data = new String[0][3];
        }

        sectionTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    private JPanel createDoctorManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Doctor Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10)); 
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton addDoctorButton = new JButton("Add Doctor");
        JButton removeDoctorButton = new JButton("Remove Doctor");
        JButton listDoctorButton = new JButton("List Doctor"); 
        JButton searchDoctorButton = new JButton("Search Doctor");
        JButton backButton = new JButton("Back");

        styleButton(addDoctorButton);
        styleButton(removeDoctorButton);
        styleButton(listDoctorButton);
        styleButton(searchDoctorButton);
        styleButton(backButton);

        buttonPanel.add(addDoctorButton);
        buttonPanel.add(removeDoctorButton);
        buttonPanel.add(listDoctorButton); 
        buttonPanel.add(searchDoctorButton);
        buttonPanel.add(backButton);

        addDoctorButton.addActionListener(e -> cardLayout.show(mainPanel, "AddDoctor"));
        removeDoctorButton.addActionListener(e -> cardLayout.show(mainPanel, "RemoveDoctor"));
        listDoctorButton.addActionListener(e -> cardLayout.show(mainPanel, "ListDoctor")); // Corrected action
        searchDoctorButton.addActionListener(e -> cardLayout.show(mainPanel, "SearchDoctor"));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Management"));

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }


    
    
    
    private JPanel createAddDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Add Doctor", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel hospitalIdLabel = new JLabel("Hospital ID:");
        hospitalIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField hospitalIdField = new JTextField(10);

        JLabel sectionIdLabel = new JLabel("Section ID:");
        sectionIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField sectionIdField = new JTextField(10);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField firstNameField = new JTextField(10);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField lastNameField = new JTextField(10);

        JLabel nationalIdLabel = new JLabel("National ID:");
        nationalIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField nationalIdField = new JTextField(10);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField genderField = new JTextField(10);

        JLabel dobLabel = new JLabel("Date of Birth (yyyy-mm-dd):");
        dobLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField dobField = new JTextField(10);

        JLabel diplomaIdLabel = new JLabel("Diploma ID:");
        diplomaIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField diplomaIdField = new JTextField(10);

        JLabel maxPatientsLabel = new JLabel("Max Patients per Day:");
        maxPatientsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField maxPatientsField = new JTextField(10);

        JButton addButton = new JButton("Add");
        JButton backButton = new JButton("Back");
        JButton refreshButton = new JButton("Refresh");

        styleButton(addButton);
        styleButton(backButton);
        styleButton(refreshButton);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(hospitalIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(hospitalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(sectionIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(sectionIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(nationalIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nationalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(genderLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(genderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(dobLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(diplomaIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(diplomaIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(maxPatientsLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(maxPatientsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(addButton, gbc);

        gbc.gridy = 10;
        formPanel.add(refreshButton, gbc);

        gbc.gridy = 11;
        formPanel.add(backButton, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(240, 248, 255));

        JLabel listTitle = new JLabel("Doctor List", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 20)); 
        rightPanel.add(listTitle, BorderLayout.NORTH);

        JTable doctorTable = new JTable();
        doctorTable.setPreferredScrollableViewportSize(new Dimension(400, 100)); 
        JScrollPane scrollPane = new JScrollPane(doctorTable);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                int hospitalId = Integer.parseInt(hospitalIdField.getText());
                int sectionId = Integer.parseInt(sectionIdField.getText());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                long nationalId = Long.parseLong(nationalIdField.getText());
                String gender = genderField.getText();
                String dob = dobField.getText();
                int diplomaId = Integer.parseInt(diplomaIdField.getText());
                int maxPatients = Integer.parseInt(maxPatientsField.getText());

                Hospital hospital = crs.getHospitals().get(hospitalId);
                if (hospital == null) {
                    JOptionPane.showMessageDialog(panel, "Hospital ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Section section = hospital.getSection(sectionId);
                if (section == null) {
                    JOptionPane.showMessageDialog(panel, "Section ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Schedule schedule = new Schedule(maxPatients);
                Doctor doctor = new Doctor(firstName, lastName, nationalId, gender, dob, diplomaId, schedule);
                section.addDoctor(doctor);

                crs.saveTableToDisk("crs_dosya.txt");
                updateDoctorTable(hospitalId, sectionId, doctorTable);
                JOptionPane.showMessageDialog(panel, "Doctor added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                hospitalIdField.setText("");
                sectionIdField.setText("");
                firstNameField.setText("");
                lastNameField.setText("");
                nationalIdField.setText("");
                genderField.setText("");
                dobField.setText("");
                diplomaIdField.setText("");
                maxPatientsField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshButton.addActionListener(e -> updateAllDoctorTables(doctorTable));

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "DoctorManagement"));

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

        
        SwingUtilities.invokeLater(() -> updateAllDoctorTables(doctorTable));

        return panel;
    }



    private JPanel createRemoveDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Remove Doctor", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel hospitalIdLabel = new JLabel("Hospital ID:");
        JTextField hospitalIdField = new JTextField(15);

        JLabel sectionIdLabel = new JLabel("Section ID:");
        JTextField sectionIdField = new JTextField(15);

        JLabel diplomaIdLabel = new JLabel("Diploma ID:");
        JTextField diplomaIdField = new JTextField(15);

        JButton removeButton = new JButton("Remove");
        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        styleButton(removeButton);
        styleButton(refreshButton);
        styleButton(backButton);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(hospitalIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(hospitalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(sectionIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(sectionIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(diplomaIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(diplomaIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(removeButton, gbc);

        gbc.gridy = 4;
        formPanel.add(refreshButton, gbc);

        gbc.gridy = 5;
        formPanel.add(backButton, gbc);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(240, 248, 255));

        JLabel listTitle = new JLabel("Doctor List", SwingConstants.CENTER);
        listTitle.setFont(new Font("Arial", Font.BOLD, 24));
        rightPanel.add(listTitle, BorderLayout.NORTH);

        JTable doctorTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(doctorTable);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        removeButton.addActionListener(e -> {
            try {
                int hospitalId = Integer.parseInt(hospitalIdField.getText());
                int sectionId = Integer.parseInt(sectionIdField.getText());
                int diplomaId = Integer.parseInt(diplomaIdField.getText());

                Hospital hospital = crs.getHospitals().get(hospitalId);
                if (hospital == null) {
                    JOptionPane.showMessageDialog(panel, "Hospital ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Section section = hospital.getSection(sectionId);
                if (section == null) {
                    JOptionPane.showMessageDialog(panel, "Section ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                section.removeDoctor(diplomaId);
                crs.saveTableToDisk("crs_dosya.txt");
                updateAllDoctorTables(doctorTable);
                JOptionPane.showMessageDialog(panel, "Doctor removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                hospitalIdField.setText("");
                sectionIdField.setText("");
                diplomaIdField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshButton.addActionListener(e -> updateAllDoctorTables(doctorTable));

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "DoctorManagement"));

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);

      
        SwingUtilities.invokeLater(() -> updateAllDoctorTables(doctorTable));

        return panel;
    }
    
    
    private JPanel createSearchDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Search Doctor", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel hospitalIdLabel = new JLabel("Hospital ID:");
        hospitalIdLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField hospitalIdField = new JTextField(15);

        JLabel sectionIdLabel = new JLabel("Section ID:");
        sectionIdLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField sectionIdField = new JTextField(15);

        JLabel diplomaIdLabel = new JLabel("Diploma ID:");
        diplomaIdLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField diplomaIdField = new JTextField(15);

        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");

        styleButton(searchButton);
        styleButton(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(hospitalIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(hospitalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(sectionIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(sectionIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(diplomaIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(diplomaIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(searchButton, gbc);

        gbc.gridy = 4;
        formPanel.add(backButton, gbc);

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        searchButton.addActionListener(e -> {
            try {
                int hospitalId = Integer.parseInt(hospitalIdField.getText());
                int sectionId = Integer.parseInt(sectionIdField.getText());
                int diplomaId = Integer.parseInt(diplomaIdField.getText());

                Hospital hospital = crs.getHospitals().get(hospitalId);
                if (hospital == null) {
                    JOptionPane.showMessageDialog(panel, "Hospital ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Section section = hospital.getSection(sectionId);
                if (section == null) {
                    JOptionPane.showMessageDialog(panel, "Section ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Doctor doctor = section.getDoctor(diplomaId);
                if (doctor == null) {
                    JOptionPane.showMessageDialog(panel, "Doctor with Diploma ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                JOptionPane.showMessageDialog(panel, "Doctor found:\nFirst Name: " + doctor.getFirstName() +
                        "\nLast Name: " + doctor.getLastName() +
                        "\nNational ID: " + doctor.getNationalID() +
                        "\nGender: " + doctor.getGender() +
                        "\nDate of Birth: " + doctor.getDateOfBirth() +
                        "\nDiploma ID: " + doctor.getDiplomaID() +
                        "\nMax Patients/Day: " + doctor.getSchedule().getMaxPatientPerDay(), "Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter valid numerical values.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "DoctorManagement"));

        return panel;
    }

    
    private JPanel createListDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("List Doctors", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JTable doctorTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(doctorTable);

        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        styleButton(refreshButton);
        styleButton(backButton);

        refreshButton.addActionListener(e -> updateAllDoctorTables(doctorTable));

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "DoctorManagement"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        
        SwingUtilities.invokeLater(() -> updateAllDoctorTables(doctorTable));

        return panel;
    }
    
    private void updateAllDoctorTables(JTable doctorTable) {
    	String[] columnNames = {"First Name", "Last Name", "National ID", "Hospital ID", "Section ID", "Diploma ID", "Max Patients/Day"};
    	String[][] data;

    	try {
    	    CRS loadedCRS = CRS.loadTableToDisk("crs_dosya.txt");
    	    if (loadedCRS != null) {
    	        crs.getHospitals().clear();
    	        crs.getHospitals().putAll(loadedCRS.getHospitals());

    	        
    	        LinkedList<Doctor> allDoctors = new LinkedList<>();
    	        for (Hospital hospital : crs.getHospitals().values()) {
    	            for (Section section : hospital.getSections()) {
    	                allDoctors.addAll(section.getDoctors());
    	            }
    	        }

    	        data = new String[allDoctors.size()][7];
    	        int index = 0;
    	        for (Doctor doctor : allDoctors) {
    	            Hospital hospital = null;
    	            Section section = null;
    	            for (Hospital h : crs.getHospitals().values()) {
    	                for (Section s : h.getSections()) {
    	                    if (s.getDoctors().contains(doctor)) {
    	                        hospital = h;
    	                        section = s;
    	                        break;
    	                    }
    	                }
    	                if (hospital != null) break;
    	            }

    	            data[index][0] = doctor.getFirstName();
    	            data[index][1] = doctor.getLastName();
    	            data[index][2] = String.valueOf(doctor.getNationalID());
    	            data[index][3] = hospital != null ? String.valueOf(hospital.getId()) : "Unknown";
    	            data[index][4] = section != null ? String.valueOf(section.getId()) : "Unknown";
    	            data[index][5] = String.valueOf(doctor.getDiplomaID());
    	            data[index][6] = String.valueOf(doctor.getSchedule().getMaxPatientPerDay());
    	            index++;
    	        }
    	    } else {
    	        data = new String[0][7];
    	    }
    	} catch (Exception e) {
    	    data = new String[0][7];
    	}

    	doctorTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));

    }

    
    
    private void updateDoctorTable(int hospitalId, int sectionId, JTable doctorTable) {
        String[] columnNames = {"First Name", "Last Name", "National ID", "Gender", "Date of Birth", "Diploma ID", "Max Patients/Day"};
        String[][] data;

        try {
            Hospital hospital = crs.getHospitals().get(hospitalId);
            if (hospital != null) {
                Section section = hospital.getSection(sectionId);
                if (section != null) {
                    data = new String[section.getDoctors().size()][7];
                    int index = 0;
                    for (Doctor doctor : section.getDoctors()) {
                        data[index][0] = doctor.getFirstName();
                        data[index][1] = doctor.getLastName();
                        data[index][2] = String.valueOf(doctor.getNationalID());
                        data[index][3] = doctor.getGender();
                        data[index][4] = doctor.getDateOfBirth();
                        data[index][5] = String.valueOf(doctor.getDiplomaID());
                        data[index][6] = String.valueOf(doctor.getSchedule().getMaxPatientPerDay());
                        index++;
                    }
                } else {
                    data = new String[0][7];
                }
            } else {
                data = new String[0][7];
            }
        } catch (Exception e) {
            data = new String[0][7];
        }

        doctorTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    private JPanel createSignUpPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Sign Up", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(15);

        JLabel nationalIdLabel = new JLabel("National ID:");
        JTextField nationalIdField = new JTextField(15);

        JLabel genderLabel = new JLabel("Gender:");
        JTextField genderField = new JTextField(15);

        JLabel dobLabel = new JLabel("Date of Birth (yyyy-mm-dd):");
        JTextField dobField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(15);

        JButton addButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        styleButton(addButton);
        styleButton(backButton);


        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(firstNameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lastNameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(nationalIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nationalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(genderLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(genderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(dobLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(addressLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(addButton, gbc);

        gbc.gridy = 9;
        formPanel.add(backButton, gbc);

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                long nationalId = Long.parseLong(nationalIdField.getText());
                String gender = genderField.getText();
                String dob = dobField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String address = addressField.getText();

                Patient patient = new Patient(firstName, lastName, nationalId, gender, dob, phone, email, address);
                crs.addPatient(patient);
                crs.saveTableToDisk("crs_dosya.txt");

                JOptionPane.showMessageDialog(panel, "Patient registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                firstNameField.setText("");
                lastNameField.setText("");
                nationalIdField.setText("");
                genderField.setText("");
                dobField.setText("");
                phoneField.setText("");
                emailField.setText("");
                addressField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Patient"));

        return panel;
    }


    
   
    private JPanel createMakeAnAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Make An Appointment", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel patientIDLabel = new JLabel("Patient ID (National ID):");
        JTextField patientIDField = new JTextField(15);

        JLabel hospitalIDLabel = new JLabel("Hospital ID:");
        JTextField hospitalIDField = new JTextField(15);

        JLabel sectionIDLabel = new JLabel("Section ID:");
        JTextField sectionIDField = new JTextField(15);

        JLabel diplomaIDLabel = new JLabel("Doctor Diploma ID:");
        JTextField diplomaIDField = new JTextField(15);

        JLabel dateLabel = new JLabel("Date (yyyy-mm-dd):");
        JTextField dateField = new JTextField(15);

        JButton addButton = new JButton("Add Appointment");
        JButton backButton = new JButton("Back");

        styleButton(addButton);
        styleButton(backButton);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(patientIDLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(patientIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(hospitalIDLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(hospitalIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(sectionIDLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(sectionIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(diplomaIDLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(diplomaIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(addButton, gbc);

        gbc.gridy = 6;
        formPanel.add(backButton, gbc);

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            try {
                long patientID = Long.parseLong(patientIDField.getText());
                int hospitalID = Integer.parseInt(hospitalIDField.getText());
                int sectionID = Integer.parseInt(sectionIDField.getText());
                int diplomaID = Integer.parseInt(diplomaIDField.getText());
                Date date = Date.valueOf(dateField.getText());

                // Check if patient exists
                Patient patient = crs.getPatients().get(patientID);
                if (patient == null) {
                    JOptionPane.showMessageDialog(panel, "Patient not found! Redirecting to patient registration form.");
                    showSignUpForm(patientID, hospitalID, sectionID, diplomaID, date);
                } else {
                    if (crs.makeRandezvous(patientID, hospitalID, sectionID, diplomaID, date)) {
                    	crs.saveTableToDisk("crs_dosya.txt");
                    	JOptionPane.showMessageDialog(panel, "Appointment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(panel, "Failed to add appointment.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Patient"));

        return panel;
    }



   
    private void showSignUpForm(long patientID, int hospitalID, int sectionID, int diplomaID, Date appointmentDate) {
        JPanel signUpPanel = new JPanel(new GridBagLayout());
        signUpPanel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Patient Registration", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(15);

        JLabel genderLabel = new JLabel("Gender:");
        JTextField genderField = new JTextField(15);

        JLabel dobLabel = new JLabel("Date of Birth (yyyy-mm-dd):");
        JTextField dobField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(15);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(15);

        JButton saveButton = new JButton("Save and Book Appointment");
        JButton backButton = new JButton("Back");

        styleButton(saveButton);
        styleButton(backButton);

       
        gbc.gridx = 0;
        gbc.gridy = 0;
        signUpPanel.add(firstNameLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        signUpPanel.add(lastNameLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        signUpPanel.add(genderLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(genderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        signUpPanel.add(dobLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        signUpPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        signUpPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        signUpPanel.add(addressLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        signUpPanel.add(saveButton, gbc);

        gbc.gridy = 8;
        signUpPanel.add(backButton, gbc);

        mainPanel.add(signUpPanel, "SignUpForm");
        cardLayout.show(mainPanel, "SignUpForm");

        saveButton.addActionListener(e -> {
            try {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String gender = genderField.getText();
                String dob = dobField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String address = addressField.getText();

                Patient newPatient = new Patient(firstName, lastName, patientID, gender, dob, phone, email, address);
                crs.addPatient(newPatient);
                crs.saveTableToDisk("crs_dosya.txt");

                JOptionPane.showMessageDialog(signUpPanel, "Patient registered successfully!");

                if (crs.makeRandezvous(patientID, hospitalID, sectionID, diplomaID, appointmentDate)) {
                	crs.saveTableToDisk("crs_dosya.txt");
                	JOptionPane.showMessageDialog(signUpPanel, "Appointment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(signUpPanel, "Failed to add appointment.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                cardLayout.show(mainPanel, "Patient");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(signUpPanel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "MakeAppointment"));
    }


    private JPanel createDeleteRegistrationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Delete Registration", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nationalIdLabel = new JLabel("National ID:");
        JTextField nationalIdField = new JTextField(15);

        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        styleButton(deleteButton);
        styleButton(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nationalIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nationalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(deleteButton, gbc);

        gbc.gridy = 2;
        formPanel.add(backButton, gbc);

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        deleteButton.addActionListener(e -> {
            try {
                long nationalID = Long.parseLong(nationalIdField.getText());
                crs.removePatient(nationalID); 
                crs.saveTableToDisk("crs_dosya.txt"); 
                JOptionPane.showMessageDialog(panel, "Patient registration deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Invalid National ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IDException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Patient"));

        return panel;
    }

    
    private JPanel createRemoveAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Remove Appointment", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nationalIdLabel = new JLabel("Patient National ID:");
        JTextField nationalIdField = new JTextField(15);

        JLabel dateLabel = new JLabel("Appointment Date (yyyy-mm-dd):");
        JTextField dateField = new JTextField(15);

        JButton removeButton = new JButton("Remove");
        JButton backButton = new JButton("Back");

        styleButton(removeButton);
        styleButton(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nationalIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nationalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(removeButton, gbc);

        gbc.gridy = 3;
        formPanel.add(backButton, gbc);

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        
        removeButton.addActionListener(e -> {
            try {
                long nationalID = Long.parseLong(nationalIdField.getText());
                String dateStr = dateField.getText();
                java.sql.Date date = java.sql.Date.valueOf(dateStr);

               
                boolean removed = false;
                for (Rendezvous rendezvous : crs.getRendezvous()) {
                    if (rendezvous.getDateTime().equals(date) && rendezvous.getPatient().getNationalID() == nationalID) {
                        crs.getRendezvous().remove(rendezvous);
                        removed = true;
                        break;
                    }
                }

                if (removed) {
                    crs.saveTableToDisk("crs_dosya.txt"); 
                    JOptionPane.showMessageDialog(panel, "Appointment removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(panel, "No appointment found for the given criteria.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Invalid input format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(panel, "Invalid date format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Patient"));

        return panel;
    }

    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private JPanel createPatientManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Patient Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10)); 
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton listPatientsButton = new JButton("List Patients");
        JButton searchPatientsButton = new JButton("Search Patients");
        JButton backButton = new JButton("Back");

        listPatientsButton.addActionListener(e -> cardLayout.show(mainPanel, "ListPatients"));
        searchPatientsButton.addActionListener(e -> cardLayout.show(mainPanel, "SearchPatients"));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Management"));

        styleButton(listPatientsButton);
        styleButton(searchPatientsButton);
        styleButton(backButton);

        buttonPanel.add(listPatientsButton);
        buttonPanel.add(searchPatientsButton);
        buttonPanel.add(backButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createListPatientsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("List Patients", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JTable patientTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(patientTable);

        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        styleButton(refreshButton);
        styleButton(backButton);

        refreshButton.addActionListener(e -> updatePatientsTable(patientTable));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "PatientManagement"));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        updatePatientsTable(patientTable);
        
        SwingUtilities.invokeLater(() -> {
            String filePath = "crs_dosya.txt"; 
            CRS loadedCRS = CRS.loadTableToDisk(filePath);
            if (loadedCRS != null) {
                crs.getPatients().clear();
                crs.getPatients().putAll(loadedCRS.getPatients());
                updatePatientsTable(patientTable); 
            } else {
                JOptionPane.showMessageDialog(panel, "Failed to load patient data from file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }


    private void updatePatientsTable(JTable patientTable) {
        String[] columnNames = {"First Name", "Last Name", "National ID", "Gender", "Date of Birth", "Phone", "Email", "Address"};
        String[][] data = crs.getPatients().values().stream()
                .map(patient -> new String[]{
                        patient.getFirstName(),
                        patient.getLastName(),
                        String.valueOf(patient.getNationalID()),
                        patient.getGender(),
                        patient.getDateOfBirth(),
                        patient.getPhoneNumber(),
                        patient.geteMail(),
                        patient.getAddress()
                })
                .toArray(String[][]::new);

        patientTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }


    
    
    private JPanel createSearchPatientsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Search Patients", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nationalIdLabel = new JLabel("Enter National ID:");
        JTextField nationalIdField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");

        styleButton(searchButton);
        styleButton(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nationalIdLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nationalIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(searchButton, gbc);

        gbc.gridy = 2;
        formPanel.add(backButton, gbc);

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        searchButton.addActionListener(e -> {
            try {
                long nationalID = Long.parseLong(nationalIdField.getText());
                Patient patient = crs.searchPatient(nationalID);
                JOptionPane.showMessageDialog(panel, "Patient found:\n" +
                        "Name: " + patient.getFirstName() + " " + patient.getLastName() +
                        "\nGender: " + patient.getGender() +
                        "\nDate of Birth: " + patient.getDateOfBirth(), "Patient Details", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Invalid National ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IDException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "PatientManagement"));

        return panel;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    private JPanel createRendezvousManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Rendezvous Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10)); 
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        JButton listButton = new JButton("List Rendezvous");
        JButton searchButton = new JButton("Search Rendezvous");
        JButton backButton = new JButton("Back");

        styleButton(listButton);
        styleButton(searchButton);
        styleButton(backButton);

        buttonPanel.add(listButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(backButton);

        listButton.addActionListener(e -> cardLayout.show(mainPanel, "ListRendezvous"));
        searchButton.addActionListener(e -> cardLayout.show(mainPanel, "SearchRendezvous"));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Management"));

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    
    private JPanel createListRendezvousPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("List Rendezvous", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JTable rendezvousTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(rendezvousTable);

        JButton refreshButton = new JButton("Refresh");
        JButton backButton = new JButton("Back");

        styleButton(refreshButton);
        styleButton(backButton);

        refreshButton.addActionListener(e -> updateRendezvousTable(rendezvousTable));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "RendezvousManagement"));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(refreshButton);
        buttonPanel.add(backButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        
        SwingUtilities.invokeLater(() -> {
            String filePath = "crs_dosya.txt"; 
            CRS loadedCRS = CRS.loadTableToDisk(filePath);
            if (loadedCRS != null) {
                crs.getHospitals().clear();
                crs.getHospitals().putAll(loadedCRS.getHospitals());
                crs.getPatients().clear();
                crs.getPatients().putAll(loadedCRS.getPatients());
                crs.getRendezvous().clear();
                crs.getRendezvous().addAll(loadedCRS.getRendezvous());
                updateRendezvousTable(rendezvousTable); 
            } else {
                JOptionPane.showMessageDialog(panel, "Failed to load rendezvous data from file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }
    

    private void updateRendezvousTable(JTable rendezvousTable) {
        String[] columnNames = {"Patient Name", "Hospital Name", "Section Name", "Doctor Name", "Date"};
        String[][] data;

        try {
            data = crs.getRendezvous().stream()
                    .map(r -> {
                        Doctor doctor = r.getDoctor();
                        Section section = null;
                        Hospital hospital = null;

                       
                        for (Hospital h : crs.getHospitals().values()) {
                            for (Section s : h.getSections()) {
                                if (s.getDoctors().contains(doctor)) {
                                    section = s;
                                    hospital = h;
                                    break;
                                }
                            }
                            if (hospital != null) break; 
                        }

                        
                        return new String[]{
                                r.getPatient().getFirstName() + " " + r.getPatient().getLastName(),
                                hospital != null ? hospital.getName() : "Unknown", 
                                section != null ? section.getName() : "Unknown", 
                                doctor.getFirstName() + " " + doctor.getLastName(), 
                                r.getDateTime().toString() 
                        };
                    })
                    .toArray(String[][]::new);
        } catch (Exception e) {
            data = new String[0][5]; 
        }

        rendezvousTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }



    
    private JPanel createSearchRendezvousPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(100, 149, 237));

        JLabel title = new JLabel("Search Rendezvous", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel dateLabel = new JLabel("Date (yyyy-mm-dd):");
        JTextField dateField = new JTextField(15);

        JLabel idLabel = new JLabel("Patient National ID:");
        JTextField idField = new JTextField(15);

        JButton searchButton = new JButton("Search");
        JButton backButton = new JButton("Back");

        styleButton(searchButton);
        styleButton(backButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(dateLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(idLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(searchButton, gbc);

        gbc.gridy = 3;
        formPanel.add(backButton, gbc);

        searchButton.addActionListener(e -> {
            try {
                String dateStr = dateField.getText();
                long nationalID = Long.parseLong(idField.getText());
                Date date = Date.valueOf(dateStr);

                LinkedList<Rendezvous> results = crs.getRendezvous().stream()
                        .filter(r -> r.getDateTime().equals(date) && r.getPatient().getNationalID() == nationalID)
                        .collect(Collectors.toCollection(LinkedList::new));

                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "No rendezvous found for the given criteria.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder resultStr = new StringBuilder("Found Rendezvous:\n");
                    for (Rendezvous r : results) {
                        resultStr.append(r.toString()).append("\n");
                    }
                    JOptionPane.showMessageDialog(panel, resultStr.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Invalid input or error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "RendezvousManagement"));

        panel.add(title, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    
    
    
    

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 139, 139));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HospitalManagementGUI::new);
    }

    public CRS getCrs() {
        return crs;
    }

    public void setCrs(CRS crs) {
        this.crs = crs;
    }
}
