<%@page import="Models.ItemSolicitudPresupuesto"%>
<%@page import="Models.SolicitudPresupuesto"%>
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
        <title>Solicitudes de Presupuesto</title>

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
            List<SolicitudPresupuesto> solicitudes = SolicitudPresupuesto.consultaSolicitudesPresupuesto(0, 0);
        %>
        <div class="container body">
            <div class="main_container">
                <%@ include file="/includes/sideMenu.jsp" %>

                <div class="right_col" role="main">
                    <div class="col-md-12 col-sm-12 col-xs-12" style="overflow-y: scroll; height: calc(100vh - 90px);">
                        <div class="x_panel">
                            <%                                
                                for (SolicitudPresupuesto solicitud : solicitudes) {
                            %>
                            <div class="x_title">
                                <h2>Solicitud de Presupuesto: <%= solicitud.getIdSolicitud()%> <small>agregar nombre usuario?</small></h2>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <ul class="list-unstyled msg_list">
                                    <%
                                        for (ItemSolicitudPresupuesto item : solicitud.getItems()) {
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
                                                <div class="col-md-2">
                                                    <h3>Cantidad: <%= item.getCantidad()%></h3>
                                                </div>
                                                <div class="col-md-5">
                                                    <form class="form-horizontal form-label-left">
                                                        <div class="item form-group">
                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="cantidad">Precio</label>
                                                            <div class="col-md-4 col-sm-4">
                                                                <input id="precio-<%= item.getIdSolicitud() %>-<%= item.getArticulo().getIdArticulo() %>" type="number" name="precio" class="form-control" min="0.01" step="0.01" pattern="^\d*(\.\d{0,2})?$" value="<%= item.getPrecio()%>">
                                                            </div>
                                                            <div class="col-md-5 col-sm-5">
                                                                <button type="button" onclick="actualizaPrecio('${pageContext.request.contextPath}/ActualizaPrecioItemSolicitudPresupuesto?idSolicitud=<%= item.getIdSolicitud() %>&idArticulo=<%= item.getArticulo().getIdArticulo()%>&precio=', <%= item.getIdSolicitud() %>, <%= item.getArticulo().getIdArticulo() %>)" class="btn btn-success">Actualizar Precio</button>
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
                                <div>
                                    <button type="button" class="btn btn-primary" onclick="location.href = '${pageContext.request.contextPath}/ActualizaEstatusSolicitudPresupuesto?idSolicitud=<%= solicitud.getIdSolicitud() %>&estatus=1'">Guardar Presupuesto</button>
                                </div>
                            </div>
                            <%
                                }
                            %>
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
        <script>
            $(document).on('keydown', 'input[pattern]', function (e) {
                var input = $(this);
                var oldVal = input.val();
                var regex = new RegExp(input.attr('pattern'), 'g');

                setTimeout(function () {
                    var newVal = input.val();
                    if (!regex.test(newVal)) {
                        input.val(oldVal);
                    }
                }, 0);
            });
            
            function actualizaPrecio(stringURL, idSolicitud, idArticulo) {
                location.href = stringURL +  + $('#precio-' + idSolicitud + '-' + idArticulo).val();
            }
        </script>
    </body>
</html>
