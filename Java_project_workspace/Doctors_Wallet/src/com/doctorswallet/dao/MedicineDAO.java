package com.doctorswallet.dao;

import com.doctorswallet.model.Medicine;
import javax.swing.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicineDAO {

    // Insert a new medicine record
    public static void insertMedicine(Medicine med) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO medicines (patientid, name, age, medicine_used, med_description) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, med.getPatientId());
        stmt.setString(2, med.getName());
        stmt.setInt(3, med.getAge());
        stmt.setString(4, med.getMedicineUsed());
        stmt.setString(5, med.getMedDescription());

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
 // ✅ Update existing medicine record
    public static boolean updateMedicine(int patientId, String medicineUsed, String description) {
        if (!PatientDAO.patientExists(patientId)) {
            JOptionPane.showMessageDialog(null, "Patient doesn't exist! Please add the patient first.");
            return false;
        }

        String sql = "UPDATE medicines SET medicine_used = ?, med_description = ? WHERE patientid = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, medicineUsed);
            ps.setString(2, description);
            ps.setInt(3, patientId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(null, "✅ Medicine record updated successfully!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No medicine record found for this patient.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating medicine record: " + e.getMessage());
        }
        return false;
    }

}
