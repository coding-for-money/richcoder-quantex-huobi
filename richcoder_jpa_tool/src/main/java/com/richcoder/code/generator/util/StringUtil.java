package com.richcoder.code.generator.util;

import java.util.HashSet;
import java.util.Set;

public class StringUtil {

    private static final Set<String> set = new HashSet<>();

    static {
        String[] javaCategories = new String[]{"abstract", "assert", "boolean", "break",
                "byte", "case", "catch", "char", "class", "const", "continue",
                "default", "do", "double", "else", "enum", "extends", "final",
                "finally", "float", "for", "goto", "if", "implements",
                "import", "instanceof", "int", "interface", "long", "native",
                "new", "package", "private", "protected", "public", "return",
                "strictfp", "short", "static", "super", "switch",
                "synchronized", "this", "throw", "throws", "transient", "try",
                "void", "volatile", "while"};
        for (String string : javaCategories) {
            set.add(string);
        }
    }

    public static String toCamelCase(String stringWithUnderline) {
        if (stringWithUnderline.indexOf('_') == -1) {
            return stringWithUnderline;
        }

        stringWithUnderline = stringWithUnderline.toLowerCase();
        char[] fromArray = stringWithUnderline.toCharArray();
        char[] toArray = new char[fromArray.length];
        int j = 0;
        for (int i = 0; i < fromArray.length; i++) {
            if (fromArray[i] == '_') {
                // 当前字符为下划线时，将指针后移一位，
                // 将紧随下划线后面一个字符转成大写并存放
                i++;
                if (i < fromArray.length) {
                    toArray[j++] = Character.toUpperCase(fromArray[i]);
                }
            } else {
                toArray[j++] = fromArray[i];
            }
        }
        return new String(toArray, 0, j);
    }

    /**
     * 类名生成包名
     */
    public static String toPackageName(String str) {
        str = firstCharToLowerCase(str);
        char[] fromArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fromArray.length; i++) {
            if (fromArray[i] >= 'A' && fromArray[i] <= 'Z') {
                if (i != 0) {
                    sb.append(".");
                }
                sb.append((char) (fromArray[i] + ('a' - 'A')));
            } else {
                sb.append(fromArray[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 是否是java关键字
     *
     * @param string
     * @return
     */
    public static boolean isJavaCategories(String string) {
        return set.contains(string.trim().toLowerCase());
    }

}
