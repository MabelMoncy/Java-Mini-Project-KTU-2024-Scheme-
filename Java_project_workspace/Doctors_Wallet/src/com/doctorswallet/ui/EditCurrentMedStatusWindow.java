package com.doctorswallet.ui;

import com.doctorswallet.dao.PatientDAO;

import javax.swing.*;
import java.awt.*;

public class EditCurrentMedStatusWindow extends JFrame {

    private JTextField patientIdField, illnessField;
    private JTextArea descriptionArea;
    private JButton updateButton, clearButton;

    public EditCurrentMedStatusWindow() {
        setTitle("Edit Current Medical Status");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 🩺 Main Panel with Padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        mainPanel.setBackground(Color.WHITE);

        // 🧱 Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // 🔹 Labels
        JLabel patientIdLabel = new JLabel("Patient ID:");
        JLabel illnessLabel = new JLabel("Diagnosed Illness:");
        JLabel descLabel = new JLabel("Description:");

        // 🔹 Input fields (compact size)
        patientIdField = new JTextField();
        illnessField = new JTextField();
        Dimension fieldSize = new Dimension(200, 20);
        patientIdField.setPreferredSize(fieldSize);
        illnessField.setPreferredSize(fieldSize);

        // 🔹 Description area
        descriptionArea = new JTextArea(4, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setPreferredSize(new Dimension(200, 60));

        // 🧩 Add components row-wise
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(patientIdLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(patientIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(illnessLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(illnessField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(descLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(descScroll, gbc);

        // Add form to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // 🔘 Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);

        updateButton = new JButton("Update");
        clearButton = new JButton("Clear");
        Dimension btnSize = new Dimension(100, 30);
        updateButton.setPreferredSize(btnSize);
        clearButton.setPreferredSize(btnSize);

        buttonPanel.add(updateButton);
        buttonPanel.add(clearButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);

        // 🧠 Button actions
        updateButton.addActionListener(e -> updateStatus());
        clearButton.addActionListener(e -> clearFields());
    }

    private void updateStatus() {
        try {
            int patientId = Integer.parseInt(patientIdField.getText().trim());
            String illness = illnessField.getText().trim();
            String desc = descriptionArea.getText().trim();

            if (illness.isEmpty() || desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            PatientDAO.updateCurrentMedicalStatus(patientId, illness, desc);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Patient ID must be a valid number!");
        }
    }

    private void clearFields() {
        patientIdField.setText("");
        illnessField.setText("");
        descriptionArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EditCurrentMedStatusWindow::new);
    }
}
