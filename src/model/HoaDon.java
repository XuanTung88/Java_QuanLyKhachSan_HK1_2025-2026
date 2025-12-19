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
public class HoaDon {
    private int maHoaDon;
    private int maKH;
    private int thanhToan;
    private int trangThai;
    private Date ngayLapHoaDon;
    private String ghiChu;
    private String tenKH; // Thêm để hiển thị
    
    public HoaDon() {}

    public HoaDon(int maHoaDon, int maKH, int thanhToan, int trangThai, Date ngayLapHoaDon, String ghiChu, String tenKH) {
        this.maHoaDon = maHoaDon;
        this.maKH = maKH;
        this.thanhToan = thanhToan;
        this.trangThai = trangThai;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.ghiChu = ghiChu;
        this.tenKH = tenKH;
    }

    /**
     * @return the maHoaDon
     */
    public int getMaHoaDon() {
        return maHoaDon;
    }

    /**
     * @param maHoaDon the maHoaDon to set
     */
    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    /**
     * @return the maKH
     */
    public int getMaKH() {
        return maKH;
    }

    /**
     * @param maKH the maKH to set
     */
    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    /**
     * @return the thanhToan
     */
    public int getThanhToan() {
        return thanhToan;
    }

    /**
     * @param thanhToan the thanhToan to set
     */
    public void setThanhToan(int thanhToan) {
        this.thanhToan = thanhToan;
    }

    /**
     * @return the trangThai
     */
    public int getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the ngayLapHoaDon
     */
    public Date getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    /**
     * @param ngayLapHoaDon the ngayLapHoaDon to set
     */
    public void setNgayLapHoaDon(Date ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    /**
     * @return the ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param ghiChu the ghiChu to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    /**
     * @return the tenKH
     */
    public String getTenKH() {
        return tenKH;
    }

    /**
     * @param tenKH the tenKH to set
     */
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }
    
    
    
}
