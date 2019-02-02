package com.kemp.kemplibrary.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;

/**
 * Created by wangkp on 2016/12/21.
 */

public class ToolUtils {

    public static String getPhoneImei(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_PHONE_STATE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
        }
        String phoneImei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        return TextUtils.isEmpty(phoneImei) ? "000000000000000" : phoneImei;
    }

    //判断字符串是否全部重复
    public static boolean isRepeatChars(String str) {
        if (TextUtils.isEmpty(str) || str.length() == 1) {
            return false;
        }
        String regex = str.substring(0, 1) + "{" + str.length() + "}";
        return str.matches(regex);
    }

    /**
     * 获取手机唯一码
     */
    public static String getUniqueCode(Context context) {
        String imeiStr = getPhoneImei(context);
        if (imeiStr == null) {
            return null;
        }
        if (isRepeatChars(imeiStr)) {
            String uuid = UUID.randomUUID().toString();
            //16位加密，从第9位到25位
            return MD5(uuid).substring(8, 24);
        } else {
            return imeiStr;
        }
    }

    public static String MD5(String strs) {

        String str = strs.toUpperCase();
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }

    public static boolean isEmpty(List<?> list) {
        return null == list || list.size() == 0;
    }
}
