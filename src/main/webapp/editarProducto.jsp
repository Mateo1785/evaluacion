<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*, com.elvis.login.logueo.models.*"%>
<%
    Producto producto = (Producto) request.getAttribute("producto");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");


%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 0;
        }

        .card {
            border-radius: 12px;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
        }

        .error {
            color: #e74c3c;
            margin-top: 8px;
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<div class="card p-4">
    <h2 class="text-center">Editar Producto</h2>
    <form action="<%= request.getContextPath() %>/editar-producto" method="post">
        <div class="form-group">
            <label for="nombre">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= producto.getNombre() %>" required>
            <% if (errores != null && errores.containsKey("nombre")) { %>
            <div class="error"><%= errores.get("nombre") %></div>
            <% } %>
        </div>

        <div class="form-group">
            <label for="descripcion">Descripción:</label>
            <textarea class="form-control" id="descripcion" name="descripcion" required><%= producto.getDescripcion() %></textarea>
            <% if (errores != null && errores.containsKey("descripcion")) { %>
            <div class="error"><%= errores.get("descripcion") %></div>
            <% } %>
        </div>

        <div class="form-group">
            <label for="precio">Precio:</label>
            <input type="text" class="form-control" id="precio" name="precio" value="<%= producto.getPrecio() %>" required>
            <% if (errores != null && errores.containsKey("precio")) { %>
            <div class="error"><%= errores.get("precio") %></div>
            <% } %>
        </div>

        <div class="form-group">
            <label for="stock">Stock:</label>
            <input type="text" class="form-control" id="stock" name="stock" value="<%= producto.getStock() %>" required>
            <% if (errores != null && errores.containsKey("stock")) { %>
            <div class="error"><%= errores.get("stock") %></div>
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
            <div class="error"><%= errores.get("Categoria") %></div>
            <% } %>
        </div>

        <input type="hidden" name="id" value="<%= producto.getIdProducto() %>">
        <button type="submit" class="btn btn-primary btn-block">Guardar</button>
    </form>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>