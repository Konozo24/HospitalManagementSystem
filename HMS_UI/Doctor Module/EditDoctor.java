
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditDoctorForm extends JFrame {

    private JTextField nameField, idField, addressField, phoneField, emailField, emergencyContactField, dateOfBirthField, employeeIdField, salaryField, specializationField, qualificationField, joiningDateField,yearsOfExperienceField;
    private JRadioButton MaleButton, FemaleButton;
    private JComboBox<String> departmentComboBox;
    private JButton updateButton, clearButton, backButton;
    private HospitalService hospitalService;
    private ButtonGroup genderGroup;

    public EditDoctorForm(HospitalService hospitalService) {
        this.hospitalService = hospitalService;

        setTitle("Edit Doctor Details");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Edit Doctor Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel idLabel = new JLabel("Enter Doctor ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton searchButton = new JButton("Search");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(searchButton);
        add(inputPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        idField = new JTextField();
        nameField = new JTextField();
        phoneField = new JTextField();
        addressField = new JTextField();
        emergencyContactField = new JTextField();
        dateOfBirthField = new JTextField();
        employeeIdField = new JTextField();
        salaryField = new JTextField();
        emailField = new JTextField();
        specializationField = new JTextField();
        qualificationField = new JTextField();
        joiningDateField = new JTextField();
        yearsOfExperienceField = new JTextField();

        MaleButton = new JRadioButton("M");
        FemaleButton = new JRadioButton("F");
        genderGroup = new ButtonGroup();
        genderGroup.add(MaleButton);
        genderGroup.add(FemaleButton);

        String[] departments = {"General Medicine", "Cardiology", "Neurology", "Orthopedics", "Pediatrics", "Surgery"};
        departmentComboBox = new JComboBox<>(departments);

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(MaleButton);
        genderPanel.add(FemaleButton);
        formPanel.add(genderPanel);
        formPanel.add(new JLabel("Department:"));
        formPanel.add(departmentComboBox);
        formPanel.add(new JLabel("Specialization:"));
        formPanel.add(specializationField);
        formPanel.add(new JLabel("Qualification:"));
        formPanel.add(qualificationField);
        formPanel.add(new JLabel("Joining Date (YYYY-MM-DD):"));
        formPanel.add(joiningDateField);
        formPanel.add(new JLabel("Years Of Experience : "));
        formPanel.add(yearsOfExperienceField);

        add(formPanel, BorderLayout.CENTER);

        // Button panel for Update, Clear, and Back buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        updateButton = new JButton("Update");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        searchButton.addActionListener(e -> searchDoctor());
        updateButton.addActionListener(e -> updateDoctorDetails());
        clearButton.addActionListener(e -> clearFields());
        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void searchDoctor() {
        String doctorId = idField.getText().trim();
        if (doctorId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Doctor ID to search.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Doctor doctor = hospitalService.findDoctorById(doctorId);
        if (doctor != null) {
            nameField.setText(doctor.getName());
            idField.setText(doctor.getId());
            addressField.setText(doctor.getAddress());
            phoneField.setText(doctor.getPhoneNumber());
            emailField.setText(doctor.getEmail());
            emergencyContactField.setText(doctor.getEmergencyContact());
            dateOfBirthField.setText(doctor.getDateOfBirth());
            employeeIdField.setText(doctor.getEmployeeId());
            salaryField.setText(String.valueOf(doctor.getSalary()));
            specializationField.setText(doctor.getSpecialization());
            qualificationField.setText(doctor.getQualification());
            joiningDateField.setText(doctor.getJoinDate());
            ParseInt(yearsOfExperienceField.getText().trim());
            if (doctor.getGender() == "M") {
                MaleButton.setSelected(true);
            } else if(doctor.getGender() == 'F'){
                FemaleButton.setSelected(true);
            }
            for (int i = 0; i < departmentComboBox.getItemCount(); i++) {
                if (departmentComboBox.getItemAt(i).equals(doctor.getDepartment())) {
                    departmentComboBox.setSelectedIndex(i);
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Doctor not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDoctorDetails() {
        String doctorId = idField.getText().trim();
        if (doctorId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Doctor ID cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = nameField.getText().trim();
        String phoneNumber = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String emergencyContact = emergencyContactField.getText().trim();
        String dateOfBirth = dateOfBirthField.getText().trim();
        String employeeId = employeeIdField.getText().trim();
        double salary = Double.parseDouble(salaryField.getText().trim());
        String address = addressField.getText().trim();
        String specialization = specializationField.getText().trim();
        String qualification = qualificationField.getText().trim();
        String joinDate = joiningDateField.getText().trim();
        int yearsOfExperience = Integer.parseInt(yearsOfExperienceField.getText().trim());
        char gender = getSelectedGender();
        String department = (String) departmentComboBox.getSelectedItem();

        Doctor updatedDoctor = new Doctor(name,doctorId,address,phoneNumber,email,emergencyContact,dateOfBirth,gender,employeeId,salary,joinDate,department,qualification,specialization,yearsOfExperience);
        boolean success = hospitalService.updateDoctorDetails(updatedDoctor);

        if (success) {
            JOptionPane.showMessageDialog(this, "Doctor details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update doctor details.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private char getSelectedGender() {

        throw new UnsupportedOperationException("Unimplemented method 'getSelectedGender'");
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        emergencyContactField.setText("");
        dateOfBirthField.setText("");
        employeeIdField.setText("");
        salaryField.setText("");
        addressField.setText("");
        specializationField.setText("");
        qualificationField.setText("");
        joiningDateField.setText("");
        yearsOfExperienceField.setText("");
        genderGroup.clearSelection();
        departmentComboBox.setSelectedIndex(0);
    }

    private void ParseInt(String trim) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
