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
        <title>Inicio</title>

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
            String idCategoria = "";
            if (request.getParameter("idc") != null && !request.getParameter("idc").equals("")) {
                idCategoria = request.getParameter("idc");
            }
            String nombre = "";
            if (request.getParameter("n") != null && !request.getParameter("n").equals("")) {
                nombre = request.getParameter("n");
            }
            String descripcion = "";
            if (request.getParameter("d") != null && !request.getParameter("d").equals("")) {
                descripcion = request.getParameter("d");
            }
            List<Categoria> categorias = Categoria.consultaCategorias();
            List<Articulo> articulos = Articulo.consultaArticulos(1, 0, idCategoria, nombre, descripcion);
        %>
        <div class="container body">
            <div class="main_container">
                <%@ include file="/includes/sideMenu.jsp" %>

                <div class="right_col" role="main">
                    <div id="main-grid" class="col-md-2 col-sm-2 col-xs-2" style="padding: 5px; overflow-y: scroll; height: calc(100vh - 90px);">
                        <h3>Filtros</h3>   
                        <div class="item form-group">
                            <select id="categoria" name="categoria" class="form-control">
                                <option value="">Selecciona una categoria</option>
                                <%                                for (Categoria categoria : categorias) {
                                %>
                                <option value="<%= categoria.getIdCategoria()%>"><%= categoria.getNombre()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                        <div class="item form-group">
                            <input type="text" id="nombre" class="form-control" placeholder="Nombre...">  
                        </div>
                        <div class="item form-group">
                            <input type="text" id="descripcion" class="form-control" placeholder="Descripcion...">
                        </div>
                        <button type="button" class="btn btn-primary" onclick="filtrar('index.jsp')">Filtrar</button>
                    </div>
                    <div class="col-md-10 col-sm-10 col-xs-10" style="overflow-y: scroll; height: calc(100vh - 90px);">
                        <h2>Los más vendidos</h2>
                        <div class="col-md-12">
                            <%
                                int i = 1;
                                for (Articulo articulo : articulos) {
                                    if (i <= 4) {
                            %>
                            <div class="col-md-3">
                                <div class="thumbnail" style="cursor: pointer;" onclick="location.href = 'articuloDetalle.jsp?idArticulo=' + <%= Integer.toString(articulo.getIdArticulo())%>">
                                    <div class="image view view-first" style="cursor: pointer;">
                                        <img style="width: 100%; display: block;" src="<%= "GetImageArticulo?idArticulo=" + Integer.toString(articulo.getIdArticulo()) + "&image=image1"%>" alt="<%= articulo.getNombre()%>">
                                    </div>
                                    <div class="caption">
                                        <p><%= articulo.getNombre() + " - " + articulo.getDescripcion()%></p>
                                    </div>
                                </div>
                            </div>
                            <%
                                        i++;
                                    }
                                }
                            %>
                        </div>
                        <div class="clearfix"></div>

                        <h2><%= articulos.size() > 4 ? "También te puede interesar" : ""%></h2>
                        <%
                            i = 1;
                            for (Articulo articulo : articulos) {
                                if (i > 4) {
                        %>
                        <div class="col-md-3">
                            <div class="thumbnail" style="cursor: pointer;" onclick="location.href = 'articuloDetalle.jsp?idArticulo=' + <%= Integer.toString(articulo.getIdArticulo())%>">
                                <div class="image view view-first" style="cursor: pointer;">
                                    <img style="width: 100%; display: block;" src="<%= "GetImageArticulo?idArticulo=" + Integer.toString(articulo.getIdArticulo()) + "&image=image1"%>" alt="<%= articulo.getNombre()%>">
                                </div>
                                <div class="caption">
                                    <p><%= articulo.getNombre() + " - " + articulo.getDescripcion()%></p>
                                </div>
                            </div>
                        </div>
                        <%
                                    i++;
                                } else {
                                    i++;
                                }
                            }
                        %>
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
        <script>
            function filtrar(stringURL) {
                let i = 0;
                let stringidc = "";
                if($('#categoria').val() != "") {
                    stringidc = "?idc=" + $('#categoria').val();
                    i++;
                }
                let stringn = "";
                if($('#nombre').val() != "") {
                    if(i == 0) {
                        stringn = "?n=" + $('#nombre').val();
                        i++;
                    } else {
                        stringn = "&n=" + $('#nombre').val();
                    }
                }
                let stringd = "";
                if($('#descripcion').val() != "") {
                    if(i == 0) {
                        stringd = "?d=" + $('#descripcion').val();
                        i++;
                    } else {
                        stringd = "&d=" + $('#descripcion').val();
                    }
                }
                location.href = stringURL + stringidc + stringn + stringd;
            }
        </script>
    </body>
</html>
