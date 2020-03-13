package com.online.test;

import org.apache.commons.lang3.StringUtils;

/**
 * @Auther jxy
 * @Date 2020-03-10
 */
public class GBtest {
    public static void main(String[] args) {
        String str = "200";
        boolean gb = StringUtils.containsIgnoreCase(str,"G");
        boolean mb = StringUtils.containsIgnoreCase(str,"M");
        boolean kb = StringUtils.containsIgnoreCase(str,"K");
        boolean b = StringUtils.containsIgnoreCase(str, "B");
        System.out.println(gb||mb||kb||b);
    }
}
