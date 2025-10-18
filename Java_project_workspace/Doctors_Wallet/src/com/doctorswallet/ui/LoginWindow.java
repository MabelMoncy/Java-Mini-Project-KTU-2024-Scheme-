package com.doctorswallet.ui;

import com.doctorswallet.dao.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginWindow extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, showPasswordButton;

    public LoginWindow() {
        setTitle("Doctor Login - Doctor’s Wallet");
        setSize(800, 500); // 🟦 Enlarged window
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🎨 Colors
        Color primaryColor = new Color(36, 123, 160);
        Color accentColor = new Color(112, 193, 179);
        Color bgColor = new Color(240, 245, 250);

        // 🩺 Background Panel
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(bgColor);
        add(backgroundPanel, BorderLayout.CENTER);

        // 💳 Login Card (Bigger)
        JPanel card = new JPanel(new GridBagLayout());
        card.setPreferredSize(new Dimension(420, 360)); // 🔹 Wider and taller card
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1),
                BorderFactory.createEmptyBorder(25, 35, 25, 35)
        ));
        card.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        // 🏥 Title
        JLabel titleLabel = new JLabel("Doctor’s Wallet Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(primaryColor);
        card.add(titleLabel, gbc);

        // 👤 Username
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        card.add(userLabel, gbc);

        gbc.gridx = 1;
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(230, 30));
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        card.add(usernameField, gbc);

        // 🔒 Password
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        card.add(passLabel, gbc);

        gbc.gridx = 1;
        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBackground(Color.WHITE);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(230, 30));
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        showPasswordButton = new JButton("Show");
        showPasswordButton.setFocusPainted(false);
        showPasswordButton.setBackground(Color.WHITE);
        showPasswordButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        showPasswordButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(showPasswordButton, BorderLayout.EAST);
        card.add(passwordPanel, gbc);

        showPasswordButton.addActionListener(e -> {
            if (passwordField.getEchoChar() != '\u0000') {
                passwordField.setEchoChar('\u0000');
                showPasswordButton.setText("Hide");
            } else {
                passwordField.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                showPasswordButton.setText("Show");
            }
        });

        // 🔘 Login Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(140, 40));
        loginButton.setBackground(primaryColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        card.add(loginButton, gbc);

        // 🩺 Footer
        gbc.gridy++;
        JLabel footerLabel = new JLabel("Secure Access | Doctor’s Wallet © 2025", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        footerLabel.setForeground(new Color(140, 140, 140));
        card.add(footerLabel, gbc);

        backgroundPanel.add(card);

        // 🔗 Login Logic
        loginButton.addActionListener(e -> checkLogin());

        setVisible(true);
    }

    private void checkLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM doctors_auth WHERE username=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                new MainWindow();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid username or password. Please try again.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginWindow::new);
    }
}
