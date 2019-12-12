/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.ValoracionCompra;
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
public class InsertaValoracionCompra extends HttpServlet {

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
        int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
        int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
        String comentarios = request.getParameter("comentarios");
        int calificacion = Integer.parseInt(request.getParameter("calificacion"));
        if(ValoracionCompra.insertaValoracionCompra(idSolicitud, idArticulo, comentarios, calificacion)) {
            response.sendRedirect("historialCompras.jsp");
        } else {
            response.sendRedirect("historialCompras.jsp");
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
