

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppointmentModule extends JFrame {
    private HospitalService hospitalService;
    private JPanel mainPanel, headerPanel, menuPanel;
    private JLabel appointmentTitleLabel;

    public AppointmentModule(HospitalService hospitalService){
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
        appointmentTitleLabel = new JLabel("Appointment Module");
        appointmentTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        appointmentTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Add to header panel
        headerPanel.add(appointmentTitleLabel, BorderLayout.CENTER);
    }

    private void createMenuPanel() {
        menuPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        
        // Create menu buttons
        JButton scheduleAppointmentButton = createMenuButton("Schedule Appointment", "Click to schedule appointment");
        scheduleAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new ScheduleAppointment(hospitalService);
            }
        });
            
       
        JButton removeAppointmentButton = createMenuButton("Remove Appointment", "Click to remove Appointment");
        removeAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new CancelAppointment(hospitalService);
            }
        });

        JButton editAppointmentButton = createMenuButton("Edit Appointment", "Click to edit Appointment");
        editAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new EditAppointment(hospitalService);
            }
        });

        JButton viewAppointmentButton = createMenuButton("View Appointment", "Click to view Appointment");
        viewAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new ViewAppointment(hospitalService);
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
        row1.add(scheduleAppointmentButton);
        row1.add(removeAppointmentButton);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        row2.add(editAppointmentButton);
        row2.add(viewAppointmentButton);

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
