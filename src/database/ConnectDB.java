/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author luuxu
 */
public class ConnectDB {
     public static Connection getConnect() {
        Connection sqlconn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=QuanLyKhachSan;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;"
                    + "user=sa;"
                    + "password=0929959586;";
            sqlconn = DriverManager.getConnection(url);
            return sqlconn;  
            //System.out.println("Ket noi thanh cong !");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e.getMessage());
        }
        return null;
    }
    
}
