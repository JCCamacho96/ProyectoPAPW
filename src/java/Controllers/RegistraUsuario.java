/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Usuario;
import Models.ValidaUsuario;
import Utils.FilesUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author edo16
 */
@MultipartConfig(maxFileSize = 1000 * 1000 * 5, maxRequestSize = 1000 * 1000 * 25, fileSizeThreshold = 1000 * 1000)
public class RegistraUsuario extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> errors = new HashMap<String, String>();
        String user = request.getParameter("user");
        if (user.equals("")) {
            errors.put("user", "El campo es requerido.");
        } else if (ValidaUsuario.validar(user, request.getParameter("correo")).isExisteUser()) {
            errors.put("user", "El usuario ya existe.");
        }
        String apellido = request.getParameter("apellido");
        if (apellido.equals("")) {
            errors.put("apellido", "El campo es requerido.");
        }
        String nombre = request.getParameter("nombre");
        if (nombre.equals("")) {
            errors.put("nombre", "El campo es requerido.");
        }
        String correo = request.getParameter("correo");
        String regexCorreo = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (correo.equals("")) {
            errors.put("correo", "El campo es requerido.");
        } else if (!correo.matches(regexCorreo)) {
            errors.put("correo", "El correo no es valido.");
        } else if (ValidaUsuario.validar(user, correo).isExisteCorreo()) {
            errors.put("correo", "El correo ya pertenece a otro usuario.");
        }
        String password = request.getParameter("password");
        String regexPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        if (password.equals("")) {
            errors.put("password", "El campo es requerido.");
        } else if (!password.matches(regexPassword)) {
            errors.put("password", "La contraseña debe contener un mínimo de 8 caracteres, una mayúscula, una minúscula y un número.");
        }
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        Part file = request.getPart("imagen");
        if (file.getSize() == 0) {
            errors.put("imagen", "El campo es requerido.");
        }

        //Creamos un folder para resguardar la imagen si es que no existe
        String path = request.getServletContext().getRealPath("");
        File fileSaveDir = new File(path + FilesUtils.RUTE_USER_IMAGE);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        //Resguardamos la imagen
        String contentType = file.getContentType();//Resguarden esto para saber el tipo
        String nameImage = file.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentType);
        file.write(path + nameImage);
        if (errors.isEmpty()) {
            //Agregamos a la base de datos
            if(Usuario.registraUsuario(nombre, apellido, user, correo, password, telefono, direccion, file.getInputStream())) {
                response.sendRedirect("login.jsp");
            }
        } else {
            // Put errors in request scope and forward back to JSP.
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("articuloDetalle.jsp?idArticulo=" + request.getParameter("idArticulo")).forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
