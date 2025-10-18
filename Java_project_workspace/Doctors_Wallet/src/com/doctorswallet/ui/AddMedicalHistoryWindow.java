package com.doctorswallet.ui;

import com.doctorswallet.dao.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddMedicalHistoryWindow extends JFrame {

    private JTextField patientIdField, nameField, descriptionField;
    private JButton saveButton, clearButton;

    public AddMedicalHistoryWindow() {
        setTitle("Add Medical History");
        setSize(400, 250);
        setLocationRelativeTo(null);

        // 🧱 Main panel with GridLayout and margin
        JPanel contentPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // top, left, bottom, right margin

        // Components
        contentPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        contentPanel.add(patientIdField);

        contentPanel.add(new JLabel("Patient Name:"));
        nameField = new JTextField();
        contentPanel.add(nameField);

        contentPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        contentPanel.add(descriptionField);

        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");
        contentPanel.add(saveButton);
        contentPanel.add(clearButton);

        // Add panel to frame
        add(contentPanel);

        // Button actions
        saveButton.addActionListener(e -> saveMedicalHistory());
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private void saveMedicalHistory() {
        try {
            int pid = Integer.parseInt(patientIdField.getText().trim());
            String name = nameField.getText().trim();
            String desc = descriptionField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Patient Name is required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO medical_history (patientid, name, description) VALUES (?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, pid);
                pst.setString(2, name);
                pst.setString(3, desc);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Medical history added successfully!");
                clearFields();
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Patient ID must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        patientIdField.setText("");
        nameField.setText("");
        descriptionField.setText("");
    }
}
