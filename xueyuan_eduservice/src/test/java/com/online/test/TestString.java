package com.online.test;

import org.junit.Test;

/**
 * @Auther jxy
 * @Date 2020-02-12
 */
public class TestString {
    @Test
    public void test(){
        String s = "a b";
        String replace = s.replace(" ", "_");
        System.out.println(replace);
    }
}
