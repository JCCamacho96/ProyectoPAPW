<%@page import="java.util.Arrays"%>
<%@page import="Models.Articulo"%>
<%@page import="java.util.List"%>
<%@page import="Models.Categoria"%>
<%@page import="javax.naming.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="image/x-icon" href="img/favicon.ico" rel="shorcut icon"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalle Articulo</title>

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
        <link href="./build/css/jquery.mCustomScrollbar.min.css" rel="stylesheet">
        <!-- Custom Theme Style -->
        <link href="./build/css/custom.css" rel="stylesheet">
    </head>
    <body class="nav-md">
        <%
            List<Categoria> categorias = Categoria.consultaCategorias();
            List<Articulo> articulos = Articulo.consultaArticulos(-1, 0);
            Articulo articuloEdit = null;
            if (request.getParameter("idArticulo") != null && Integer.parseInt(request.getParameter("idArticulo")) != 0) {
                articuloEdit = Articulo.consultaArticulos(-1, Integer.parseInt(request.getParameter("idArticulo"))).get(0);
            }
        %>
        <div class="container body">
            <div class="main_container">
                <%@ include file="/includes/sideMenu.jsp" %>

                <div class="right_col" role="main">
                    <div id="main-grid" class="col-md-3 col-sm-3 col-xs-3" style="padding: 5px; overflow-y: scroll;">
                        <h3>Articulos</h3>
                        <div>
                            <ul id="grid" class="col-md-12 col-sm-12 col-xs-12" style="list-style: none; padding-left: 0px; height: calc(100vh - 160px);">
                                <%                                    
                                    for (Articulo articulo : articulos) {
                                %>
                                <li class='col-md-12' onclick="location.href = 'registraArticulos.jsp?idArticulo=<%= articulo.getIdArticulo()%>'">
                                    <div class='col-md-2'><div class='main-grid-marker'></div></div>
                                    <div class='col-md-10'><span style='font-size: 14px;'><%= articulo.getNombre() + " - " + articulo.getDescripcion()%></span></div>
                                </li>
                                <%
                                    }
                                %>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-9" style="overflow-y: scroll; height: calc(100vh - 90px);">
                        <form method="POST" action="<%= articuloEdit != null ? "/DB/ActualizaArticulo" : "InsertaArticulo"%>" enctype="multipart/form-data" class="form-horizontal form-label-left">
                            <div class="item form-group" style="display: none;">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="idArticulo">ID</label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text" id="idArticulo" name="idArticulo" class="form-control" value="<%= articuloEdit != null ? articuloEdit.getIdArticulo() : ""%>">
                                </div>
                            </div>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="nombre">Nombre Artículo</label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text" id="nombre" name="nombre" class="form-control" value="<%= articuloEdit != null ? articuloEdit.getNombre() : ""%>">
                                </div>
                            </div>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="descripcion">Descripción</label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text" id="descripcion" name="descripcion" class="form-control" value="<%= articuloEdit != null ? articuloEdit.getDescripcion() : ""%>">
                                </div>
                            </div>                            
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="categoria">Categoría</label>
                                <div class="col-md-6 col-sm-6 ">
                                    <select id="categoria" name="categoria" class="select2_multiple form-control" multiple="multiple">
                                        <%
                                            List<String> listCategoriasArticulo = null;
                                            if (articuloEdit != null) {
                                                String[] categoriasArticulo = articuloEdit.getCategoria().split(",");
                                                listCategoriasArticulo = Arrays.asList(categoriasArticulo);
                                            }
                                            for (Categoria categoria : categorias) {
                                        %>
                                        <option value="<%= categoria.getIdCategoria()%>" <%= articuloEdit != null ? (listCategoriasArticulo.contains(Integer.toString(categoria.getIdCategoria())) ? "selected" : "") : ""%>><%= categoria.getNombre()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="unidades">Unidades</label>
                                <div class="col-md-6 col-sm-6">
                                    <input type="number" id="unidades" name="unidades" class="form-control" value="<%= articuloEdit != null ? articuloEdit.getUnidades() : ""%>">
                                </div>
                            </div>  
                            <%
                                if (articuloEdit != null) {
                            %>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align"></label>
                                <div class="col-md-6 col-sm-6">
                                    <img style="height: 50px; display: block;" src="<%= "GetImageArticulo?idArticulo=" + Integer.toString(articuloEdit.getIdArticulo()) + "&image=image1"%>" alt="<%= articuloEdit.getNombre()%>">
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="imagen1">Imagen 1</label>
                                <div class="col-md-6 col-sm-6">
                                    <input type="file" class="form-control" name="imagen1" accept="image/x-png,image/gif,image/jpeg" />
                                </div>
                            </div> 
                            <%
                                if (articuloEdit != null) {
                            %>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align"></label>
                                <div class="col-md-6 col-sm-6">
                                    <img style="height: 50px; display: block;" src="<%= "GetImageArticulo?idArticulo=" + Integer.toString(articuloEdit.getIdArticulo()) + "&image=image2"%>" alt="<%= articuloEdit.getNombre()%>">
                                </div>
                            </div>
                            <%
                                }
                            %>   
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="imagen2">Imagen 2</label>
                                <div class="col-md-6 col-sm-6">
                                    <input type="file" class="form-control" name="imagen2" accept="image/x-png,image/gif,image/jpeg" />
                                </div>
                            </div> 
                            <%
                                if (articuloEdit != null) {
                            %>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align"></label>
                                <div class="col-md-6 col-sm-6">
                                    <img style="height: 50px; display: block;" src="<%= "GetImageArticulo?idArticulo=" + Integer.toString(articuloEdit.getIdArticulo()) + "&image=image3"%>" alt="<%= articuloEdit.getNombre()%>">
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="imagen3">Imagen 3</label>
                                <div class="col-md-6 col-sm-6">
                                    <input type="file" class="form-control" name="imagen3" accept="image/x-png,image/gif,image/jpeg" />
                                </div>
                            </div>  
                            <%
                                if (articuloEdit != null) {
                            %>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align"></label>
                                <div class="col-md-6 col-sm-6">
                                    <img style="height: 50px; display: block;" src="<%= "GetImageArticulo?idArticulo=" + Integer.toString(articuloEdit.getIdArticulo()) + "&image=video"%>" alt="<%= articuloEdit.getNombre()%>">
                                </div>
                            </div>
                            <%
                                }
                            %>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="video">Video</label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="file" class="form-control" name="video" accept="image/x-png,image/gif,image/jpeg" />
                                </div>
                            </div>   
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align">Activo</label>
                                <div class="col-md-6 col-sm-6 ">
                                    <select id="active" name="active" class="select2 form-control">
                                        <option value="1" <%= articuloEdit != null ? (articuloEdit.getActive() == 1 ? "selected" : "") : ""%>>Sí</option>
                                        <option value="0" <%= articuloEdit != null ? (articuloEdit.getActive() == 0 ? "selected" : "") : ""%>>No</option>
                                    </select>
                                </div>
                            </div>
                            <div class="ln_solid"></div>
                            <div class="item form-group">
                                <div class="col-md-6 col-sm-6 offset-md-3">
                                    <button type="submit" class="btn btn-success">Guardar</button>
                                    <a class="btn btn-danger" onclick="location.href = 'BorraArticulo?idArticulo=<%= articuloEdit != null ? articuloEdit.getIdArticulo() : ""%>'" style="display: <%= articuloEdit != null ? "inline" : "none"%>">Borrar</a>
                                    <a class="btn btn-primary" onclick="location.href = 'registraArticulos.jsp'" style="display: <%= articuloEdit != null ? "inline" : "none"%>">Nuevo</a>
                                </div>
                            </div>
                        </form>
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
        <script src="./build/js/jquery.mCustomScrollvar.min.js"></script>
        <!-- Custom Theme Scripts -->
        <script src="./build/js/custom.min.js"></script>
    </body>
</html>
