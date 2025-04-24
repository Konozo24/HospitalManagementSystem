

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;



public class EditAppointmentDialog extends JDialog {
    private JComboBox<Patient> patientComboBox;
    private JComboBox<Doctor> doctorComboBox;
    private JComboBox<String> statusComboBox;
    private JTextField dateField, timeField, consultationRoomField;
    private JTextArea purposeArea;  
    private HospitalService hospitalService;
    private JButton saveButton, backButton;
    private Appointment appointment;
    private Runnable onUpdate;

    public EditAppointmentDialog(JFrame parent, Appointment appointment, HospitalService hospitalService, Runnable onUpdate){
        super(parent, "Edit Appointment", true);
        this.hospitalService = hospitalService;
        this.appointment = appointment;
        this.onUpdate = onUpdate;
        this.setLayout(new BorderLayout());
        this.setSize(450, 500);
        this.setLocationRelativeTo(parent);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

    
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setVisible(true);
    }

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

        statusComboBox = new JComboBox<>(new String[]{"Scheduled", "Completed", "Cancelled"});
        statusComboBox.setSelectedItem(appointment.getStatus());
        statusComboBox.setPreferredSize(new Dimension(200, 30));
        statusComboBox.setFont(new Font("Arial", Font.PLAIN, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Patient
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Patient:"), gbc);

        gbc.gridx = 1;
        formPanel.add(patientComboBox, gbc);

        // Doctor
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Doctor:"), gbc);

        gbc.gridx = 1;
        formPanel.add(doctorComboBox, gbc);

        // Date 
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);

        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        // Time 
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Time (HH:MM):"), gbc);

        gbc.gridx = 1;
        formPanel.add(timeField, gbc);

        // Consultation Room
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Consultation Room:"), gbc);

        gbc.gridx = 1;
        formPanel.add(consultationRoomField, gbc);

        // Purpose
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Purpose:"), gbc);

        gbc.gridx = 1;
        formPanel.add(purposeScrollPane, gbc);

        // Status
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Status:"), gbc);

        gbc.gridx = 1;
        formPanel.add(statusComboBox, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 35));
        saveButton.setFont(new Font("Arial", Font.BOLD, 15));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 35));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                if(!isValidForm()){
                    return;
                }

                try {
                    appointment.setPatient((Patient) patientComboBox.getSelectedItem());
                    appointment.setDoctor((Doctor) doctorComboBox.getSelectedItem());
                    appointment.setDate(dateField.getText().trim());
                    appointment.setTime(timeField.getText().trim());
                    appointment.setConsultationRoom(consultationRoomField.getText().trim());
                    appointment.setPurpose(purposeArea.getText().trim());
                    appointment.setStatus((String) statusComboBox.getSelectedItem());

                    JOptionPane.showMessageDialog(EditAppointmentDialog.this, "Appointment updated successfully!");
                    onUpdate.run(); // Refresh the UI
                    dispose();
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(EditAppointmentDialog.this, "Error: " + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
    
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        return buttonPanel;
    }

    private boolean isValidForm() {
        String dateText = dateField.getText().trim();

        if (
            dateText.isEmpty() ||
            timeField.getText().trim().isEmpty() ||
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
        
        // Check if the date is a valid calendar date
        try {
            LocalDate.parse(dateText); // throws exception if date is invalid
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date. Please enter a correct date in the format YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

}
