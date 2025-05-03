
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PatientModule extends JFrame {
    private HospitalService hospitalService;
    private JPanel mainPanel, headerPanel, menuPanel;
    private JLabel patientTitleLabel;

    public PatientModule(HospitalService hospitalService){
        this.hospitalService = hospitalService;

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

    private void initComponents(){
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
        patientTitleLabel = new JLabel("Patient Module");
        patientTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        patientTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Add to header panel
        headerPanel.add(patientTitleLabel, BorderLayout.CENTER);
    }

    private void createMenuPanel() {
        menuPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        
        // Create menu buttons
        JButton addPatientButton = createMenuButton("Add Patient", "Click to a new patient to the system");
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AddPatientForm(hospitalService);
            }
        });
            
       
        JButton removePatientButton = createMenuButton("Remove Patient", "Click to remove patient");
        removePatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new RemovePatient(hospitalService);
            }
        });
        

        JButton editPatientButton = createMenuButton("Edit Patient", "Click to edit patient");
        editPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new EditPatientForm(hospitalService);
            }
        });
        

        JButton viewPatientButton = createMenuButton("View Patient", "Click to view patient");
        viewPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new ViewPatientForm(hospitalService);
            }
        });

        JButton backButton = createMenuButton("Back", "Click to back to menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        
        // Create rows as sub-panels
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        row1.add(addPatientButton);
        row1.add(removePatientButton);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        row2.add(editPatientButton);
        row2.add(viewPatientButton);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        row3.add(backButton); // Centered

        // Add buttons to menu panel
        menuPanel.add(row1);
        menuPanel.add(row2);
        menuPanel.add(row3);
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
        button.setPreferredSize(new Dimension(300, 100));
        
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
        
        
        
        
        return button;
    }

    
}
