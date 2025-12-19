/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import database.ConnectDB;
import model.Phong;
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
public class controller_Phong {
    //lay tat ca phong
    public List<Phong> getAllPhong(){
        List<Phong> ds = new ArrayList<>();
        String sql = "SELECT * FROM Tbl_Phong";
        try(Connection sqlconn=ConnectDB.getConnect();
            PreparedStatement prest=sqlconn.prepareStatement(sql);
            ResultSet rs=prest.executeQuery()) {
            
            while(rs.next()){
                Phong phong = new Phong();
                phong.setMaPhong(rs.getInt("MAPHONG"));
                phong.setTenPhong(rs.getString("TENPHONG"));
                phong.setGiaPhong(rs.getInt("GIAPHONG"));
                ds.add(phong);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error : "+e.getMessage());
        }
        return ds;
    }
       
    //lay tat ca cac phong trong theo dk ngay thang nhap
    public List<Phong> getPhongTrong(){
        List<Phong> ds = new ArrayList<>();
        String sql = "SELECT p.* FROM Tbl_Phong p "
                + "WHERE p.MAPHONG NOT IN ("
                + "SELECT dp.MAPHONG FROM Tbl_DatPhong dp "
                + "WHERE GETDATE() BETWEEN dp.NGAYNHANPHONG AND dp.NGAYTRAPHONG)";
        try(Connection sqlconn = ConnectDB.getConnect();
            PreparedStatement prest = sqlconn.prepareStatement(sql);
            ResultSet rs = prest.executeQuery()) {
            
            while (rs.next()) {
                Phong phong = new Phong();
                phong.setMaPhong(rs.getInt("MAPHONG"));
                phong.setTenPhong(rs.getString("TENPHONG"));
                phong.setGiaPhong(rs.getInt("GIAPHONG"));
                ds.add(phong);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return ds;
    }
            
    //them phong
    public boolean addPhong(Phong phong){
        String sql = "INSERT INTO Tbl_Phong (TENPHONG, GIAPHONG) VALUES (?, ?)";
        try(Connection sqlconn=ConnectDB.getConnect();
            PreparedStatement prest=sqlconn.prepareStatement(sql))  {
           
            prest.setString(1, phong.getTenPhong());
            prest.setInt(2, phong.getGiaPhong());
            int row=prest.executeUpdate();
            return row>0;
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    //sua phong
    public boolean updatePhong(Phong phong){
        String sql = "UPDATE Tbl_Phong SET TENPHONG = ?, GIAPHONG = ? WHERE MAPHONG = ?";
        try (Connection sqlconn = ConnectDB.getConnect();
             PreparedStatement prest=sqlconn.prepareStatement(sql)) {
            
            prest.setString(1, phong.getTenPhong());
            prest.setInt(2, phong.getGiaPhong());
            prest.setInt(3, phong.getMaPhong());
            int row=prest.executeUpdate();
            return row>0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    //delete phong
    public boolean deletePhong(int maphong){
        String sql = "DELETE FROM Tbl_Phong WHERE MAPHONG = ?";
        try(Connection sqlconn=ConnectDB.getConnect();
            PreparedStatement prest=sqlconn.prepareStatement(sql)){
            
            prest.setInt(1, maphong);
            int row=prest.executeUpdate();
            return row>0;
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return false;
    }
    
    public Phong getPhong(int mp){
        String sql = "SELECT * FROM Tbl_Phong WHERE MAPHONG = ?";
        try(Connection sqlconn=ConnectDB.getConnect();
            PreparedStatement prest=sqlconn.prepareStatement(sql)){
            
            prest.setInt(1, mp);
            ResultSet rs=prest.executeQuery();
            if(rs.next()) 
            {
                Phong phong = new Phong();
                phong.setMaPhong(rs.getInt("MAPHONG"));
                phong.setTenPhong(rs.getString("TENPHONG"));
                phong.setGiaPhong(rs.getInt("GIAPHONG"));
                return phong;
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return null;
    }
            
            
            
            
            
            
}
