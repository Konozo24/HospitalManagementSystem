

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.*;

public class AddDoctorForm extends JFrame {

    private JTextField nameField, idField, addressField, phoneField, emailField, emergencyContactField, dateOfBirthField, employeeIdField, salaryField, specializationField, qualificationField, joiningDateField,yearsOfExperienceField;
    private JRadioButton MaleButton, FemaleButton;
    private JComboBox<String> departmentComboBox;
    private JButton submitButton, clearButton, backButton;
    private HospitalService hospitalService;
    private ButtonGroup group;

    public AddDoctorForm(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
        this.setTitle("Add New Doctor");
        this.setSize(1000, 600);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Doctor Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    private void clearForm() {
        JTextField[] fields = {
            idField, nameField, phoneField, emailField,addressField,emergencyContactField,dateOfBirthField,employeeIdField,salaryField,specializationField,qualificationField,joiningDateField,yearsOfExperienceField
        };
        for (JTextField field : fields) {
            field.setText("");
        }
    
        group.clearSelection();
        departmentComboBox.setSelectedIndex(0);
    }    

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

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

        JTextField[] fields = {nameField, idField, addressField, phoneField, emailField, emergencyContactField, dateOfBirthField, employeeIdField, salaryField, specializationField, qualificationField, joiningDateField,yearsOfExperienceField};
        for (JTextField field : fields) {
            field.setPreferredSize(new Dimension(200, 30));
            field.setFont(new Font("Arial", Font.PLAIN, 15));
        }

        MaleButton = new JRadioButton("Male");
        FemaleButton = new JRadioButton("Female");
        MaleButton.setFont(new Font("Arial", Font.PLAIN, 15));
        FemaleButton.setFont(new Font("Arial", Font.PLAIN, 15));

        group = new ButtonGroup();
        group.add(MaleButton);
        group.add(FemaleButton);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.add(MaleButton);
        genderPanel.add(FemaleButton);

        String[] departments = {"General Medicine", "Cardiology", "Neurology", "Orthopedics", "Pediatrics", "Surgery"};
        departmentComboBox = new JComboBox<>(departments);
        departmentComboBox.setPreferredSize(new Dimension(200, 30));
        departmentComboBox.setFont(new Font("Arial", Font.PLAIN, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row; formPanel.add(new JLabel("Doctor ID : "), gbc);
        gbc.gridx = 1; formPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Name : "), gbc);
        gbc.gridx = 1; formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Phone : "), gbc);
        gbc.gridx = 1; formPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Email : "), gbc);
        gbc.gridx = 1; formPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Address : "), gbc);
        gbc.gridx = 1; formPanel.add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Emergency Contact : "), gbc);
        gbc.gridx = 1; formPanel.add(emergencyContactField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Date of birth : "), gbc);
        gbc.gridx = 1; formPanel.add(dateOfBirthField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Employee ID : "), gbc);
        gbc.gridx = 1; formPanel.add(employeeIdField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Salary : "), gbc);
        gbc.gridx = 1; formPanel.add(salaryField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Gender : "), gbc);
        gbc.gridx = 1; formPanel.add(genderPanel, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Department : "), gbc);
        gbc.gridx = 1; formPanel.add(departmentComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Specialization : "), gbc);
        gbc.gridx = 1; formPanel.add(specializationField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Qualification : "), gbc);
        gbc.gridx = 1; formPanel.add(qualificationField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Joining Date : "), gbc);
        gbc.gridx = 1; formPanel.add(joiningDateField, gbc);

        gbc.gridx = 0; gbc.gridy = ++row; formPanel.add(new JLabel("Years Of Experience : "), gbc);
        gbc.gridx = 1; formPanel.add(yearsOfExperienceField, gbc);

        return formPanel;
    }

    private JPanel createButtonPanel() {

        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 35));
        submitButton.setFont(new Font("Arial", Font.BOLD, 15));

        clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(100, 35));
        clearButton.setFont(new Font("Arial", Font.BOLD, 15));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 35));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        submitButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed (ActionEvent e){
                if (isValidForm()){
                    Doctor newDoctor = new Doctor(
                        idField.getText().trim(),                
                        nameField.getText().trim(),               
                        addressField.getText().trim(),
                        phoneField.getText().trim(),             
                        emailField.getText().trim(),
                        emergencyContactField.getText().trim(),            
                        dateOfBirthField.getText().trim(),
                        getSelectedGender(),
                        employeeIdField.getText().trim(),
                        Double.parseDouble(salaryField.getText().trim()),
                        joiningDateField.getText().trim(),
                        (String) departmentComboBox.getSelectedItem(),
                        qualificationField.getText().trim(),
                        specializationField.getText().trim(),
                        Integer.parseInt(yearsOfExperienceField.getText().trim())
                    );

                    hospitalService.addDoctor(newDoctor);
                    JOptionPane.showMessageDialog(null, 
                    "Doctor added successfully!\n" +
                    "\nDoctor ID: " + newDoctor.getEmployeeId() +
                    "\nName: " + newDoctor.getName());
                    clearForm();
                }
            }
        });
    
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

    private char getSelectedGender(){
        if (MaleButton.isSelected()){
            return 'M';
        } else if (FemaleButton.isSelected()){
            return 'F';
        } else return 'N';
    }


    private boolean isValidForm() {
        if (
            nameField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            addressField.getText().trim().isEmpty() ||
            emergencyContactField.getText().trim().isEmpty() ||
            dateOfBirthField.getText().trim().isEmpty() ||
            employeeIdField.getText().trim().isEmpty() ||
            salaryField.getText().trim().isEmpty() ||
            (!MaleButton.isSelected() && !FemaleButton.isSelected()) ||
            departmentComboBox.getSelectedIndex() == -1 || 
            specializationField.getText().trim().isEmpty() ||
            qualificationField.getText().trim().isEmpty() ||
            joiningDateField.getText().trim().isEmpty() ||
            yearsOfExperienceField.getText().trim().isEmpty()
        ) {
            JOptionPane.showMessageDialog(this, "Please fill in all the information.", "Missing Info", JOptionPane.WARNING_MESSAGE);
            return false;
            }
        
        return true;
       
    }
    
        
    


}
