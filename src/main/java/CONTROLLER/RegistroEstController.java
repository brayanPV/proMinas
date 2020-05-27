/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import NEGOCIO.ProMina;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stive
 */
@WebServlet(name = "RegistroEstController", urlPatterns = {"/RegistroEstController"})
public class RegistroEstController extends HttpServlet {

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
        try {

            ProMina pro = new ProMina();

            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String carrera = request.getParameter("carrera");
            String fechaN = request.getParameter("fechaN");
            String genero = request.getParameter("genero");

            boolean g = Boolean.parseBoolean(genero);
                if (pro.registrarEstudiante(codigo, nombre, apellido, carrera, fechaN, g)) {
                    request.getSession().setAttribute("exito", pro);
                    request.getRequestDispatcher("index.jsp").forward(request, response);

                } else {
                    request.getSession().setAttribute("error", "papi errorsote");
                    request.getRequestDispatcher("/JSP/ERROR/errorInsertarEst.jsp").forward(request, response);
                }
        }catch (Exception e) {
                request.getSession().setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/JSP/ERROR/errorInsertarEst.jsp").forward(request, response);
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
