package com.online.test;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @Auther jxy
 * @Date 2020-02-10
 */
public class testvalue {
    public static final String REGEX_USERNAME = ".*[/\\\\\n\r].*"; //汉字

    public static boolean validateUserName(String userName){
        boolean rs = false;
        rs = matcher(userName);
        if (rs) {
            int strLenth = getStrLength(userName);
            if (strLenth < 2 || strLenth > 50) {
                rs = false;
            }
        }
        return rs;
    }

    public static boolean matcher(String username) {
        return !Pattern.matches(REGEX_USERNAME, username);
    }

    public static int getStrLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }


    @Test
    public void test1(){
        boolean jia = validateUserName("afdd1贾*相宇1_");
        System.out.println(jia);

    }
}
