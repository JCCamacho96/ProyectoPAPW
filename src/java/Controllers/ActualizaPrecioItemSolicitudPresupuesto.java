/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.ItemSolicitudPresupuesto;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edo16
 */
public class ActualizaPrecioItemSolicitudPresupuesto extends HttpServlet {

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
        int idSolicitud = Integer.parseInt(request.getParameter("idSolicitud"));
        int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        if(ItemSolicitudPresupuesto.actualizaPrecioItem(idSolicitud, idArticulo, precio)) {
            response.sendRedirect("solicitudesPresupuesto.jsp");
        } else {
            response.sendRedirect("solicitudesPresupuesto.jsp");
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
