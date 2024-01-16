package com.roadjava.psi.utils;/*
 *ClassName:NoUtil
 *Description: generate number
 *@Author:deanzhou
 *@Date:2024-01-15 21:19
 */

public class NoUtil {
    public static String getNo(String prefix) {
        return prefix + System.nanoTime();
    }
}
