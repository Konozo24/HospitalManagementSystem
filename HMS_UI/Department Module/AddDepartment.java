import java.awt.*;
import java.util.List;
import javax.swing.*;

public class AddDepartment extends JFrame {
    private JTextField idField, nameField, locationField;
    private JComboBox<Doctor> headDoctorComboBox;
    private JButton addButton, clearButton, backButton;
    private HospitalService hospitalService;

    public AddDepartment(HospitalService hospitalService) {
        this.hospitalService = hospitalService;

        setTitle("Add Department");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Add New Department");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = new JTextField(20);
        nameField = new JTextField(20);
        locationField = new JTextField(20);
        headDoctorComboBox = new JComboBox<>();

        // Load all doctors into ComboBox
        List<Doctor> doctors = hospitalService.viewAllDoctors();
        for (Doctor doc : doctors) {
            headDoctorComboBox.addItem(doc);
        }

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Department ID:"), gbc);
        gbc.gridx = 1;
        panel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        panel.add(locationField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Head Doctor:"), gbc);
        gbc.gridx = 1;
        panel.add(headDoctorComboBox, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        addButton = new JButton("Add");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        panel.add(addButton);
        panel.add(clearButton);
        panel.add(backButton);

        addButton.addActionListener(e -> addDepartment());
        clearButton.addActionListener(e -> clearFields());
        backButton.addActionListener(e -> dispose());

        return panel;
    }

    private void addDepartment() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String location = locationField.getText().trim();
        Doctor selectedDoctor = (Doctor) headDoctorComboBox.getSelectedItem();

        if (id.isEmpty() || name.isEmpty() || location.isEmpty() || selectedDoctor == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Department newDepartment = new Department(id, name, location, selectedDoctor.getName());

        try {
            hospitalService.addDepartment(newDepartment);
            JOptionPane.showMessageDialog(this, "Department added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        if (headDoctorComboBox.getItemCount() > 0) {
            headDoctorComboBox.setSelectedIndex(0);
        }
    }
}
