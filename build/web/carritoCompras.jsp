<%@page import="Models.CarritoCompra"%>
<%@page import="Models.Articulo"%>
<%@page import="Models.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="image/x-icon" href="img/favicon.ico" rel="shorcut icon"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Carrito Compra</title>

        <!-- Bootstrap -->
        <link href="./vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="./vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="./vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="./vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        <!-- bootstrap-wysiwyg -->
        <link href="./vendors/google-code-prettify/bin/prettify.min.css" rel="stylesheet">
        <!-- Select2 -->
        <link href="./vendors/select2/dist/css/select2.min.css" rel="stylesheet">
        <!-- Switchery -->
        <link href="./vendors/switchery/dist/switchery.min.css" rel="stylesheet">
        <!-- starrr -->
        <link href="./vendors/starrr/dist/starrr.css" rel="stylesheet">
        <!-- bootstrap-daterangepicker -->
        <link href="./vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

        <!-- Custom Theme Style -->
        <link href="./build/css/custom.css" rel="stylesheet">
    </head>
    <body class="nav-md">
        <%
            int idUsuario = (Integer) session.getAttribute("idUsuario");
            List<CarritoCompra> carrito = CarritoCompra.consultaCarritoCompra(idUsuario);
        %>
        <div class="container body">
            <div class="main_container">
                <%@ include file="/includes/sideMenu.jsp" %>

                <div class="right_col" role="main">
                    <div class="col-md-12 col-sm-12 col-xs-12" style="overflow-y: scroll; height: calc(100vh - 90px);">
                        <div class="x_panel">
                            <div class="x_title">
                                <h2>Carrito de Compras <small>Pre-Presupuesto</small></h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <ul class="list-unstyled msg_list">
                                    <%                                        for (CarritoCompra item : carrito) {
                                    %>
                                    <li>
                                        <a style="width: 100%">
                                            <div class="col-md-12">
                                                <div  class="col-md-2">
                                                    <img style="height: 100px" src="<%= "GetImageArticulo?idArticulo=" + Integer.toString(item.getArticulo().getIdArticulo()) + "&image=image1"%>" alt="<%= item.getArticulo().getNombre()%>">
                                                </div>
                                                <div class="col-md-3">
                                                    <h3><%= item.getArticulo().getNombre()%></h3>
                                                    <h3><%= item.getArticulo().getDescripcion()%></h3>
                                                </div>
                                                <div class="col-md-4">
                                                    <form method="POST" action="${pageContext.request.contextPath}/ActualizaItemCarrito" enctype="multipart/form-data" class="form-horizontal form-label-left">
                                                        <div class="item form-group" style="display: none;">
                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="idArticulo">ID</label>
                                                            <div class="col-md-6 col-sm-6 ">
                                                                <input type="text" id="idArticulo" name="idArticulo" class="form-control" value="<%= item.getArticulo().getIdArticulo()%>">
                                                            </div>
                                                        </div>
                                                        <div class="item form-group" style="display: none;">
                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="idUsuario">ID</label>
                                                            <div class="col-md-6 col-sm-6 ">
                                                                <input type="text" id="idUsuario" name="idUsuario" class="form-control" value="<%= session.getAttribute("idUsuario")%>">
                                                            </div>
                                                        </div>
                                                        <div class="item form-group">
                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="cantidad">Cantidad</label>
                                                            <div class="col-md-9 col-sm-9">
                                                                <input type="number" id="cantidad" name="cantidad" class="form-control" min="1" max="<%= Integer.toString(item.getDisponible())%>" value="<%= item.getCantidad()%>">
                                                            </div>
                                                        </div>
                                                        <div class="item form-group">
                                                            <div class="col-md-12 col-sm-12">
                                                                <button type="submit" class="btn btn-success">Actualizar</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                                <div class="col-md-3">
                                                    <form method="GET" action="/DB/EliminaItemCarrito?idU=<%= session.getAttribute("idUsuario")%>&idA=<%= item.getArticulo().getIdArticulo()%>" enctype="multipart/form-data" class="form-horizontal form-label-left">
                                                        <div class="item form-group">
                                                            <div class="col-md-12 col-sm-12">
                                                                <button type="submit" class="btn btn-danger">Eliminar</button>
                                                            </div>
                                                        </div>
                                                    </form>        
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <%
                                        }
                                    %>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script src="./vendors/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="./vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="./vendors/fastclick/lib/fastclick.js"></script>
        <!-- NProgress -->
        <script src="./vendors/nprogress/nprogress.js"></script>
        <!-- bootstrap-progressbar -->
        <script src="./vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
        <!-- iCheck -->
        <script src="./vendors/iCheck/icheck.min.js"></script>
        <!-- bootstrap-daterangepicker -->
        <script src="./vendors/moment/min/moment.min.js"></script>
        <script src="./vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
        <!-- bootstrap-wysiwyg -->
        <script src="./vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
        <script src="./vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
        <script src="./vendors/google-code-prettify/src/prettify.js"></script>
        <!-- jQuery Tags Input -->
        <script src="./vendors/jquery.tagsinput/src/jquery.tagsinput.js"></script>
        <!-- Switchery -->
        <script src="./vendors/switchery/dist/switchery.min.js"></script>
        <!-- Select2 -->
        <script src="./vendors/select2/dist/js/select2.full.min.js"></script>
        <!-- Parsley -->
        <script src="./vendors/parsleyjs/dist/parsley.min.js"></script>
        <!-- Autosize -->
        <script src="./vendors/autosize/dist/autosize.min.js"></script>
        <!-- jQuery autocomplete -->
        <script src="./vendors/devbridge-autocomplete/dist/jquery.autocomplete.min.js"></script>
        <!-- starrr -->
        <script src="./vendors/starrr/dist/starrr.js"></script>
        <!-- Custom Theme Scripts -->
        <script src="./build/js/custom.js"></script>
    </body>
</html>
