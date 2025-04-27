import java.awt.*;
import java.util.List;
import javax.swing.*;

public class RemovePatient extends JDialog {
    private JComboBox<String> patientIdComboBox;
    private JButton removeButton, cancelButton;
    private HospitalService hospitalService;

    public RemovePatient(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
        setTitle("Remove Patient");
        setSize(1200, 800); // Set the size of the dialog
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        // Create a panel for the combo box and label
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        patientIdComboBox = new JComboBox<>();
        patientIdComboBox.setPreferredSize(new Dimension(200, 25)); // Smaller combo box

        List<Patient> patients = hospitalService.viewAllPatients();
        for (Patient patient : patients) {
            patientIdComboBox.addItem(patient.getPatientId());
        }

        topPanel.add(new JLabel("Select Patient ID:"));
        topPanel.add(patientIdComboBox);

        // Create a panel for the buttons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(100, 25)); // Smaller button
        removeButton.addActionListener(e -> removePatient());

        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(100, 25)); // Smaller button
        cancelButton.addActionListener(e -> dispose());

        bottomPanel.add(removeButton);
        bottomPanel.add(cancelButton);

        // Add panels to the dialog
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void removePatient() {
        try {
            String patientId = (String) patientIdComboBox.getSelectedItem();
            Patient patient = hospitalService.findPatientById(patientId);
            if (patient != null) {
                hospitalService.removePatient(patient);
                JOptionPane.showMessageDialog(this, "Patient removed successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Patient not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please select a valid Patient ID.");
        }
    }
}
