import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoveDepartment extends JFrame {
    private JTextField idField;
    private JButton removeButton, clearButton, backButton;
    private HospitalService hospitalService;

    public RemoveDepartment (HospitalService hospitalService) {
        this.hospitalService = hospitalService;

        setTitle("Remove Department");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Remove Department");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Department ID:"), gbc);
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        removeButton = new JButton("Remove");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        removeButton.addActionListener(e -> removeDepartment());
        clearButton.addActionListener(e -> clearFields());
        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void removeDepartment() {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the Department ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Department department = hospitalService.hospital.findDepartmentById(id);
            if (department == null) {
                JOptionPane.showMessageDialog(this, "Department not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            hospitalService.removeDepartment(department);
            JOptionPane.showMessageDialog(this, "Department removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
    }
}
