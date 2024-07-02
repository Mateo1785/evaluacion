package com.elvis.login.logueo.controllers;

import com.elvis.login.logueo.models.Categoria;
import com.elvis.login.logueo.models.Producto;
import com.elvis.login.logueo.services.ProductoService;
import com.elvis.login.logueo.services.ProductoServiceJdbcImplment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/formulario")
public class ProductoFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplment(conn);

        // Obtener la lista de categorías
        List<Categoria> categorias = service.listarCategorias();

        // Establecer categorías como atributo en la solicitud
        req.setAttribute("categorias", categorias);

        // Enviar la solicitud al JSP para mostrar el formulario de agregar producto
        req.getRequestDispatcher("/formulario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplment(conn);
        String nombre = req.getParameter("nombre");
        Integer idcategoria;
        try {
            idcategoria = Integer.parseInt(req.getParameter("idCategoria"));
        } catch (NumberFormatException e) {
            idcategoria = 0;
        }
        String descripcionCategoria = req.getParameter("descripcion");
        Double precio;
        try {
            precio = Double.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0.0;
        }
        Integer stock;
        try {
            stock = Integer.parseInt(req.getParameter("stock"));
        } catch (NumberFormatException e) {
            stock = 0;
        }
        Integer idProducto;
        try {
            idProducto = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            idProducto = 0;
        }
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre no puede estar vacío");
        }
        if (descripcionCategoria == null || descripcionCategoria.isBlank()) {
            errores.put("descripcion", "La descripción no puede estar vacía");
        }
        if (precio == 0 || precio < 0) {
            errores.put("precio", "El precio no puede estar vacío o ser negativo");
        }
        if (idcategoria.equals(0)) {
            errores.put("Categoria", "La categoría no puede estar vacía");
        }
        if (stock == 0 || stock < 0) {
            errores.put("stock", "El stock no puede estar vacío o ser negativo");
        }

        Producto producto = new Producto();
        producto.setNombre(nombre);
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idcategoria);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcionCategoria);
        producto.setStock(stock);

        if (errores.isEmpty()) {
            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("producto", producto);
            req.getRequestDispatcher("/formulario.jsp").forward(req, resp);
        }
    }
}


