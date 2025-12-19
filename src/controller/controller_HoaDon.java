/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.HoaDon;
import database.ConnectDB;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author luuxu
 */
public class controller_HoaDon {
     // Lấy tất cả hóa đơn
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> ds = new ArrayList<>();
        String sql = "SELECT hd.*, kh.TENKH FROM Tbl_HoaDon hd " +
                    "INNER JOIN Tbl_KhachHang kh ON hd.MAKH = kh.MAKH " +
                    "ORDER BY hd.MAHOADON DESC";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql);
             ResultSet rs = prest.executeQuery()) {
            
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt("MAHOADON"));
                hd.setMaKH(rs.getInt("MAKH"));
                hd.setThanhToan(rs.getInt("THANHTOAN"));
                hd.setTrangThai(rs.getInt("TRANGTHAI"));
                hd.setNgayLapHoaDon(rs.getDate("NGAYLAPHOADON"));
                hd.setGhiChu(rs.getString("GHICHU"));
                hd.setTenKH(rs.getString("TENKH"));
                ds.add(hd);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return ds;
    }
    
    // Thêm hóa đơn
    public int addHoaDon(HoaDon hd) {
        String sql = "INSERT INTO Tbl_HoaDon (MAKH, THANHTOAN, TRANGTHAI, NGAYLAPHOADON, GHICHU) " +
                    "OUTPUT INSERTED.MAHOADON VALUES (?, ?, ?, ?, ?)";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, hd.getMaKH());
            prest.setInt(2, hd.getThanhToan());
            prest.setInt(3, hd.getTrangThai());
            prest.setDate(4, new java.sql.Date(hd.getNgayLapHoaDon().getTime()));
            prest.setString(5, hd.getGhiChu());
            
            ResultSet rs = prest.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Trả về MAHOADON vừa tạo
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return -1;
    }
    
    // Sửa hóa đơn
    public boolean updateHoaDon(HoaDon hd) {
        String sql = "UPDATE Tbl_HoaDon SET MAKH = ?, THANHTOAN = ?, TRANGTHAI = ?, " +
                    "NGAYLAPHOADON = ?, GHICHU = ? WHERE MAHOADON = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, hd.getMaKH());
            prest.setInt(2, hd.getThanhToan());
            prest.setInt(3, hd.getTrangThai());
            prest.setDate(4, new java.sql.Date(hd.getNgayLapHoaDon().getTime()));
            prest.setString(5, hd.getGhiChu());
            prest.setInt(6, hd.getMaHoaDon());
            
            int row = prest.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Xóa hóa đơn
    public boolean deleteHoaDon(int maHoaDon) {
        String sql = "DELETE FROM Tbl_HoaDon WHERE MAHOADON = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maHoaDon);
            int row = prest.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Lấy hóa đơn theo mã
    public HoaDon getHoaDonByMa(int maHoaDon) {
        String sql = "SELECT hd.*, kh.TENKH FROM Tbl_HoaDon hd " +
                    "INNER JOIN Tbl_KhachHang kh ON hd.MAKH = kh.MAKH " +
                    "WHERE hd.MAHOADON = ?";
        HoaDon hd = null;
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maHoaDon);
            ResultSet rs = prest.executeQuery();
            
            if (rs.next()) {
                hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt("MAHOADON"));
                hd.setMaKH(rs.getInt("MAKH"));
                hd.setThanhToan(rs.getInt("THANHTOAN"));
                hd.setTrangThai(rs.getInt("TRANGTHAI"));
                hd.setNgayLapHoaDon(rs.getDate("NGAYLAPHOADON"));
                hd.setGhiChu(rs.getString("GHICHU"));
                hd.setTenKH(rs.getString("TENKH"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return hd;
    }
    
    // Cập nhật trạng thái thanh toán
    public boolean updateTrangThaiHoaDon(int maHoaDon, int trangThai) {
        String sql = "UPDATE Tbl_HoaDon SET TRANGTHAI = ? WHERE MAHOADON = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, trangThai);
            prest.setInt(2, maHoaDon);
            
            int row = prest.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Tính tổng doanh thu theo tháng
    public int getTongDoanhThuThang(int thang, int nam) {
        String sql = "SELECT ISNULL(SUM(THANHTOAN), 0) FROM Tbl_HoaDon " +
                    "WHERE MONTH(NGAYLAPHOADON) = ? AND YEAR(NGAYLAPHOADON) = ? " +
                    "AND TRANGTHAI = 1"; // Đã thanh toán
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, thang);
            prest.setInt(2, nam);
            ResultSet rs = prest.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return 0;
    }
    
    
}
