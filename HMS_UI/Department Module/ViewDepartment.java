import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewDepartment extends JFrame {
    private JTable departmentTable;
    private HospitalService hospitalService;
    private JButton backButton;

    public ViewDepartment(HospitalService hospitalService) {
        this.hospitalService = hospitalService;

        setTitle("View All Departments");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("All Departments");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Table
        departmentTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(departmentTable);
        add(scrollPane, BorderLayout.CENTER);

        loadDepartmentData();

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");

        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void loadDepartmentData() {
        String[] columnNames = {"Department ID", "Name", "Location", "Head Doctor"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        List<Department> departments = hospitalService.viewAllDepartments();

        for (Department dept : departments) {
            Object[] row = {
                dept.getDepartmentId(),
                dept.getName(),
                dept.getLocation(),
                dept.getHeadDoctor()
            };
            model.addRow(row);
        }

        departmentTable.setModel(model);
    }
}
