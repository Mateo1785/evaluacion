<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ingresando al Sistema</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            background-color: #eceff1;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #2c3e50;
            text-align: center;
            margin-top: 150px;
            font-weight: 300;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 30px;
            background: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        label {
            color: #34495e;
            font-size: 14px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px 20px;
            border: 1px solid #bdc3c7;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
            margin-bottom: 15px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 12px 20px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        /* Estilos para el nav-bar */
        .navbar {
            background-color: #2c3e50;
            overflow: hidden;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1000;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .navbar ul {
            display: flex;
            justify-content: flex-end;
            padding: 0;
            margin: 0;
            list-style: none;
        }

        .navbar li {
            margin: 0 15px;
        }

        .navbar a {
            color: white;
            padding: 14px 20px;
            text-decoration: none;
            display: block;
            transition: background-color 0.3s ease;
        }

        .navbar a:hover {
            background-color: #34495e;
        }

        .navbar .icon {
            display: flex;
            align-items: center;
            padding: 14px 20px;
        }

        .navbar img {
            height: 24px;
            vertical-align: middle;
        }

        /* Ajuste del contenido al nav-bar */
        .content {
            padding-top: 80px;
        }
    </style>
</head>
<body>

<!-- Nav-bar -->
<div class="navbar">
    <ul>
        <li class="nav-item"><a class="nav-link" href="/logueo_war_exploded/index.html"> <img src="imagenes/login.png" ></a></li>
    </ul>
</div>

<!-- Contenido principal -->
<div class="content">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <h1 class="card-header text-center">Iniciar sesión</h1>
                    <div class="card-body">
                        <form action="/logueo_war_exploded/Login" method="post">
                            <div class="form-group">
                                <label for="username">Nombre de usuario</label>
                                <input type="text" class="form-control" id="username" name="username" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Contraseña</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Login</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>