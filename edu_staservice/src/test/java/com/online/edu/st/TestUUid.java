package com.online.edu.st;

import java.util.UUID;

/**
 * @Auther jxy
 * @Date 2020-03-03
 */
public class TestUUid {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().substring(0,8));
    }
}
