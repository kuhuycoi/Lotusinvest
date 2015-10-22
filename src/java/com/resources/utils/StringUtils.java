package com.resources.utils;

public class StringUtils {

    public static boolean isEmpty(String input) {
        return input == null || input.trim().length() == 0;
    }

    public static String escapeHtmlEntity(String value) {
        String[] specialCharacters = {"<", ">", "&", "¢", "£", "¥", "€", "©", "®"};
        String[] entityNames = {"&lt;", "&gt;", "&amp;", "&cent;", "&pound", "&yen;", "&euro;", "&copy;", "&reg;"};
        return value == null ? null : org.apache.commons.lang3.StringUtils.replaceEach(value, specialCharacters, entityNames);
    }
}
