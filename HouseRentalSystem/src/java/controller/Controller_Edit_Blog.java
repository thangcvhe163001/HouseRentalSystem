/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.DAOBlog;
import entity.Account;
import entity.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author win
 */
public class Controller_Edit_Blog extends HttpServlet {
   
     /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet Controller_Edit_Blog</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controller_Edit_Blog at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        DAOBlog b = new DAOBlog();
        String nav = request.getParameter("nav");
        int idB = Integer.parseInt(request.getParameter("id"));
        Blog blog = b.getBlogFollowId(idB);
        if(nav!=null){
             request.setAttribute("nav", nav);
        }
        request.setAttribute("idB", idB);
        request.setAttribute("title", blog.getTopic());
        request.setAttribute("content", blog.getContent());
        request.setAttribute("image", blog.getImage());
        request.getRequestDispatcher("editblog.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account)session.getAttribute("acc");
        DAOBlog blog = new DAOBlog();
        String title, image, content,nav;
        int idB = Integer.parseInt(request.getParameter("idB"));
        int posterId = a.getId();
        nav = request.getParameter("nav");
        title = request.getParameter("title");
        image = request.getParameter("imageblog").trim().isEmpty()?blog.getBlogFollowId(idB).getImage():request.getParameter("imageblog");
        content = request.getParameter("content");
        blog.editBlog(idB, posterId, title, content, image);
        response.sendRedirect(nav==null?"listblog":"manageblogs");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
