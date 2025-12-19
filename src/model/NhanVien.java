/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author luuxu
 */
public class NhanVien {
    private String manv;
    private String tennv;
    private String sodt;
    private String email;
    private String diachi;

    public NhanVien(String manv, String tennv, String sodt, String email, String diachi) {
        this.manv = manv;
        this.tennv = tennv;
        this.sodt = sodt;
        this.email = email;
        this.diachi = diachi;
    }

    public String getManv() {
        return manv;
    }

    public String getTennv() {
        return tennv;
    }

    public String getSodt() {
        return sodt;
    }

    public String getEmail() {
        return email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    
    
}
