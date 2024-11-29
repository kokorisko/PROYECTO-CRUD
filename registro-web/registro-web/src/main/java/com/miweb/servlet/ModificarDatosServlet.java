/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.miweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
public class ModificarDatosServlet extends HttpServlet {

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
            out.println("<title>Servlet ModificarDatosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModificarDatosServlet at " + request.getContextPath() + "</h1>");
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
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("login.html");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Modificar Datos</title>");
            out.println("<link rel='stylesheet' href='css/styles.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Modificar Datos</h1>");
            out.println("<form action='modificarDatos' method='post'>");
            out.println("<label for='nombreCompleto'>Nombre Completo:</label>");
            out.println("<input type='text' name='nombreCompleto' value='" + usuario.getNombreCompleto() + "' required>");
            out.println("<label for='correo'>Correo Electrónico:</label>");
            out.println("<input type='email' name='correo' value='" + usuario.getCorreo() + "' required>");
            out.println("<label for='contraseña'>Nueva Contraseña:</label>");
            out.println("<input type='password' name='contraseña' placeholder='Nueva Contraseña' required>");
            out.println("<label for='confirmarContraseña'>Confirmar Contraseña:</label>");
            out.println("<input type='password' name='confirmarContraseña' placeholder='Confirmar Contraseña' required>");
            out.println("<button type='submit'>Modificar Datos</button>");
            out.println("</form>");
            out.println("<a href='index.html'>Volver al Inicio</a>");
            out.println("</body>");
            out.println("</html>");
        }
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
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("index.html");
            return;
        }

        String nombreCompleto = request.getParameter("nombreCompleto");
        String correo = request.getParameter("correo");
        String contraseña = request.getParameter("contraseña");
        String confirmarContraseña = request.getParameter("confirmarContraseña");

        if (!contraseña.equals(confirmarContraseña)) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error</h1>");
                out.println("<p>Las contraseñas no coinciden. Intenta de nuevo.</p>");
                out.println("<a href='modificarDatos'>Volver al formulario</a>");
                out.println("</body>");
                out.println("</html>");
            }
            return;
        }
        usuario.setNombreCompleto(nombreCompleto);
        usuario.setCorreo(correo);
        usuario.setContraseña(contraseña);

        List<Usuario> usuarios = JSONHandler.leerUsuarios();

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(usuario.getNombreUsuario())) {
                usuarios.set(i, usuario);
                break;
            }
        }
        JSONHandler.escribirUsuarios(usuarios);

        response.sendRedirect("index.html");
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
