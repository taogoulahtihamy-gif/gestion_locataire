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

        boolean isStatic =
                uri.contains(".css") || uri.contains(".js") || uri.contains(".png") ||
                        uri.contains(".jpg") || uri.contains(".jpeg") || uri.contains(".gif") ||
                        uri.contains(".svg") || uri.contains(".woff") || uri.contains(".woff2");

        boolean isAdminLogin = uri.equals(contextPath + "/login");
        boolean isLogout = uri.equals(contextPath + "/logout");

        boolean isPublicLocataire =
                uri.equals(contextPath + "/") ||
                        uri.equals(contextPath + "/index.jsp") ||
                        uri.equals(contextPath + "/register") ||
                        uri.equals(contextPath + "/login-locataire") ||
                        uri.equals(contextPath + "/offres") ||
                        uri.equals(contextPath + "/demande-location");

        boolean isLocataireProtected =
                uri.equals(contextPath + "/profil-locataire") ||
                        uri.equals(contextPath + "/mes-demandes");

        HttpSession session = req.getSession(false);
        boolean adminLogged = session != null && session.getAttribute("user") != null;
        boolean locataireLogged = session != null && session.getAttribute("locataireSession") != null;

        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        if (isStatic || isAdminLogin || isLogout || isPublicLocataire) {
            chain.doFilter(req, resp);
            return;
        }

        if (isLocataireProtected) {
            if (locataireLogged) {
                chain.doFilter(req, resp);
            } else {
                resp.sendRedirect(contextPath + "/login-locataire");
            }
            return;
        }

        if (!adminLogged) {
            resp.sendRedirect(contextPath + "/login");
            return;
        }

        if (uri.startsWith(contextPath + "/utilisateurs")) {
            Utilisateur user = (Utilisateur) session.getAttribute("user");
            if (user == null || user.getRole() == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
                resp.sendRedirect(contextPath + "/dashboard");
                return;
            }
        }

        chain.doFilter(req, resp);
    }
}