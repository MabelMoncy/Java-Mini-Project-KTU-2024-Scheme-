package com.doctorswallet.ui;

import com.doctorswallet.dao.PatientDAO;
import javax.swing.*;
import java.awt.*;

public class ViewMedicineDetailsWindow extends JFrame {

    private JTextField patientIdField;
    private JTable table;

    public ViewMedicineDetailsWindow() {
        setTitle("View Medicine Details");
        setSize(700, 150);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Enter Patient ID:"));
        patientIdField = new JTextField(10);
        topPanel.add(patientIdField);
        JButton loadBtn = new JButton("Load Medicines");
        topPanel.add(loadBtn);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadBtn.addActionListener(e -> loadData());
        setVisible(true);
    }

    private void loadData() {
        try {
            int id = Integer.parseInt(patientIdField.getText().trim());
            table.setModel(PatientDAO.getMedicineDetails(id));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid patient ID.");
        }
    }
}
