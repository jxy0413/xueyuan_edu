package com.online.test;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Auther jxy
 * @Date 2020-02-17
 */
public class TestJsonArray {
    @Test
    public void testJsonArray(){
        JSONArray jsonArry = new JSONArray();
        jsonArry.add("111");
        int size = jsonArry.size();
        System.out.println(size);


        ArrayList a = new ArrayList<>();
        a.add("");
        System.out.println(a.size());

    }
}
