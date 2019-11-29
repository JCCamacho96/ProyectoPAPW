/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Articulo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edo16
 */
public class GetImageArticulo extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Articulo articulo = null;
        articulo = Articulo.consultaArticulos(1, Integer.parseInt(request.getParameter("idArticulo"))).get(0);
        byte[] image = null;
        switch(request.getParameter("image")) {
            case "image1": {
                image = articulo.getImagen1();
                response.setContentType("image/png");
                break;
            }
            case "image2": {
                image = articulo.getImagen2();
                response.setContentType("image/png");
                break;
            }
            case "image3": {
                image = articulo.getImagen3();
                response.setContentType("image/png");
                break;
            }
            case "video": {
                image = articulo.getVideo();
                response.setContentType("image/png");
                break;
            }
        }
        response.getOutputStream().write(image);
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
