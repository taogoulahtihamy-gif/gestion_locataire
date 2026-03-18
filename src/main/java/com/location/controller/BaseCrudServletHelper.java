package com.location.controller;

import jakarta.servlet.http.HttpServletRequest;

public class BaseCrudServletHelper {
    public static Integer parseId(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (value == null || value.isBlank()) return null;
        return Integer.parseInt(value);
    }
}
