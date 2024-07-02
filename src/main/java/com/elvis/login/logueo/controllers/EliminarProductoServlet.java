package com.elvis.login.logueo.controllers;

import com.elvis.login.logueo.models.Producto;
import com.elvis.login.logueo.repositories.ProductoRepositoryJdbcImpl;
import com.elvis.login.logueo.repositories.Repository;

import com.elvis.login.logueo.services.ProductoService;
import com.elvis.login.logueo.services.ProductoServiceJdbcImplment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/eliminar-producto")
public class EliminarProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplment(conn);
        Integer idProducto;
        try {
            idProducto = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            idProducto = 0;
        }

        if (idProducto > 0) {
            service.eliminar(idProducto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto inválido");
        }
    }
}

