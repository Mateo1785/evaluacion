<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.elvis.login.logueo.models.*" %>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Listado de Productos</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            background-color: #eceff1;
            margin: 0;
            padding: 0;
        }

        .navbar {
            background-color: #1a1a1a;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .navbar-nav .nav-item .nav-link {
            color: white;
        }

        .navbar-nav .nav-item .nav-link:hover {
            background-color: #333;
        }

        .content {
            padding-top: 80px;
            padding-left: 20px;
            padding-right: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        table, th, td {
            border: 1px solid #333;
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
        }

        th {
            background-color: #1a1a1a;
            color: white;
            font-weight: bold;
        }

        tr:nth-child(even) {
            background-color: #333;
            color: white;
        }

        tr:hover {
            background-color: #555;
        }

        .button-container {
            margin-top: 20px;
            display: flex;
            justify-content: flex-end;
        }

        .button-container button {
            background-color: #1a1a1a;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-size: 16px;
            transition: background-color 0.3s ease;
            margin-left: 10px;
        }

        .button-container button:hover {
            background-color: #333;
        }

        .button-container a {
            color: white;
            text-decoration: none;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout">
                        <img src="imagenes/logout.png" alt="Logout" height="24">
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="content container">
    <h1 class="my-4">Listado de Productos</h1>
    <% if (username.isPresent()) { %>
    <div class="alert alert-info">
        Hola <%= username.get() %>, ¡Bienvenido!
    </div>
    <% if ("admin".equals(username.get())) { %>
    <div class="button-container">
        <button>
            <a href="<%= request.getContextPath() %>/formulario">Crear Nuevo Producto</a>
        </button>
    </div>
    <% } %>
    <% } %>

    <table class="table table-striped table-hover mt-4">
        <thead>
        <tr>
            <th>ID Producto</th>
            <th>Nombre</th>
            <th>Categoría</th>
            <th>Descripción</th>
            <% if (username.isPresent() && "admin".equals(username.get())) { %>
            <th>Precio</th>
            <th>Editar</th>
            <th>Eliminar</th>
            <% } else if (username.isPresent() && "user".equals(username.get())) { %>
            <th>Agregar</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <% for (Producto p : productos) { %>
        <tr>
            <td><%= p.getIdProducto() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getCategoria().getNombre() %></td>
            <td><%= p.getDescripcion() %></td>
            <% if (username.isPresent()) { %>
            <% if ("admin".equals(username.get())) { %>
            <td><%= p.getPrecio() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/editar-producto?idProducto=<%= p.getIdProducto() %>">
                    <img src="imagenes/write.png" alt="Editar" height="20">
                </a>
            </td>
            <td>
                <a onclick="return confirm('¿Estás seguro que deseas eliminar el producto?');" href="<%= request.getContextPath() %>/eliminar-producto?id=<%= p.getIdProducto() %>">
                    <img src="imagenes/eliminar.png" alt="Eliminar" height="20">
                </a>
            </td>
            <% } else if ("user".equals(username.get())) { %>
            <td>
                <a href="<%= request.getContextPath() %>/agregar-carro?id=<%= p.getIdProducto() %>">
                    <img src="imagenes/checklist.png" alt="Agregar" height="20">
                </a>
            </td>
            <% } %>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
