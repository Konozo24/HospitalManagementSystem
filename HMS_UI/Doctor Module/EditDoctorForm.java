
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditDoctorForm extends JFrame {

    private JTextField doctorIDField, idField, nameField, phoneField, emailField, specializationField, qualificationField, joiningDateField, yearsOfExperienceField;
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

        // Input panel for entering Doctor ID to search
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel idLabel = new JLabel("Enter Doctor ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        doctorIDField = new JTextField(20);
        doctorIDField.setFont(new Font("Arial", Font.PLAIN, 16));
        JButton searchButton = new JButton("Search");

        inputPanel.add(idLabel);
        inputPanel.add(doctorIDField);
        inputPanel.add(searchButton);
        add(inputPanel, BorderLayout.NORTH);

        // Form fields for editing doctor details
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        idField = new JTextField();
        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        specializationField = new JTextField();
        qualificationField = new JTextField();
        joiningDateField = new JTextField();
        yearsOfExperienceField = new JTextField();

        // Gender radio buttons
        MaleButton = new JRadioButton("Male");
        FemaleButton = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(MaleButton);
        genderGroup.add(FemaleButton);

        // Department combo box
        String[] departments = {"General Medicine", "Cardiology", "Neurology", "Orthopedics", "Pediatrics", "Surgery"};
        departmentComboBox = new JComboBox<>(departments);

        formPanel.add(new JLabel("ID: "));
        formPanel.add(idField);
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
        String doctorId = doctorIDField.getText().trim();
        if (doctorId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Doctor ID to search.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }


        Doctor doctor = hospitalService.findDoctorById(doctorId);
        if (doctor != null) {
            // Populate fields with doctor data


            idField.setText(doctor.getId());
            nameField.setText(doctor.getName());
            phoneField.setText(doctor.getPhoneNumber());
            emailField.setText(doctor.getEmail());
            specializationField.setText(doctor.getSpecialization());
            qualificationField.setText(doctor.getQualification());
            joiningDateField.setText(doctor.getJoinDate());
            yearsOfExperienceField.setText(String.valueOf(doctor.getYearsOfExperience()));

            // Set gender radio button
            if (doctor.getGender() == 'M' || doctor.getGender() == 'm') {
                MaleButton.setSelected(true);
            } else {
                FemaleButton.setSelected(true);
            }

            // Set department combo box
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
        String doctorId = doctorIDField.getText().trim();
        Doctor doctor = hospitalService.findDoctorById(doctorId);
        if (doctorId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Doctor ID cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!DateValidator.isValidDate(joiningDateField.getText())) {
            JOptionPane.showMessageDialog(this, "Invalid Date of Birth format. Please use YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();
        String specialization = specializationField.getText().trim();
        String qualification = qualificationField.getText().trim();
        String joiningDate = joiningDateField.getText().trim();
        int yearsOfExperience = Integer.parseInt(yearsOfExperienceField.getText().trim());
        char gender = MaleButton.isSelected() ? 'M' : 'F';
        String department = (String) departmentComboBox.getSelectedItem();

        doctor.setId(id);
        doctor.setName(name);
        doctor.setPhoneNumber(phone);
        doctor.setEmail(email);
        doctor.setSpecialization(specialization);
        doctor.setQualification(qualification);
        doctor.setJoinDate(joiningDate);
        doctor.setYearsOfExperience(yearsOfExperience);
        doctor.setGender(gender);
        doctor.setDepartment(department);


        

        
        JOptionPane.showMessageDialog(this, "Doctor details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        clearFields();
        
        
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        specializationField.setText("");
        qualificationField.setText("");
        joiningDateField.setText("");
        yearsOfExperienceField.setText("");
        genderGroup.clearSelection();
        departmentComboBox.setSelectedIndex(0);
    }
}
