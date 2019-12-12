<%@page import="Models.Usuario"%>
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
        <%
            boolean errorLog = false;
            String param = request.getParameter("param");

            String user = request.getParameter("user");
            String pass = request.getParameter("password");
            boolean sesion = Boolean.parseBoolean(request.getParameter("session"));

            Usuario usuario = null;

            //Si la solicitud es inicio de sesion
            if (param != null && param.equals("login")) {
                //login
                if (user != null && !user.equals("") && pass != null && !pass.equals("")) {
                    usuario = Usuario.iniciaSesion(user, pass);

                    if (usuario != null) {

                        session.setAttribute("nombre", usuario.getNombre());
                        session.setAttribute("user", usuario.getUser());
                        session.setAttribute("password", usuario.getPassword());
                        session.setAttribute("idUsuario", usuario.getIdUsuario());
                        //Crear Cookies
                        if (sesion) {
                            Cookie cookie = new Cookie("user", usuario.getUser());
                            cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
                            response.addCookie(cookie);
                            Cookie cookie2 = new Cookie("password", usuario.getPassword());
                            cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
                            response.addCookie(cookie2);
                            session.setMaxInactiveInterval(-1);
                        } else {
                            Cookie cookie = new Cookie("user", "");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                            Cookie cookie2 = new Cookie("password", "");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie2);
                        }
                        response.sendRedirect("index.jsp");

                    } else {
                        errorLog = true;
                    }
                } else {
                    errorLog = true;
                }

                //Si la solicitud es cerrar la sesion
            } else if (param != null && param.equals("logout")) {
                session.invalidate();
                response.sendRedirect("login.jsp");
            } else {
                String nombre = null;
                String password = null;
                Cookie[] cookies = request.getCookies();

                //Valida Cookies
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("user")) {
                            nombre = cookie.getValue();
                        } else if (cookie.getName().equals("user")) {
                            password = cookie.getValue();
                        }
                    }
                }

                //Redirecciona al index o a la pagina de la que proviene
                if (nombre != null) {
                    session.invalidate();
                    usuario = Usuario.iniciaSesion(nombre, password);

                    if (usuario != null) {
                        session.setAttribute("nombre", usuario.getNombre());
                        session.setAttribute("user", usuario.getUser());
                        session.setAttribute("password", usuario.getPassword());
                        session.setAttribute("idUsuario", usuario.getIdUsuario());
                        if (sesion) {
                            session.setMaxInactiveInterval(-1);
                        }
                        response.sendRedirect("index.jsp");
                    }
                }
            }

        %>
        <div style="padding-top: 5%;">
            <div class="login_wrapper">
                <div class="form login_form">
                    <section class="login_content">
                        <form id="formLogin" role="form" method="POST" action="login.jsp?param=login">
                            <h1>Iniciar Sesión</h1>
                            <%  if (errorLog) {
                                    out.print("<h4><span class='label label-danger'>Usuario o contraseña no válidos</span></h4>");
                                }%>
                            <div>
                                <input type="text" class="form-control" placeholder="Correo o Nombre de Usuario" name="user" autofocus/>
                            </div>
                            <div>
                                <input type="password" class="form-control" placeholder="Contraseña" name="password"/>
                            </div>
                            <div>
                                <input type="checkbox" name="session">Recordar sesión</input>
                            </div>
                            <div>
                                <a class="btn btn-default submit" onclick="document.getElementById('formLogin').submit()">Iniciar Sesión</a>
                            </div>

                            <div class="clearfix"></div>

                            <div class="separator">
                                <p class="change_link">No tienes cuenta?
                                    <a href="registro.jsp" class="to_register"> Registrarse </a>
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
