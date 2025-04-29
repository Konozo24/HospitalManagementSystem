

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeleteDoctorForm extends JFrame {

    private JTextField idField;
    private JButton deleteButton, clearButton, backButton;
    private HospitalService hospitalService;

    public DeleteDoctorForm(HospitalService hospitalService) {
        this.hospitalService = hospitalService;

        this.setTitle("Delete Doctor");
        this.setSize(500, 250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Delete Doctor Record");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel idLabel = new JLabel("Enter Doctor ID : ");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 16));

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(idLabel, gbc);

        gbc.gridx = 1;
        inputPanel.add(idField, gbc);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        JButton[] buttons = {deleteButton, clearButton, backButton};
        for (JButton btn : buttons) {
            btn.setPreferredSize(new Dimension(100, 35));
            btn.setFont(new Font("Arial", Font.BOLD, 15));
            buttonPanel.add(btn);
        }

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctorId = idField.getText().trim();
                if (doctorId.isEmpty()) {
                    JOptionPane.showMessageDialog(DeleteDoctorForm.this, "Please enter a Doctor ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean deleted = false;
                
                if (hospitalService.findDoctorById(doctorId) != null){
                    deleted = true;
                    hospitalService.removeDoctor(hospitalService.findDoctorById(doctorId));
                    
                }
                


                if (deleted) {
                    JOptionPane.showMessageDialog(DeleteDoctorForm.this, "Doctor with ID " + doctorId + " deleted successfully.");
                    idField.setText("");
                } else {
                    JOptionPane.showMessageDialog(DeleteDoctorForm.this, "Doctor ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearButton.addActionListener(e -> idField.setText(""));
        backButton.addActionListener(e -> dispose());

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);
    }
}
