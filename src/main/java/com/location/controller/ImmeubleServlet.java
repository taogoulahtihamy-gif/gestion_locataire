package com.location.controller;

import com.location.entity.Immeuble;
import com.location.service.ImmeubleService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/immeubles")
public class ImmeubleServlet extends HttpServlet {
    private final ImmeubleService service = new ImmeubleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = BaseCrudServletHelper.parseId(request, "id");
        String action = request.getParameter("action");
        if ("delete".equals(action) && id != null) {
            service.delete(id);
            response.sendRedirect(request.getContextPath() + "/immeubles");
            return;
        }
        if ("edit".equals(action) && id != null) {
            request.setAttribute("item", service.findById(id));
        }
        request.setAttribute("items", service.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/immeubles/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Immeuble item = new Immeuble();
        Integer id = BaseCrudServletHelper.parseId(request, "id");
        item.setId(id);
        item.setNom(request.getParameter("nom"));
        item.setAdresse(request.getParameter("adresse"));
        item.setNombreUnites(BaseCrudServletHelper.parseId(request, "nombreUnites"));
        item.setEquipements(request.getParameter("equipements"));
        item.setDescription(request.getParameter("description"));
        service.save(item);
        response.sendRedirect(request.getContextPath() + "/immeubles");
    }
}
