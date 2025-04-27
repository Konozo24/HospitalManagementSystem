import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditDepartment extends JFrame {
    private JTextField idField, nameField, locationField, headDoctorField;
    private JButton updateButton, clearButton, backButton;
    private HospitalService hospitalService;

    public EditDepartment(HospitalService hospitalService) {
        this.hospitalService = hospitalService;

        setTitle("Edit Department");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Edit Department");
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
        headDoctorField = new JTextField(20);

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
        panel.add(headDoctorField, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        updateButton = new JButton("Update");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        panel.add(updateButton);
        panel.add(clearButton);
        panel.add(backButton);

        updateButton.addActionListener(e -> updateDepartment());
        clearButton.addActionListener(e -> clearFields());
        backButton.addActionListener(e -> dispose());

        return panel;
    }

    private void updateDepartment() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String location = locationField.getText().trim();
        String headDoctor = headDoctorField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || location.isEmpty() || headDoctor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Department updatedDepartment = new Department(id, name, location, headDoctor);

        try {
            hospitalService.editDepartment(updatedDepartment);
            JOptionPane.showMessageDialog(this, "Department updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        headDoctorField.setText("");
    }
}
