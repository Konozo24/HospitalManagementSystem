
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ViewDoctorForm extends JFrame {

    private JTextField idField;
    private JTextArea resultArea;
    private JButton searchButton, clearButton, backButton;
    private HospitalService hospitalService;

    public ViewDoctorForm(HospitalService hospitalService) {
        this.hospitalService = hospitalService;

        setTitle("View Doctor Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("View Doctor Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel idLabel = new JLabel("Enter Doctor ID:");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchButton = new JButton("Search");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(searchButton);
        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createTitledBorder("Doctor Details"));
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        clearButton = new JButton("Clear");
        backButton = new JButton("Back");

        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        searchButton.addActionListener(e -> searchDoctor());
        clearButton.addActionListener(e -> {
            idField.setText("");
            resultArea.setText("");
        });
        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void searchDoctor() {
        String doctorId = idField.getText().trim();
        if (doctorId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Doctor ID.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Doctor doctor = hospitalService.findDoctorById(doctorId);
        if (doctor != null) {
            StringBuilder details = new StringBuilder();
            details.append("Doctor ID       : ").append(doctor.getId()).append("\n");
            details.append("Name            : ").append(doctor.getName()).append("\n");
            details.append("Address         : ").append(doctor.getAddress()).append("\n");
            details.append("Phone           : ").append(doctor.getPhoneNumber()).append("\n");
            details.append("Email           : ").append(doctor.getEmail()).append("\n");
            details.append("Emergency       : ").append(doctor.getEmergencyContact()).append("\n");
            details.append("Gender          : ").append(doctor.getGender()).append("\n");
            details.append("Department      : ").append(doctor.getDepartment()).append("\n");
            details.append("Specialization  : ").append(doctor.getSpecialization()).append("\n");
            details.append("Qualification   : ").append(doctor.getQualification()).append("\n");
            details.append("Joining Date    : ").append(doctor.getJoinDate()).append("\n");
            details.append("Years of experience : ").append(doctor.getyearsOfExperience()).append("\n");

            resultArea.setText(details.toString());
        } else {
            resultArea.setText("");
            JOptionPane.showMessageDialog(this, "Doctor not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
        }
    }
}
