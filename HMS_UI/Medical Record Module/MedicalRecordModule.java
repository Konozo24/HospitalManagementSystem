
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MedicalRecordModule extends JFrame {
    private HospitalService hospitalService;
    private JPanel mainPanel, headerPanel, menuPanel;
    private JLabel medicalRecordTitleLabel;

    public MedicalRecordModule(HospitalService hospitalService) {
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
        medicalRecordTitleLabel = new JLabel("Medical Record Module");
        medicalRecordTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        medicalRecordTitleLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add to header panel
        headerPanel.add(medicalRecordTitleLabel, BorderLayout.CENTER);
    }

    private void createMenuPanel() {
        menuPanel = new JPanel(new GridLayout(4, 1, 20, 20));

        // Create menu buttons
        JButton addMedicalRecordButton = createMenuButton("Add Medical Record", "Click to add a new medical record");
        addMedicalRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMedicalRecord(hospitalService);
            }
        });

        JButton editMedicalRecordButton = createMenuButton("Edit Medical Record", "Click to edit a medical record");
        editMedicalRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditMedicalRecord(hospitalService);
            }
        });

        JButton viewMedicalRecordButton = createMenuButton("View Medical Records", "Click to view medical records");
        viewMedicalRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewMedicalRecord(hospitalService);
            }
        });

        JButton deleteMedicalRecordButton = createMenuButton("Delete Medical Record", "Click to delete a medical record");
        deleteMedicalRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveMedicalRecord(hospitalService);
            }
        });

        JButton backButton = createMenuButton("Back", "Click to go back to the main menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Add buttons to menu panel
        menuPanel.add(addMedicalRecordButton);
        menuPanel.add(editMedicalRecordButton);
        menuPanel.add(viewMedicalRecordButton);
        menuPanel.add(deleteMedicalRecordButton);
        menuPanel.add(backButton);
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
