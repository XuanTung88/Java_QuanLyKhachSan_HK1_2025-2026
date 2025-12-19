/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author luuxu
 */
public class TaiKhoan {
    private String taikhoan;
    private String matkhau;
    private int quyen;
    public TaiKhoan(){};
    public TaiKhoan(String tk,String mk,int q){
        this.taikhoan=tk;
        this.matkhau=mk;
        this.quyen=q;
    }

    /**
     * @return the taikhoan
     */
    public String getTaikhoan() {
        return taikhoan;
    }

    /**
     * @return the matkhau
     */
    public String getMatkhau() {
        return matkhau;
    }

    /**
     * @return the quyen
     */
    public int getQuyen() {
        return quyen;
    }
    
}
