package com.doctorswallet.ui;

import com.doctorswallet.dao.PatientDAO;
import com.doctorswallet.model.Patient;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class EditPatientWindow extends JFrame {

    private JTextField searchIdField;
    private JButton searchButton;

    private JTextField nameField, ageField, bloodGroupField, addressField, phoneField, emergencyField;
    private JComboBox<String> genderBox;
    private JButton updateButton;

    public EditPatientWindow() {
        setTitle("Edit Patient Details");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🧱 Main panel with margin
        JPanel contentPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // ← adds padding (top, left, bottom, right)

        // Add form components
        contentPanel.add(new JLabel("Enter Patient ID to search:"));
        searchIdField = new JTextField();
        contentPanel.add(searchIdField);

        searchButton = new JButton("Search");
        contentPanel.add(searchButton);
        contentPanel.add(new JLabel()); // Empty label for spacing

        contentPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        contentPanel.add(nameField);

        contentPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        contentPanel.add(ageField);

        contentPanel.add(new JLabel("Gender:"));
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        contentPanel.add(genderBox);

        contentPanel.add(new JLabel("Blood Group:"));
        bloodGroupField = new JTextField();
        contentPanel.add(bloodGroupField);

        contentPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        contentPanel.add(addressField);

        contentPanel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        contentPanel.add(phoneField);

        contentPanel.add(new JLabel("Emergency Number:"));
        emergencyField = new JTextField();
        contentPanel.add(emergencyField);

        updateButton = new JButton("Update");
        contentPanel.add(updateButton);
        contentPanel.add(new JLabel());

        // Add panel to frame
        add(contentPanel);

        // Disable fields until search
        enableFields(false);

        searchButton.addActionListener(e -> searchPatient());
        updateButton.addActionListener(e -> updatePatient());

        setVisible(true);
    }

    private void enableFields(boolean enable) {
        nameField.setEnabled(enable);
        ageField.setEnabled(enable);
        genderBox.setEnabled(enable);
        bloodGroupField.setEnabled(enable);
        addressField.setEnabled(enable);
        phoneField.setEnabled(enable);
        emergencyField.setEnabled(enable);
        updateButton.setEnabled(enable);
    }

    private void searchPatient() {
        try {
            int patientId = Integer.parseInt(searchIdField.getText().trim());
            Connection conn = com.doctorswallet.dao.DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patients WHERE patientid=?");
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                ageField.setText(String.valueOf(rs.getInt("age")));
                genderBox.setSelectedItem(rs.getString("gender"));
                bloodGroupField.setText(rs.getString("blood_group"));
                addressField.setText(rs.getString("address"));
                phoneField.setText(rs.getString("phone_number"));
                emergencyField.setText(rs.getString("emrgncy_number"));
                enableFields(true);
            } else {
                JOptionPane.showMessageDialog(this, "Patient not found!");
                enableFields(false);
            }

            conn.close();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Patient ID must be a number!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePatient() {
        try {
            int patientId = Integer.parseInt(searchIdField.getText().trim());
            Patient patient = new Patient(
                patientId,
                nameField.getText().trim(),
                Integer.parseInt(ageField.getText().trim()),
                genderBox.getSelectedItem().toString(),
                bloodGroupField.getText().trim(),
                addressField.getText().trim(),
                phoneField.getText().trim(),
                emergencyField.getText().trim()
            );

            Connection conn = com.doctorswallet.dao.DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE patients SET name=?, age=?, gender=?, blood_group=?, address=?, phone_number=?, emrgncy_number=? WHERE patientid=?"
            );

            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getBloodGroup());
            stmt.setString(5, patient.getAddress());
            stmt.setString(6, patient.getPhoneNumber());
            stmt.setString(7, patient.getEmergencyNumber());
            stmt.setInt(8, patient.getPatientId());

            stmt.executeUpdate();
            conn.close();
            JOptionPane.showMessageDialog(this, "Patient updated successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditPatientWindow());
    }
}
