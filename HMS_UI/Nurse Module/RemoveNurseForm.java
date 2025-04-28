package Assignment;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class RemoveNurseForm extends JFrame {
    private JTable nurseTable;
    private JTextField nurseSearchField;
    private DefaultTableModel tableModel;
    private Nurse selectedNurse;
    private JButton removeButton, backButton;
    private HospitalService hospitalService;


    // Constructor initializes the form and sets up all components
    public RemoveNurseForm(HospitalService hospitalService){
        this.hospitalService = hospitalService;
        this.setTitle("Remove Nurse");
        this.setSize(1200, 750);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only this window

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("Remove Nurse Form");
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


    // Creates and organizes all input fields into a form panel
    private JPanel createFormPanel(){
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        nurseSearchField = new JTextField();
        nurseSearchField.setPreferredSize(new Dimension(200, 30));
        nurseSearchField.setFont(new Font("Arial", Font.PLAIN, 15));

        // Allow user to press 'Enter' key to search
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
                        JOptionPane.showMessageDialog(formPanel, "Appointment successfully Found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(formPanel,"No appointment found with ID: " + searchText,"Search Result", JOptionPane.INFORMATION_MESSAGE);
                        loadAllNurses(tableModel);
                    }
                }
            }
        });
        
        // Set up common constraints for consistent padding and alignment of form elements
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Search Nurse: "), gbc);

        gbc.gridx = 1;
        formPanel.add(nurseSearchField, gbc);

        // Create a panel to hold the nurse table with a titled border
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(),             // Border style
        "Available Nurses",                       // Title text
        TitledBorder.LEFT,                              // Title alignment
        TitledBorder.TOP,                               // Title position
        new Font("Arial", Font.BOLD, 14)      // Title font
        ));

        // Column names for the table
        String[] col = {"Employee ID", "Name", "Department", "Assigned Doctor", "Shift", "Object"};
        tableModel = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        // Load existing nurses into the table model
        loadAllNurses(tableModel);
        

        nurseTable = new JTable(tableModel);
        nurseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nurseTable.setFont(new Font("Arial", Font.PLAIN, 14));
        nurseTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        nurseTable.setRowHeight(25);
        
        // Hide the "Object" column, used internally for selecting the object
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

        // Get the selected Nurse object based on the selected row in the table
        nurseTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                if (!e.getValueIsAdjusting()){
                    int selectedRow = nurseTable.getSelectedRow();
                    if (selectedRow >= 0){
                        selectedNurse = (Nurse) nurseTable.getValueAt(selectedRow, 5);
                    }
                }
            }
        });
        return formPanel;
    }

    // Load all nurses 
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


    // Creates and returns the panel containing Remove and Back buttons
    private JPanel createButtonPanel(){
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(100, 35));
        removeButton.setFont(new Font("Arial", Font.BOLD, 15));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 35));
        backButton.setFont(new Font("Arial", Font.BOLD, 15));

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                if (selectedNurse != null){

                    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this nurse?", "Remove Nurse", JOptionPane.YES_NO_OPTION);
                    
                    if (option == JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(null, "Nurse removed successfully \n", "Success", JOptionPane.INFORMATION_MESSAGE);
                        hospitalService.removeNurse(selectedNurse);
                        // Remove the row from the table model
                        int selectedRow = nurseTable.getSelectedRow();
                        if (selectedRow >= 0) {
                            tableModel.removeRow(selectedRow);
                        }
                        selectedNurse = null;
                        // Reset selection
                        

                    } else {
                        JOptionPane.showMessageDialog(buttonPanel, "Failed to remove Nurse", "No Selection", JOptionPane.WARNING_MESSAGE);
                    }    
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);

        return buttonPanel;
    }


}
