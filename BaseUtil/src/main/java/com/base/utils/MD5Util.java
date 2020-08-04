package com.base.utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;

/**
 * @author xiongyang
 * @date 2020/3/2 11:33
 * @Description:
 */
public class MD5Util {

    private static final Logger log = Logger.getLogger(MD5Util.class);

    public MD5Util() {
    }

    public static String encode(String value) {
        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] bs = value.getBytes();
            byte[] mb = md.digest(bs);

            for(int i = 0; i < mb.length; ++i) {
                int v = mb[i] & 255;
                if (v < 16) {
                    sb.append("0");
                }

                sb.append(Integer.toHexString(v));
            }
        } catch (Exception var7) {
            log.error(var7.toString(), var7);
        }

        return sb.toString();
    }
}
