package com.zeng.fanda.mylibrary.Utils;

/**
 * Created by zfd on 2016/12/2 0002.
 * 字符串辅助工具类
 */

public class StringUtils {

    private StringUtils() { /* cannot be instantiated */ }

    /**
     * 判断字符串是否全为空格
     *
     * @param str
     * @return
     */
    public static boolean isSpace(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }
}
