

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
    
    
    // Components
    private JLabel hospitalNameLabel;
    
    
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
        JButton patientButton = createMenuButton("Patient", "Click to enter Patient Module");
        patientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                PatientModule module = new PatientModule(hospitalService);
        
                // When the patient module is closed, make the main menu visible again
                module.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent we) {
                        setVisible(true);  // Make the main menu visible again when the module is closed
                    }
                });
                module.setVisible(true);  // Show the Patient Module
                }
        });
            
       
        JButton appointmentButton = createMenuButton("Appointment", "Click to enter Appointment Module");
        appointmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                AppointmentModule module = new AppointmentModule(hospitalService);
        
                // When the appointment module is closed, make the main menu visible again
                module.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent we) {
                        setVisible(true);  // Make the main menu visible again when the module is closed
                    }
                });
                module.setVisible(true);  // Show the Appointment Module
            }
        });

        JButton medicalRecordButton = createMenuButton("Medical Record", "Click to enter Medical Record Module");
        medicalRecordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                MedicalRecordModule module = new MedicalRecordModule(hospitalService);
        
                // When the medical record module is closed, make the main menu visible again
                module.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent we) {
                        setVisible(true);  // Make the main menu visible again when the module is closed
                    }
                });
                module.setVisible(true);  // Show the Medical Record Module
            }
        });

        JButton doctorButton = createMenuButton("Doctor", "Click to enter Doctor Module");
        doctorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                DoctorModule module = new DoctorModule(hospitalService);
        
                // When the doctor module is closed, make the main menu visible again
                module.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent we) {
                        setVisible(true);  // Make the main menu visible again when the module is closed
                    }
                });
                module.setVisible(true);  // Show the Doctor Module
            }
        });

        JButton nurseButton = createMenuButton("Nurse", "Click to enter Nurse Module");
        nurseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                NurseModule module = new NurseModule(hospitalService);
        
                // When the nurse module is closed, make the main menu visible again
                module.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent we) {
                        setVisible(true);  // Make the main menu visible again when the module is closed
                    }
                });
                module.setVisible(true);  // Show the Nurse Module
            }
        });

        JButton departmentButton = createMenuButton("Department", "Click to enter Department Module");
        departmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                setVisible(false);
                DepartmentModule module = new DepartmentModule(hospitalService);
        
                // When the department module is closed, make the main menu visible again
                module.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent we) {
                        setVisible(true);  // Make the main menu visible again when the module is closed
                    }
                });
                module.setVisible(true);  // Show the Department Module
            }
        });
        
        // Add buttons to menu panel
        menuPanel.add(patientButton);
        menuPanel.add(appointmentButton);
        menuPanel.add(medicalRecordButton);
        menuPanel.add(doctorButton);
        menuPanel.add(nurseButton);
        menuPanel.add(departmentButton);
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
    
   
    

    
    // Main method for running the UI
    public static void main(String[] args) {
        // Create a sample hospital for testing
        Hospital sampleHospital = new Hospital(
            "Jull General Hospital",
            "123 Healthcare Avenue, Medical District",
            "0192212211",
            "info@jullgeneralhospital.com"
        );
        sampleHospital.loadSampleDate();
        // Run the UI
        SwingUtilities.invokeLater(() -> {
            new HospitalManagementUI(sampleHospital);
        });
    }
}
