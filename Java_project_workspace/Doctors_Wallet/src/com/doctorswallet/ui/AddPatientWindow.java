package com.doctorswallet.ui;

import com.doctorswallet.dao.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddPatientWindow extends JFrame {

    private JTextField patientIdField, nameField, ageField, genderField, bloodField, addressField, phoneField, emergencyField;
    private JButton saveButton, clearButton;

    public AddPatientWindow() {
        setTitle("Add Patient");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 🩺 Main Panel with Padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        mainPanel.setBackground(Color.WHITE);

        // 🧱 Form Panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // 🔹 Labels
        JLabel idLabel = new JLabel("Patient ID:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel ageLabel = new JLabel("Age:");
        JLabel genderLabel = new JLabel("Gender:");
        JLabel bloodLabel = new JLabel("Blood Group:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel phoneLabel = new JLabel("Phone Number:");
        JLabel emergencyLabel = new JLabel("Emergency Number:");

        // 🔹 Text Fields (Compact)
        Dimension fieldSize = new Dimension(200, 20);
        patientIdField = new JTextField(); patientIdField.setPreferredSize(fieldSize);
        nameField = new JTextField(); nameField.setPreferredSize(fieldSize);
        ageField = new JTextField(); ageField.setPreferredSize(fieldSize);
        genderField = new JTextField(); genderField.setPreferredSize(fieldSize);
        bloodField = new JTextField(); bloodField.setPreferredSize(fieldSize);
        addressField = new JTextField(); addressField.setPreferredSize(fieldSize);
        phoneField = new JTextField(); phoneField.setPreferredSize(fieldSize);
        emergencyField = new JTextField(); emergencyField.setPreferredSize(fieldSize);

        // 🧩 Add fields row by row
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(idLabel, gbc);
        gbc.gridx = 1; formPanel.add(patientIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(nameLabel, gbc);
        gbc.gridx = 1; formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(ageLabel, gbc);
        gbc.gridx = 1; formPanel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(genderLabel, gbc);
        gbc.gridx = 1; formPanel.add(genderField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(bloodLabel, gbc);
        gbc.gridx = 1; formPanel.add(bloodField, gbc);

        gbc.gridx = 0; gbc.gridy = 5; formPanel.add(addressLabel, gbc);
        gbc.gridx = 1; formPanel.add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy = 6; formPanel.add(phoneLabel, gbc);
        gbc.gridx = 1; formPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 7; formPanel.add(emergencyLabel, gbc);
        gbc.gridx = 1; formPanel.add(emergencyField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // 🔘 Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");

        Dimension btnSize = new Dimension(100, 30);
        saveButton.setPreferredSize(btnSize);
        clearButton.setPreferredSize(btnSize);

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // 🔗 Actions (same logic preserved)
        saveButton.addActionListener(e -> savePatient());
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private void savePatient() {
        try {
            int pid = Integer.parseInt(patientIdField.getText().trim());
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String gender = genderField.getText().trim();
            String blood = bloodField.getText().trim();
            String address = addressField.getText().trim();
            String phone = phoneField.getText().trim();
            String emergency = emergencyField.getText().trim();

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO patients (patientid, name, age, gender, blood_group, address, phone_number, emrgncy_number) VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, pid);
                pst.setString(2, name);
                pst.setInt(3, age);
                pst.setString(4, gender);
                pst.setString(5, blood);
                pst.setString(6, address);
                pst.setString(7, phone);
                pst.setString(8, emergency);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Patient added successfully!");
                clearFields();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void clearFields() {
        patientIdField.setText("");
        nameField.setText("");
        ageField.setText("");
        genderField.setText("");
        bloodField.setText("");
        addressField.setText("");
        phoneField.setText("");
        emergencyField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddPatientWindow::new);
    }
}
