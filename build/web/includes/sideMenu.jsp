<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="Models.Usuario"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
    }
    StringBuilder sbl = new StringBuilder();
    sbl.append("<div class='col-md-3 left_col menu_fixed mCustomScrollbar _mCS_1 mCS-autoHide'>")
            .append("<div class='left_col scroll-view'>")
            .append("<div class='navbar nav_title' style='border: 0;'>")
            .append("<a class='site_title'><img id='logo_menu' style='width:100%;' src='img/logoPagina.png'></a>")
            .append("</div>")
            .append("<div class='clearfix'></div>")
            .append("<div id='sidebar-menu' class='main_menu_side hidden-print main_menu'>")
            .append("<div class='menu_section'>")
            .append("<ul class='nav side-menu'>");

    sbl.append("<li><a href='index.jsp'><i class='fa fa-home'></i>")
            .append("<span>Inicio</span>")
            .append("</a></li>");
    
    sbl.append("<li><a href='carritoCompras.jsp'><i class='fa fa-shopping-cart'></i>")
            .append("<span>Carrito Compras</span>")
            .append("</a></li>");

    if (session.getAttribute("user").equals("Administrador")) {
        sbl.append("<li><a href='registraArticulos.jsp'><i class='fa fa-camera'></i>")
                .append("<span>Registra Artículos</span>")
                .append("</a></li>");
    }

    sbl.append("</ul>")
            .append("</div>")
            .append("</div>")
            .append("</div>")
            .append("</div>");

    sbl.append("<div class='top_nav'>")
            .append("<div class='nav_menu'>")
            .append("<nav>")
            .append("<div class='nav toggle'>")
            .append("<a id='menu_toggle'><i class='fa fa-bars'></i></a>")
            .append("</div>");

    sbl.append("<ul class='nav navbar-nav navbar-right' style='width: 90%;'>")
            .append("<li class=''>")
            .append("<div><h3></h3></div>")
            .append("<a href='javascript:;' class='user-profile dropdown-toggle' data-toggle='dropdown' aria-expanded='false'>")
            .append(session.getAttribute("user") + " <i class=' fa fa-angle-down'></i> <img src='GetPerfilImage?user=" + session.getAttribute("user") + "&token=" + session.getAttribute("password") + "' alt=''>")
            .append("</a>")
            .append("<ul class='dropdown-menu dropdown-usermenu pull-right'>")
            .append("<li><a href='login.jsp?param=logout'><i class='fa fa-sign-out pull-right'></i> Cerrar Sesión</a></li>")
            .append("</ul>");

    sbl.append("</li>")
            .append("</nav>")
            .append("</div>")
            .append("</div>"
                    + "<div class=\"modal fade\" id=\"loadMe\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"loadMeLabel\" style=\"position: absolute;\"><div class=\"modal-dialog modal-sm\" role=\"document\" style=\"margin: 0px;position: relative;top: 50%;left: 50%;transform: translate(-50%, -50%);\"><div style=\"background-color: transparent;\"><div class=\"modal-body text-center\"><div class=\"loader\"></div></div></div></div></div>");
    
    out.print(sbl.toString());
%>