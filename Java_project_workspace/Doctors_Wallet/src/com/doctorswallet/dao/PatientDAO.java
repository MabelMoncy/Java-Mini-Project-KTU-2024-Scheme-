
package com.doctorswallet.dao;

import com.doctorswallet.model.Patient;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class PatientDAO {
	
	// ✅ Check if a patient exists by ID
	public static boolean patientExists(int patientId) {
	    String sql = "SELECT COUNT(*) FROM patients WHERE patientid = ?";
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, patientId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) return rs.getInt(1) > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	

    // ✅ Insert patient
    public static void insertPatient(Patient patient) {
        String sql = "INSERT INTO patients (patientid, name, age, gender, blood_group, address, phone_number, emrgncy_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patient.getPatientId());
            stmt.setString(2, patient.getName());
            stmt.setInt(3, patient.getAge());
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getBloodGroup());
            stmt.setString(6, patient.getAddress());
            stmt.setString(7, patient.getPhoneNumber());
            stmt.setString(8, patient.getEmergencyNumber());
            stmt.executeUpdate();
            System.out.println("Patient inserted successfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Unable to insert patients! Check the fileds are filledS");
        }
    }

    // 🧩 Helper: delete all dependent records for a given patientId
    private static void deleteChildTables(Connection conn, int patientId) throws SQLException {
        try (PreparedStatement ps1 = conn.prepareStatement("DELETE FROM medical_history WHERE patientid = ?");
             PreparedStatement ps2 = conn.prepareStatement("DELETE FROM currnt_med_sts WHERE patientid = ?");
             PreparedStatement ps3 = conn.prepareStatement("DELETE FROM medicines WHERE patientid = ?")) {

            ps1.setInt(1, patientId);
            ps1.executeUpdate();

            ps2.setInt(1, patientId);
            ps2.executeUpdate();

            ps3.setInt(1, patientId);
            ps3.executeUpdate();
        }
    }

    // ✅ Delete by Patient ID  (cascade in Java)
    public static boolean deletePatientById(int id) {
        boolean isDeleted = false;
        String sql = "DELETE FROM patients WHERE patientid = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);  // start transaction

            // delete child table data first
            deleteChildTables(conn, id);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rows = stmt.executeUpdate();
                isDeleted = rows > 0;
            }

            conn.commit();
            if (isDeleted)
                JOptionPane.showMessageDialog(null,"Patient ID " + id + " and all related records deleted!");
            else
            	 JOptionPane.showMessageDialog(null,"No patient found with ID " + id);

        } catch (SQLException e) {
        	 JOptionPane.showMessageDialog(null,"Unexpected Error Occurred!");
        }

        return isDeleted;
    }

    // ✅ Delete by Name  (cascade in Java)
    public static boolean deletePatientByName(String name) {
        boolean isDeleted = false;

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);  // start transaction

            // Find all IDs with that name (in case duplicates exist)
            try (PreparedStatement findStmt = conn.prepareStatement("SELECT patientid FROM patients WHERE name = ?")) {
                findStmt.setString(1, name);
                ResultSet rs = findStmt.executeQuery();

                while (rs.next()) {
                    int pid = rs.getInt("patientid");
                    deleteChildTables(conn, pid);
                }
            }

            try (PreparedStatement delStmt = conn.prepareStatement("DELETE FROM patients WHERE name = ?")) {
                delStmt.setString(1, name);
                int rows = delStmt.executeUpdate();
                isDeleted = rows > 0;
            }

            conn.commit();
            if (isDeleted)
            	 JOptionPane.showMessageDialog(null,"Patient(s) named '" + name + "' and related records deleted!");
            else
            	 JOptionPane.showMessageDialog(null,"No patient found with name '" + name + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }

    // ✅ View medical history
    public static DefaultTableModel getMedicalHistory(int patientId) {
        String[] cols = {"Patient ID", "Name", "Description"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        String sql = "SELECT * FROM medical_history WHERE patientid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = {
                		rs.getInt("patientid"),
                		rs.getString("name"),
                        rs.getString("description"),
                        };
                model.addRow(row);
            }
        } catch (SQLException e) {
        	 JOptionPane.showMessageDialog(null,"Patient doesn't exist!.Add new patient.");
        }
        return model;
    }

    // ✅ View current medical status
    public static DefaultTableModel getCurrentMedicalStatus(int patientId) {
        String[] cols = {"Patient ID", "Name","Diagonised Illness", "Description"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        String sql = "SELECT * FROM currnt_med_sts WHERE patientid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = {
                		rs.getInt("patientid"), rs.getString("name"),
                        rs.getString("diagonized_illness"), 
                        rs.getString("description"),};
                model.addRow(row);
            }
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null,"Patient doesn't exist!.Add new patient.No current medical status found!");
        }
        return model;
    }

    // ✅ View medicine details
    public static DefaultTableModel getMedicineDetails(int patientId) {
        String[] cols = {"Patient ID", "Name", "Age", "Medicine Used", "Description"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        String sql = "SELECT * FROM medicines WHERE patientid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] row = {
                		rs.getInt("patientid"),
                		rs.getString("name"),
                        rs.getString("age"),
                        rs.getString("medicine_used"),
                        rs.getString("med_description"),};
                model.addRow(row);
            }
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null,"Patient doesn't exist!.Add new patient.No medicine details found!");
        }
        return model;
    }
    
 // ✅ Update current medical status for a patient
    public static boolean updateCurrentMedicalStatus(int patientId, String illness, String description) {
        if (!patientExists(patientId)) {
            JOptionPane.showMessageDialog(null, "Patient doesn't exist! Please add the patient first.");
            return false;
        }

        String sql = "UPDATE currnt_med_sts SET diagonized_illness = ?, description = ? WHERE patientid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, illness);
            ps.setString(2, description);
            ps.setInt(3, patientId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "✅ Current medical status updated successfully!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No existing medical status found for this patient.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating medical status: " + e.getMessage());
        }
        return false;
    }

}





