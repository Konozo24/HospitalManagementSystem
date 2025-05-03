
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditPatientForm extends JDialog {
    private JComboBox<String> patientIdComboBox;
    private JTextField nameField, addressField, phoneField, emailField, emergencyContactField, dateOfBirthField, admitDateField, dischargeDateField;
    private JTextArea insuranceInfoArea;
    private JRadioButton MaleButton, FemaleButton;
    private JComboBox<String> bloodGroupComboBox;
    private ButtonGroup group;
    private HospitalService hospitalService;

    public EditPatientForm(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
        setTitle("Edit Patient");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new GridBagLayout());

        patientIdComboBox = new JComboBox<>();
        List<Patient> patients = hospitalService.viewAllPatients();
        for (Patient patient : patients) {
            patientIdComboBox.addItem(patient.getPatientId());
        }

        nameField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        emergencyContactField = new JTextField();
        dateOfBirthField = new JTextField();
        admitDateField = new JTextField();
        dischargeDateField = new JTextField();
        insuranceInfoArea = new JTextArea();
        MaleButton = new JRadioButton("Male");
        FemaleButton = new JRadioButton("Female");
        group = new ButtonGroup();
        group.add(MaleButton);
        group.add(FemaleButton);
        String[] bloodGroupTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        bloodGroupComboBox = new JComboBox<>(bloodGroupTypes);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Patient ID: "), gbc);
        gbc.gridx = 1;
        add(patientIdComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Name: "), gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Address: "), gbc);
        gbc.gridx = 1;
        add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Phone Number: "), gbc);
        gbc.gridx = 1;
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Email: "), gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Emergency Contact: "), gbc);
        gbc.gridx = 1;
        add(emergencyContactField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Date of Birth (YYYY-MM-DD): "), gbc);
        gbc.gridx = 1;
        add(dateOfBirthField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Gender: "), gbc);
        gbc.gridx = 1;
        add(new JPanel() {{
            add(MaleButton);
            add(FemaleButton);
        }}, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Blood Group: "), gbc);
        gbc.gridx = 1;
        add(bloodGroupComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(new JLabel("Admit Date (YYYY-MM-DD): "), gbc);
        gbc.gridx = 1;
        add(admitDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        add(new JLabel("Discharge Date (YYYY-MM-DD): "), gbc);
        gbc.gridx = 1;
        add(dischargeDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        add(new JLabel("Insurance Info: "), gbc);
        gbc.gridx = 1;
        add(new JScrollPane(insuranceInfoArea), gbc);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePatient();
            }
        });
        gbc.gridy = 12;
        add(updateButton, gbc);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridy = 13;
        add(cancelButton, gbc);

        patientIdComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPatientDetails();
            }
        });

        setVisible(true);
    }

    private void loadPatientDetails() {
        String patientId = (String) patientIdComboBox.getSelectedItem();
        Patient patient = hospitalService.findPatientById(patientId);
        if (patient != null) {
            nameField.setText(patient.getName());
            addressField.setText(patient.getAddress());
            phoneField.setText(patient.getPhoneNumber());
            emailField.setText(patient.getEmail());
            emergencyContactField.setText(patient.getEmergencyContact());
            dateOfBirthField.setText(patient.getDateOfBirth());
            if (patient.getGender() == 'M') {
                MaleButton.setSelected(true);
            } else {
                FemaleButton.setSelected(true);
            }
            bloodGroupComboBox.setSelectedItem(patient.getBloodGroup());
            admitDateField.setText(patient.getAdmitDate());
            dischargeDateField.setText(patient.getDischargeDate());
            insuranceInfoArea.setText(patient.getInsuranceInfo());
        }
    }

    private void updatePatient() {
        try {
            String patientId = (String) patientIdComboBox.getSelectedItem();
            String name = nameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String emergencyContact = emergencyContactField.getText();
            String dateOfBirth = dateOfBirthField.getText();
            char gender = getSelectedGender();
            String bloodGroup = (String) bloodGroupComboBox.getSelectedItem();
            String admitDate = admitDateField.getText();
            String dischargeDate = dischargeDateField.getText();
            String insuranceInfo = insuranceInfoArea.getText();

            if (isValidForm()) {
                Patient patient = hospitalService.findPatientById(patientId);
                if (patient != null) {
                    patient.setName(name);
                    patient.setAddress(address);
                    patient.setPhoneNumber(phone);
                    patient.setEmail(email);
                    patient.setEmergencyContact(emergencyContact);
                    patient.setDateOfBirth(dateOfBirth);
                    patient.setGender(gender);
                    patient.setBloodGroup(bloodGroup);
                    patient.setAdmitDate(admitDate);
                    patient.setDischargeDate(dischargeDate);
                    patient.setInsuranceInfo(insuranceInfo);

                    JOptionPane.showMessageDialog(this, "Patient updated successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Patient not found!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid information.");
        }
    }

    private char getSelectedGender() {
        if (MaleButton.isSelected()) {
            return 'M';
        } else if (FemaleButton.isSelected()) {
            return 'F';
        } else return 'N';
    }

    private boolean isValidForm() {
        if (
            patientIdComboBox.getSelectedItem() == null ||
            nameField.getText().trim().isEmpty() ||
            addressField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            emergencyContactField.getText().trim().isEmpty() ||
            dateOfBirthField.getText().trim().isEmpty() ||
            (!MaleButton.isSelected() && !FemaleButton.isSelected()) ||
            bloodGroupComboBox.getSelectedIndex() == -1 ||
            admitDateField.getText().trim().isEmpty() ||
            insuranceInfoArea.getText().trim().isEmpty()
        ) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required information.", "Missing Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!DateValidator.isValidDate(dateOfBirthField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Date of Birth format. Please use YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!DateValidator.isValidDate(admitDateField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Admit Date format. Please use YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!dischargeDateField.getText().trim().isEmpty() && !DateValidator.isValidDate(dischargeDateField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Discharge Date format. Please use YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Additional check to ensure discharge date is not before admit date
        if (!dischargeDateField.getText().trim().isEmpty()) {
            try {
                Date admitDate = new SimpleDateFormat("yyyy-MM-dd").parse(admitDateField.getText());
                Date dischargeDate = new SimpleDateFormat("yyyy-MM-dd").parse(dischargeDateField.getText());
                if (dischargeDate.before(admitDate)) {
                    JOptionPane.showMessageDialog(this, "Discharge Date cannot be before Admit Date.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        return true;
    }
}
