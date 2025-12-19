/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.DatPhong;
import database.ConnectDB;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Phong;
/**
 *
 * @author luuxu
 */
public class controller_DatPhong {
    controller_Phong ctrP= new controller_Phong();
    // Lấy tất cả đặt phòng
    public List<DatPhong> getAllDatPhong() {
        List<DatPhong> ds = new ArrayList<>();
        String sql = "SELECT dp.*, p.TENPHONG, kh.TENKH " +
                    "FROM Tbl_DatPhong dp " +
                    "INNER JOIN Tbl_Phong p ON dp.MAPHONG = p.MAPHONG " +
                    "INNER JOIN Tbl_HoaDon hd ON dp.MAHOADON = hd.MAHOADON " +
                    "INNER JOIN Tbl_KhachHang kh ON hd.MAKH = kh.MAKH " +
                    "ORDER BY dp.MAHOADON DESC";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql);
             ResultSet rs = prest.executeQuery()) {
            
            while (rs.next()) {
                DatPhong dp = new DatPhong();
                dp.setMaHoaDon(rs.getInt("MAHOADON"));
                dp.setMaPhong(rs.getInt("MAPHONG"));
                dp.setNgayNhanPhong(rs.getDate("NGAYNHANPHONG"));
                dp.setNgayTraPhong(rs.getDate("NGAYTRAPHONG"));
                dp.setSoNgayLuuTru(rs.getInt("SONGAYLUUTRU"));
                dp.setGiaPhongHT(rs.getInt("GIAPHONGHT"));
                dp.setTienPhongHT(rs.getInt("TIENPHONGHT"));
                dp.setTenPhong(rs.getString("TENPHONG"));
                ds.add(dp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return ds;
    }
    
    // Thêm đặt phòng
    public boolean addDatPhong(DatPhong dp) {
        String sql = "INSERT INTO Tbl_DatPhong (MAHOADON, MAPHONG, NGAYNHANPHONG, " +
                    "NGAYTRAPHONG, SONGAYLUUTRU, GIAPHONGHT, TIENPHONGHT) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, dp.getMaHoaDon());
            prest.setInt(2, dp.getMaPhong());
            prest.setDate(3, new java.sql.Date(dp.getNgayNhanPhong().getTime()));
            prest.setDate(4, new java.sql.Date(dp.getNgayTraPhong().getTime()));
            prest.setInt(5, dp.getSoNgayLuuTru());
            prest.setInt(6, dp.getGiaPhongHT());
            prest.setInt(7, dp.getTienPhongHT());
            
            int row = prest.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Sửa đặt phòng
    public boolean updateDatPhong(DatPhong dp) {
        String sql = "UPDATE Tbl_DatPhong SET NGAYNHANPHONG = ?, NGAYTRAPHONG = ?, " +
                    "SONGAYLUUTRU = ?, GIAPHONGHT = ?, TIENPHONGHT = ? " +
                    "WHERE MAHOADON = ? AND MAPHONG = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setDate(1, new java.sql.Date(dp.getNgayNhanPhong().getTime()));
            prest.setDate(2, new java.sql.Date(dp.getNgayTraPhong().getTime()));
            prest.setInt(3, dp.getSoNgayLuuTru());
            prest.setInt(4, dp.getGiaPhongHT());
            prest.setInt(5, dp.getTienPhongHT());
            prest.setInt(6, dp.getMaHoaDon());
            prest.setInt(7, dp.getMaPhong());
            
            int row = prest.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Xóa đặt phòng
    public boolean deleteDatPhong(int maHoaDon, int maPhong) {
        String sql = "DELETE FROM Tbl_DatPhong WHERE MAHOADON = ? AND MAPHONG = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maHoaDon);
            prest.setInt(2, maPhong);
            int row = prest.executeUpdate();
            return row > 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Lấy đặt phòng theo mã hóa đơn
    public List<DatPhong> getDatPhongByMaHoaDon(int maHoaDon) {
        List<DatPhong> ds = new ArrayList<>();
        String sql = "SELECT dp.*, p.TENPHONG FROM Tbl_DatPhong dp " +
                    "INNER JOIN Tbl_Phong p ON dp.MAPHONG = p.MAPHONG " +
                    "WHERE dp.MAHOADON = ?";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maHoaDon);
            ResultSet rs = prest.executeQuery();
            
            while (rs.next()) {
                DatPhong dp = new DatPhong();
                dp.setMaHoaDon(rs.getInt("MAHOADON"));
                dp.setMaPhong(rs.getInt("MAPHONG"));
                dp.setNgayNhanPhong(rs.getDate("NGAYNHANPHONG"));
                dp.setNgayTraPhong(rs.getDate("NGAYTRAPHONG"));
                dp.setSoNgayLuuTru(rs.getInt("SONGAYLUUTRU"));
                dp.setGiaPhongHT(rs.getInt("GIAPHONGHT"));
                dp.setTienPhongHT(rs.getInt("TIENPHONGHT"));
                dp.setTenPhong(rs.getString("TENPHONG"));
                ds.add(dp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return ds;
    }
    
    // Kiểm tra phòng có trống không trong khoảng thời gian
    public boolean isPhongTrong(int maPhong, Date ngayNhan, Date ngayTra) {
        String sql = "SELECT COUNT(*) FROM Tbl_DatPhong " +
                    "WHERE MAPHONG = ? AND " +
                    "((NGAYNHANPHONG BETWEEN ? AND ?) OR " +
                    "(NGAYTRAPHONG BETWEEN ? AND ?) OR " +
                    "(? BETWEEN NGAYNHANPHONG AND NGAYTRAPHONG) OR " +
                    "(? BETWEEN NGAYNHANPHONG AND NGAYTRAPHONG))";
        
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setInt(1, maPhong);
            prest.setDate(2, new java.sql.Date(ngayNhan.getTime()));
            prest.setDate(3, new java.sql.Date(ngayTra.getTime()));
            prest.setDate(4, new java.sql.Date(ngayNhan.getTime()));
            prest.setDate(5, new java.sql.Date(ngayTra.getTime()));
            prest.setDate(6, new java.sql.Date(ngayNhan.getTime()));
            prest.setDate(7, new java.sql.Date(ngayTra.getTime()));
            
            ResultSet rs = prest.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    // Tính tổng tiền phòng theo hóa đơn
    public int getTongTienPhongByHoaDon(int maHoaDon) {
        String sql = "SELECT ISNULL(SUM(TIENPHONGHT), 0) FROM Tbl_DatPhong WHERE MAHOADON = ?";
        
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
    
    public List<Phong> getPhongTrong(Date ngayDen,Date ngayDi){
        List<Phong> listPhong=new ArrayList<>();
        String sql ="select * from Tbl_Phong where MAPHONG NOT IN(select distinct MAPHONG from Tbl_DatPhong where NGAYTRAPHONG<=? AND NGAYNHANPHONG>=?)";
        try{
            Connection conn=ConnectDB.getConnect();
            PreparedStatement st=conn.prepareStatement(sql);
            st.setDate(1,ngayDen);
            st.setDate(2,ngayDi);
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                listPhong.add(new Phong(rs.getInt("MAPHONG"),rs.getString("TENPHONG"),rs.getInt("GIAPHONG")));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return listPhong;
    }
    
    
}
