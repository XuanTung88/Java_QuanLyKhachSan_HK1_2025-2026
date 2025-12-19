/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.TaiKhoan;
import database.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import zhelp.MaHoa;
/**
 *
 * @author luuxu
 */
public class controller_TaiKhoan {
    //da dc cap tai khoan
    public TaiKhoan login_NV(String user,String pass){
        String sql="SELECT * FROM Tbl_TaiKhoan WHERE TAIKHOAN=? AND MATKHAU=?";
        try(Connection sqlconn=ConnectDB.getConnect()){
            PreparedStatement prest=sqlconn.prepareStatement(sql);
            prest.setString(1, user );
            prest.setString(2, pass);
            ResultSet rs=prest.executeQuery();
            if(rs.next()){
                String taikhoan = rs.getString("TAIKHOAN");
                String matkhau = rs.getString("MATKHAU");
                int quyen = rs.getInt("QUYEN");
                TaiKhoan tk = new TaiKhoan(taikhoan, matkhau, quyen);
                return tk;
            }
            rs.close();
            prest.close();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error : "+e.getMessage());
        }
        return null;
    }
    
    //login KH 
    public TaiKhoan login_KH(String user , String pass){
        String sql = "SELECT * FROM Tbl_TaiKhoan WHERE TAIKHOAN=? AND MATKHAU=? AND QUYEN=?";
        try(Connection sqlconn=ConnectDB.getConnect()){
            PreparedStatement prest=sqlconn.prepareStatement(sql);
            prest.setString(1, user );
            prest.setString(2, pass);
            prest.setString(3, "1");
            ResultSet rs=prest.executeQuery();
            if(rs.next()){
                String taikhoan = rs.getString("TAIKHOAN");
                String matkhau = rs.getString("MATKHAU");
                int quyen = rs.getInt("QUYEN");
                TaiKhoan tk = new TaiKhoan(taikhoan, matkhau, quyen);
                return tk;
            }
            rs.close();
            prest.close();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error : "+e.getMessage());
        }
        return null;
    }
    
    //KH đã tồn tại tk
    public boolean is_Exist(String user){
        String sql = "SELECT * FROM Tbl_TaiKhoan WHERE TAIKHOAN=?";
        try(Connection sqlconn=ConnectDB.getConnect()){
            PreparedStatement prest=sqlconn.prepareStatement(sql);
            prest.setString(1, user );
            ResultSet rs=prest.executeQuery();
            if(rs.next()){
                return true;
            }
            rs.close();
            prest.close();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error : "+e.getMessage());
        }
        return false;
    }
    
    //dang ký KH
    public boolean register_KH(String user,String pass){
        String sql = "INSERT INTO Tbl_TaiKhoan(TAIKHOAN, MATKHAU, QUYEN) VALUES(?,?,?)";
        try(Connection sqlconn=ConnectDB.getConnect()){
            PreparedStatement prest=sqlconn.prepareStatement(sql);
            prest.setString(1, user);
            prest.setString(2, pass);
            prest.setString(3, "1");
            int row=prest.executeUpdate();
            return row>0;
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error : "+e.getMessage());
        }
        return false;
    }
    
    
    
    
    
    
    
}
