import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EditMedicalRecord extends JDialog {
    private JComboBox<String> recordIdComboBox;
    private JTextField patientIdField, doctorIdField, dateField, diagnosisField, treatmentField, prescriptionField, notesField, followUpDateField;
    private JButton updateButton, cancelButton;
    private HospitalService hospitalService;

    public EditMedicalRecord(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
        setTitle("Edit Medical Record");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new GridBagLayout());

        recordIdComboBox = new JComboBox<>();
        List<MedicalRecord> records = hospitalService.viewAllMedicalRecords();
        for (MedicalRecord record : records) {
            recordIdComboBox.addItem(record.getRecordId());
        }

        patientIdField = new JTextField();
        doctorIdField = new JTextField();
        dateField = new JTextField();
        diagnosisField = new JTextField();
        treatmentField = new JTextField();
        prescriptionField = new JTextField();
        notesField = new JTextField();
        followUpDateField = new JTextField();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Record ID: "), gbc);
        gbc.gridx = 1;
        add(recordIdComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Patient ID: "), gbc);
        gbc.gridx = 1;
        add(patientIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Doctor ID: "), gbc);
        gbc.gridx = 1;
        add(doctorIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Date: "), gbc);
        gbc.gridx = 1;
        add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Diagnosis: "), gbc);
        gbc.gridx = 1;
        add(diagnosisField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Treatment: "), gbc);
        gbc.gridx = 1;
        add(treatmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Prescription: "), gbc);
        gbc.gridx = 1;
        add(prescriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Notes: "), gbc);
        gbc.gridx = 1;
        add(notesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Follow-Up Date: "), gbc);
        gbc.gridx = 1;
        add(followUpDateField, gbc);

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMedicalRecord();
            }
        });
        gbc.gridy = 9;
        add(updateButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridy = 10;
        add(cancelButton, gbc);

        recordIdComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMedicalRecordDetails();
            }
        });

        setVisible(true);
    }

    private void loadMedicalRecordDetails() {
        String recordId = (String) recordIdComboBox.getSelectedItem();
        MedicalRecord record = hospitalService.findMedicalRecordById(recordId);
        if (record != null) {
            patientIdField.setText(record.getPatient().getPatientId());
            doctorIdField.setText(record.getDoctor().getEmployeeId());
            dateField.setText(record.getDate());
            diagnosisField.setText(record.getDiagnosis());
            treatmentField.setText(record.getTreatment());
            prescriptionField.setText(record.getPrescription());
            notesField.setText(record.getNotes());
            followUpDateField.setText(record.getFollowUpDate());
        }
    }

    private void updateMedicalRecord() {
        try {
            String recordId = (String) recordIdComboBox.getSelectedItem();
            String patientId = patientIdField.getText();
            String doctorId = doctorIdField.getText();
            String date = dateField.getText();
            String diagnosis = diagnosisField.getText();
            String treatment = treatmentField.getText();
            String prescription = prescriptionField.getText();
            String notes = notesField.getText();
            String followUpDate = followUpDateField.getText();

            if (isValidForm()) {
                MedicalRecord record = hospitalService.findMedicalRecordById(recordId);
                if (record != null) {
                    Patient patient = hospitalService.findPatientById(patientId);
                    Doctor doctor = hospitalService.findDoctorById(doctorId);

                    if (patient == null || doctor == null) {
                        JOptionPane.showMessageDialog(this, "Patient or Doctor not found!");
                        return;
                    }

                    record.setPatient(patient);
                    record.setDoctor(doctor);
                    record.setDate(date);
                    record.setDiagnosis(diagnosis);
                    record.setTreatment(treatment);
                    record.setPrescription(prescription);
                    record.setNotes(notes);
                    record.setFollowUpDate(followUpDate);

                    JOptionPane.showMessageDialog(this, "Medical Record updated successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Medical Record not found!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid information.");
        }
    }

    private boolean isValidForm() {
        if (
            recordIdComboBox.getSelectedItem() == null ||
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

        if (!DateValidator.isValidDate(dateField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Date format. Please use YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!DateValidator.isValidDate(followUpDateField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Follow-Up Date format. Please use YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }
}
