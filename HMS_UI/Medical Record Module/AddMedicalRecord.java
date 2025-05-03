
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddMedicalRecord extends JFrame {
    private JTextField patientIdField, doctorIdField, dateField, diagnosisField, treatmentField, prescriptionField, notesField, followUpDateField;
    private JButton submitButton, clearButton, backButton;
    private HospitalService hospitalService;

    public AddMedicalRecord(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
        setTitle("Add New Medical Record");
        setSize(1200, 750);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Medical Record Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        patientIdField = new JTextField(30);
        doctorIdField = new JTextField(30);
        dateField = new JTextField(30);
        diagnosisField = new JTextField(30);
        treatmentField = new JTextField(30);
        prescriptionField = new JTextField(30);
        notesField = new JTextField(30);
        followUpDateField = new JTextField(30);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Patient ID: "), gbc);
        gbc.gridx = 1;
        formPanel.add(patientIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Doctor ID: "), gbc);
        gbc.gridx = 1;
        formPanel.add(doctorIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Date (YYYY-MM-DD): "), gbc);
        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Diagnosis: "), gbc);
        gbc.gridx = 1;
        formPanel.add(diagnosisField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Treatment: "), gbc);
        gbc.gridx = 1;
        formPanel.add(treatmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Prescription: "), gbc);
        gbc.gridx = 1;
        formPanel.add(prescriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Notes: "), gbc);
        gbc.gridx = 1;
        formPanel.add(notesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(new JLabel("Follow-Up Date (YYYY-MM-DD): "), gbc);
        gbc.gridx = 1;
        formPanel.add(followUpDateField, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidForm()) {
                    addMedicalRecord();
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        return buttonPanel;
    }

    private void addMedicalRecord() {
        try {
            String patientId = patientIdField.getText();
            String doctorId = doctorIdField.getText();
            String date = dateField.getText();
            String diagnosis = diagnosisField.getText();
            String treatment = treatmentField.getText();
            String prescription = prescriptionField.getText();
            String notes = notesField.getText();
            String followUpDate = followUpDateField.getText();

            Patient patient = hospitalService.findPatientById(patientId);
            Doctor doctor = hospitalService.findDoctorById(doctorId);

            if (patient == null || doctor == null) {
                JOptionPane.showMessageDialog(this, "Patient or Doctor not found!");
                return;
            }

            MedicalRecord newRecord = new MedicalRecord(
                patient,
                doctor,
                date,
                diagnosis,
                treatment,
                prescription,
                notes,
                followUpDate
            );

            hospitalService.addMedicalRecord(newRecord);
            JOptionPane.showMessageDialog(this, "Medical Record added successfully!");
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid information.");
        }
    }

    private boolean isValidForm() {
        if (
            patientIdField.getText().trim().isEmpty() ||
            doctorIdField.getText().trim().isEmpty() ||
            dateField.getText().trim().isEmpty() ||
            diagnosisField.getText().trim().isEmpty() ||
            treatmentField.getText().trim().isEmpty() ||
            prescriptionField.getText().trim().isEmpty() ||
            notesField.getText().trim().isEmpty() ||
            followUpDateField.getText().trim().isEmpty()
        ) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required information.", "Missing Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateField.getText());
            dateFormat.parse(followUpDateField.getText());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void clearForm() {
        patientIdField.setText("");
        doctorIdField.setText("");
        dateField.setText("");
        diagnosisField.setText("");
        treatmentField.setText("");
        prescriptionField.setText("");
        notesField.setText("");
        followUpDateField.setText("");
    }
}
