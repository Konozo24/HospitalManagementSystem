package Assignment;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

public class EditAppointment extends JFrame {
    private JTable appointmentTable;
    private JTextField appointmentSearchField;
    private HospitalService hospitalService;
    private JButton editButton, backButton;
    private Appointment selectedAppointment;
    private DefaultTableModel tableModel; 
    
    public EditAppointment(HospitalService hospitalService){
        this.hospitalService = hospitalService;
        this.setTitle("Edit Appointment");
        this.setSize(1200, 750);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close only this window

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel("Edit Appointment Form");
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

    private JPanel createFormPanel(){
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        appointmentSearchField = new JTextField();
        appointmentSearchField.setPreferredSize(new Dimension(200, 30));
        appointmentSearchField.setFont(new Font("Arial", Font.PLAIN, 15));
        
        appointmentSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e ){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String searchText = appointmentSearchField.getText().trim();

                    // Remove all existing rows
                    tableModel.setRowCount(0);

                    if(searchText.isEmpty()){
                        loadAllAppointments(tableModel);
                        return;
                    }  

                    boolean found = false;
                    for (Appointment a : hospitalService.viewAllAppointments()){
                        if (a.getAppointmentId().equalsIgnoreCase(searchText)){
                            tableModel.addRow(new Object[]{
                                a.getAppointmentId(),
                                a.getPatient().getName(),
                                a.getDoctor().getName(),
                                a.getDate(),
                                a.getTime(),
                                a.getConsultationRoom(),
                                a.getPurpose(),
                                a.getStatus(),
                                a
                            });
                            found = true;
                            break;
                        } 
                    }

                    if(found){
                        JOptionPane.showMessageDialog(formPanel, "Appointment successfully Found", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(formPanel,"No appointment found with ID: " + searchText,"Search Result", JOptionPane.INFORMATION_MESSAGE);
                        loadAllAppointments(tableModel);
                    }
                }
            }
        });

        editButton = new JButton("Edit");
        editButton.setPreferredSize(new Dimension(100, 35));
        editButton.setFont(new Font("Arial", Font.BOLD, 15));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                if (selectedAppointment != null) {
                    new EditAppointmentDialog((JFrame) SwingUtilities.getWindowAncestor(formPanel), selectedAppointment, hospitalService, () -> {
                        // Refresh your table here after save
                        tableModel.setRowCount(0);
                        loadAllAppointments(tableModel);
                    }).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(formPanel, "Please select an appointment to edit.");
                }
            }

        });


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Search Appointment: "), gbc);

        gbc.gridx = 1;
        formPanel.add(appointmentSearchField, gbc);

        gbc.gridx = 2;
        formPanel.add(editButton);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(),
        "Available Appointments",
        TitledBorder.LEFT,
        TitledBorder.TOP,
        new Font("Arial", Font.BOLD, 14)
        ));

        String[] col = {"ID", "Patient", "Doctor", "Date", "Time", "Consulation Room", "Purpose", "Status", "Object"};
        tableModel = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        loadAllAppointments(tableModel);

        appointmentTable = new JTable(tableModel);
        appointmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        appointmentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        appointmentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        appointmentTable.setRowHeight(25);
        appointmentTable.getColumnModel().getColumn(8).setMinWidth(0);
        appointmentTable.getColumnModel().getColumn(8).setMaxWidth(0);
        appointmentTable.getColumnModel().getColumn(8).setWidth(0);

        JScrollPane tablePane = new JScrollPane(appointmentTable);
        tablePane.setPreferredSize(new Dimension(650, 300));
        tablePanel.add(tablePane, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        formPanel.add(tablePanel, gbc);

        appointmentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e){
                if (!e.getValueIsAdjusting()){
                    int selectedRow = appointmentTable.getSelectedRow();
                    if (selectedRow >= 0){
                        selectedAppointment = (Appointment) appointmentTable.getValueAt(selectedRow, 8);
                    }
                }
            }
        });
        return formPanel;
    }   


    private void loadAllAppointments(DefaultTableModel tableModel){
        for (Appointment a : hospitalService.viewAllAppointments()){
            tableModel.addRow(new Object[]{
                a.getAppointmentId(),
                a.getPatient(),
                a.getDoctor(),
                a.getDate(),
                a.getTime(),
                a.getConsultationRoom(),
                a.getPurpose(),
                a.getStatus(),
                a
            });
        }
    }

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


}
