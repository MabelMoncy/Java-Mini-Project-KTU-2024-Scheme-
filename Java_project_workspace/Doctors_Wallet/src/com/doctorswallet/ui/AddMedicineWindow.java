package com.doctorswallet.ui;

import com.doctorswallet.dao.MedicineDAO;
import com.doctorswallet.model.Medicine;
import javax.swing.*;
import java.awt.*;

public class AddMedicineWindow extends JFrame {

    private JTextField patientIdField, nameField, ageField, medicineField, descriptionField;
    private JButton saveButton, clearButton;

    public AddMedicineWindow() {
        setTitle("Add Medicine");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🧱 Main panel with margin
        JPanel contentPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // top, left, bottom, right margin

        // Fields
        patientIdField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        medicineField = new JTextField();
        descriptionField = new JTextField();

        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");

        // Add components
        contentPanel.add(new JLabel("Patient ID:")); contentPanel.add(patientIdField);
        contentPanel.add(new JLabel("Patient Name:")); contentPanel.add(nameField);
        contentPanel.add(new JLabel("Age:")); contentPanel.add(ageField);
        contentPanel.add(new JLabel("Medicine Used:")); contentPanel.add(medicineField);
        contentPanel.add(new JLabel("Description:")); contentPanel.add(descriptionField);
        contentPanel.add(saveButton); contentPanel.add(clearButton);

        // Add panel to frame
        add(contentPanel);

        // Button actions
        saveButton.addActionListener(e -> saveMedicine());
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    // ✅ Save medicine to database
    private void saveMedicine() {
        try {
            int patientId = Integer.parseInt(patientIdField.getText().trim());
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String medicineUsed = medicineField.getText().trim();
            String medDescription = descriptionField.getText().trim();

            // Validate empty fields
            if (name.isEmpty() || medicineUsed.isEmpty() || medDescription.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Medicine med = new Medicine(patientId, name, age, medicineUsed, medDescription);
            MedicineDAO.insertMedicine(med);
            clearFields();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Patient ID and Age must be valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // ✅ Clear input fields
    private void clearFields() {
        patientIdField.setText("");
        nameField.setText("");
        ageField.setText("");
        medicineField.setText("");
        descriptionField.setText("");
    }

    // Test window
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddMedicineWindow::new);
    }
}
