package com.doctorswallet.ui;

import com.doctorswallet.dao.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddCurrentMedicalStatusWindow extends JFrame {

    private JTextField patientIdField, nameField, diagField, descriptionField;
    private JButton saveButton, clearButton;

    public AddCurrentMedicalStatusWindow() {
        setTitle("Add Current Medical Status");
        setSize(400, 300);
        setLocationRelativeTo(null);

        // 🧱 Main panel with margin
        JPanel contentPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // top, left, bottom, right margin

        // Add components
        contentPanel.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        contentPanel.add(patientIdField);

        contentPanel.add(new JLabel("Patient Name:"));
        nameField = new JTextField();
        contentPanel.add(nameField);

        contentPanel.add(new JLabel("Diagnosed Illness:"));
        diagField = new JTextField();
        contentPanel.add(diagField);

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
        saveButton.addActionListener(e -> saveCurrentStatus());
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private void saveCurrentStatus() {
        String pid = patientIdField.getText().trim();
        String name = nameField.getText().trim();
        String diag = diagField.getText().trim();
        String desc = descriptionField.getText().trim();

        if (pid.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID and Name are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO currnt_med_sts (patientid, name, diagonized_illness, description) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, pid);
            pst.setString(2, name);
            pst.setString(3, diag);
            pst.setString(4, desc);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Current medical status added successfully!");
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        patientIdField.setText("");
        nameField.setText("");
        diagField.setText("");
        descriptionField.setText("");
    }
}
