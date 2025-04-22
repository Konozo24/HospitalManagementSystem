package Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HospitalManagementUI extends JFrame {
    private Hospital hospital;
    private HospitalService hospitalService;
    
    // Main panels
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel menuPanel;
    private JPanel infoPanel;
    
    // Components
    private JLabel hospitalNameLabel;
    private JLabel hospitalInfoLabel;
    
    public HospitalManagementUI(Hospital hospital) {
        if (hospital == null) {
            throw new IllegalArgumentException("Hospital cannot be null");
        }
        
        this.hospital = hospital;
        this.hospitalService = new HospitalService(hospital);
        
        // Configure frame
        setTitle("Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize UI components
        initComponents();
        
        // Display the frame
        setVisible(true);
    }
    
    private void initComponents() {
        // Create main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Add header panel
        createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Add menu panel
        createMenuPanel();
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        
        // Add info panel
        createInfoPanel();
        mainPanel.add(infoPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        add(mainPanel);
    }
    
    private void createHeaderPanel() {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
        // Hospital name
        hospitalNameLabel = new JLabel(hospital.getname());
        hospitalNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        hospitalNameLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Add to header panel
        headerPanel.add(hospitalNameLabel, BorderLayout.CENTER);
    }
    
    private void createMenuPanel() {
        menuPanel = new JPanel(new GridLayout(3, 2, 20, 20));
        
        // Create menu buttons
        JButton addPatientButton = createMenuButton("Add Patient", "Click to a new patient to the system");
        addPatientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new AddPatientForm(hospitalService);
            }
        });
            
       
        JButton scheduleButton = createMenuButton("Schedule Appointment", "Click to schedule an appointment");
        scheduleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new ScheduleAppointment(hospitalService);
            }
        });

        JButton addMedicalRecordButton = createMenuButton("Add Medical Record", "Click to add a medical record");
        addMedicalRecordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new AddMedicalRecord(hospitalService);
            }
        });

        JButton cancelAppointmentButton = createMenuButton("Cancel Appointment", "Click to cancel an appointment");
        cancelAppointmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                new CancelAppointment(hospitalService);
            }
        });

        JButton appointmentsButton = createMenuButton("Appointments", "Manage appointments");
        JButton recordsButton = createMenuButton("Medical Records", "Manage medical records");
        
        // Add buttons to menu panel
        menuPanel.add(addPatientButton);
        menuPanel.add(scheduleButton);
        menuPanel.add(addMedicalRecordButton);
        menuPanel.add(cancelAppointmentButton);
        menuPanel.add(appointmentsButton);
        menuPanel.add(recordsButton);
    }
    
    private JButton createMenuButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setToolTipText(tooltip);
        button.setFocusPainted(false);
        
        // Style the button
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(150, 100));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(30, 100, 150)); // Darker blue
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180)); // Steel blue
            }
        });
        
        // Add action listener
        
        
        return button;
    }
    
    private void createInfoPanel() {
        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Hospital info
        hospitalInfoLabel = new JLabel(createHospitalInfoHTML());
        hospitalInfoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        // Add to info panel
        infoPanel.add(hospitalInfoLabel, BorderLayout.CENTER);
    }
    
    private String createHospitalInfoHTML() {
        return "<html>" +
               "Address: " + hospital.getaddress() + "<br>" +
               "Contact: " + hospital.getcontactNumber() + "<br>" +
               "Email: " + hospital.getemail() + "<br>" +
               "Departments: " + hospital.getAllDepartments().size() + " | " +
               "Doctors: " + hospital.getAllDoctors().size() + " | " +
               "Nurses: " + hospital.getAllNurses().size() + " | " +
               "Patients: " + hospital.getAllPatients().size() +
               "</html>";
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Create a sample hospital for testing
        Hospital sampleHospital = new Hospital(
            "City General Hospital",
            "123 Healthcare Avenue, Medical District",
            "(555) 123-4567",
            "info@citygeneralhospital.com"
        );
        
        // Run the UI
        SwingUtilities.invokeLater(() -> {
            new HospitalManagementUI(sampleHospital);
        });
    }
}
