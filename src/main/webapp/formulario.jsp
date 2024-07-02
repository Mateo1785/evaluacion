<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.elvis.login.logueo.models.*" %>
<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Producto producto = (Producto) request.getAttribute("producto");
    if (producto == null) {
        producto = new Producto();  // Aqui inicializa un nuevo objeto Producto si es null
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Agregar Producto</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
<div class="card p-4 shadow-sm">
    <h2 class="text-center">Agregar Nuevo Producto Aqui</h2>
    <form action="<%= request.getContextPath() %>/formulario" method="post">
        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= producto.getNombre() %>" required>
            <% if (errores != null && errores.containsKey("nombre")) { %>
            <div class="error text-danger"><%= errores.get("nombre") %></div>
            <% } %>
        </div>

        <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <textarea class="form-control" id="descripcion" name="descripcion" required><%= producto.getDescripcion() %></textarea>
            <% if (errores != null && errores.containsKey("descripcion")) { %>
            <div class="error text-danger"><%= errores.get("descripcion") %></div>
            <% } %>
        </div>

        <div class="form-group">
            <label for="precio">Precio:</label>
            <input type="text" class="form-control" id="precio" name="precio" value="<%= producto.getPrecio() %>" required>
            <% if (errores != null && errores.containsKey("precio")) { %>
            <div class="error text-danger"><%= errores.get("precio") %></div>
            <% } %>
        </div>

        <div class="form-group">
            <label for="stock">Stock:</label>
            <input type="text" class="form-control" id="stock" name="stock" value="<%= producto.getStock() %>" required>
            <% if (errores != null && errores.containsKey("stock")) { %>
            <div class="error text-danger"><%= errores.get("stock") %></div>
            <% } %>
        </div>

        <div class="form-group">
            <label for="idCategoria">Categoría:</label>
            <select class="form-control" id="idCategoria" name="idCategoria" required>
                <% if (categorias != null) { %>
                <% for (Categoria c : categorias) { %>
                <option value="<%= c.getIdCategoria() %>" <%= (producto.getCategoria() != null && c.getIdCategoria() == producto.getCategoria().getIdCategoria()) ? "selected" : "" %>><%= c.getNombre() %></option>
                <% } %>
                <% } %>
            </select>
            <% if (errores != null && errores.containsKey("Categoria")) { %>
            <div class="error text-danger"><%= errores.get("Categoria") %></div>
            <% } %>
        </div>

        <input type="hidden" name="id" value="<%= producto.getIdProducto() %>">
        <button type="submit" class="btn btn-primary btn-block"><%= (producto.getIdProducto() != null && producto.getIdProducto() > 0 ? "Editar" : "Crear") %></button>
    </form>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>