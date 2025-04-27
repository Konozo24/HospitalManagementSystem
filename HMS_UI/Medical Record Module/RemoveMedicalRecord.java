import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RemoveMedicalRecord extends JDialog {
    private JComboBox<String> recordIdComboBox;
    private HospitalService hospitalService;

    public RemoveMedicalRecord(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
        setTitle("Remove Medical Record");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new GridLayout(3, 2, 10, 10));

        recordIdComboBox = new JComboBox<>();
        List<MedicalRecord> records = hospitalService.viewAllMedicalRecords();
        for (MedicalRecord record : records) {
            recordIdComboBox.addItem(record.getRecordId());
        }

        add(new JLabel("Record ID:"));
        add(recordIdComboBox);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMedicalRecord();
            }
        });
        add(removeButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton);

        setVisible(true);
    }

    private void removeMedicalRecord() {
        try {
            String recordId = (String) recordIdComboBox.getSelectedItem();
            MedicalRecord record = hospitalService.findMedicalRecordById(recordId);
            if (record != null) {
                hospitalService.removeMedicalRecord(record);
                JOptionPane.showMessageDialog(this, "Medical Record removed successfully!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Medical Record not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please select a valid Medical Record ID.");
        }
    }
}
