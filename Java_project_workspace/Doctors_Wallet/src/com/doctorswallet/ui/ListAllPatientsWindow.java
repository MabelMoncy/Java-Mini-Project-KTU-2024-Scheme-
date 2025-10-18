package com.doctorswallet.ui;

import com.doctorswallet.dao.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ListAllPatientsWindow extends JFrame {

    private JTable patientsTable;
    private DefaultTableModel tableModel;

    public ListAllPatientsWindow() {
        setTitle("All Patients - Doctor’s Wallet");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(245, 248, 250));

        // 🩺 Title
        JLabel titleLabel = new JLabel("All Patients List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(36, 123, 160));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // 🧾 Table setup
        String[] columns = {
            "Patient ID", "Name", "Age", "Gender", "Blood Group", 
            "Address", "Phone", "Emergency Number"
        };

        tableModel = new DefaultTableModel(columns, 0);
        patientsTable = new JTable(tableModel);
        patientsTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        patientsTable.setRowHeight(28);
        patientsTable.setFillsViewportHeight(true);
        patientsTable.setShowHorizontalLines(true);
        patientsTable.setGridColor(new Color(230, 230, 230));

        // 🧩 Alternate row colors
        patientsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final Color evenColor = new Color(250, 250, 250);
            private final Color oddColor = new Color(235, 245, 250);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                          boolean isSelected, boolean hasFocus,
                                                          int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? evenColor : oddColor);
                } else {
                    c.setBackground(new Color(180, 215, 255));
                }
                return c;
            }
        });

        // 🧠 Table header style
        JTableHeader header = patientsTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setBackground(new Color(36, 123, 160));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        // 🖼️ Scroll pane with margin
        JScrollPane scrollPane = new JScrollPane(patientsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30)); // margins
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(scrollPane, BorderLayout.CENTER);

        // 🧭 Load Data
        loadPatients();

        setVisible(true);
    }

    private void loadPatients() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM patients";
            ResultSet rs = stmt.executeQuery(sql);

            tableModel.setRowCount(0); // clear table first

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("patientid"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("blood_group"),
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    rs.getString("emrgncy_number")
                };
                tableModel.addRow(row);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "⚠️ No patient details available.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListAllPatientsWindow::new);
    }
}
