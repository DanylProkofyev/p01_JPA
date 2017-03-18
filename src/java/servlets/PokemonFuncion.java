/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.stukemonEJB;
import entities.Pokemon;
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
 * @author x6095888z
 */
@WebServlet(name = "PokemonFuncion", urlPatterns = {"/PokemonFuncion"})
public class PokemonFuncion extends HttpServlet {

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
            out.println("<link rel=\"icon\" \n"
                    + "              type=\"image/png\" \n"
                    + "              href=\"https://cdn1.iconfinder.com/data/icons/video-games-7/24/video_game_play_pokemon_pokeball-128.png\">");
            out.println("<title>Servlet PokemonFuncion</title>");
            out.println("</head>");
            out.println("<body>");
            String nombre = request.getParameter("nombre");
            String tipo = request.getParameter("tipo");
            String habilidad = request.getParameter("habilidad");
            Integer ataque = Integer.parseInt(request.getParameter("ataque"));
            Integer defensa = Integer.parseInt(request.getParameter("defensa"));
            Integer velocidad = Integer.parseInt(request.getParameter("velocidad"));
            Integer vida = Integer.parseInt(request.getParameter("vida"));
            String entrenador = request.getParameter("entrenador");

            Pokemon p = new Pokemon(nombre, tipo, habilidad, ataque, defensa, velocidad, vida, 0);
            Trainer t = ejb.encontrarEntrenador(entrenador);
            p.setTrainer(t);
            if (!ejb.tieneSeisPokemon(t)) {
                out.println("<h1>este entrenador ya tiene 6 pokemon</h1>");
            } else {
                if (ejb.insertPoke(p)) {
                    out.println("<h1>Nuevo pokemon creado</h1>");
                } else {
                    out.println("<h1>No se ha podido crear el pokemon</h1>");
                }
            }
            out.println("<form action='AltaPokemon'><input type='submit' name='altaPokemon' value='Dar de alta a otro Pokemon'/></form>");
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
