
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ViewMedicalRecord extends JDialog {
    private JTable medicalRecordTable;
    private JTextField filterField;
    private HospitalService hospitalService;
    private TableRowSorter<DefaultTableModel> sorter;

    public ViewMedicalRecord(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
        setTitle("View Medical Records");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        medicalRecordTable = new JTable();
        medicalRecordTable.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Record ID", "Patient ID", "Doctor ID", "Date", "Diagnosis", "Treatment", "Prescription", "Notes", "Follow-Up Date"}
        ));
        medicalRecordTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        medicalRecordTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        medicalRecordTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        medicalRecordTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        medicalRecordTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        medicalRecordTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        medicalRecordTable.getColumnModel().getColumn(6).setPreferredWidth(150);
        medicalRecordTable.getColumnModel().getColumn(7).setPreferredWidth(200);
        medicalRecordTable.getColumnModel().getColumn(8).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(medicalRecordTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterField = new JTextField(20);
        filterPanel.add(new JLabel("Filter:"));
        filterPanel.add(filterField);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {loadMedicalRecords();
            filterField.setText("");});
        filterPanel.add(refreshButton);

        add(filterPanel, BorderLayout.NORTH);

        sorter = new TableRowSorter<>((DefaultTableModel) medicalRecordTable.getModel());
        medicalRecordTable.setRowSorter(sorter);
        filterField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                String text = filterField.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        loadMedicalRecords();
        setVisible(true);
    }

    private void loadMedicalRecords() {
        DefaultTableModel model = (DefaultTableModel) medicalRecordTable.getModel();
        model.setRowCount(0);
        List<MedicalRecord> records = hospitalService.viewAllMedicalRecords();
        for (MedicalRecord record : records) {
            model.addRow(new Object[]{
                record.getRecordId(),
                record.getPatient().getPatientId(),
                record.getDoctor().getEmployeeId(),
                record.getDate(),
                record.getDiagnosis(),
                record.getTreatment(),
                record.getPrescription(),
                record.getNotes(),
                record.getFollowUpDate()
            });
        }
    }
}
