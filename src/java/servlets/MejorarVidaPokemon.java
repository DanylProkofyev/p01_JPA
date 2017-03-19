/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.stukemonEJB;
import entities.Pokemon;
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
@WebServlet(name = "MejorarVidaPokemon", urlPatterns = {"/MejorarVidaPokemon"})
public class MejorarVidaPokemon extends HttpServlet {

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
            out.println("<title>Servlet MejorarVidaPokemon</title>");
            out.println("<link rel=\"icon\" \n"
                    + "              type=\"image/png\" \n"
                    + "              href=\"https://cdn1.iconfinder.com/data/icons/video-games-7/24/video_game_play_pokemon_pokeball-128.png\">");
            out.println("</head>");
            out.println("<body>");
            List<Pokemon> trainerPokemonList = ejb.pokemonPorEntrenador(request.getParameter("entrenador"));
            out.println("<form action=\"MejorarVidaFuncion\" method=\"GET\">");
            out.println("<label>Seleccionar Pokemon</label>");
            out.println("<select name=\"pokemon\">");
            try {
                for (Pokemon pokemonAhora : trainerPokemonList) {
                    out.println("<option value=" + pokemonAhora.getName() + ">" + pokemonAhora.getName() + " Vida: " + pokemonAhora.getLife() + "</option>");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value\"ok\">");
            out.println("</form>");
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
