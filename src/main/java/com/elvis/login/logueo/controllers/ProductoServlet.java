package com.elvis.login.logueo.controllers;

import com.elvis.login.logueo.models.Producto;
import com.elvis.login.logueo.repositories.ProductoRepositoryJdbcImpl;
import com.elvis.login.logueo.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productoServlet","/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn= (Connection) req.getAttribute("conn");
        ProductoService service= new ProductoServiceJdbcImplment(conn);
        List<Producto> productos = service.listar();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional=auth.getUserName(req);

        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);
        //Aqui es donde reenviamos la info al jsp
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req,resp);


    }
}
