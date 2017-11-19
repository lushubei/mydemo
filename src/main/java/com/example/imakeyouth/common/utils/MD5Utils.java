package com.example.imakeyouth.common.utils;

import com.example.imakeyouth.common.Constants;
import com.example.imakeyouth.exception.ApplicationException;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    public static final String MD5UserPwd(String password, String key) {

        byte[] e = null;
        MessageDigest mdInst = null;
        try {
            e = (password + key).getBytes("utf-8");
            mdInst = MessageDigest.getInstance("MD5");
        } catch (UnsupportedEncodingException e1) {
            throw new ApplicationException("不支持这种编码方式");
        } catch (NoSuchAlgorithmException e1) {
            throw new ApplicationException("不存在这种加密方式");
        }
        mdInst.update(e);
        byte[] md = mdInst.digest();

        BASE64Encoder base64en = new BASE64Encoder();

//        StringBuffer sb = new StringBuffer();
//        for (byte aByte : md) {
//            String s1 = Integer.toHexString(0xff & aByte);
//            if (s1.length() == 1) {
//                sb.append("0" + password);
//            } else {
//                sb.append(password);
//            }
//        }
        return base64en.encode(md);
    }

    public static void main(String[] args) {
        System.out.println(MD5UserPwd("888888", Constants.DEFAULT_PASSWORD_KEY));

    }
}
