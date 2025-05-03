

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ScheduleAppointment extends JFrame {
    private JComboBox<Patient> patientComboBox;
    private JComboBox<Doctor> doctorComboBox;
    private JTextField dateField, timeField, consultationRoomField;
    private JTextArea purposeArea;  
    private HospitalService hospitalService;
    private JButton submitButton, clearButton, backButton;


    // Constructor to initialize the schedule appointment window
    public ScheduleAppointment(HospitalService hospitalService){
        this.hospitalService = hospitalService;
        this.setTitle("Schedule Appointment");
        this.setSize(1200, 750);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only this window
    
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("Schedule Appointment Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));
        
        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setLocationRelativeTo(null); // center the window
        this.setVisible(true);

    }

    // Configure the form fields for appointment details
    private JPanel createFormPanel(){
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        dateField = new JTextField();
        dateField.setPreferredSize(new Dimension(200, 30));
        dateField.setFont(new Font("Arial", Font.PLAIN, 15));

        timeField = new JTextField();
        timeField.setPreferredSize(new Dimension(200, 30));
        timeField.setFont(new Font("Arial", Font.PLAIN, 15));

        consultationRoomField = new JTextField();
        consultationRoomField.setPreferredSize(new Dimension(200, 30));
        consultationRoomField.setFont(new Font("Arial", Font.PLAIN, 15));

        purposeArea = new JTextArea();
        purposeArea.setFont(new Font("Arial", Font.PLAIN, 15));
        purposeArea.setLineWrap(true);
        purposeArea.setWrapStyleWord(true);
        JScrollPane purposeScrollPane = new JScrollPane(purposeArea);
        purposeScrollPane.setPreferredSize(new Dimension(200,60));

        patientComboBox = new JComboBox<>();
        patientComboBox.setPreferredSize(new Dimension(200, 30));
        patientComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        
        
        for (Patient p : hospitalService.viewAllPatients()){
            patientComboBox.addItem(p);
        }

        doctorComboBox = new JComboBox<>();
        doctorComboBox.setPreferredSize(new Dimension(200, 30));
        doctorComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        
        for (Doctor d : hospitalService.viewAllDoctors()){
            doctorComboBox.addItem(d);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // First column of data fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Patient: "), gbc);

        gbc.gridx = 1;
        formPanel.add(patientComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Date (YYYY-MM-DD): "), gbc);

        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Consultation Room: "), gbc);
        
        gbc.gridx = 1;
        formPanel.add(consultationRoomField, gbc);

        // Right column of data fields

        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Doctor: "), gbc);

        gbc.gridx = 3;
        formPanel.add(doctorComboBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Time (HH:MM): "), gbc);

        gbc.gridx = 3;
        formPanel.add(timeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Purpose: "), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        formPanel.add(purposeScrollPane, gbc);

        return formPanel;

    }
    
    private JPanel createButtonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 35));
        submitButton.setFont(new Font("Arial", Font.BOLD, 15));

        clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(100, 35));
        clearButton.setFont(new Font("Arial", Font.BOLD, 15));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 35));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));

        // Submit button logic
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                if (isValidForm()){
                    Appointment newAppointment = new Appointment( 
                        (Patient) patientComboBox.getSelectedItem(), 
                        (Doctor) doctorComboBox.getSelectedItem(), 
                        dateField.getText(), 
                        timeField.getText(), 
                        consultationRoomField.getText(), 
                        purposeArea.getText());

                    hospitalService.addAppointment(newAppointment);
                    JOptionPane.showMessageDialog(null, 
                    "Appointment added successfully!\n" + newAppointment);
                    clearForm();
                }
            }
        });
        
        // Clear form data
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                clearForm();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        return buttonPanel;
    }

    // Validate the form fields for correct outputs formatting
    private boolean isValidForm() {
        String dateText = dateField.getText().trim();
        String timeText = timeField.getText().trim();

        if (
            dateText.isEmpty() ||
            timeText.isEmpty() ||
            consultationRoomField.getText().trim().isEmpty() ||
            purposeArea.getText().trim().isEmpty() ||
            patientComboBox.getSelectedIndex() == -1 ||
            doctorComboBox.getSelectedIndex() == -1 
        ) {
            JOptionPane.showMessageDialog(this, "Please fill in all the information.", "Missing Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Regex format to check data validity (YYYY-MM-DD)
        if (!dateText.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Date must be in the format YYYY-MM-DD.", "Invalid Date Format", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!timeText.matches("^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$")) {
            JOptionPane.showMessageDialog(this, "Time must be in the format HH:MM.", "Invalid Time Format", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Check if the date is a valid calendar date
        try {
            LocalDate.parse(dateText); // throws exception if date is invalid
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date. Please enter a correct date in the format YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

    // Reset to default form
    private void clearForm(){
        patientComboBox.setSelectedIndex(0);
        doctorComboBox.setSelectedIndex(0);
        dateField.setText("");
        timeField.setText("");
        consultationRoomField.setText("");
        purposeArea.setText("");
    }

}
