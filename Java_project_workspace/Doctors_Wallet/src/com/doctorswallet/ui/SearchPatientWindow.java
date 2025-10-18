package com.doctorswallet.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SearchPatientWindow extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JTable resultTable;

    public SearchPatientWindow() {
        setTitle("Search Patient");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search by Name or ID:"));
        searchField = new JTextField(15);
        topPanel.add(searchField);
        searchButton = new JButton("Search");
        topPanel.add(searchButton);
        add(topPanel, BorderLayout.NORTH);

        resultTable = new JTable();
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchPatient());

        setVisible(true);
    }

    private void searchPatient() {
        String keyword = searchField.getText().trim();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Patient ID", "Name", "Age", "Gender", "Blood Group", "Address", "Phone", "Emergency"});

        try {
            Connection conn = com.doctorswallet.dao.DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM patients WHERE name LIKE ? OR patientid LIKE ?"
            );
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("patientid"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("blood_group"),
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    rs.getString("emrgncy_number")
                });
            }
            resultTable.setModel(model);
            conn.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchPatientWindow());
    }
}
