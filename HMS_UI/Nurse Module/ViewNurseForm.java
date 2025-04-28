

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class ViewNurseForm extends JFrame {
    private JTable nurseTable;
    private JTextField nurseSearchField;
    private HospitalService hospitalService;
    private JButton backButton;
    
    private DefaultTableModel tableModel;

    // Initialize and configure the UI components
    public ViewNurseForm(HospitalService hospitalService){
        this.hospitalService = hospitalService;
        this.setTitle("View Nurse");
        this.setSize(1200, 750);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only this window


        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("View Nurse");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0,0,15,0));

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);
        this.setLocationRelativeTo(null); // center the window
        this.setVisible(true);
    }

    // Create the UI components 
    private JPanel createFormPanel(){
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        nurseSearchField = new JTextField();
        nurseSearchField.setPreferredSize(new Dimension(200, 30));
        nurseSearchField.setFont(new Font("Arial", Font.PLAIN, 15));
        // Allow user to press 'Enter' to search
        nurseSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e ){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String searchText = nurseSearchField.getText().trim();

                    // Remove all existing rows
                    tableModel.setRowCount(0);

                    if(searchText.isEmpty()){
                        loadAllNurses(tableModel);
                        return;
                    }  

                    boolean found = false;
                    for (Nurse n : hospitalService.viewAllNurses()){
                        if (n.getEmployeeId().equalsIgnoreCase(searchText)){
                            tableModel.addRow(new Object[]{
                                n.getEmployeeId(),
                                n.getName(),
                                n.getDepartment(),
                                n.getAssignedDoctor(),
                                n.getShift(),
                                n
                            });
                            found = true;
                            break;
                        } 
                    }

                    if(found){
                        JOptionPane.showMessageDialog(formPanel, "Nurse successfully Found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(formPanel,"No Nurse found with ID: " + searchText,"Search Result", JOptionPane.INFORMATION_MESSAGE);
                        loadAllNurses(tableModel);
                    }
                }
            }
        });
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Search Nurse: "), gbc);

        gbc.gridx = 1;
        formPanel.add(nurseSearchField, gbc);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(),
        "Available Nurse",
        TitledBorder.LEFT,
        TitledBorder.TOP,
        new Font("Arial", Font.BOLD, 14)
        ));

        // Columns name for table
        String[] col = {"Employee ID", "Name", "Department", "Assigned Doctor", "Shift", "Object"};
        tableModel = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        loadAllNurses(tableModel);
        

        nurseTable = new JTable(tableModel);
        nurseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nurseTable.setFont(new Font("Arial", Font.PLAIN, 14));
        nurseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        nurseTable.setRowHeight(25);
        // Hide the "Object", used internally for selecting
        nurseTable.getColumnModel().getColumn(5).setMinWidth(0);
        nurseTable.getColumnModel().getColumn(5).setMaxWidth(0);
        nurseTable.getColumnModel().getColumn(5).setWidth(0);

        JScrollPane tablePane = new JScrollPane(nurseTable);
        tablePane.setPreferredSize(new Dimension(650, 300));
        tablePanel.add(tablePane, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        formPanel.add(tablePanel, gbc);

        return formPanel;
    }   

    // Create back button
    private JPanel createButtonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 35));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        buttonPanel.add(backButton);

        return buttonPanel;
    }

    // Load All Nurses details into the table
    private void loadAllNurses(DefaultTableModel tableModel){
        for (Nurse n : hospitalService.viewAllNurses()){
            tableModel.addRow(new Object[]{
                n.getEmployeeId(),
                n.getName(),
                n.getDepartment(),
                n.getAssignedDoctor(),
                n.getShift(),
                n
            });
        }
    }
}
