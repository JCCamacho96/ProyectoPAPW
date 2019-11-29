/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.CarritoCompra;
import Models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edo16
 */
@MultipartConfig(maxFileSize = 1000 * 1000 * 5, maxRequestSize = 1000 * 1000 * 25, fileSizeThreshold = 1000 * 1000)
public class AgregarItemCarrito extends HttpServlet {

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
        int cantidad = 0;
        if(request.getParameter("cantidad") == null || request.getParameter("cantidad").equals("")) {
            errors.put("cantidad", "Por favor ingresa la cantidad.");
        } else {
            cantidad = Integer.parseInt(request.getParameter("cantidad")); 
        }
        
        if (errors.isEmpty()) {
            if(CarritoCompra.insertaItemCarrito(Integer.parseInt(request.getParameter("idUsuario")), Integer.parseInt(request.getParameter("idArticulo")), cantidad)) {
                response.sendRedirect("carritoCompras.jsp");
            }
        } else {
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
