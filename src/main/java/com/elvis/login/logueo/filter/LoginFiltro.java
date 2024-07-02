package com.elvis.login.logueo.filter;

import com.elvis.login.logueo.services.LoginService;
import com.elvis.login.logueo.services.LoginServiceSessionImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/agregar-carro/*","/form/*","/eliminar/*"})
public class LoginFiltro implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImpl();
        Optional<String> username= service.getUserName((HttpServletRequest) request);
        if(username.isPresent()) {
            chain.doFilter(request, response);
        }else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "ALERTA ,No estas autorizado para ingresar a esta página");
        }
    }
}
