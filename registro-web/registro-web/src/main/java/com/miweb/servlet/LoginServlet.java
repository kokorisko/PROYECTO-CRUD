/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.miweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.miweb.model.Usuario;
import com.miweb.util.JSONHandler;
import java.util.List;

/**
 *
 * @author user
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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

        response.setContentType("text/html;charset=UTF-8");

        String usuarioInput = request.getParameter("usuario");
        String contraseñaInput = request.getParameter("contraseña");

        List<Usuario> usuarios = JSONHandler.leerUsuarios();

        Usuario usuarioAutenticado = null;
        for (Usuario u : usuarios) {
            if ((u.getNombreUsuario().equals(usuarioInput) || u.getCorreo().equals(usuarioInput))
                    && u.getContraseña().equals(contraseñaInput)) {
                usuarioAutenticado = u;
                break;
            }
        }

        try (var out = response.getWriter()) {
            if (usuarioAutenticado != null) {

                request.getSession().setAttribute("usuario", usuarioAutenticado);
                response.sendRedirect("modificarDatos"); // Redirige al servlet de modificación de datos

            } else {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error de Inicio de Sesión</title>");
                out.println("<link rel='stylesheet' href='css/styles.css'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h1>Error</h1>");
                out.println("<p>Usuario o contraseña incorrectos.</p>");
                out.println("<a href='index.html'>Intentar de nuevo</a>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}
