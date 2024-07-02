package com.elvis.login.logueo.controllers;

import com.elvis.login.logueo.models.Categoria;
import com.elvis.login.logueo.models.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/editar-producto")
public class EditarProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplment(conn);
        Integer idProducto;
        try {
            idProducto = Integer.parseInt(req.getParameter("idProducto"));
        } catch (NumberFormatException e) {
            idProducto = 0;
        }

        Optional<Producto> productoOptional = service.porId(idProducto);
        if (productoOptional.isPresent()) {
            req.setAttribute("producto", productoOptional.get());
            List<Categoria> categorias = service.listarCategorias();
            req.setAttribute("categorias", categorias);
            req.getRequestDispatcher("/editarProducto.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplment(conn);

        // Obtener parámetro id
        Integer idProducto = null;
        String idProductoParam = req.getParameter("id");
        if (idProductoParam != null && !idProductoParam.isBlank()) {
            try {
                idProducto = Integer.parseInt(idProductoParam);
            } catch (NumberFormatException e) {
                // Manejar el error si no se puede convertir a Integer
                idProducto = null;
            }
        }

        // Obtener el producto existente por su ID
        Optional<Producto> productoOptional = service.porId(idProducto);
        if (!productoOptional.isPresent()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
            return;
        }

        Producto producto = productoOptional.get(); // Producto existente

        String nombre = req.getParameter("nombre");

        Integer idcategoria;
        try {
            String idCategoriaParam = req.getParameter("idCategoria");
            if (idCategoriaParam != null && !idCategoriaParam.isBlank()) {
                idcategoria = Integer.parseInt(idCategoriaParam);
            } else {
                idcategoria = null;
            }
        } catch (NumberFormatException | NullPointerException e) {
            idcategoria = null; // Manejar error
        }

        String descripcionCategoria = req.getParameter("descripcion");

        Double precio;
        try {
            String precioParam = req.getParameter("precio");
            if (precioParam != null && !precioParam.isBlank()) {
                precio = Double.valueOf(precioParam);
            } else {
                precio = null;
            }
        } catch (NumberFormatException | NullPointerException e) {
            precio = null; // Manejar error
        }

        Integer stock;
        try {
            String stockParam = req.getParameter("stock");
            if (stockParam != null && !stockParam.isBlank()) {
                stock = Integer.parseInt(stockParam);
            } else {
                stock = null;
            }
        } catch (NumberFormatException | NullPointerException e) {
            stock = null; // Manejar error
        }

        // Validar errores
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "Nombre no debe estar vacío");
        }
        if (idcategoria == null) {
            errores.put("idCategoria", "ID de categoría inválido");
        }
        if (descripcionCategoria == null || descripcionCategoria.isBlank()) {
            errores.put("descripcion", "Descripción no debe estar vacía");
        }
        if (precio == null || precio <= 0) {
            errores.put("precio", "Precio debe ser un número positivo");
        }
        if (stock == null || stock <= 0) {
            errores.put("stock", "Stock debe ser un número positivo");
        }

        // Procesar producto si no hay errores
        if (errores.isEmpty()) {
            // Actualizar el producto existente con los nuevos valores
            producto.setNombre(nombre);
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(idcategoria);
            producto.setCategoria(categoria);
            producto.setPrecio(precio);
            producto.setDescripcion(descripcionCategoria);
            producto.setStock(stock);

            service.guardar(producto); // Guardar los cambios

            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            // Mostrar errores y volver a la página de edición
            req.setAttribute("errores", errores);
            req.setAttribute("producto", producto); // Para mantener los valores en el formulario
            List<Categoria> categorias = service.listarCategorias();
            req.setAttribute("categorias", categorias);
            req.getRequestDispatcher("/editarProducto.jsp").forward(req, resp);
        }
    }
}
