

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddNurseForm extends JFrame {
    
    private JTextField nameField, idField, phoneField, emailField, dateOfBirthField, emergencyContactField, salaryField, joinDateField, qualificationField;
    private JTextArea addressArea, dutiesArea;
    private JRadioButton MaleButton, FemaleButton;
    private JComboBox<String> departmentComboBox;
    private JComboBox<String> shiftComboBox;
    private JComboBox<Doctor> assignedDoctorComboBox;
    private JButton submitButton, clearButton, backButton;
    private HospitalService hospitalService;
    private ButtonGroup group;

    // Constructor initializes the form and sets up all components.
    public AddNurseForm(HospitalService hospitalService) {

        this.hospitalService = hospitalService;
        this.setTitle("Add New Nurse");
        this.setSize(1200, 750);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only this window

        // Main container panel using BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("Nurse Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));

        JPanel formPanel = createFormPanel();       // Panel with input fields
        JPanel buttonPanel = createButtonPanel();   // Panel with action buttons

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setLocationRelativeTo(null); // center the window
        this.setVisible(true);

    }

    // Creates and organizes all input fields into a form panel
    private JPanel createFormPanel(){
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 30));
        nameField.setFont(new Font("Arial", Font.PLAIN, 15));

        idField = new JTextField();
        idField.setPreferredSize(new Dimension(200, 30));
        idField.setFont(new Font("Arial", Font.PLAIN, 15));

        phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(200, 30));
        phoneField.setFont(new Font("Arial", Font.PLAIN, 15));

        emailField = new JTextField(); 
        emailField.setPreferredSize(new Dimension(200, 30));
        emailField.setFont(new Font("Arial", Font.PLAIN, 15));

        emergencyContactField = new JTextField();
        emergencyContactField.setPreferredSize(new Dimension(200, 30));
        emergencyContactField.setFont(new Font("Arial", Font.PLAIN, 15));
        
        salaryField = new JTextField();
        salaryField.setPreferredSize(new Dimension(200, 30));
        salaryField.setFont(new Font("Arial", Font.PLAIN, 15));

        joinDateField = new JTextField();
        joinDateField.setPreferredSize(new Dimension(200, 30));
        joinDateField.setFont(new Font("Arial", Font.PLAIN, 15));
        
        qualificationField = new JTextField();
        qualificationField.setPreferredSize(new Dimension(200, 30));
        qualificationField.setFont(new Font("Arial", Font.PLAIN, 15));

        dateOfBirthField = new JTextField();
        dateOfBirthField.setPreferredSize(new Dimension(200, 30));
        dateOfBirthField.setFont(new Font("Arial", Font.PLAIN, 15));
        
        addressArea = new JTextArea();
        addressArea.setFont(new Font("Arial", Font.PLAIN, 15));
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        addressScrollPane.setPreferredSize(new Dimension(200, 60));

        dutiesArea = new JTextArea();
        dutiesArea.setFont(new Font("Arial", Font.PLAIN, 15));
        dutiesArea.setLineWrap(true);
        dutiesArea.setWrapStyleWord(true);
        JScrollPane dutiesScrollPane = new JScrollPane(dutiesArea);
        dutiesScrollPane.setPreferredSize(new Dimension(200, 60));
        
        
        MaleButton = new JRadioButton("Male");
        MaleButton.setFont(new Font("Arial", Font.PLAIN, 15));
        FemaleButton = new JRadioButton("Female");
        FemaleButton.setFont(new Font("Arial", Font.PLAIN, 15));

        group = new ButtonGroup();
        group.add(MaleButton);
        group.add(FemaleButton);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        genderPanel.add(MaleButton);
        genderPanel.add(FemaleButton);

        String[] departmentTypes = {"General Medicine", "Cardiology", "Neurology", "Orthopedics", "Pediatrics", "Surgery"};
        departmentComboBox = new JComboBox<>(departmentTypes);
        departmentComboBox.setPreferredSize(new Dimension(200, 30));
        departmentComboBox.setFont(new Font("Arial", Font.PLAIN, 15));

        String[] shiftTime = {"Morning", "Noon", "Midnight"};
        shiftComboBox = new JComboBox<>(shiftTime);
        shiftComboBox.setPreferredSize(new Dimension(200, 30));
        shiftComboBox.setFont(new Font("Arial", Font.PLAIN, 15));

        assignedDoctorComboBox = new JComboBox<>();
        assignedDoctorComboBox.setPreferredSize(new Dimension(200, 30));
        assignedDoctorComboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        
        for (Doctor d : hospitalService.viewAllDoctors()){
            assignedDoctorComboBox.addItem(d);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // First column of data fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Name: "), gbc);

        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Date of Birth: "), gbc);

        gbc.gridx = 1;
        formPanel.add(dateOfBirthField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Gender: "), gbc);

        gbc.gridx = 1;
        formPanel.add(genderPanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Address: "), gbc);

        gbc.gridx = 1;
        formPanel.add(addressScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Email: "), gbc);
        
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Emergency Contact: "), gbc);
        
        gbc.gridx = 1;
        formPanel.add(emergencyContactField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Shift: "), gbc);

        gbc.gridx = 1;
        formPanel.add(shiftComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(new JLabel("Assigned Doctor: "), gbc);

        gbc.gridx = 1;
        formPanel.add(assignedDoctorComboBox, gbc);

        //Right column of data fields
        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(new JLabel("ID: "), gbc);
        
        gbc.gridx = 3;
        formPanel.add(idField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Phone Number: "), gbc);

        gbc.gridx = 3;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Join Date (YYYY-MM-DD): "), gbc);
        
        gbc.gridx = 3;
        formPanel.add(joinDateField, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Qualification: "), gbc);
        
        gbc.gridx = 3;
        formPanel.add(qualificationField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Salary: "), gbc);
        
        gbc.gridx = 3;
        formPanel.add(salaryField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Department: "), gbc);

        gbc.gridx = 3;
        formPanel.add(departmentComboBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Duties: "), gbc);

        gbc.gridx = 3;
        formPanel.add(dutiesScrollPane, gbc);
        
        return formPanel;

    }

    // Creates and returns the panel containing Submit, Clear, and Back buttons
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

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                // Check if any required fields are empty
                if (isValidForm()){
                    Nurse newNurse = new Nurse(
                        idField.getText(),                 // ID
                        nameField.getText(),               // Name
                        addressArea.getText(),             // Address
                        phoneField.getText(),              // Phone number
                        emailField.getText(),              // Email
                        emergencyContactField.getText(),           // Emergency contact
                        dateOfBirthField.getText(),                // Date of birth
                        getSelectedGender(),               // Genders
                        Double.parseDouble(salaryField.getText()),         // Salary
                        joinDateField.getText(),         // Join Date
                        departmentComboBox.getSelectedItem().toString(), // Department
                        qualificationField.getText(),          // Qualification
                        assignedDoctorComboBox.getSelectedItem().toString(), // Assigned Doctor
                        shiftComboBox.getSelectedItem().toString(), // Shift Time
                        dutiesArea.getText()           // Duties
                    );

                    hospitalService.addNurse(newNurse);
                    JOptionPane.showMessageDialog(null, 
                    "Nurse added successfully!\n" +
                    "\nNurse ID: " + newNurse.getEmployeeId() +
                    "\nName: " + nameField.getText());
                    clearForm();
                }
            }
        });
        
        // Clear the form
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                clearForm();
            }
        });

        // Exit to Nurse Module
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


    // Convert selected gender to character
    private char getSelectedGender(){
        if (MaleButton.isSelected()){
            return 'M';
        } else if (FemaleButton.isSelected()){
            return 'F';
        } else return 'N';
    }


    // Checks whether all the information is filled
    private boolean isValidForm() {
        if (
            idField.getText().trim().isEmpty() ||
            nameField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            addressArea.getText().trim().isEmpty() ||
            emergencyContactField.getText().trim().isEmpty() ||
            dateOfBirthField.getText().trim().isEmpty() ||
            (!MaleButton.isSelected() && !FemaleButton.isSelected()) ||
            departmentComboBox.getSelectedIndex() == -1 || 
            assignedDoctorComboBox.getSelectedIndex() == -1 ||
            shiftComboBox.getSelectedIndex() == -1 ||
            qualificationField.getText().trim().isEmpty() ||
            joinDateField.getText().trim().isEmpty() ||
            salaryField.getText().trim().isEmpty() ||
            dutiesArea.getText().trim().isEmpty()
        ) {
            JOptionPane.showMessageDialog(this, "Please fill in all the information.", "Missing Info", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Regex format to check data validity (YYYY-MM-DD)
        if (!dateOfBirthField.getText().trim().matches("\\d{4}-\\d{2}-\\d{2}") && !joinDateField.getText().trim().matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Date must be in the format YYYY-MM-DD.", "Invalid Date Format", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Check if the date is a valid calendar date
        try {
            LocalDate.parse(dateOfBirthField.getText().trim()); // throws exception if date is invalid
            LocalDate.parse(joinDateField.getText().trim());
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date. Please enter a correct date in the format YYYY-MM-DD.", "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }


    // Clears all fields and resets the form 
    private void clearForm(){
        idField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressArea.setText("");
        emergencyContactField.setText("");
        dateOfBirthField.setText("");
        group.clearSelection();
        departmentComboBox.setSelectedIndex(0);
        assignedDoctorComboBox.setSelectedIndex(0);
        shiftComboBox.setSelectedIndex(0);
        qualificationField.setText("");
        joinDateField.setText("");
        salaryField.setText("");
        dutiesArea.setText("");
    }

}



