/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.stukemonEJB;
import entities.Trainer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dergenburn
 */
@WebServlet(name = "ConseguirPocionesFuncion", urlPatterns = {"/ConseguirPocionesFuncion"})
public class ConseguirPocionesFuncion extends HttpServlet {

    @EJB
    stukemonEJB ejb;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConseguirPocionesFuncion</title>");
            out.println("</head>");
            out.println("<body>");
            Trainer t = ejb.encontrarEntrenador(request.getParameter("entrenador"));
            int cantidadPociones = Integer.parseInt(request.getParameter("cantidadPociones"));
            int coste = cantidadPociones * 10;
            if (t.getPoints() >= coste) {
                out.println("<h2>Has comprado " + cantidadPociones + " pocione/s</h2>");
                t.setPoints(t.getPoints() - coste);
                t.setPotions(t.getPotions() + cantidadPociones);
                out.println("<h4>Tiene " + t.getPotions() + " pociones y " + t.getPoints() + " puntos</h4>");
                ejb.actuEntrenador(t);
            } else {
                out.println("<h2>No tienes suficientes puntos</h2>");
            }

            out.println("<form action='index.html'><input type='submit' name='volverInicio' value='Ir de vuelta a Inicio'/></form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
    }

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
        processRequest(request, response);
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
