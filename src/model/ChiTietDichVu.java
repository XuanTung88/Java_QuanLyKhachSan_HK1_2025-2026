/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author luuxu
 */
public class ChiTietDichVu {
     private int maHoaDon;
    private int maDichVu;
    private int giaDichVuHT;
    private String tenDichVu; // Thêm để hiển thị
    private int maKH; // Thêm để hiển thị
    
    public ChiTietDichVu() {}

    public ChiTietDichVu(int maHoaDon, int maDichVu, int giaDichVuHT, String tenDichVu, int maKH) {
        this.maHoaDon = maHoaDon;
        this.maDichVu = maDichVu;
        this.giaDichVuHT = giaDichVuHT;
        this.tenDichVu = tenDichVu;
        this.maKH = maKH;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public int getMaDichVu() {
        return maDichVu;
    }

    public int getGiaDichVuHT() {
        return giaDichVuHT;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    public void setGiaDichVuHT(int giaDichVuHT) {
        this.giaDichVuHT = giaDichVuHT;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }
    
    
    
    
    
    
}
