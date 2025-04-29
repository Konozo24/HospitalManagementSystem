
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ViewPatientForm extends JDialog {
    private JTable patientTable;
    private JTextField filterField;
    private HospitalService hospitalService;
    private TableRowSorter<DefaultTableModel> sorter;

    public ViewPatientForm(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
        setTitle("View Patients");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());

        patientTable = new JTable();
        patientTable.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Patient ID", "Name", "Address", "Phone Number", "Email", "Emergency Contact", "Date of Birth", "Gender", "Blood Group", "Admit Date", "Discharge Date", "Insurance Info"}
        ));
        patientTable.getColumnModel().getColumn(0).setPreferredWidth(70);
        patientTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        patientTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        patientTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        patientTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        patientTable.getColumnModel().getColumn(5).setPreferredWidth(150);
        patientTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        patientTable.getColumnModel().getColumn(7).setPreferredWidth(50);
        patientTable.getColumnModel().getColumn(8).setPreferredWidth(70);
        patientTable.getColumnModel().getColumn(9).setPreferredWidth(100);
        patientTable.getColumnModel().getColumn(10).setPreferredWidth(100);
        patientTable.getColumnModel().getColumn(11).setPreferredWidth(150);

        JScrollPane scrollPane = new JScrollPane(patientTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterField = new JTextField(20);
        filterPanel.add(new JLabel("Filter:"));
        filterPanel.add(filterField);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> {loadPatients();
        filterField.setText("");});
        filterPanel.add(refreshButton);

        add(filterPanel, BorderLayout.NORTH);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        filterPanel.add(cancelButton);

        add(filterPanel, BorderLayout.NORTH);


        sorter = new TableRowSorter<>((DefaultTableModel) patientTable.getModel());
        patientTable.setRowSorter(sorter);
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

        loadPatients();
        setVisible(true);
    }

    private void loadPatients() {
        DefaultTableModel model = (DefaultTableModel) patientTable.getModel();
        model.setRowCount(0);
        List<Patient> patients = hospitalService.viewAllPatients();
        for (Patient patient : patients) {
            model.addRow(new Object[]{
                patient.getPatientId(),
                patient.getName(),
                patient.getAddress(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getEmergencyContact(),
                patient.getDateOfBirth(),
                patient.getGender(),
                patient.getBloodGroup(),
                patient.getAdmitDate(),
                patient.getDischargeDate(),
                patient.getInsuranceInfo()
            });
        }
    }
}
