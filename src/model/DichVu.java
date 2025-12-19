/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author luuxu
 */
public class DichVu {
    private int maDichVu;
    private String tenDichVu;
    private int giaDichVu;
    
    public DichVu() {}
    
    public DichVu(int maDichVu, String tenDichVu, int giaDichVu) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.giaDichVu = giaDichVu;
    }

    /**
     * @return the maDichVu
     */
    public int getMaDichVu() {
        return maDichVu;
    }

    /**
     * @param maDichVu the maDichVu to set
     */
    public void setMaDichVu(int maDichVu) {
        this.maDichVu = maDichVu;
    }

    /**
     * @return the tenDichVu
     */
    public String getTenDichVu() {
        return tenDichVu;
    }

    /**
     * @param tenDichVu the tenDichVu to set
     */
    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    /**
     * @return the giaDichVu
     */
    public int getGiaDichVu() {
        return giaDichVu;
    }

    /**
     * @param giaDichVu the giaDichVu to set
     */
    public void setGiaDichVu(int giaDichVu) {
        this.giaDichVu = giaDichVu;
    }
    
    @Override
    public String toString() {
        return tenDichVu + " - " + giaDichVu + " VNĐ";
    }
}
