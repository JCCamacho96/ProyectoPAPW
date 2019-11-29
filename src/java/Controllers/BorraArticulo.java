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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author edo16
 */
public class BorraArticulo extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> errors = new HashMap<String, String>();
        int idArticulo = Integer.parseInt(request.getParameter("idArticulo"));
        
        if (errors.isEmpty()) {
            try {
                if(Articulo.borraArticulo(idArticulo)) {
                    response.sendRedirect("registraArticulos.jsp");
                }
            } catch (IOException ex) {
                Logger.getLogger("ERROR: " + ex.getMessage());
            }
        } else {
            // Put errors in request scope and forward back to JSP.
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("registraArticulos.jsp?idArticulo=" + Integer.toString(idArticulo));
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
