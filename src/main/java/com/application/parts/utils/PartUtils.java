package com.application.parts.utils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class PartUtils {

    /**
     * Extracts parameters from a request and inserts them into Map
     * @param request HttpServletRequest from controller
     * @return Map, k - parameter name, v - parameter value
     */
    public static Map<String, String> extractParams(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();

        if (request.getParameter("partname") != null && !request.getParameter("partname").isEmpty()) {
            result.put("part_name", request.getParameter("partname"));
        }

        if (request.getParameter("partnumber") != null && !request.getParameter("partnumber").isEmpty()) {
            result.put("part_number", request.getParameter("partnumber"));
        }

        if (request.getParameter("vendor") != null && !request.getParameter("vendor").isEmpty()) {
            result.put("vendor", request.getParameter("vendor"));
        }

        if (request.getParameter("qty") != null && !request.getParameter("qty").isEmpty()) {
            result.put("qty", request.getParameter("qty"));
        }

        if (request.getParameter("shipped_after") != null && !request.getParameter("shipped_after").isEmpty()) {
            result.put("shippedAfter", convertToSqlDate(request.getParameter("shipped_after")));
        }

        if (request.getParameter("shipped_before") != null && !request.getParameter("shipped_before").isEmpty()) {
            result.put("shippedBefore", convertToSqlDate(request.getParameter("shipped_before")));
        }

        if (request.getParameter("receive_after") != null && !request.getParameter("receive_after").isEmpty()) {
            result.put("receiveAfter", convertToSqlDate(request.getParameter("receive_after")));
        }

        if (request.getParameter("receive_before") != null && !request.getParameter("receive_before").isEmpty()) {
            result.put("receiveBefore", convertToSqlDate(request.getParameter("receive_before")));
        }

        return result;
    }

    /**
     * Checks if parameter in parameters map should be inserted as Date in SQL query
     * @param key parameter key
     * @return
     */
    public static boolean isDate(String key) {
        return "shippedAfter".equals(key) || "shippedBefore".equals(key) ||
                "receiveAfter".equals(key) || "receiveBefore".equals(key);
    }

    /**
     * Checks if parameter in parameters map should be inserted as Integer in SQL query
     * @param k parameter key
     * @return
     */
    public static boolean isInteger(String k) {
        return "qty".equals(k);
    }

    /**
     * Convert from "MMM dd, yyyy" format to "yyyy-MM-dd"
     * @param s date as String
     * @return converted String
     */
    private static String convertToSqlDate(String s) {

        LocalDate ld = LocalDate.parse(s.toLowerCase(), DateTimeFormatter.ofPattern("MMM dd, yyyy"));

        return ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Check if String is an Integer
     * @param str String to be tested
     * @return
     */
    public static boolean validateInteger(String str) {
        if (str == null) {
            return false;
        }
        if (str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

}
