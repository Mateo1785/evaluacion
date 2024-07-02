<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Eliminar Producto</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
<div class="card p-4 shadow-sm">
    <h2 class="text-center">Eliminar Producto</h2>
    <p class="text-center">¿Está seguro que desea eliminar el producto <strong>${producto.nombre}</strong>?</p>
    <form action="eliminarProducto" method="post">
        <input type="hidden" name="id" value="${producto.id}">
        <div class="d-flex justify-content-between">
            <a href="javascript:history.back()" class="btn btn-secondary">Cancelar</a>
            <input type="submit" value="Eliminar" class="btn btn-danger">
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>