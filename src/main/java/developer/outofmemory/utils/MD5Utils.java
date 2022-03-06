package developer.outofmemory.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Utils {

    public static String getMD5(String string) {
        try {
            // 创建加密对象
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs = digest.digest(string.getBytes());
            String hexString = "";
            for (byte b : bs) {

                int temp = b & 255;

                if (temp < 16 && temp >= 0) {
                    hexString = hexString + "0" + Integer.toHexString(temp);
                } else {
                    hexString = hexString + Integer.toHexString(temp);
                }
            }
            return hexString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
