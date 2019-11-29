<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="./vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="./vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="./vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- Animate.css -->
        <link href="./vendors/animate.css/animate.min.css" rel="stylesheet">
        <!-- Custom Theme Style -->
        <link href="./build/css/custom.css" rel="stylesheet">
        <title>Login</title>
    </head>
    <body class="login">
        <div style="padding-top: 5%;">
            <div class="login_wrapper">
                <div class="form login_form">
                    <section class="login_content">
                        <form id="formRegistrar" method="POST" action="RegistraUsuario" enctype="multipart/form-data">
                            <h1>Registrarse</h1>
                            <div>
                                <input type="text" class="form-control" placeholder="Nombre" name="nombre" value="${param.nombre}"/>
                                <span class="error">${errors.nombre}</span>
                            </div>
                            <div>
                                <input type="text" class="form-control" placeholder="Apellido" name="apellido" value="${param.apellido}"/>
                                <span class="error">${errors.apellido}</span>
                            </div>
                            <div>
                                <input type="text" class="form-control" placeholder="Nombre Usuario" name="user" value="${param.user}"/>
                                <span class="error">${errors.user}</span>
                            </div>
                            <div>
                                <input type="email" class="form-control" placeholder="Correo Electrónico" name="correo" value="${param.correo}"/>
                                <span class="error">${errors.correo}</span>
                            </div>
                            <div>
                                <input type="password" class="form-control" placeholder="Contraseña" name="password" value="${param.password}"/>
                                <span class="error">${errors.password}</span>
                            </div>
                            <div>
                                <input type="text" class="form-control" placeholder="Teléfono" name="telefono" value="${param.telefono}"/>
                            </div>
                            <div>
                                <input type="text" class="form-control" placeholder="Dirección" name="direccion" value="${param.direccion}"/>
                            </div>
                            <div>
                                <label>Imagen de Usuario</label>
                                <input type="file" class="form-control" name="imagen" accept="image/x-png,image/gif,image/jpeg" />
                                <span class="error">${errors.imagen}</span>
                            </div>
                            <div>
                                <a class="btn btn-default submit" onclick="document.getElementById('formRegistrar').submit()">Registrar</a>
                            </div>

                            <div class="clearfix"></div>

                            <div class="separator">
                                <p class="change_link">Ya tienes cuenta?
                                    <a href="login.jsp" class="to_register"> Iniciar Sesión </a>
                                </p>

                                <div class="clearfix"></div>
                                <br />
                            </div>
                        </form>
                    </section>
                </div>
            </div>
        </div>
    </body>
</html>
