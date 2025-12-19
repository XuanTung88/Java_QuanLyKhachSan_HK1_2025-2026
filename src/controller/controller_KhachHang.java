/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import database.ConnectDB;
import model.KhachHang;
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
public class controller_KhachHang {
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> ds = new ArrayList<>();
        String sql = "SELECT * FROM Tbl_KhachHang";

        try (Connection sqlconn = ConnectDB.getConnect(); 
             PreparedStatement prest = sqlconn.prepareStatement(sql); 
             ResultSet rs = prest.executeQuery()) {

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt("MAKH"));
                kh.setTenKH(rs.getString("TENKH"));
                kh.setSoDT(rs.getString("SODT"));
                kh.setCccd(rs.getString("CCCD"));
                kh.setEmail(rs.getString("EMAIL"));
                kh.setDiaChi(rs.getString("DIACHI"));
                ds.add(kh);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return ds;
    }
    //add khach hang
    public boolean addKhachHang(KhachHang kh){
        String sql = "INSERT INTO Tbl_KhachHang (TENKH, SODT, CCCD, EMAIL, DIACHI) "
                + "VALUES (?, ?, ?, ?, ?)";
        try(Connection sqlconn = ConnectDB.getConnect();
            PreparedStatement prest = sqlconn.prepareStatement(sql)) {
            
            prest.setString(1, kh.getTenKH());
            prest.setString(2, kh.getSoDT());
            prest.setString(3, kh.getCccd());
            prest.setString(4, kh.getEmail());
            prest.setString(5, kh.getDiaChi());
            int row=prest.executeUpdate();
            return row>0;
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    //sua khachhang
    public boolean updateKhachHang(KhachHang kh){
        String sql = "UPDATE Tbl_KhachHang "
                + "SET TENKH = ?, SODT = ?, CCCD = ?, EMAIL = ?, DIACHI = ? "
                + "WHERE MAKH = ?";
        try(Connection sqlconn=ConnectDB.getConnect();
            PreparedStatement prest=sqlconn.prepareStatement(sql)){
            prest.setString(1, kh.getTenKH());
            prest.setString(2, kh.getSoDT());
            prest.setString(3, kh.getCccd());
            prest.setString(4, kh.getEmail());
            prest.setString(5, kh.getDiaChi());
            prest.setInt(6, kh.getMaKH());
            int row=prest.executeUpdate();
            return row>0;
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
        
    }
    
    //xoa kahchhang
    public boolean deleteKhachHang(int makh){
        String sql = "DELETE FROM Tbl_KhachHang WHERE MAKH = ?";
        try(Connection sqlconn=ConnectDB.getConnect();
            PreparedStatement prest=sqlconn.prepareStatement(sql)){
            
            prest.setInt(1, makh);
            int row=prest.executeUpdate();
            return row>0;
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    //tim kh theo cccd
    public KhachHang getKhachHangByCCCD(String cccd){
        String sql = "SELECT * FROM Tbl_KhachHang WHERE CCCD = ?";
        KhachHang kh = null;
        try(Connection sqlconn=ConnectDB.getConnect();
            PreparedStatement prest=sqlconn.prepareStatement(sql)){
            
            prest.setString(1, cccd);
            ResultSet rs=prest.executeQuery();
            if(rs.next()){
                kh = new KhachHang();
                kh.setMaKH(rs.getInt("MAKH"));
                kh.setTenKH(rs.getString("TENKH"));
                kh.setSoDT(rs.getString("SODT"));
                kh.setCccd(rs.getString("CCCD"));
                kh.setEmail(rs.getString("EMAIL"));
                kh.setDiaChi(rs.getString("DIACHI"));
            }
            //rs.close();
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return kh;
    }
    
    
     
     
     
     
     
     
     
     
     
     
     
     
}
