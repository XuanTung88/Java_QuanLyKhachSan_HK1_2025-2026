/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author luuxu
 */
public class Phong {
    private int maPhong;
    private String tenPhong;
    private int giaPhong;
    
    public Phong() {}
    
    public Phong(int maPhong, String tenPhong, int giaPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.giaPhong = giaPhong;
    }

    /**
     * @return the maPhong
     */
    public int getMaPhong() {
        return maPhong;
    }

    /**
     * @param maPhong the maPhong to set
     */
    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    /**
     * @return the tenPhong
     */
    public String getTenPhong() {
        return tenPhong;
    }

    /**
     * @param tenPhong the tenPhong to set
     */
    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    /**
     * @return the giaPhong
     */
    public int getGiaPhong() {
        return giaPhong;
    }

    /**
     * @param giaPhong the giaPhong to set
     */
    public void setGiaPhong(int giaPhong) {
        this.giaPhong = giaPhong;
    }

    /**
     * @return the trangThai
     */
    
     @Override
    public String toString() {
        return tenPhong + " - " + giaPhong + " VNĐ";
    }
    
    
}
