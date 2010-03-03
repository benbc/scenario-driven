package com.thoughtworks.teach.web;

import java.util.List;

public class Utils {

    private Utils() {}

    public static String join(List list, String separator) {
        String result = "";
        for (Object item : list) {
            if (!result.equals("")) {
                result += separator;
            }
            result += item;
        }
        return result;
    }
}
