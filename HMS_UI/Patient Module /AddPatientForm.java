import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AddPatientForm extends JFrame {

    private JTextField nameField, idField, phoneField, emailField, emergencyContactField, dateOfBirthField, admitDateField, dischargeDateField;
    private JTextArea addressArea, insuranceInfoArea;
    private JRadioButton MaleButton, FemaleButton;
    private JComboBox<String> bloodGroupComboBox;
    private JButton submitButton, clearButton, backButton;
    private HospitalService hospitalService;
    private ButtonGroup group;

    public AddPatientForm(HospitalService hospitalService) {

        this.hospitalService = hospitalService;
        this.setTitle("Add New Patient");
        this.setSize(1200, 750);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only this window
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("Patient Registration Form");
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
        // Generate a default ID for convenience
        idField.setText("P" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(200, 30));
        phoneField.setFont(new Font("Arial", Font.PLAIN, 15));

        emailField = new JTextField(); 
        emailField.setPreferredSize(new Dimension(200, 30));
        emailField.setFont(new Font("Arial", Font.PLAIN, 15));

        emergencyContactField = new JTextField();
        emergencyContactField.setPreferredSize(new Dimension(200, 30));
        emergencyContactField.setFont(new Font("Arial", Font.PLAIN, 15));
        
        dateOfBirthField = new JTextField();
        dateOfBirthField.setPreferredSize(new Dimension(200, 30));
        dateOfBirthField.setFont(new Font("Arial", Font.PLAIN, 15));

        admitDateField = new JTextField();
        admitDateField.setPreferredSize(new Dimension(200, 30));
        admitDateField.setFont(new Font("Arial", Font.PLAIN, 15));

        dischargeDateField = new JTextField();
        dischargeDateField.setPreferredSize(new Dimension(200, 30));
        dischargeDateField.setFont(new Font("Arial", Font.PLAIN, 15));
        
        addressArea = new JTextArea();
        addressArea.setFont(new Font("Arial", Font.PLAIN, 15));
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        addressScrollPane.setPreferredSize(new Dimension(200, 60));

        insuranceInfoArea = new JTextArea();
        insuranceInfoArea.setFont(new Font("Arial", Font.PLAIN, 15));
        insuranceInfoArea.setLineWrap(true);
        insuranceInfoArea.setWrapStyleWord(true);
        JScrollPane insuranceScrollPane = new JScrollPane(insuranceInfoArea);
        insuranceScrollPane.setPreferredSize(new Dimension(200, 60));
        
        
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

        String[] bloodGroupTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        bloodGroupComboBox = new JComboBox<>(bloodGroupTypes);
        bloodGroupComboBox.setPreferredSize(new Dimension(200, 30));
        bloodGroupComboBox.setFont(new Font("Arial", Font.PLAIN, 15));

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

        //Right column of data fields
        gbc.gridx = 2;
        gbc.gridy = 0;
        formPanel.add(new JLabel("ID: "), gbc);
        
        gbc.gridx = 3;
        formPanel.add(idField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Blood Group"), gbc);

        gbc.gridx = 3;
        formPanel.add(bloodGroupComboBox, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Phone Number: "), gbc);

        gbc.gridx = 3;
        formPanel.add(phoneField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Admit Date (YYYY-MM-DD): "), gbc);
        
        gbc.gridx = 3;
        formPanel.add(admitDateField, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Discharge Date (YYYY-MM-DD): "), gbc);
        
        gbc.gridx = 3;
        formPanel.add(dischargeDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Insurance Info: "), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        formPanel.add(insuranceScrollPane, gbc);
        
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
                    if (idField.getText().trim().isEmpty()) {
                        idField.setText("P" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                    }

                    Patient newPatient = new Patient(
                        idField.getText(),
                        nameField.getText(),
                        addressArea.getText(),
                        phoneField.getText(),
                        emailField.getText(),
                        emergencyContactField.getText(),
                        dateOfBirthField.getText(),
                        getSelectedGender(),
                        bloodGroupComboBox.getSelectedItem().toString(),
                        admitDateField.getText(),
                        dischargeDateField.getText(),
                        insuranceInfoArea.getText()
                    );

                    hospitalService.addPatient(newPatient);
                    JOptionPane.showMessageDialog(null,
                        "Patient added successfully!\n" +
                        "\nPatient ID: " + newPatient.getPatientId() +
                        "\nName: " + nameField.getText());
                    clearForm();
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

    private char getSelectedGender() {
        if (MaleButton.isSelected()) {
            return 'M';
        } else if (FemaleButton.isSelected()) {
            return 'F';
        } else return 'N';
    }

    private boolean isValidForm() {
        if (
            nameField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            addressArea.getText().trim().isEmpty() ||
            emergencyContactField.getText().trim().isEmpty() ||
            dateOfBirthField.getText().trim().isEmpty() ||
            idField.getText().trim().isEmpty() ||
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

    private void clearForm() {
        nameField.setText("");
        idField.setText("P" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        addressArea.setText("");
        phoneField.setText("");
        emailField.setText("");
        emergencyContactField.setText("");
        dateOfBirthField.setText("");
        group.clearSelection();
        bloodGroupComboBox.setSelectedIndex(0);
        admitDateField.setText("");
        dischargeDateField.setText("");
        insuranceInfoArea.setText("");
    }
}
