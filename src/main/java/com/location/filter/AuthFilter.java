package com.location.filter;

import com.location.entity.Utilisateur;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter implements Filter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        boolean isLoginRequest = uri.equals(contextPath + "/login");
        boolean isLogoutRequest = uri.equals(contextPath + "/logout");
        boolean isPublicResource =
                uri.contains(".css") ||
                        uri.contains(".js") ||
                        uri.contains(".png") ||
                        uri.contains(".jpg") ||
                        uri.contains(".jpeg") ||
                        uri.contains(".gif") ||
                        uri.contains(".svg") ||
                        uri.contains(".woff") ||
                        uri.contains(".woff2");

        HttpSession session = req.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        // Empêche le cache des pages protégées
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        if (!loggedIn && !(isLoginRequest || isLogoutRequest || isPublicResource)) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        // ADMIN uniquement pour /utilisateurs
        if (loggedIn && uri.startsWith(contextPath + "/utilisateurs")) {
            Utilisateur user = (Utilisateur) session.getAttribute("user");

            if (user == null || user.getRole() == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
                resp.sendRedirect(contextPath + "/dashboard");
                return;
            }
        }

        chain.doFilter(req, resp);
    }
}