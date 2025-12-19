/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zhelp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author luuxu
 */
public class Support {
     // Format tiền tệ Việt Nam
    public static String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }
    
    // Format ngày tháng
    public static String formatDate(java.util.Date date) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    
    // Clear table
    public static void clearTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }
    
    // Hiển thị thông báo
    public static void showMessage(java.awt.Component parent, String message) {
        javax.swing.JOptionPane.showMessageDialog(parent, message);
    }
    
    // Xác nhận
    public static boolean confirm(java.awt.Component parent, String message) {
        int result = javax.swing.JOptionPane.showConfirmDialog(parent, message, "Xác nhận", 
                javax.swing.JOptionPane.YES_NO_OPTION);
        return result == javax.swing.JOptionPane.YES_OPTION;
    }
}
