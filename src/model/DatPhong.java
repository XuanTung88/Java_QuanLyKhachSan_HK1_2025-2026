/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author luuxu
 */
public class DatPhong {
    private int maHoaDon;
    private int maPhong;
    private Date ngayNhanPhong;
    private Date ngayTraPhong;
    private int soNgayLuuTru;
    private int giaPhongHT;
    private int tienPhongHT;
    private String tenPhong; // Thêm để hiển thị
    
    public DatPhong() {}

    public DatPhong(int maHoaDon, int maPhong, Date ngayNhanPhong, Date ngayTraPhong, int soNgayLuuTru, int giaPhongHT, int tienPhongHT, String tenPhong) {
        this.maHoaDon = maHoaDon;
        this.maPhong = maPhong;
        this.ngayNhanPhong = ngayNhanPhong;
        this.ngayTraPhong = ngayTraPhong;
        this.soNgayLuuTru = soNgayLuuTru;
        this.giaPhongHT = giaPhongHT;
        this.tienPhongHT = tienPhongHT;
        this.tenPhong = tenPhong;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public Date getNgayNhanPhong() {
        return ngayNhanPhong;
    }

    public Date getNgayTraPhong() {
        return ngayTraPhong;
    }

    public int getSoNgayLuuTru() {
        return soNgayLuuTru;
    }

    public int getGiaPhongHT() {
        return giaPhongHT;
    }

    public int getTienPhongHT() {
        return tienPhongHT;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public void setNgayNhanPhong(Date ngayNhanPhong) {
        this.ngayNhanPhong = ngayNhanPhong;
    }

    public void setNgayTraPhong(Date ngayTraPhong) {
        this.ngayTraPhong = ngayTraPhong;
    }

    public void setSoNgayLuuTru(int soNgayLuuTru) {
        this.soNgayLuuTru = soNgayLuuTru;
    }

    public void setGiaPhongHT(int giaPhongHT) {
        this.giaPhongHT = giaPhongHT;
    }

    public void setTienPhongHT(int tienPhongHT) {
        this.tienPhongHT = tienPhongHT;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }
    
    
    
}
