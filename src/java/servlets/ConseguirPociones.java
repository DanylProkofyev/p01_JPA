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
import java.util.List;
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
@WebServlet(name = "ConseguirPociones", urlPatterns = {"/ConseguirPociones"})
public class ConseguirPociones extends HttpServlet {

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
            out.println("<title>Servlet ConseguirPociones</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"ConseguirPocionesFuncion\">");
            out.println("<label><h2>Entrenador</h2></label>");
            out.println("<select name=\"entrenador\">");
            try {
                List<Trainer> todosEntrenadores = ejb.seleccionarTodosEntrenadores();
                for (Trainer entrenadorAhora : todosEntrenadores) {
                    out.println("<option value=" + entrenadorAhora.getName() + ">" + entrenadorAhora.getName() + ": Puntos disponibles: " + entrenadorAhora.getPoints() + "</option>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("</select>");
            out.println("<label><p><h2>Cantidad de pociones que quieres comprar</h2></p></label>");
            out.println("<input type=\"number\" name=\"cantidadPociones\">");
            out.println("<input type=\"submit\" value=\"ok\">");
            out.println("</form>");
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
