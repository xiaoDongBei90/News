package com.lxw.news.util;

import com.lxw.news.Constant;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * author  LiXiaoWei
 * date  2018/2/1.
 * desc:
 */

public class ToutiaoUtil {
    public static Map<String, String> getAsCp() {
        int t = (int) (System.currentTimeMillis() / 1000);
        String i = Integer.toHexString(t).toUpperCase();
        String e = getMD5(String.valueOf(t)).toUpperCase();
        String s = e.substring(0, 5);
        String o = e.substring(e.length() - 5, e.length());
        String n = "";
        for (int j = 0; j < 5; j++) {
            n += s.substring(j, j + 1) + i.substring(j, j + 1);
        }

        String l = "";
        for (int j = 0; j < 5; j++) {
            l += i.substring(j + 3, j + 3 + 1) + o.substring(j, j + 1);
        }
        String as = "A1" + n + i.substring(i.length() - 3, i.length());
        String cp = i.substring(0, 3) + 1 + "E1";
        Map<String, String> map = new HashMap<>();
        map.put(Constant.AS, as);
        map.put(Constant.CP, cp);
        return map;
    }

    /**
     * 对字符串 MD5 加密
     */
    public static String getMD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
