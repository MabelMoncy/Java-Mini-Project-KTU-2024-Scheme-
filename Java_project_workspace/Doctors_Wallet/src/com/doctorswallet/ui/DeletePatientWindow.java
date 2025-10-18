package com.doctorswallet.ui;

import com.doctorswallet.dao.PatientDAO;

import javax.swing.*;
import java.awt.*;

public class DeletePatientWindow extends JFrame {

    private JTextField searchField;
    private JComboBox<String> searchTypeBox;
    private JButton deleteButton;

    public DeletePatientWindow() {
        setTitle("Delete Patient");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dropdown to select search type (by ID or Name)
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Search By:"), gbc);

        searchTypeBox = new JComboBox<>(new String[]{"Patient ID", "Name"});
        gbc.gridx = 1;
        add(searchTypeBox, gbc);

        // Input field
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Enter Value:"), gbc);

        searchField = new JTextField();
        gbc.gridx = 1;
        add(searchField, gbc);

        // Delete button
        deleteButton = new JButton("Delete Patient");
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(deleteButton, gbc);

        deleteButton.addActionListener(e -> handleDelete());

        setVisible(true);
    }

    private void handleDelete() {
        String searchType = (String) searchTypeBox.getSelectedItem();
        String input = searchField.getText().trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a value to search.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean deleted = false;
        if (searchType.equals("Patient ID")) {
            try {
                int id = Integer.parseInt(input);
                deleted = PatientDAO.deletePatientById(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (searchType.equals("Name")) {
            deleted = PatientDAO.deletePatientByName(input);
        }

        if (deleted) {
            JOptionPane.showMessageDialog(this, "Patient record deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No matching record found.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
