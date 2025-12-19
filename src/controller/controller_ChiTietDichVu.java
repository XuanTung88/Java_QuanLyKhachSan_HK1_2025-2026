/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.ChiTietDichVu;
import database.ConnectDB;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author luuxu
 */
public class controller_ChiTietDichVu {
     // Lấy tất cả chi tiết dịch vụ
    public List<ChiTietDichVu> getAllChiTietDichVu() {
        List<ChiTietDichVu> ds = new ArrayList<>();
        String sql = "SELECT ctdv.*, dv.TENDICHVU, hd.MAKH " +
                    "FROM Tbl_ChiTietDichVu ctdv " +
                    "INNER JOIN Tbl_DichVu dv ON ctdv.MADICHVU = dv.MADICHVU " +
                    "INNER JOIN Tbl_HoaDon hd ON ctdv.MAHOADON = hd.MAHOADON " +
                    "ORDER BY ctdv.MAHOADON DESC";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql);
             ResultSet rs = prest.executeQuery()) {
            
            while (rs.next()) {
                ChiTietDichVu ctdv = new ChiTietDichVu();
                ctdv.setMaHoaDon(rs.getInt("MAHOADON"));
                ctdv.setMaDichVu(rs.getInt("MADICHVU"));
                ctdv.setGiaDichVuHT(rs.getInt("GIADICHVUHT"));
                ctdv.setTenDichVu(rs.getString("TENDICHVU"));
                ctdv.setMaKH(rs.getInt("MAKH"));
                ds.add(ctdv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return ds;
    }
    
    // Thêm chi tiết dịch vụ
    public boolean addChiTietDichVu(ChiTietDichVu ctdv) {
        String sql = "INSERT INTO Tbl_ChiTietDichVu (MAHOADON, MADICHVU, GIADICHVUHT) VALUES (?, ?, ?)";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, ctdv.getMaHoaDon());
            prest.setInt(2, ctdv.getMaDichVu());
            prest.setInt(3, ctdv.getGiaDichVuHT());
            
            int row = prest.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Sửa chi tiết dịch vụ
    public boolean updateChiTietDichVu(ChiTietDichVu ctdv) {
        String sql = "UPDATE Tbl_ChiTietDichVu SET GIADICHVUHT = ? " +
                    "WHERE MAHOADON = ? AND MADICHVU = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, ctdv.getGiaDichVuHT());
            prest.setInt(2, ctdv.getMaHoaDon());
            prest.setInt(3, ctdv.getMaDichVu());
            
            int row = prest.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Xóa chi tiết dịch vụ
    public boolean deleteChiTietDichVu(int maHoaDon, int maDichVu) {
        String sql = "DELETE FROM Tbl_ChiTietDichVu WHERE MAHOADON = ? AND MADICHVU = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maHoaDon);
            prest.setInt(2, maDichVu);
            int row = prest.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Lấy chi tiết dịch vụ theo mã hóa đơn
    public List<ChiTietDichVu> getChiTietDichVuByMaHoaDon(int maHoaDon) {
        List<ChiTietDichVu> ds = new ArrayList<>();
        String sql = "SELECT ctdv.*, dv.TENDICHVU FROM Tbl_ChiTietDichVu ctdv " +
                    "INNER JOIN Tbl_DichVu dv ON ctdv.MADICHVU = dv.MADICHVU " +
                    "WHERE ctdv.MAHOADON = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maHoaDon);
            ResultSet rs = prest.executeQuery();
            
            while (rs.next()) {
                ChiTietDichVu ctdv = new ChiTietDichVu();
                ctdv.setMaHoaDon(rs.getInt("MAHOADON"));
                ctdv.setMaDichVu(rs.getInt("MADICHVU"));
                ctdv.setGiaDichVuHT(rs.getInt("GIADICHVUHT"));
                ctdv.setTenDichVu(rs.getString("TENDICHVU"));
                ds.add(ctdv);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return ds;
    }
    
    // Tính tổng tiền dịch vụ theo hóa đơn
    public int getTongTienDichVuByHoaDon(int maHoaDon) {
        String sql = "SELECT ISNULL(SUM(GIADICHVUHT), 0) FROM Tbl_ChiTietDichVu WHERE MAHOADON = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maHoaDon);
            ResultSet rs = prest.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return 0;
    }
    
    // Kiểm tra dịch vụ đã tồn tại trong hóa đơn chưa
    public boolean isDichVuExistInHoaDon(int maHoaDon, int maDichVu) {
        String sql = "SELECT COUNT(*) FROM Tbl_ChiTietDichVu WHERE MAHOADON = ? AND MADICHVU = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maHoaDon);
            prest.setInt(2, maDichVu);
            ResultSet rs = prest.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    
    
    
}
