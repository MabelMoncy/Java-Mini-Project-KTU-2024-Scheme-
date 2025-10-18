package com.doctorswallet.ui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel buttonPanel;
    private JTextArea chatArea;
    private JTextField chatInput;
    private JButton sendButton;

    private String chatbotState = "welcome";

    public MainWindow() {
        setTitle("Doctor's Wallet - Dashboard");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 🎨 Theme colors
        Color primaryColor = new Color(36, 123, 160);
        Color secondaryColor = new Color(112, 193, 179);
        Color backgroundColor = new Color(240, 245, 250);
        Color buttonColor = new Color(255, 255, 255);

        // 🏥 Header
        JLabel headerLabel = new JLabel("Welcome Doctor... Let's start another day of curing people!", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setForeground(primaryColor);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(headerLabel, BorderLayout.NORTH);

        // 💠 Button Panel (Dashboard)
        buttonPanel = new JPanel(new GridLayout(0, 4, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        buttonPanel.setBackground(backgroundColor);

        // Buttons
        JButton addPatientBtn = new JButton("Add Patient");
        JButton editPatientBtn = new JButton("Edit Patient");
        JButton searchPatientBtn = new JButton("Search Patient");
        JButton listAllBtn = new JButton("List All Patients");
        JButton addMedicineBtn = new JButton("Add Medicine");
        JButton addMedicalHistoryBtn = new JButton("Add Medical History");
        JButton addCurrentMedStatusBtn = new JButton("Add Current Medical Status");
        JButton deletePatientBtn = new JButton("Delete Patient");
        JButton viewMedstsBtn = new JButton("View Current Medical Status");
        JButton viewMedHistoryBtn = new JButton("View Medical History");
        JButton viewMedicinesBtn = new JButton("View Medicines");
        JButton editCurrentMedBtn = new JButton("Edit Current Medical Status");
        JButton editMedicineBtn = new JButton("Edit Medicine");

        // All buttons array
        JButton[] buttons = {
            addPatientBtn, editPatientBtn, searchPatientBtn, listAllBtn,
            addMedicineBtn, addMedicalHistoryBtn, addCurrentMedStatusBtn,
            deletePatientBtn, viewMedstsBtn, viewMedHistoryBtn,
            viewMedicinesBtn, editCurrentMedBtn, editMedicineBtn
        };

        // 🧱 Styling buttons
        Dimension buttonSize = new Dimension(230, 60);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        for (JButton btn : buttons) {
            btn.setPreferredSize(buttonSize);
            btn.setFont(buttonFont);
            btn.setBackground(buttonColor);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // 🧠 Chatbot Panel
        JPanel chatPanel = new JPanel(new BorderLayout(5, 5));
        chatPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(primaryColor, 1), "Assistant"));
        chatPanel.setBackground(Color.WHITE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setForeground(Color.DARK_GRAY);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chatPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBackground(Color.WHITE);
        chatInput = new JTextField();
        chatInput.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatInput.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        sendButton = new JButton("Send");
        sendButton.setBackground(primaryColor);
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setFocusPainted(false);
        sendButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        sendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        inputPanel.add(chatInput, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        chatPanel.add(inputPanel, BorderLayout.SOUTH);
        chatPanel.setPreferredSize(new Dimension(1000, 220));

        add(chatPanel, BorderLayout.SOUTH);

        // 🧾 Chatbot intro
        chatArea.append("AI: Hello Doctor! How can I assist you today?\n");
        chatArea.append("AI: Would you like to know how to use the features?\n");

        // 🎯 Button Actions
        addPatientBtn.addActionListener(e -> new AddPatientWindow());
        editPatientBtn.addActionListener(e -> new EditPatientWindow());
        searchPatientBtn.addActionListener(e -> new SearchPatientWindow());
        listAllBtn.addActionListener(e -> new ListAllPatientsWindow());
        addMedicineBtn.addActionListener(e -> new AddMedicineWindow());
        addMedicalHistoryBtn.addActionListener(e -> new AddMedicalHistoryWindow());
        addCurrentMedStatusBtn.addActionListener(e -> new AddCurrentMedicalStatusWindow());
        deletePatientBtn.addActionListener(e -> new DeletePatientWindow());
        viewMedstsBtn.addActionListener(e -> new ViewCurrentMedStatusWindow());
        viewMedHistoryBtn.addActionListener(e -> new ViewMedicalHistoryWindow());
        viewMedicinesBtn.addActionListener(e -> new ViewMedicineDetailsWindow());
        editCurrentMedBtn.addActionListener(e -> new EditCurrentMedStatusWindow());
        editMedicineBtn.addActionListener(e -> new EditMedicineWindow());

        // Chat actions
        sendButton.addActionListener(e -> handleChat());
        chatInput.addActionListener(e -> handleChat());

        setVisible(true);
    }

    // 🧠 Chatbot Logic (Unchanged, Cleaned Text)
    private void handleChat() {
        String input = chatInput.getText().trim().toLowerCase();
        if (input.isEmpty()) return;

        chatArea.append("Doctor: " + input + "\n");
        chatInput.setText("");

        switch (chatbotState) {
            case "welcome":
                if (input.equals("yes") || input.equals("y")) {
                    chatbotState = "help_menu";
                    chatArea.append("AI: Sure! Here are some options:\n"
                            + "1. Add Patient\n"
                            + "2. Edit Patient\n"
                            + "3. Search Patient\n"
                            + "4. List All Patients\n"
                            + "5. Add Medicine\n"
                            + "6. Add Current Medical Status\n"
                            + "7. View Medicines\n"
                            + "8. View Medical History\n"
                            + "9. View Current Medical Status\n"
                            + "10. Exit Help\n");
                } else {
                    chatArea.append("AI: Please type 'yes' to start.\n");
                }
                break;

            case "help_menu":
                switch (input) {
                    case "1": chatArea.append("AI: To add a patient, click 'Add Patient' and fill in all required details.\n"); break;
                    case "2": chatArea.append("AI: To edit, click 'Edit Patient' and update the info.\n"); break;
                    case "3": chatArea.append("AI: To search, use 'Search Patient' and enter ID or name.\n"); break;
                    case "4": chatArea.append("AI: 'List All Patients' shows every record.\n"); break;
                    case "5": chatArea.append("AI: Add medicines for existing patients with 'Add Medicine'.\n"); break;
                    case "6": chatArea.append("AI: Add current health info using 'Add Current Medical Status'.\n"); break;
                    case "7": chatArea.append("AI: View all medicines using 'View Medicines'.\n"); break;
                    case "8": chatArea.append("AI: View full medical history using 'View Medical History'.\n"); break;
                    case "9": chatArea.append("AI: See latest health data under 'View Current Medical Status'.\n"); break;
                    case "10":
                    case "exit":
                    case "quit":
                        chatArea.append("AI: Thank you, Doctor! Have a great day ahead.\n");
                        chatbotState = "welcome";
                        break;
                    default:
                        chatArea.append("AI: Please enter a valid option (1–10).\n");
                }
                if (!input.equals("10") && !input.equals("exit") && !input.equals("quit")) {
                    chatArea.append("AI: Need more help? (Type 1–10 or 'exit' to stop)\n");
                }
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
