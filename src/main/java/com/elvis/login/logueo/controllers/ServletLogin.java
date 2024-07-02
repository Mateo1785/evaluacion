package com.elvis.login.logueo.controllers;

import com.elvis.login.logueo.services.LoginService;
import com.elvis.login.logueo.services.LoginServiceSessionImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

//creamos el Path para la conexión al Servlet
@WebServlet({"/Login", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
    final static String ADMIN_USERNAME = "admin";
    final static String ADMIN_PASSWORD = "123";
    final static String USER_USERNAME = "josue";
    final static String USER_PASSWORD = "123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUserName(req);
        if (usernameOptional.isPresent()) {
            String username = usernameOptional.get();
            if (ADMIN_USERNAME.equals(username)) {
                resp.sendRedirect(req.getContextPath() + "/index_ad.html");
            } else if (USER_USERNAME.equals(username)) {
                resp.sendRedirect(req.getContextPath() + "/index_usu.html");
            } else {
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/index_ad.html");
        } else if (USER_USERNAME.equals(username) && USER_PASSWORD.equals(password)) {
            session.setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/index_usu.html");
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
