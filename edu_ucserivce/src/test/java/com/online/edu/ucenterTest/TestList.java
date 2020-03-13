package com.online.edu.ucenterTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther jxy
 * @Date 2020-03-11
 */
public class TestList {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(64L);
        //判断
        System.out.println(list.contains(63L));
    }
}
