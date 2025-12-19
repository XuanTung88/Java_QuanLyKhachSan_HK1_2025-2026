/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zhelp;
import java.util.regex.Pattern;
/**
 *
 * @author luuxu
 */
public class Validation {
     // Kiểm tra email
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
    
    // Kiểm tra số điện thoại
    public static boolean isValidPhone(String phone) {
        String phoneRegex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        return Pattern.compile(phoneRegex).matcher(phone).matches();
    }
    
    // Kiểm tra CCCD
    public static boolean isValidCCCD(String cccd) {
        return cccd.matches("\\d{12}");
    }
    
    // Kiểm tra số
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
