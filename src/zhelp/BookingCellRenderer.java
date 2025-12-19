/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zhelp;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.DatPhong;
import model.Phong;

/**
 *
 * @author Dell
 */
public class BookingCellRenderer extends DefaultTableCellRenderer {

    private List<DatPhong> listDP;
    private List<Phong> listPhong;
    private Date startOfWeek;

    public BookingCellRenderer(List<DatPhong> listDP, List<Phong> listPhong, Date startOfWeek) {
        this.listDP=listDP;
        this.listPhong=listPhong;
        this.startOfWeek=removeTime(startOfWeek);   // bỏ phần giờ
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int col) {
        Component cell=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        // reset màu mặc định
        if (!isSelected) cell.setBackground(Color.WHITE);
        // chỉ xử lý cột ngày
        if (col>=1){
            // tính ngày của cột
            Calendar cal=Calendar.getInstance();
            cal.setTime(startOfWeek);
            cal.add(Calendar.DAY_OF_YEAR,col-1);
            Date currentDay=removeTime(cal.getTime());
            // lấy mã phòng ở cột đầu tiên
            Object roomObj=table.getValueAt(row, 0);
            if (roomObj != null) {
                int maPhong=Integer.parseInt(roomObj.toString());
                // duyệt danh sách đặt phòng
                for (DatPhong dp : listDP) {
                    if (dp.getMaPhong()==maPhong){
                        Date ngayDen=removeTime(dp.getNgayNhanPhong());
                        Date ngayDi=removeTime(dp.getNgayTraPhong());
                        //System.out.println(ngayDen);
                        //System.out.println(ngayDi);
                        if ((currentDay.after(ngayDen)&&!currentDay.after(ngayDi))){
                                cell.setBackground(Color.BLUE);
                                setText("HD:"+dp.getMaHoaDon());
                                setForeground(Color.white);
                        }
                    }
                }
            }
        }
        return cell;
    }
    // Hàm bỏ phần giờ để so sánh chuẩn theo ngày
    private Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
