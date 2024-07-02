package com.elvis.login.logueo.repositories;

import com.elvis.login.logueo.models.Categoria;
import com.elvis.login.logueo.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImpl implements Repository<Producto> {
    private Connection conn;

    public ProductoRepositoryJdbcImpl(Connection conn) {
        this.conn = conn; // Inicializar la conexión en el constructor
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery("SELECT p.idproducto, p.nombre, p.descripcion, p.precio, p.stock, p.codigo, p.condicion, c.idcategoria, " +
                     "c.nombre AS nombre_categoria FROM producto AS p JOIN categoria AS c ON p.idcategoria = c.idcategoria")) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Integer id) throws SQLException {
        Producto producto = null;
        String sql = "SELECT p.*, c.nombre as nombre_categoria FROM producto as p " +
                "inner join categoria as c ON(p.idcategoria=c.idcategoria) WHERE p.idproducto=?";
        try (PreparedStatement smt = conn.prepareStatement(sql)) {
            smt.setInt(1, id);
            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getIdProducto() != null && producto.getIdProducto() > 0) {
            // Update existente
            sql = "UPDATE producto SET nombre=?, idcategoria=?, descripcion=?, precio=?, stock=?, codigo=?, condicion=? WHERE idproducto = ?";
            try (PreparedStatement smt = conn.prepareStatement(sql)) {
                smt.setString(1, producto.getNombre());
                smt.setInt(2, producto.getCategoria().getIdCategoria());
                smt.setString(3, producto.getDescripcion());
                smt.setDouble(4, producto.getPrecio());
                smt.setInt(5, producto.getStock());
                smt.setString(6, producto.getCodigo());
                smt.setInt(7, producto.getCondicion());
                smt.setInt(8, producto.getIdProducto());
                smt.executeUpdate();
            }
        } else {
            // Nuevo inserción - Generar un nuevo ID para el producto
            sql = "INSERT INTO producto(idproducto, nombre, idcategoria, descripcion, precio, stock, codigo, condicion) VALUES(?,?,?,?,?,?,?,?)";
            int newId = generarNuevoId(); // Método para generar un nuevo ID
            try (PreparedStatement smt = conn.prepareStatement(sql)) {
                smt.setInt(1, newId);
                smt.setString(2, producto.getNombre());
                smt.setInt(3, producto.getCategoria().getIdCategoria());
                smt.setString(4, producto.getDescripcion());
                smt.setDouble(5, producto.getPrecio());
                smt.setInt(6, producto.getStock());
                smt.setString(7, producto.getCodigo());
                smt.setInt(8, producto.getCondicion());
                smt.executeUpdate();
                producto.setIdProducto(newId);
            }
        }
    }

    private int generarNuevoId() throws SQLException {
        String sql = "SELECT MAX(idproducto) AS max_id FROM producto";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("max_id") + 1;
            } else {
                throw new SQLException("No se pudo generar un nuevo ID");
            }
        }
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM producto WHERE idproducto=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Producto activar(Integer id) throws SQLException {
        return null; // Implementación pendiente
    }

    @Override
    public Producto desactivar(Integer id) throws SQLException {
        return null; // Implementación pendiente
    }

    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdProducto(rs.getInt("idproducto"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getDouble("precio"));
        p.setStock(rs.getInt("stock"));
        p.setCodigo(rs.getString("codigo"));
        p.setCondicion(rs.getInt("condicion"));
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("idcategoria"));
        c.setNombre(rs.getString("nombre_categoria"));
        p.setCategoria(c);
        return p;
    }

    public List<Categoria> obtenerCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT idcategoria, nombre FROM categoria";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idcategoria"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        }
        return categorias;
    }
}
