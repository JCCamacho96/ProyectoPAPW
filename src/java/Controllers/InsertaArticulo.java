/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Articulo;
import Utils.FilesUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
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
public class InsertaArticulo extends HttpServlet {

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
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String categoria = request.getParameter("categoria");
        int unidades = Integer.parseInt(request.getParameter("unidades"));
        Part imagen1 = request.getPart("imagen1");
        Part imagen2 = request.getPart("imagen2");
        Part imagen3 = request.getPart("imagen3");
        Part video = request.getPart("video");
        int active = Integer.parseInt(request.getParameter("active"));

        //Creamos un folder para resguardar la imagen si es que no existe
        String path = request.getServletContext().getRealPath("");
        File fileSaveDir = new File(path + FilesUtils.RUTE_USER_IMAGE);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String contentType1 = imagen1.getContentType();
        String nameImage1 = imagen1.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentType1);
        imagen1.write(path + nameImage1);
        String contentType2 = imagen2.getContentType();
        String nameImage2 = imagen2.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentType2);
        imagen2.write(path + nameImage2);
        String contentType3 = imagen3.getContentType();
        String nameImage3 = imagen3.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentType3);
        imagen3.write(path + nameImage3);
        String contentTypeV = video.getContentType();
        String nameVideo = video.getName() + System.currentTimeMillis() + FilesUtils.GetExtension(contentTypeV);
        imagen1.write(path + nameVideo);
        if (errors.isEmpty()) {
            try {
                if(Articulo.insertaArticulo(nombre, descripcion, categoria, unidades, imagen1.getInputStream(), imagen2.getInputStream(), imagen3.getInputStream(), video.getInputStream(), active)) {
                    response.sendRedirect("registraArticulos.jsp");
                }
            } catch (IOException ex) {
                Logger.getLogger("ERROR: " + ex.getMessage());
            }
        } else {
            // Put errors in request scope and forward back to JSP.
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("registraArticulos.jsp").forward(request, response);
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
