/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zhelp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author luuxu
 */
public class MaHoa {
    public static String encrypt_md5(String input){
        try{
            //tao doi tuong su dung tt md5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //chuyen chuoi dau vao thanh mang byte
            byte[] passwordEncrypt = md.digest(input.getBytes(StandardCharsets.UTF_8));
            //xay dung chuoi kq dang hexa(16)
            StringBuilder sb = new StringBuilder();
            //chuyen tung byte trong mang thanh chuoi hexa(2 ki tu)
            for(byte b : passwordEncrypt){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
