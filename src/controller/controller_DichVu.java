/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import database.ConnectDB;
import model.DichVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author luuxu
 */
public class controller_DichVu {
    //lay tca dich vu
     public List<DichVu> getAllDichVu() {
        List<DichVu> ds = new ArrayList<>();
        String sql = "SELECT * FROM Tbl_DichVu";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql);
             ResultSet rs = prest.executeQuery()) {
            
            while (rs.next()) {
                DichVu dv = new DichVu();
                dv.setMaDichVu(rs.getInt("MADICHVU"));
                dv.setTenDichVu(rs.getString("TENDICHVU"));
                dv.setGiaDichVu(rs.getInt("GIADICHVU"));
                ds.add(dv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return ds;
    }
    
     //them dich vu
    public boolean addDichVu(DichVu dv) {
        String sql = "INSERT INTO Tbl_DichVu (TENDICHVU, GIADICHVU) VALUES (?, ?)";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setString(1, dv.getTenDichVu());
            prest.setInt(2, dv.getGiaDichVu());
            
            int row=prest.executeUpdate();
            return row>0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    //sua dich vu
    public boolean updateDichVu(DichVu dv) {
        String sql = "UPDATE Tbl_DichVu SET TENDICHVU = ?, GIADICHVU = ? WHERE MADICHVU = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setString(1, dv.getTenDichVu());
            prest.setInt(2, dv.getGiaDichVu());
            prest.setInt(3, dv.getMaDichVu());
            
            int row = prest.executeUpdate();
            return row>0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    //soa dich vu
    public boolean deleteDichVu(int maDichVu) {
        String sql = "DELETE FROM Tbl_DichVu WHERE MADICHVU = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maDichVu);
            int row=prest.executeUpdate();
            return row>0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    
    
    
    
    
    
    
}
