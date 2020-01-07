package com.zijing.schoolonline.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    //验证手机号
    public static boolean regTelPhoneNumber(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }

    //验证密码
    public static boolean regPassword(String value) {
        if (value != null && value.length() == 11) {
            Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$");
            Matcher matcher = pattern.matcher(value);
            return matcher.matches();
        }
        return false;
    }
}
