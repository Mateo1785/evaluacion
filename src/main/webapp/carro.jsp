<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.elvis.login.logueo.models.*" %>
<%Carro carro = (Carro) session.getAttribute("carro");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Carrito de compras</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .navbar-custom {
            background-color: #00cccc; /* Aqua color with slight change for better appearance */
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Adding shadow for depth */
        }

        .navbar-custom a {
            color: white;
            transition: background-color 0.3s ease, color 0.3s ease; /* Smooth transition for both background and text color */
        }

        .navbar-custom a:hover {
            background-color: #007bff;
            color: #fff; /* Ensure text color remains white on hover */
        }

        .content {
            padding-top: 70px; /* Adjusted padding to account for navbar */
        }

        .table-hover tbody tr:hover {
            background-color: #e1e1e1; /* Highlight row on hover */
        }
    </style>
</head>
<body>
<!-- Nav-bar -->
<nav class="navbar navbar-expand-lg navbar-custom fixed-top">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/index_usu.html">Inicio</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/logout">
                    <img src="imagenes/logout.png" alt="Logout" height="24" class="d-inline-block align-middle">
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container content">
    <h1 class="text-center my-4">Carro de compras</h1>
    <% if (carro == null || carro.getItems().isEmpty()) { %>
    <p class="text-center">Lo sentimos, no hay productos en el carro de compras!</p>
    <% } else { %>
    <div class="table-responsive">
        <table class="table table-hover table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>ID PRODUCTO</th>
                <th>NOMBRE</th>
                <th>PRECIO</th>
                <th>CANTIDAD</th>
                <th>SUBTOTAL</th>
            </tr>
            </thead>
            <tbody>
            <% for(ItemCarro item : carro.getItems()) { %>
            <tr>
                <td><%= item.getProducto().getIdProducto() %></td>
                <td><%= item.getProducto().getNombre() %></td>
                <td><%= item.getProducto().getPrecio() %></td>
                <td><%= item.getCantidad() %></td>
                <td><%= item.getImporte() %></td>
            </tr>
            <% } %>
            <tr>
                <td colspan="4" class="text-right font-weight-bold">Total :</td>
                <td><%= carro.getTotal() %></td>
            </tr>
            </tbody>
        </table>
    </div>
    <% } %>
    <div class="d-flex justify-content-end mt-4">
        <a href="<%= request.getContextPath() %>/productos" class="btn btn-primary mr-2">Seguir comprando</a>
        <a href="<%= request.getContextPath() %>/index.html" class="btn btn-secondary">Volver</a>
    </div>
</div>

<!-- Bootstrap JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>