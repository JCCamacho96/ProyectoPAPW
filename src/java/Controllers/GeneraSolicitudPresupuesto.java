/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.CarritoCompra;
import Models.ItemSolicitudPresupuesto;
import Models.SolicitudPresupuesto;
import Models.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edo16
 */
public class GeneraSolicitudPresupuesto extends HttpServlet {

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
        Map<String, String> error = new HashMap<String, String>();
        int idUsuario = Integer.parseInt(request.getParameter("idU"));
        if (SolicitudPresupuesto.generaSolicitudPresupuesto(idUsuario)) {
            if (ItemSolicitudPresupuesto.insertaItemSolicitudPresupuesto(idUsuario)) {
                CarritoCompra.borraItemCarrito(idUsuario, 0);
            } else {
                error.put("mensaje", "Hubo un error al generar la solicitud.");
            }
        } else {
            error.put("mensaje", "Hubo un error al generar la solicitud.");
        }
        
        if (error.isEmpty()) {
            response.sendRedirect("carritoCompras.jsp");
        } else {
            // Put errors in request scope and forward back to JSP.
            request.setAttribute("error", error);
            request.getRequestDispatcher("carritoCompras.jsp").forward(request, response);
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
