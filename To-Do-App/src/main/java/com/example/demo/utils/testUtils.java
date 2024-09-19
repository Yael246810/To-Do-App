package com.example.demo.utils;

public class testUtils {
    private static int count = 1;

    public static void test(String title){
        System.out.println(String.format("Test %03d - %s\n ,",count++,title));
    }
}
